package com.span.challenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.span.challenge.exceptions.FileFormatException;
import com.span.challenge.model.GameDetails;
import com.span.challenge.model.TeamInfo;
import com.span.challenge.service.RankingService;
/**
 * Main Class runs the program
 * @author daniel
 *
 */
@SpringBootApplication
public class SpanChallengeApplication implements CommandLineRunner {
	
	Logger logger = LoggerFactory.getLogger(SpanChallengeApplication.class);

	@Autowired
	public RankingService rankingService;

	public static void main(String[] args) {
		SpringApplication.run(SpanChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length == 0) {
			logger.error("Please pass the input file path.");
			return;
		}

		try {
			String fileName = args[0];
			Path path = Paths.get(fileName);

			List<GameDetails> gameDetailsList = rankingService.getGameDetails(fileName);
			List<TeamInfo> teamRankingList = rankingService.getRanking(gameDetailsList);

			String outputPath = path.getParent().toString() + "/Ranking_output.txt";
			rankingService.printRanking(teamRankingList, outputPath);
			
			logger.info("This is the path for the ranking file");
			logger.info(outputPath);
		} catch (FileFormatException |  IOException e) {
			logger.error("There is an error please validate..");
			logger.error(e.getMessage());
		}
	}

}
