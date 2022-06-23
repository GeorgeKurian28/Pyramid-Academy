/**
 * Contains Game of Dragons. The playor has to find the friendly dragon or be eaten by the hungry dragon
 *
 * @author George Kurian
 */
import com.sun.jdi.IntegerValue;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;
public class DragonGame {
    /**
     * Launches the application
     *
     * @param args - Application startup arguments
     */
    Scanner scan = new Scanner(System.in);
    public static void main(String args[]){
        /**
         * Generates the two dragons and randomly assigns one to be friendly and the other as hungry dragon
         *
         * @param - rand_Int    -Random integer between 0 or 1
         * @param - friendlyDragon1 and friendlyDragon2  -The dragons in the two caves
         * @param - stop         -Boolean value to stop the game
         * @param - valid        -Boolean to validate the input option
         * @param - option       -String input for choosing the caves 1 or 2
         * @return - Welcome message
         */

        Random rand = new Random();
        int randInt;
        boolean friendlyDragon1 = true, friendlyDragon2 = true;//friendly
        boolean stop = false;
        boolean valid;
        Scanner scan = new Scanner(System.in);
        DragonGame game = new DragonGame();
        String option="";

        while(!stop){
            valid = false;
            randInt = rand.nextInt(2);
            if (randInt <1) {
                friendlyDragon1 = false;
                friendlyDragon2 = true;
            }
            else {
                friendlyDragon1 = true;
                friendlyDragon2 = false;
            }

            game.message1();
            while(!valid){
                option = scan.next();
                if(!(option.equals("1") || option.equals("2")))
                    System.out.print("\n\n\n\n\n\n\n\n\nThis is not a valid input, please retry : - ");
                else
                    valid = true;
            }

            if(option.equals("1")) {
                if (friendlyDragon1)
                    game.message3();
                else
                    game.message2();
            }
            else{
                if (friendlyDragon2)
                    game.message3();
                else
                    game.message2();
            }


            System.out.print("\n\n\n\n\n\n\n\n\nDo you want to continue (Y/N): - ");
            stop = game.continueGame();

        }


    }

    /**
     * Welcome Message
     *
     */
    public void message1(){
        System.out.print("\n\n\n\n\n\n\n\n\nYou are in a land full of DRAGONS. \n In front of you, you see two caves. \n In one cave the dragon is friendly. \n The other dragon is greedy and hungry and will eat you on sight. \n Which one will you go in to? 1/2 :- ");
    }

    /**
     * Statement if player meets an unfriendly hungry dragon
     *
     */
    public void message2(){
        System.out.println("\n\n\n\n\n\n\n\n\nYou approach the cave...\nIt'd dark and spooky...\n A large dragon jumps in front of you! He opens his jaws and ...\nGobbles you down in one bite..");
    }
    /**
     * Statement if player meets a friendly dragon
     *
     */
    public void message3(){
        System.out.println("\n\n\n\n\n\n\n\n\nYou approach the cave...\nIt'd dark and spooky...\n A large dragon jumps in front of you!...\n With a big smile he shares his treasure with you");
    }

    /**
     * Get a valid input from the player if they want to continue playing
     *
     * @return - Boolean value "true" if they don't want to continue and "false" if they want to continue
     */
    public boolean continueGame(){
        String option;
        Scanner scan = new Scanner(System.in);
        while(true){
            option = scan.next();
            if(!(option.equals("Y") || option.equals("N")))
                System.out.print("\n\n\n\n\n\n\n\n\nThis is not a valid input, please retry : - ");
            else{
                if(option.equals("Y"))
                    return false;
                else
                    return true;
            }
        }
    }



}


