import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Hangman {
    static int guessesLeft;
    static int score = 0;
    static ArrayList<Character> hiddenWord = new ArrayList();
    static ArrayList<Character> theWord = new ArrayList();
    static ArrayList<Character> incorrectGuesses = new ArrayList();
    static ArrayList<Character> allGuesses = new ArrayList();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        resetBoard();
        updateHiddenWord();
        displayBoardState();
        while (guessesLeft > 0) {
            guessHandler(getAndVetInput());
        }
        System.out.println("GAME OVER!!!");
        System.out.println("The word was: ");
        ALPrinter(theWord, "");
        System.out.println("Score: " + score);
    }

    public static String getNewWord() {
        Scanner dictionary = null;
        try {
            File file = new File("dictionary.txt");
            dictionary = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("file not found!!");
        }
        String aWord = "";
        ArrayList<String> possibleWords = new ArrayList();

        while (dictionary.hasNext()) {
            possibleWords.add(dictionary.next());
        }
        aWord = possibleWords.get((int) (Math.random() * possibleWords.size()));
        return aWord;
    }

    public static void resetBoard() {
        guessesLeft = 7;
        hiddenWord.clear();
        theWord.clear();
        //loading a new word into theWord
        for (char c : getNewWord().toCharArray()) {
            theWord.add(Character.toUpperCase(c));
        }
        for (int i = 0; i < theWord.size(); i++) {
            hiddenWord.add('_');
        }
        incorrectGuesses.clear();
        allGuesses.clear();
    }

    public static void updateHiddenWord() {
        //compare hiddenWord against allGuesses
        //loading a letter from allGuesses into theChar
        for (char theChar : allGuesses) {
            //loading an index from theWord into i
            for (int i = 0; i < theWord.size(); i++) {
                //comparing theWord.get(i) to theChar from allGuesses
                if (theWord.get(i) == theChar) {
                    hiddenWord.set(i, theWord.get(i));
                }
            }
        }
        if (!hiddenWord.contains('_')) {
            System.out.println("You guessed the word!");
            score += 100;
            score += 30 * guessesLeft;
            System.out.println("The word was: ");
            ALPrinter(theWord, "");
            System.out.println();
            resetBoard();
            updateHiddenWord();
        }
    }

    public static void guessHandler(char theGuess) {
        //duplicate guess
        if (allGuesses.contains(theGuess)) {
            System.out.println("You have already guessed that.");
        }
        //successful guess
        else if (theWord.contains(theGuess)) {
            for (Character theElement : theWord) {
                if (theElement == theGuess) {
                    score += 10;
                }
            }
            allGuesses.add(theGuess);
            updateHiddenWord();
        }
        //incorrect guess
        else {
            allGuesses.add(theGuess);
            Collections.sort(allGuesses);
            guessesLeft--;
            System.out.println("Sorry, there were no " + theGuess + "'s");
            //update incorrectGuesses
            incorrectGuesses.add(theGuess);
            Collections.sort(incorrectGuesses);
        }
        displayBoardState();
    }

    public static void displayBoardState() {
        System.out.print("Hidden Word: ");
        ALPrinter(hiddenWord, " ");
        System.out.print("Incorrect Guesses: ");
        ALPrinter(incorrectGuesses, ", ");
        System.out.println("Guesses: " + guessesLeft);
        System.out.println("Score: " + score);
    }

    public static void ALPrinter(ArrayList<Character> theArrayList, String theDelimiter) {
        for (int i = 0; i < theArrayList.size(); i++) {
            if (i != theArrayList.size() - 1) {
                System.out.print(theArrayList.get(i) + theDelimiter);
            }
            else {
                System.out.print(theArrayList.get(i));
            }
        }
        System.out.println();
    }

    public static char getAndVetInput() {
        System.out.print("Enter next guess: ");
        String inputString = input.next();
        while (!(inputString.length() == 1 && Character.isLetter(inputString.charAt(0)))) {
            System.out.println("Please enter a valid letter");
            inputString = input.next();
        }
        System.out.println();
        return Character.toUpperCase(inputString.charAt(0));
    }
    public static void writeHighscore() {
        File file = new File("highscores.txt");


    }
}
