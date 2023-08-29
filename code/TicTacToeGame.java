/**
 * This is for computer science A.I
 * In this code we are creating the game Tic-Tac-Toe
 * So we first create the board, afterwards we find a way to 
 * insert the "X" and "O" characters in the empty loops 
 * @author Raymond Johnson
 * @version 05/04/2020
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
public class TicTacToeGame {

	//global arrayList for when the player or computer win
	static ArrayList<Integer> playerTargets = new ArrayList<Integer>();
	static ArrayList<Integer> computerTargets = new ArrayList<Integer>();

	public static void main(String[] args){
		//first going to create the board

		char[][] gamingBoard = {{' ', '|', ' ', '|', ' '}, 
				{'-', '+', '-', '+', '-'}, 
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}};

		printBoard(gamingBoard);
		System.out.println("This is how the Tic-Tac-Toe board set up looks. Welcome");
		System.out.println();

		String answer = "";
		do
		{//this do while loop will prompt the user on whether they want to
			// continue or not  

			while(true) {//this will allow the game to be played consistently
				Scanner s = new Scanner(System.in);
				System.out.println("Please enter your input (0-9)");
				int target = s.nextInt();

				//this while loop will keep the computer from overwritint the users 
				// input
				while(playerTargets.contains(target) || computerTargets.contains(target)){
					System.out.print("Position taken! Enter another position!");
					target = s.nextInt();
				}

				placeTarget(gamingBoard, target, "player");
				String outcome = winnerCheck();//checking winning/losing status after user input
				if(outcome.length()>0){
					System.out.println(outcome);
					break;
				}

				// May want to place the input of the user first, then print board
				// then the computer pick and then pick board as well
				// this will have the computer pick a target on the board
				Random r = new Random();
				int compTarget = r.nextInt(9)+1;

				while(playerTargets.contains(compTarget) || computerTargets.contains(compTarget)){
					compTarget = r.nextInt(9)+1;
				}//end of while
				placeTarget(gamingBoard, compTarget, "computer");

				printBoard(gamingBoard);//now will be able to place X on board

				//this will print out who have won the game between the user and then
				// computer. Or whether or not it has resulted in a tie
				outcome = winnerCheck();
				if(outcome.length()>0){
					System.out.println(outcome);
					break;
				}
			}//end of while(true)
			Scanner sc = new Scanner(System.in);
			System.out.println("Do you want to keep going?(Y/N)");
			answer = sc.next();
		}while(answer.equalsIgnoreCase("y"));
	}//end of main method


	public static void printBoard(char[][] gamingBoard){
		//creating a nested loop to print out the board above
		//It only prints out the board, nothing more
		for(char[] row : gamingBoard){
			for(char r : row){
				System.out.print(r);
			}
			System.out.println();
		}
	}

	/**
	 * This method will print out the players point/target on the board
	 * if the player is the user then it will print out a "X"
	 * however if the player is the computer then it will print out an "O"
	 */
	public static void placeTarget(char[][] gamingBoard, int target, String user){

		char output = ' ';//this is the default output

		//this will allow us to switch output characters
		//depending on if the player is the user or computer
		if(user.equals("player")){
			output = 'X';
			playerTargets.add(target);
		}
		else if(user.equals("computer")){
			output = 'O';
			computerTargets.add(target);
		}
		//will have to change into an "if" statement later 
		switch(target){
		case 1:
			gamingBoard[0][0] = output;
			break;
		case 2:
			gamingBoard[0][2] = output;
			break;
		case 3:
			gamingBoard[0][4] = output;
			break;
		case 4:
			gamingBoard[2][0] = output;
			break;
		case 5:
			gamingBoard[2][2] = output;
			break;
		case 6:
			gamingBoard[2][4] = output;
			break;
		case 7:
			gamingBoard[4][0] = output;
			break;
		case 8:
			gamingBoard[4][2] = output;
			break;
		case 9:
			gamingBoard[4][4] = output;
			break;
		default:
			break;

		}
	}

	public static String winnerCheck(){

		List topRow = Arrays.asList(1, 2, 3);
		List middle = Arrays.asList(4, 5, 6);
		List bottom = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List middleCol = Arrays.asList(2, 5, 8);
		List rghtCol = Arrays.asList(3, 6, 9);
		List cross1= Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);

		// to check through each scenario to make sure if the player
		// or computer has won the game
		List<List> won = new ArrayList<List>();
		won.add(topRow);
		won.add(middle);
		won.add(bottom);
		won.add(leftCol);
		won.add(middleCol);
		won.add(rghtCol);
		won.add(cross1);
		won.add(cross2);

		for(List l: won){
			if(playerTargets.containsAll(l)){
				return "You've won!";
			} else if(computerTargets.containsAll(l)){
				return "Sorry you have lost, the computer wins";
			} else if(playerTargets.size() + computerTargets.size() == 9){
				return "TIE!";
			}

		}//end of loop

		return "";

	}


}//end of class



