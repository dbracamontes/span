Feature: File Service Print Ranking
  As a user
  I want to calculate the ranking for the current games and store in a file

  Scenario Outline: Creating ranking table from <input>
    Given I have an input file <input>
    When Get ranking file
    Then the output <output> should be <expected>

  Examples: 
    | input 					 			 | output		 				 				| expected									|
    | files/Case_1_input.txt | files/Case_1_output.txt 	| files/Case_1_expected.txt	|
    | files/Case_2_input.txt | files/Case_2_output.txt 	| files/Case_2_expected.txt	|
    

  Scenario Outline: Creating ranking list from <input>
    Given I have an input file <input> for ranking list
    When Get ranking list
    Then Ranking List output should be <expected>

  Examples: 
    | input 					 			 | expected		 				 																		|
    | files/Case_1_input.txt |Lions;Tarantulas;Benfica;FC Awesome;Snakes;Grouches|
    
 
 Scenario Outline: Read game details from <input>
    Given I have an input file <input> get game details
    When Get game details
    Then GameList output should be <expected>

  Examples: 
    | input 					 			 | expected		 				 																		|
    | files/Case_1_input.txt | GameDetails [localTeam=Lions, localTeamScore=3, visitorTeam=Snakes, visitorTeamScore=3, gameResult=DRAW]|
  
    