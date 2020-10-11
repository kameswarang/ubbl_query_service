# The Ultimate Big Bash League Dream Team Selection Challenge 

###  Important instructions:
There are two levels of the problem. Once you complete the first level, you can proceed to the second level (next question). It is not compulsory to attempt the second level and you can attempt the second level only when you complete the first level. However, it is recommended to proceed with the second level once you have provided the submission for the first level.

Note: We highly encourage you to submit your work even if it is at an incomplete stage.

### Problem statement:
For the new season of the Big Bash League (BBL), the owners of different cricket teams are reconstructing their teams to ensure that their team wins this season. They require crunched data to build their desired team and guarantee a win. You are required to help the team coaches, who are not friendly to computer technologies, to make a quick and effective decision by retrieving the data that they require.

In order to help them, you are required to implement a full stack system for the Big Bash League by using the AngularJS framework for the front end of the system. The system must implement the following features:
- Microservices architecture that extracts the provided data  
- Microservices APIs for the quick retrieval of the data
- The provided dataset is an extensive dataset of the cricket players that contains the following details:
	- Personal details: Name, Role, Value, Team, and Nationality
	- Batting stats: Matches, Innings played, Strike rate, Highest score, Batting average, 100s, 50s, Fours, and Sixes
	- Bowling stats: Bowling economy, Balls bowled, Wickets taken, and 5 wicket hauls
	- There are three tables that you must add to your database.

### Tasks
- Retrieve the BBL dataset that is provided into your NoSQL database and create the required schema.
- Implement at least two microservices for the following:
	- Authentication of the incoming request: Implement an API key authentication to validate users
	- Querying the database: Create queries from the JSON input

**Query service**
This service must be the only user-facing service that can be called by using an API endpoint. Your task is to implement the API key authentication for this service. The tasks of the data service are as follows:
- It is required to take input and provide output in the JSON format. You are expected to create your own JSON schema for the input and output.
- It is required to validate the API key and JSON schema.
- Post validation, it must internally call the data-retrieval service.

**Data service**
The data service is not user-facing and should restrict access to certain predefined IP addresses. It can only be invoked from internal machines of the private network and not from the public web. The primary task of this API is to query the database. The tasks of this service are as follows:
- It takes the complex JSON (nested JSON) as the input and creates a query to fetch data from many tables.
- It must feed the resulting set to the first service.

**Required APIs**
You are required to implement at least two APIs for the query service:
- get_player_info(player_name)
This API must take player_name as the input and retrieve extensive data about that player from the database.
The data should contain the player's personal details, stats, affiliations, traits, and overall potential.
- get_team_player_list(team_name)
This API retrieves the names of all the players who play for a specific team.
It must take team_name as the input and retrieve extensive data about all the players who play for that team.  
- The data should contain all the player's personal details, stats, affiliations, traits and, overall potential.

### Guide
- BBL Data-dump: [ http://hck.re/hhyS4A ]
- TechStack
  - Java
  - AngularJS
  - ExpressJS
  - NodeJS
  - NoSQL (e.g: MongoDB)

### Submission instructions
- Zip and upload your source code and deployment instructions.
- Note: The code must build and run without any intervention from the evaluators.
- Insert comments in your code so that it is easy to understand for judges while assessment.
- Explain the logic behind your choices in an accompanying document.
