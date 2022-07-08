import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.function.Consumer;

public class HangMan {
    Scanner scan = new Scanner(System.in);
    public Consumer<String> print = System.out::println;

    /******
     * Message method prints all the messages that will be used in the game using a switch case and any new messages can just be added as a case
     *
     * @param - print     -String that stores the message to be printed
     */
    public void message(String ...msg){
        String print = "";
        switch (msg[0]){
            case "lost":
                print = "You Lost !!!!";
                break;
            case "won":
                print = "Yes the secret word is \"" + msg[1] + "\"! You have won";
                break;
            case "missed":
                print = "Missed Letters: " + msg[1];
                break;
            case "guess":
                print = "\nGuess a letter";
                break;
            case "too many":
                print = "You can only guess one letter !!!!";
                break;
            case "not alphabet":
                print = "You have to guess a letter digits and special charectors are not allowed!!!";
                break;
            case "repeated":
                print = "You already guessed the letter choose again ";
                break;
            case "play":
                print = "Would you like to play again Y / N :";
                break;
            case "incorrect":
                print = "This is not a correct option enter Y/N";
                break;
            default:
                print = msg[0];
                break;
        }
        System.out.println(print);
    }

    /******
     * GuessALetter method that returns a letter after it has been validated
     *
     * @param  - userGuess     -String that stores the user guess
     * @param  - valid         -boolean value set based on if the input is valid and if not keep asking for another input
     * @return - Return a string that contains the user guess
     */
    public String guessALetter(){
        String userGuess;
        boolean valid = false;
        message("guess");
        userGuess = scan.next();
        while(!valid){
            valid = isValidGuess(userGuess);
        }
        return userGuess;
    }

    /******
     * IsValidGuess validates the guess to ensure that its only one alphabet
     *
     * @return - Return a boolean value based of wether the input is valid
     */
    public boolean isValidGuess(String userGuess){
        if(userGuess.length() > 1) {
            message("too many");
            return false;
        }
        else if (Character.isLetter(userGuess.toCharArray()[0])) {
            return true;
        }
        message("not alphabet");
        return false;
    }
/*
    public boolean isAlreadyGuessed(String userGuess, String LettersGuessed){
        return LettersGuessed.contains(userGuess);
    }

    public boolean isGuessCorrect(String userGuess, String systemGuess){
        return(systemGuess.contains(userGuess));
    }*/

    /******
     * getWord method chooses a word randomly from the list which can be expanded
     *
     * @param  - wordSet  - this is an array that contains all the words that can be used and more words can be added to this set
     * @return - Return a string that contains the system guess
     */
    public String getWord(){
        String wordSet[] = {"Apple","Mango","Letters","Sleep","fly","cat","dog"};
        return wordSet[(int)Math.floor(Math.random()*(wordSet.length-1))];
    }

    /******
     * setProgress method updates the porgess variable that stores the correct guesses by the user
     *
     * @return - Return a string that contains the updated progress variable
     */
    public String setProgress(char c, @NotNull String word, String progress, @NotNull String lettersEntered){
        for(int i = 0; i <word.length(); i++) {
            if(word.charAt(i)==c)
                progress = progress.substring(0,i)+c+progress.substring(i+1,word.length());
            else if(lettersEntered.length() == 0)
                progress += "_";
        }
        return progress;
    }

    /******
     * hangManGame the actual hangman game that the user will play
     *
     * @param  - word           -String     Stores the systemGuess
     * @param  - progress       -String     Stores the letters correctly guessed by the user
     * @param  - missedLetters  -String     Stores the incorrectly guessed letters by the user
     * @param  - lettersEntered -String     Stores all the letters gussed by the user
     * @param  - letter         -String     Stores the current validated guess by the user
     * @param  - complete       -boolean    Stores the boolean value of whether the conditions to stop the game are met
     * @param  - cnt            -int        Stores the counter of the hangman progress
     * @param  - hangManLevel   -String[]   Stores all the stages of the hangman
     * @return - Return a string that contains the updated progress variable
     */
    public void hangManGame(){

        String word = getWord();
        String progress = setProgress(' ', word, "", "");
        String missedLetters = "";
        String lettersEntered = "";
        String letter ="";
        boolean complete = false;
        int cnt = 0;
        String[]  hangManLevel = {
                "+---+\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ===",
                "+---+\n" +
                        "O  |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ===",
                "+---+\n" +
                        "O  |\n" +
                        "|  |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ===",
                "+---+\n" +
                        "O  |\n" +
                        "|  |\n" +
                        "|  |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ===",
                "+---+\n" +
                        "O  |\n" +
                        "|_ |\n" +
                        "|  |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ===",
                "+---+\n" +
                        "O_ |\n" +
                        "|_ |\n" +
                        "|  |\n" +
                        "   |\n" +
                        "   |\n" +
                        "   |\n" +
                        "  ===",
                " +---+\n" +
                        "_O_ |\n" +
                        " |_ |\n" +
                        " |  |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "   ==="};


        while(!complete){
            if (cnt > 6) {      //This condition checks if you have been hanged and breaks the loop
                message("lost");
                complete = true;
            }
            else if (progress.equals(word)) {       //This condition checks if you have won and breaks the loop
                message("won",word);
                complete = true;;
            }
            else {                                  //If none of the conditions are satisfied then the game continues
                message(hangManLevel[cnt]);
                message("missed", missedLetters);
                message(progress);
                letter = guessALetter();
                if(lettersEntered.contains(letter))// Is the letter repeated? by checking the letters entered
                    message("repeated");
                else {
                    lettersEntered += letter;
                    if (word.contains(letter))             // If not repeated the check if it is contained in the word update progress
                        progress = setProgress(letter.toCharArray()[0], word, progress, lettersEntered); // If not contained in the word update missed words
                    else
                        missedLetters += letter;// Update letters entered
                    cnt++;
                    complete = false;
                }
            }
        }
    }

    /******
     * play method runs the game till the user decides to quit
     *
     * @param  - option         -String     Stores all the letters gussed by the user
     * @param  - keepPlaying    -boolean     Stores the current validated guess by the user
     * @param  - valid          -boolean    Stores the boolean value of whether the conditions to stop the game are met
     */
    public void play(){
        boolean keepPlaying = true;
        boolean valid;
        String option= "";
        while(keepPlaying){
            valid = true;
            hangManGame();
            message("play");
            while(valid){
                option = scan.next();
                if(option.contains("n") || option.contains("N")) {
                    keepPlaying = false;
                    valid = false;
                }
                else if(option.contains("y")||option.contains("Y"))
                    valid = false;
                else
                    message("incorrect");
            }
        }
    }

    public static void main(String ...args){
        HangMan hangMan = new HangMan();
        hangMan.play();
    }
}
