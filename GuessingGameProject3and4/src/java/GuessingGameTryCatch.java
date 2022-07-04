import java.util.Scanner;

public class GuessingGameTryCatch {
    Scanner scan = new Scanner(System.in);
    private String player;
    private int systemGuess;

    /**
     * There are three possibilies - User guess greater than, lower than or equal to system guess
     *
     * @param -womGame -This is a boolean variable true if won
     *                 return boolean true if guess is correct else false
     */
    public boolean game(int guess, int guessCnt) {
        boolean wonGame = false;

        if (guess == systemGuess) {
            System.out.println("Good Job," + player + "! You guessed my number in " + guessCnt + " guesses!");
            wonGame = true;
        } else if (guess > systemGuess)
            System.out.println("Your guess is too high");
        else
            System.out.println("Your guess is too low");

        return wonGame;
    }

    public void guessAgain() {
        int cnt = 0;
        int guess;
        boolean play = true;
        System.out.println("\n\n\n\n\nWell, " + player + ", I am thinking of  a number between 1 and 20.");
        while (play) {
            if (cnt < 6) {
                System.out.println("\nTake a guess.\n\n");
                guess = guess();
                play = !game(guess, cnt);
                cnt++;
            }
            else
                play = false;
        }
    }

    public int guess() {
        String guess;
        int num;
        guess = scan.next();
        try {
            num = Integer.parseInt(guess);
            try {
                validGuess(num);
                return num;
            } catch (Exception e) {
                System.out.print(e);
                guess();
            }
        } catch (Exception e) {
            System.out.print("The guess has to be a number!!!!, Guess again : ");
            guess();
        }
        return 0;
    }

    public void validGuess(int guess) throws Exception {
        if (guess > 20 || guess < 0)
            throw new Exception("The number cannot be greater than 20 or less than 0. Guess again :");
    }

    /**
     * Launch the game and give the player option to continue and validate the option using a try catch block
     *
     * @param - option       -String the option choosen by the user to continue
     * @param - valid        -Boolean to continue the game
     */
    public void playGame() {
        String option;
        boolean valid = false;
        int randSeed = (int) Math.floor(Math.random() * 20);
        setSystemGuess(randSeed);
        welcomeMessage();
        guessAgain();
        while (!valid) {
            System.out.println("Would you like to continue \n Enter Yes or No");
            option = scan.next();
            try {
                option = getOption(option);
                if (option.contains("N") || option.contains("n"))
                    valid = true;
                else
                    guessAgain();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Validate the input and throw exception if not valid
     *
     * @param - option       -String input for choosing the caves yes or no
     * @return - the valid option
     */
    public String getOption(String option) throws Exception {
        if (!(option.contains("Y") || option.contains("N") || option.contains("y") || option.contains("n"))) {
            throw new Exception("\n\n\n\n\n\n\n\n\nThis is not a valid input, please retry ");
        } else
            return option;
    }

    /**
     * Welcome message and request player name.
     */
    public void welcomeMessage() {// Welcome Message
        System.out.println("Hello! What is your name? \n\n\n\n\n");
        player = scan.next();
    }

    /**
     * Setter for the system guess.
     */

    public void setSystemGuess(int randSeed) {
        systemGuess = randSeed;
    }

     public static void main(String args[]){
        GuessingGameTryCatch guessingGameTryCatch = new GuessingGameTryCatch();
        guessingGameTryCatch.playGame();
     }


}


