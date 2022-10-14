package com.span.challenge.service;

import java.io.IOException;
import java.util.List;

import com.span.challenge.exceptions.FileFormatException;
import com.span.challenge.model.GameDetails;
import com.span.challenge.model.TeamInfo;

/**
 * 
 * @author daniel
 *
 */
public interface RankingService {
	
	/**
	 * This method gets the List of game details that is calculated processing the input file. 
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws FileFormatException
	 */
	List<GameDetails> getGameDetails(String fileName) throws IOException, FileFormatException;
	
	/**
	 * This method get the ranking and return it an order List with the details of the ranking
	 * @param gameDetailsList
	 * @return
	 */
	List<TeamInfo> getRanking(List<GameDetails> gameDetailsList);
	
	/**
	 * This method creates the output file that contains the ranking.
	 * @param teamRankingList
	 * @param fileName
	 * @throws IOException
	 */
	public void printRanking(List<TeamInfo> teamRankingList, String fileName) throws IOException;

}
