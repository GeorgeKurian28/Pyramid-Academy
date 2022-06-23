import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    /**
     * Guessing game - guess the number.
     *
     * @author George Kurian
     * @param -player -The name of the player
     * @param -systemGuess -The random number guessed by the system between 0 and 20
     */


    String player;
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    int systemGuess;

    /**
     * Launches the application
     *
     * @param args - Application startup arguments
     */
    public static void main(String ...args){
        GuessingGame game = new GuessingGame();
        game.message1();// Welcome Message
        game.continueGame();// Game
    }

    /**
     * Welcome message and request player name.
     *
     */
    public void message1(){// Welcome Message

        System.out.println("Hello! What is your name? \n\n\n\n\n");
        player = scan.next();

    }

    /**
     * There are three possibilies - User guess greater than, lower than or equal to system guess
     *
     * @param -cnt -The counter to ensure user can guess only 3 times, which is incremented after each guess
     * @param -userGuess -The variable that stores the number guessed by the yser
     */
    public void guessingGame(){
        systemGuess = rand.nextInt(20);
        int userGuess, cnt = 0;
        while(cnt < 3) {
            if(cnt > 0 )
                System.out.println("Take another guess.\n\n");
            userGuess = validateGuess();// Validate user guess
            if(userGuess > systemGuess){
                System.out.println("Your guess is too high\n\n");
            }
            else if(userGuess < systemGuess){
                System.out.println("Your guess is too low\n\n");
            }
            else{
                System.out.println("Good Job,"+player+"! You guessed my number if 3 guesses!");
                cnt = 3;
            }
            cnt++;
        }

    }

    /**
     * Method to decide if to continue playing the game.
     *
     * @param -play -This is the boolean variable to keep the while loop running
     */
    public void continueGame(){
        boolean play = true;
        while(play){
            System.out.println("\n\n\n\n\nWell, "+player+", I am thinking of  a number between 1 and 20.\n\n\nTake a guess.\n\n\n\n");
            guessingGame();// call to the game method
            System.out.println("Would you like to play again? (y or n)");
            play = response();// validated response
        }
    }

    /**
     * Contains method for validation of user response to decide if to continue playing
     *
     * @param -play -The boolean response that is returned
     * @param -option -The Y/N option by user
     * @return -boolean response to play or not
     */
    public boolean response(){
        boolean play = true;
        String option;
        option = scan.next();
        while(!(option.equals("y") || option.equals("n"))){
            System.out.println("This is not a valid option try again (y or n) : ");
            option = scan.next();
        }
        if(option.equals("n"))
            play = false;
        return play;
    }

    /**
     * Contains method for validation of user guesses
     *
     * @param -userGuess -User guess value to be returned
     * @param -userInput -String input that will be checked to see if in the range  of0 and 20 and not a string
     * @param -valid -Boolean variable to continue the loop till correct input is received
     * @return -int response contiatning the user guess
     */
    public int validateGuess(){
        int userGuess = 0;
        boolean valid = false;
        String userInput;

        while(!valid){
            userInput = scan.next();// User input
            try {
                userGuess = Integer.parseInt(userInput);// parse to see if there will an excpetion
                if(userGuess > 20 || userGuess < 0){
                    System.out.println("Input has to be in the range 0 to 20");
                }
                else
                    valid = true;// if not break the loop and use the input
            }
            catch(Exception e){
                System.out.println("Input has to be numeric Try again");
            }

        }
        return userGuess;
    }

    /** Below is how the method has to be written so that they can be unit teted with a return value
     * Here this method does what it is named. It just takes the two inputs and checks them
     * So we can just test it with as many inputs as we want
     * We can use better method names like isValid() with a boolean return and so on
     * */
    /*
    String response(int guess, int secretNumber){
        if (guess < secretNumber){
            return "too low";
        }
        if (guess > secretNumber){
            return "too high";
        }
        return "correct you win!";
    }
*/

}
