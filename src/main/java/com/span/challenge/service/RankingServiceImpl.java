package com.span.challenge.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import com.span.challenge.enums.GameResultEnum;
import com.span.challenge.exceptions.FileFormatException;
import com.span.challenge.model.GameDetails;
import com.span.challenge.model.TeamInfo;

@Service
public class RankingServiceImpl implements RankingService {

	@Override
	public List<GameDetails> getGameDetails(String fileName) throws IOException, FileFormatException {
		List<GameDetails> gameDetailsList = new ArrayList<>();

		File initialFile = new File(fileName);
		InputStreamReader streamReader = new InputStreamReader(new FileInputStream(initialFile));
		int count = 1;

		try (BufferedReader reader = new BufferedReader(streamReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				boolean isValidLine = line.matches("^([^\\r\\n;]*\\d)\\s*,([^\\r\\n;]*\\d)\\s*$");
				if (!isValidLine) {
					throw new FileFormatException(
							String.format("There is a format error in line %d.%n %s", count, line));
				}

				String[] splitResult = line.split(", ");

				GameDetails gameDetails = new GameDetails();
				String teamLocal = splitResult[0];
				int index = teamLocal.lastIndexOf(" ");
				String localTeamName = teamLocal.substring(0, index);
				String localTeamScore = teamLocal.substring(index + 1);
				gameDetails.setLocalTeam(localTeamName);
				gameDetails.setLocalTeamScore(Integer.valueOf(localTeamScore));

				String teamVisitor = splitResult[1];
				int index2 = teamVisitor.lastIndexOf(" ");
				String visitorTeamName = teamVisitor.substring(0, index2);
				String visitorTeamScore = teamVisitor.substring(index2 + 1);
				gameDetails.setVisitorTeam(visitorTeamName);
				gameDetails.setVisitorTeamScore(Integer.valueOf(visitorTeamScore));

				if (gameDetails.getLocalTeamScore() == gameDetails.getVisitorTeamScore()) {
					gameDetails.setGameResult(GameResultEnum.DRAW);
				} else if (gameDetails.getLocalTeamScore() > gameDetails.getVisitorTeamScore()) {
					gameDetails.setGameResult(GameResultEnum.LOCAL);
				} else {
					gameDetails.setGameResult(GameResultEnum.VISITOR);
				}

				count++;
				gameDetailsList.add(gameDetails);

			}

			streamReader.close();
			reader.close();
		} catch (NumberFormatException e) {
			throw new FileFormatException(String.format("There is a format error in line %d ", count));
		}

		return gameDetailsList;
	}

	@Override
	public List<TeamInfo> getRanking(List<GameDetails> gameDetailsList) {
		Map<String, Integer> gameDetailsMap = new HashMap<>();

		gameDetailsList.forEach(gameDetails -> {
			GameResultEnum gameResultEnume = gameDetails.getGameResult();

			int gameLocalPoints = gameResultEnume == GameResultEnum.DRAW ? 1
					: gameResultEnume == GameResultEnum.LOCAL ? 3 : 0;
			int gameVisitorPoints = gameResultEnume == GameResultEnum.DRAW ? 1
					: gameResultEnume == GameResultEnum.VISITOR ? 3 : 0;

			int gameTotalPoints = gameDetailsMap.containsKey(gameDetails.getLocalTeam())
					? gameDetailsMap.get(gameDetails.getLocalTeam())
					: 0;

			int visitorTotalPoints = gameDetailsMap.containsKey(gameDetails.getVisitorTeam())
					? gameDetailsMap.get(gameDetails.getVisitorTeam())
					: 0;

			gameDetailsMap.put(gameDetails.getLocalTeam(), gameTotalPoints + gameLocalPoints);
			gameDetailsMap.put(gameDetails.getVisitorTeam(), visitorTotalPoints + gameVisitorPoints);

		});

		List<TeamInfo> teamInfoList = gameDetailsMap.entrySet().stream()
				.map(entry -> new TeamInfo(entry.getKey(), entry.getValue()))
				.sorted(Comparator.comparing(TeamInfo::getPoints).reversed().thenComparing(TeamInfo::getName))
				.collect(Collectors.toList());

		return teamInfoList;
	}

	@Override
	public void printRanking(List<TeamInfo> teamRankingList, String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));

		int count = 1;
		for (TeamInfo teamInfo : teamRankingList) {
			String line = String.format("%d. %s, %d pts %s", count, teamInfo.getName(), teamInfo.getPoints(),
					System.lineSeparator());
			writer.append(line);

			count++;
		}

		writer.close();
	}

}
