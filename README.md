# bWNP7nAH8BbKmr
## Relay 5

## Project Setup

- Clone repository
- Got to repository folder
- To build Server
```sh
cd connectfiveserver
mvn clean install
mvn spring-boot:run
```
- To build Client
```sh
cd connectclient
mvn clean install
mvn spring-boot:run
```
-To Run Game with JAR files
```sh
cd game_jars
[For Server]: 
java -jar connectfiveserver.jar
[For Clients]:
java -jar connectclient.jar
```

## Play game

### 1. Run Server in one terminal
![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/ServerStartup.PNG)
### 2. Run Client for Player-1
![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/ClientStartip.PNG)
### 3. Enter Player-1 Details
> Note:  Perform step-4 after terminal prompts "Waiting for player 2"     

![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/ClientStartupPlayer1.PNG)
### 4. Run Client for Player-2 in Separate Terminal
![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/ClientStartupPlayer2.PNG)
### 5. Game play
![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/GamePlay.PNG)

## Testing

- `Screenshot for tests attached below :`
- `You can run test using any IDE directly`

![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/CodeCoverage.PNG)


## Swagger URL
- Swaggger is enabled and can be viewed after server startup at: 
>http://localhost:8080/swagger-ui.html

>http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

![alt text](https://github.com/SpreeRaj/bWNP7nAH8BbKmr/blob/main/Screenshots/Swagger.PNG)

## Additional Notes :
- If one player disconnects game takes 6 seconds to declare other player winner.
- To start a new game shutdown both server and clients and follow steps again of  `Play Game`
- Tests only involve unit test for 2 Manager Classes. Integration tests would need further upgrade/Implementation.
- `Know issue`: Cannot Start two clients simultaneously. Need to finish entering details of Player 1 before starting client 2.
- Server is capable of handling game of any size and connect of any number. Currently built with 6X9 and win of relay 5. Just rebuild client by making those changes and you are good to go.

## Concepts Used
### 1.Spring boot
- To develop client
### 2. Spring Boot Rest
- To develop server apis.
### 3. Junit 5
- For Unit Testing

### 4. Multithreding and Long Polling
- To handle two player gameplay without web socket



# Enjoy the Game  !!!! :)

## License

MIT





   
