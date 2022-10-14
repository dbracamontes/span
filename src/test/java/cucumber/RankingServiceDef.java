package cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.span.challenge.exceptions.FileFormatException;
import com.span.challenge.model.GameDetails;
import com.span.challenge.model.TeamInfo;
import com.span.challenge.service.RankingService;

/**
 * 
 * @author daniel
 * This class implements all the definitions for cucumber ranking_service.feature
 */
public class RankingServiceDef {

	@Autowired
	private RankingService rankingService;

	private List<GameDetails> gameDetails;
	private List<TeamInfo> rankingList;
	private String inputPath;

	public RankingServiceDef() {
		inputPath = new File("src/test/resources/").getAbsolutePath() + "/";
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Creating ranking table from <input>
	 * Given I have an input file <input>
	 * @param fileName
	 * @throws IOException
	 * @throws FileFormatException
	 */
	@Given("I have an input file {word}")
	public void given_file_service(String fileName) throws IOException, FileFormatException {
		gameDetails = rankingService.getGameDetails(inputPath + fileName);
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Creating ranking table from <input>
	 * When Get ranking file
	 */
	@When("Get ranking file")
	public void when_get_raking() {
		rankingList = rankingService.getRanking(gameDetails);
	}

	/**
	  * This method implements the below step
	 * Scenario Outline: Creating ranking table from <input>
	 * Then the output <output> should be <expected>
	 * @param output
	 * @param expected
	 * @throws IOException
	 */
	@Then("the output {word} should be {word}")
	public void then_files_should_match(String output, String expected) throws IOException {
		File file = new File("src/test/resources/");
		String testResourcesPath = file.getAbsolutePath() + "/";

		rankingService.printRanking(rankingList, testResourcesPath + output);
		File expectedFile = new File(testResourcesPath + expected);
		File resultFile = new File(testResourcesPath + output);

		BufferedReader reader1 = new BufferedReader(new FileReader(expectedFile));
		BufferedReader reader2 = new BufferedReader(new FileReader(resultFile));

		String line1 = reader1.readLine();
		String line2 = reader2.readLine();

		while (line1 != null || line2 != null) {
			assertEquals(line1, line2);
			line1 = reader1.readLine();
			line2 = reader2.readLine();
		}
		
		reader1.close();
		reader2.close();
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Creating ranking table from <input>
     * Given I have an input file <input> for ranking list
	 * @param fileName
	 * @throws IOException
	 * @throws FileFormatException
	 */
	@Given("I have an input file {word} for ranking list")
	public void given_input_ranking_list(String fileName) throws IOException, FileFormatException {
		gameDetails = rankingService.getGameDetails(inputPath + fileName);
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Creating ranking table from <input>
	 * When Get ranking list
	 */
	@When("Get ranking list")
	public void when_get_raking_list() {
		rankingList = rankingService.getRanking(gameDetails);
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Creating ranking table from <input>
	 * Then Ranking List output should be <expected>
	 * @param testData
	 * @throws IOException
	 */
	@Then("Ranking List output should be {}")
	public void then_ranking_list_should_match(String testData) throws IOException {
		List<String> list = new ArrayList<String>(Arrays.asList(testData.split(";")));
		assertEquals(rankingList.get(0).getName(), list.get(0));
		assertEquals(rankingList.get(1).getName(), list.get(1));
		assertEquals(rankingList.get(2).getName(), list.get(2));
		assertEquals(rankingList.get(3).getName(), list.get(3));
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Read game details from <input>
	 * Given I have an input file <input> get game details
	 * @param fileName
	 * @throws IOException
	 * @throws FileFormatException
	 */
	@Given("I have an input file {word} get game details")
	public void given_input_gamedetails_list(String fileName) throws IOException, FileFormatException {
		gameDetails = rankingService.getGameDetails(inputPath + fileName);
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Read game details from <input>
	 * When Get game details
	 */
	@When("Get game details")
	public void when_get_gamedetails_list() {
		rankingList = rankingService.getRanking(gameDetails);
	}

	/**
	 * This method implements the below step
	 * Scenario Outline: Read game details from <input>
	 * Then GameList output should be <expected>
	 */
	@Then("GameList output should be {}")
	public void then_gamedetails_list_should_match(String testData) throws IOException {

		assertEquals(testData, gameDetails.get(0).toString());
		assertEquals(rankingList.size(), 6);

	}

}