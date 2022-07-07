import java.util.Scanner;

public class HangMan {
    /*** The class variables
     *
     * @author          George Kurian
     * @param word             -String this stores the word that has to be guessed
     * @param lettersEntered   -String this stores the letters that have already been guessed by the user
     * @param progress         -String this stores the guessing progress by the player
     * @param cnt              -int this stores the progress level of the hangman
     * @param hangManLevel     -String array this stores the image for each stage of the hangman
     */
    Scanner scan = new Scanner(System.in);
    private String word;
    private String lettersEntered="";
    private String progress="";
    private String missedLetters ="";
    private int cnt = 0;
    private String[]  hangManLevel = {
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

    /**
     * The actual game. The user has to choose between two caves 1 and 2, any other option is returned as invalid
     *
     * @param - continueGame     -Boolean decides if to continue the game
     */
    public void game(){
        boolean continueGame = true;
        while(continueGame) {
            if (cnt > 6) {      //This condition checks if you have been hanged and breaks the loop
                System.out.println("You Lost !!!!");
                continueGame = false;
            }
            else if (progress.equals(word)) {       //This condition checks if you have won and breaks the loop
                System.out.println("Yes the secret word is \"" + word + "\"! You have won");
                continueGame = false;;
            }
            else {                                  //If none of the conditions are satisfied then the game continues
                System.out.println(hangManLevel[cnt]);
                System.out.println("Missed Letters: " + missedLetters);
                System.out.println(progress);
                updateMissingWord(guessLetter());
                continueGame = true;
            }
        }
    }

    /**
     * Guess the letter function returns the letter if all the conditions are satisfied by using the ValidateScan function
     *
     * @param - letter          -String stores the letter entered
     * @param - validScan       -Boolean false if the letter entered is incorrect
     */

    public String guessLetter() {
        String letter = "";
        boolean validScan = false;
        while(!validScan){                                 //If the letter entered doesn't match the conditions request another letter
            System.out.println("\nGuess a letter");
            validScan= validateScan(letter = scan.next()); //Call the validateScan function to validate the letter entered
        }
        return letter;
    }

    /**
     * ValidateScan function that validates the letter enetered
     *
     */
    public boolean validateScan(String letter){

        if (letter.length() != 1)                                     //If more than one character were entered
            System.out.println("You can only guess one letter !!!!");
        else if(Character.isLetter(letter.toCharArray()[0]))          //If the character is aa alphabet
            return true;
        else                                                          //If the character is not an alphabet (interger or special character)
            System.out.println("You have to guess a letter digits and special charectors are not allowed!!!");

        return false;
    }

    /**
     * Update the missingWord, the lettersEnetered, the progress using setProgress function and the cnt (counter) variable
     *
     * @param - continueGame     -Boolean decides if to continue the game
     */
    public void updateMissingWord(String letter){
        if(word.contains(letter)) {
            if (lettersEntered.contains(letter))
                System.out.println("You already guessed the letter choose again ");
            else {
                lettersEntered += letter;
                setProgress(letter.toCharArray()[0]);
            }
        }
        else {
            lettersEntered += letter;
            missedLetters += letter;
            cnt++;
        }
    }

    public void setWord(){
        String wordSet[] = {"Apple","Mango","Letters","Sleep","fly","cat","dog"};
        word = wordSet[(int)Math.floor(Math.random()*(wordSet.length-1))];
    }

    public void setProgress(char c){
        for(int i = 0; i <word.length(); i++) {
            if(word.charAt(i)==c)
                progress = progress.substring(0,i)+c+progress.substring(i+1,word.length());
            else if(lettersEntered.length() == 0)
                progress += "_";
        }
    }

    public void playGame(){
        setWord();
        setProgress(' ');
        game();
    }

    public static void main(String ...args){
        HangMan hangMan = new HangMan();
        hangMan.playGame();
    }

}













