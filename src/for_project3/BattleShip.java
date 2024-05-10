package for_project3;

import java.util.Random;

public class BattleShip extends Game{
	
	private String[][] hiddenBoard = new String[7][7];
	private String[][] board = new String[7][7];
	private String[][] computer = new String[7][7];
	private int guessedY;
	private int guessedX;
	private int compGuessedX;
	private int compGuessedY;
	private String[] guessedPos;
	
	
	public BattleShip() {
		
		Random rand = new Random();
		
		int randomBX = rand.nextInt(7);
		int randomBY = rand.nextInt(7);
		
		int randomBX2;
		int randomBY2;
		
		int randomBX3;
		int randomBY3;
		
		
		board[randomBY][randomBX] = "#";
		
		while (true){
			randomBX2 = rand.nextInt(7);
			randomBY2 = rand.nextInt(7);
			
			if(randomBX - 1 != randomBX2 && randomBX + 1 != randomBX2 && randomBX != randomBX2 ) {
				break;
			}
			
			if(randomBY - 1 != randomBY2 && randomBY + 1 != randomBY2 && randomBY != randomBY2 ) {
				break;
			}
		}
		
		
		board[randomBY2][randomBX2] = "#";
		
		while(true) {
			
			randomBX3 = rand.nextInt(7);
			randomBY3 = rand.nextInt(7);
			
			if((randomBX - 1 != randomBX2 && randomBX - 1 != randomBX3 && randomBX2 - 1 != randomBX3 ) && ( randomBX + 1 != randomBX2 && randomBX + 1 != randomBX3 && randomBX2 + 1 != randomBX3 ) &&  (randomBX != randomBX2 && randomBX != randomBX3 && randomBX2 != randomBX3)) {
				break;
			}
			
			if((randomBY - 1 != randomBY2 && randomBY - 1 != randomBY3 && randomBY2 - 1 != randomBY3) && (randomBY + 1 != randomBY2 && randomBY + 1 != randomBY3 && randomBY2 + 1 != randomBY3) && (randomBY != randomBY2 && randomBY != randomBY3 && randomBY2 != randomBY3)) {
				break;
			}
			
		}
		
		board[randomBY3][randomBX3] = "#";
		
////////////////////////////////////////////// Hidden Board Generation ///////////////////////////////////////////////////
		
		
		Random rand2 = new Random();
		
		int randomHX = rand2.nextInt(7);
		int randomHY = rand2.nextInt(7);
		
		int randomHX2;
		int randomHY2;
		
		int randomHX3;
		int randomHY3;
		
		
		hiddenBoard[randomHY][randomHX] = "#";
		
		while (true){
			randomHX2 = rand2.nextInt(7);
			randomHY2 = rand2.nextInt(7);
			
			if(randomHX - 1 != randomHX2 && randomHX + 1 != randomHX2 && randomHX != randomHX2 ) {
				break;
			}
			
			if(randomHY - 1 != randomHY2 && randomHY + 1 != randomHY2 && randomHY != randomHY2 ) {
				break;
			}
		}
		
		
		hiddenBoard[randomHY2][randomHX2] = "#";
		
		while(true) {
			
			randomHX3 = rand2.nextInt(7);
			randomHY3 = rand2.nextInt(7);
			
			if((randomHX - 1 != randomHX2 && randomHX - 1 != randomHX3 && randomHX2 - 1 != randomHX3 ) && ( randomHX + 1 != randomHX2 && randomHX + 1 != randomHX3 && randomHX2 + 1 != randomHX3 ) &&  (randomHX != randomHX2 && randomHX != randomHX3 && randomHX2 != randomHX3)) {
				break;
			}
			
			if((randomHY - 1 != randomHY2 && randomHY - 1 != randomHY3 && randomHY2 - 1 != randomHY3) && (randomHY + 1 != randomHY2 && randomHY + 1 != randomHY3 && randomHY2 + 1 != randomHY3) && (randomHY != randomHY2 && randomHY != randomHY3 && randomHY2 != randomHY3)) {
				break;
			}
			
		}
		
		hiddenBoard[randomHY3][randomHX3] = "#";
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] != "#") {
					board[i][j] = "_";
				}
			}
		}
		
		for(int i = 0; i < computer.length; i++) {
			for(int j = 0; j < computer.length; j++) {
				computer[i][j] = "_";
			}
		}
		
		for(int i = 0; i < hiddenBoard.length; i++) {
			for(int j = 0; j < hiddenBoard.length; j++) {
				if(hiddenBoard[i][j] != "#") {
					hiddenBoard[i][j] = "_";
				}
			}
		}
		
		
	}
	
	
	@Override
	public String explainRules() {
		System.out.println("You and the computer will alternate turns, calling out one shot per turn to try and hit each other's ships!");
		System.out.println("Displayed on your board will be '#' to represent where your ships are!");
		System.out.println("On your turn, you will pick a set of cordinates (y,x) and see if you've hit or missed!");
		System.out.println("If you hit your oponent an X will be dispplayed on the map, if its a miss it will be an O!\n");
		return "Good Luck!\n";
	}

	@Override
	public String setup() {
		
		System.out.println("\n--------------------------");

		for(int i = 0; i < computer.length; i++) {
			for(int j = 0; j < computer.length; j++) {
				System.out.print(computer[i][j] + " ");
			}
			System.out.print("\n");
		}
		
		System.out.println("\n--------------------------");
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.print("\n");
		}
		
		
		return "Pick a set of cordinates (Formated like this 'y,x' in the rang from 0,0 to 6,6) to take your shot:";
	}

	@Override
	public boolean goodPlayerInput(String guess) {
		guessedPos = guess.split(",");
		
		if(guessedPos.length != 2) {
			return false;
		}
		
		try {
			guessedY = Integer.parseInt(guessedPos[0]);
			guessedX = Integer.parseInt(guessedPos[1]);
			
			if(!(0 <= guessedY && guessedY < 7 && 0 <= guessedX && guessedX < 7)) {
				return false;
			}else {
				return true;
			}
			
		}catch(NumberFormatException e){
			return false;
		}
		
	}

	@Override
	public String checkWinOrLose() {
		Random random = new Random();
		
		compGuessedX = random.nextInt(7);
		compGuessedY = random.nextInt(7);	
		
		for(int k = 0; k < board.length; k++) {
			for(int l = 0; l < board.length; l++) {
				if(board[compGuessedY][compGuessedX] == "#") {
					board[compGuessedY][compGuessedX] = "X";
					System.out.println("The computer has hit one of your ships!");
				}else if(board[compGuessedY][compGuessedX] == "_"){
					board[compGuessedY][compGuessedX] = "O";
				}
			}
		}

		if(board[compGuessedY][compGuessedX] == "X" || (board[compGuessedY][compGuessedX] == "O")){
			System.out.println("The computer has missed!");

		}	
		
		
		
		for(int i = 0; i < hiddenBoard.length; i++) {
			for(int j = 0; j < hiddenBoard.length; j++) {
				if(hiddenBoard[guessedY][guessedX] == "#") {
					computer[guessedY][guessedX] = "X";
					hiddenBoard[guessedY][guessedX] = "X";
					return "You've hit a ship!";
				}
				
			}
		}
		if(hiddenBoard[guessedY][guessedX] == "X") {
			return "You've already hit a ship on those cordinates";
		}else {
			computer[guessedY][guessedX] = "O";
			return "You've missed!";

		}
		
	}

	@Override
	public boolean canPlayAgain() {
		int counter = 0;

		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board.length; j++) {
				if(board[i][j] == "#"){
					counter += 1;
				}
			}
		}
		
		if(counter == 0) {
			System.out.print("The computer has won!");
			return false;
		}
		
		for(int i = 0; i < hiddenBoard.length; i++) {
			for(int j = 0; j < hiddenBoard.length; j++) {
				if(hiddenBoard[i][j] == "#") {
					return true;
				}
			}
		}
		System.out.print("You have won!");
		return false;
	}

	
}
