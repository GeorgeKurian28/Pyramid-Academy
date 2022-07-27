import javax.swing.text.DefaultStyledDocument;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.*;
import java.util.Arrays;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

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

        if(Character.isLetter(userGuess.toCharArray()[0]))
            return userGuess;
        else
            return "";
    }

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
     * setProgress method updates the progess variable that stores the correct guesses by the user
     *
     * @return - Return a string that contains the updated progress variable
     */
    public String setProgress(char c, String word, String progress, String lettersEntered){


        IntStream ins  = IntStream.range(0,word.length());//Declare an IntStream with the range of number equal to the number of characters in the word

        if(progress.length() == 0)
            return ins.mapToObj(i -> "_").reduce("",(s1,s2) -> s1+s2);
        else
            return ins.mapToObj(i -> word.charAt(i)==c ? c : progress.charAt(i)).map(letters -> Character.toString(letters)).reduce("", (s1 , s2) -> s1+s2);

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
                hangManLevel(cnt);
                message("missed", missedLetters);
                message(progress);
                letter = guessALetter();
                if(lettersEntered.contains(letter))// Is the letter repeated? by checking the letters entered
                    message("repeated");
                else {
                    lettersEntered += letter;
                    if (word.contains(letter))             // If not repeated the check if it is contained in the word update progress
                        progress = setProgress(letter.toCharArray()[0], word, progress, lettersEntered); // If not contained in the word update missed words
                    else {
                        missedLetters += letter;// Update letters entered
                        cnt++;
                    }
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
        String option;
        //Ask name
        while(keepPlaying){
            message("play");
            option = scan.next();
            if(option.contains("y")||option.contains("Y")) {
                if (option.contains("y") || option.contains("Y"))
                    hangManGame();
                else if (option.contains("n")||option.contains("N"))
                    keepPlaying = false;
            }
        }
    }

    public static void hangManLevel(int level){
        String fileName = "src/java/hangman";
        fileName = fileName + level+".txt";
        File f = new File(fileName);
        try {
            BufferedReader read = new BufferedReader(new FileReader(f));
            read.lines().forEach(System.out::println);
        }
        catch(Exception e){
            System.out.print("File"+ fileName  +"does not exist");
        }
    }

    public static void main(String ...args){
        HangMan hangMan = new HangMan();
        hangMan.play();
    }
}
