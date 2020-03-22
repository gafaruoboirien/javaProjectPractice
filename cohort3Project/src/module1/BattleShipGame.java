/*
This game utilizes a single 2D Array to create the classic battleship game.
You want to create a 10X10 grid which represents
your ocean. To do that, I find it helpful
to fill the Array with empty spaces
which will be replaced with symbols later on.
Once the map is created, you'll
need the coordinates of the user ships.
Ask the user for them, while making sure
none of the coordinates overlap or are out of bounds.
If so, prompt the user for new coordinates.
Print the ships to the map by replacing the empty space in the
array with an '@' symbol. Next, you'll need
to deploy the computer ships which will
have randomly generated coordinates.
Again you must make sure the coordinates
do not overlap or are out of bounds
as well as they do not overlap with the
user ships. These values should be stored
but nothing should be printed to the map.
When the user and computer guess
coordinates of where the ships are
display those coordinates in the Array
with an X. Keep a count of how many ships
everyone has left as the user plays the game
and every time a ship is sunk,
deduct 1 from the count. Once either the
computer or user win the game, display
the appropriate ending message.
A congratulations! or Sorry you lose!
And you'll have completed battleship.


* declaring and initializing array
 */

package module1;


	import java.util.Random;
	import java.util.Scanner;
	public class BattleShipGame { public static void main(String[] args) {
	    System.out.println("**** Welcome to Battle Ships game ****");
	    System.out.println("");
	    System.out.println("Right now, the sea is empty.");
	    System.out.println("");
	    Scanner input = new Scanner(System.in);


	    //Creating two different game map which will help us later on when we want to store the missed turn's for computer.
	    //It creates a layer on top of the game map which only visible for the computer.
	    //So the computer does not make repeated and similar guesses.
	    String[][] gameMap = createGameMap();
	    String[][] computerShips = createGameMap();
	    printMap(gameMap);


	    int xCord = 0;
	    int yCord = 0;

	    System.out.println("");
	    System.out.println("---------------------");
	    System.out.println("Deploy your ships:");

	    //Taking the coordinates from the user.
	    for(int i = 0; i<5; i++) {
	                   //---------------------------X COORDINATES---------------------------//
	        //Taking the X coordinate until it is given withing the requirements, otherwise it will continue looping.
	        int counter = 0;
	        while (counter < 1) {
	            System.out.print("Enter X coordinate for your " + (i + 1) + ". ship:");
	            xCord = input.nextInt();
	            if (xCord > 9 || xCord < 0) {
	                System.out.println("Invalid placement, please give your X coordinate within the range of grids.");
	                continue;
	            }
	            counter++;
	        }

	                   //---------------------------Y COORDINATES---------------------------//
	        //Taking the Y coordinate until its given withing the requirements, otherwise it will continue looping.
	        //The same method is used for x coordinates also.
	        counter = 0;
	        while (counter < 1) {
	            System.out.print("Enter Y coordinate for your " + (i + 1) + ". ship:");
	            yCord = input.nextInt();
	            if (yCord > 9 || yCord < 0) {
	                System.out.println("Invalid placement, please give your Y coordinate within the range of grids.");
	                continue;
	            }
	            counter++;
	        }

	        //Checking if there is any ship exists where we want to place it.
	        if (gameMap[xCord][yCord].equals("1")) {
	            System.out.println("At (" + xCord + "," + yCord + ") a ship exists please choose another place for the " + (i + 1) + ".ship");
	            i--;
	        }else{ // if not then place the ship according to the coordinates given.
	            gameMap[xCord][yCord] = "@"; // Placing the ship.
	        }
	    }
	    System.out.println("---------------------");
	    System.out.println("");
	    System.out.println("Computer is deploying ships");

	    //Computer is placing its ships.

	    Random randomNumb =  new Random(); int counter =0;
	   while(counter < 5){
	       //Creating random coordinates with secure random for the placement coordinates.
	        xCord = randomNumb.nextInt(9);
	        yCord = randomNumb.nextInt(9);
	        //Checking if there is any ship exists
	        if(gameMap[xCord][yCord].equals("1") || computerShips[xCord][yCord].equals("2")){
	            continue;
	        }else
	        {//If not then place the ship.
	            computerShips[xCord][yCord] = "2";
	            System.out.println((counter+1) +". ship DEPLOYED");
	        }
	        counter++;
	    }
	    System.out.println("---------------------");
	    System.out.println("");


	   //-------BATTLE SYSTEM-----//
	    //This is created by do-while loop while counting the ships.
	    int playerShipCounter =5, computerShipCounter = 5;
	    System.out.println("YOUR TURN");
	   do{
	       //---------PLAYERS TURN----------//
	       //---X COORDINATES---//
	       counter = 0;
	       while (counter < 1) {
	           System.out.print("Enter X coordinate: ");
	           xCord = input.nextInt();
	           if (xCord > 9 || xCord < 0) {
	               System.out.println("Invalid placement, please give your X coordinate within the range of grids.");
	               continue;
	           }
	           counter++;
	       }

	       //---Y COORDINATES---//
	       counter = 0;
	       while (counter < 1) {
	           System.out.print("Enter Y coordinate: ");
	           yCord = input.nextInt();
	           if (yCord > 9 || yCord < 0) {
	               System.out.println("Invalid placement, please give your Y coordinate within the range of grids.");
	               continue;
	           }
	           counter++;
	       }

	       //Notification and the action section, it gives the information of what's happening in the game.
	       if(gameMap[xCord][yCord].equals("-") || gameMap[xCord][yCord].equals("x") || gameMap[xCord][yCord].equals("!")){
	           System.out.println("This place has been guessed please try again.");
	           continue;
	       }
	       else if(computerShips[xCord][yCord].equals("2")){
	           gameMap[xCord][yCord] = "!";
	           System.out.println("Boom! You sunk the ship!");
	           computerShipCounter--;
	       }else if(gameMap[xCord][yCord].equals("@")){
	           System.out.println("Oh no, you sunk your own ship :(");
	           gameMap[xCord][yCord] = "x";
	           playerShipCounter--;
	       }else{
	           System.out.println("You missed");
	           gameMap[xCord][yCord] = "-";
	       }


	       System.out.println("COMPUTER'S TURN");
	        //----------COMPUTERS TURN----------//
	            //random numbers are created for the x and y coordinates.
	       xCord = randomNumb.nextInt(9);
	       yCord = randomNumb.nextInt(9);

	       if(computerShips[xCord][yCord].equals("-") || gameMap[xCord][yCord].equals("x") || gameMap[xCord][yCord].equals("!")){
	           continue;
	       } else if(computerShips[xCord][yCord].equals("2")){
	           gameMap[xCord][yCord] = "!";
	           System.out.println("The Computer sunk one of its own ships");
	           computerShipCounter--;
	       }else if(gameMap[xCord][yCord].equals("@")){
	           System.out.println("The Computer sunk one of your ships!");
	           gameMap[xCord][yCord] = "x";
	           playerShipCounter--;
	           System.out.println(playerShipCounter);
	       }else{
	           System.out.println("Computer missed");
	           computerShips[xCord][yCord] = "-"; // This is not visible by the user.
	           //This layer is created to prevent similar guesses.
	       }
	       System.out.println("");
	       printMap(gameMap);

	       System.out.println("Your ships: " + playerShipCounter + " | " + "Computer ships: " + computerShipCounter);
	       System.out.println("--------------------------------");
	       System.out.println("");
	   }while(playerShipCounter != 0 && computerShipCounter != 0);



	}

	//This methods prints the game map.
	public static void printMap(String[][] seaMap){
	    System.out.println("   0123456789");
	    //This prints the design of the map with each indexes.
	    for(int i = 0; i<seaMap.length; i++){
	        System.out.print(i + " |" );
	        for(int j =0; j<seaMap[0].length; j++){
	            System.out.print(seaMap[i][j]);
	        }
	        System.out.print("| "+i);
	        System.out.println();
	    }
	    System.out.println("   0123456789");
	}

	//Creates the game map, just to keep the main clean.
	public static String[][] createGameMap(){
	    String[][] gameMap = new String[10][10];
	    for(int i = 0; i<gameMap.length; i++){
	        for(int j = 0; j< gameMap[0].length; j++){
	            gameMap[i][j] = " "; // Each index is filled with space.
	        }
	    }
	    return gameMap;
	}
	
}
