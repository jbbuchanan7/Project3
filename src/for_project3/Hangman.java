package for_project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Hangman extends Game{

	private ArrayList<String> hangman = new ArrayList<>();
	private ArrayList<String> words = new ArrayList<>();
	private ArrayList<Character> guessedLetter = new ArrayList<>();
	private String chosenWord;
	private String guessedWord;
	private int counter = 0;
	private boolean fate;
	
	public Hangman(){
		try {
			File hangmanFile= new File("hangman.txt");
			Scanner scan = new Scanner(hangmanFile);
			scan.useDelimiter(",");
			while(scan.hasNext()) {
				String item = scan.next();
				hangman.add(item);
				
			}
			
			
			File wordsFile = new File("words.txt");
			Scanner scan2 = new Scanner(wordsFile);
			scan2.useDelimiter(",");
			while(scan2.hasNext()) {
				words.add(scan2.next());
			}
			
			scan.close();
			scan2.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

		
		Random random = new Random();
		int randomIndex =  random.nextInt(words.size());
		
		chosenWord = words.get(randomIndex);
		
		for(int i = 0; i < chosenWord.length(); i++) {
			guessedLetter.add('_');
		}
				
	}

	
	@Override
	public String explainRules() {
		return "The point of the game is to guess the correct word befor the man is hung.\n";
	}

	@Override
	public String setup() {

		System.out.println(hangman.get(counter));
		for(int i = 0; i < chosenWord.length(); i++) {
			if(guessedLetter.get(i).equals('_')) {
				System.out.print("_ ");
			}else {
				
			System.out.print(guessedLetter.get(i)+ " ");
			}
		}
		
		return "\n\nEnter in a single letter or guess the word to continue:";
		
	}

	@Override
	public boolean goodPlayerInput(String guess) {
		if(guess.length() == 1 && Character.isLetter(guess.charAt(0))){
			guessedWord = guess.toLowerCase();
			return true;
		}else{
			for(int i = 0; i < guess.length(); i++) {
				if(!Character.isLetter(guess.charAt(i))) {
					return false;
				}
			}
			guessedWord = guess.toLowerCase();
			return true;
		}
		
	}

	@Override
	public String checkWinOrLose() {
		
		fate = false;
		
		if(guessedWord.equals(chosenWord)){
			fate = true;
		}else if(guessedWord.length() == 1) {
			for(int i = 0; i < chosenWord.length(); i++) {
				if(chosenWord.charAt(i) == guessedWord.charAt(0)) {
					guessedLetter.set(i, guessedWord.charAt(0));
					fate = true;
					
				}
			}
		}else {
			fate = false;
			counter += 1;
			return "The word you have entered is wrong try again!";
		}
		
		
		if(fate ==true) {
			return "Good job, the letters have been updated!";
			
		}else {
			counter += 1;
			return "Letter not found in word. Try again!";
		}
		

		
	}

	@Override
	public boolean canPlayAgain() {
		
		if(counter > 5) {
			System.out.println(hangman.get(6));
			return false;
		}
		
		if (fate == false){
			return true;
		}else{
			if(guessedWord.equals(chosenWord)) {
				System.out.println("You've guessed the word, it was " + chosenWord);
				return false;
			}else {
				
			for(int i = 0; i < guessedLetter.size(); i++) {
				if(guessedLetter.get(i).equals('_')) {
					return true;
				}
			}
			
			System.out.println("You've guessed the word, it was " + chosenWord);
			return false;
			}
			
		}
	}

}
