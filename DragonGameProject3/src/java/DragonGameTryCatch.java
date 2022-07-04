import java.util.Scanner;

public class DragonGameTryCatch {
    private boolean dragon1;
    private boolean dragon2;
    Scanner scan = new Scanner(System.in);

    /**
     * Generates the two dragons and randomly assigns one to be friendly and the other as hungry dragon
     *
     * @param - dragon1    -Boolean value for friendly dragon
     * @param - dragon2    -Boolean value for friendly dragon
     * @return - String    -friendly dragon
     */
    public String getFriendlyDragon(){
        dragon1 = Math.floor(Math.random()*10) > 5 ?false: true;
        dragon2 = !dragon1;
        return dragon1 ? "dragon1" : "dragon2";
    }

    /**
     * The actual game. The user has to choose between two caves 1 and 2, any other option is returned as invalid
     *
     * @param - cave     -Integer value for the cave choosen by the user
     */

    public void game(){
        String cave;
        System.out.print("\n\n\n\n\n\n\n\n\nYou are in a land full of DRAGONS. \n In front of you, you see two caves. \n In one cave the dragon is friendly. \n The other dragon is greedy and hungry and will eat you on sight. \n Which one will you go in to? 1/2 :- ");
        cave = "dragon"+getUserGuess();
        System.out.println("\n\n\n\n\n\n\n\n\nYou approach the cave...\nIt'd dark and spooky...\n A large dragon jumps in front of you!...");
        if(cave.equals(getFriendlyDragon()))
            System.out.println("\n With a big smile he shares his treasure with you");
        else
            System.out.println("\n A large dragon jumps in front of you! He opens his jaws and ...\nGobbles you down in one bite..");
    }

    /**
     * Launch the game and give the player option to continue and validate the option using a try catch block
     *
     * @param - option       -String the option choosen by the user to continue
     * @param - valid        -Boolean to continue the game
     */
    public void playGame(){
        String option;
        boolean valid = false;
        game();
        while(!valid){
            System.out.println("Would you like to continue \n Enter Yes or No");
            option = scan.next();
            try {
                option = getOption(option);
                if(option.contains("N") ||option.contains("n"))
                    valid = true;
                else
                    game();
            }
            catch (Exception e){
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
    public String getOption(String option)throws Exception{
        if(!(option.contains("Y") || option.contains("N") || option.contains("y") || option.contains("n"))) {
            throw new Exception("\n\n\n\n\n\n\n\n\nThis is not a valid input, please retry ");
        }
        else
            return option;
    }

    /**
     * Gets the user guess and validates it
     *
     * @param - cave         -String has to be 1 or 2
     * @param - option       -Integer value of the correct option 1 or 2
     * @param - valid        -Boolean to validate the input option
     * @return - Welcome message
     */
    public int getUserGuess(){
        String cave = "";
        int option = 0;
        boolean valid = false;
        while (!valid){
            cave = scan.next();
            try {
                option = Integer.parseInt(cave);
                if(option != 1 & option  != 2)
                    System.out.println("There is no cave "+ cave + " so please choose 1  or 2");
                else
                    valid = true;
            }
            catch(Exception e){
                System.out.println("The input has to be an integer 1 or 2. Please try again");
            }
        }
        return option;
    }
    /**
     * Launches the application calls the playGame method to start the game
     *
     *
     */
    public static void main(String ... args){
        DragonGameTryCatch dragonGame = new DragonGameTryCatch();
        dragonGame.playGame();
    }

}
