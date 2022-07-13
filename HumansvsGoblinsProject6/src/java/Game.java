import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game {
    Scanner scan = new Scanner(System.in);

    public static void main(String ...args){
        Game game = new Game();
        game.game();
    }
    public void game(){
        Human[] humans;
        Goblin[] goblins;
        int[][] positions;
        int[] numberOfCharacters;
        Land land;
        welcome();
        land = chooseGridSize();
        land.createGridMatrix();
        numberOfCharacters = getNumberOfCharacters(land.getGridSize());//Get number of humans and Goblins and pass that number to
        positions = getThePositionOfCharacters(numberOfCharacters,land.getGridSize());//Generate the positions of the characters;

        //Get Humans and get Goblins with the array of positions as parameters
        humans = getHumans(numberOfCharacters[0],positions);
        goblins = getGoblines(numberOfCharacters,positions);
        while( noOfHumans(humans)> 0 & noOfGoblins(goblins)>0){
            land.setGridMatrix(goblins, humans);
            land.setGrid(goblins, humans);
            System.out.println(land);
            humanPlayerTurn(humans, land, goblins);//human turn
            //land.setGridMatrix(goblins, humans);//update all the new positions to the gridmatrix//IT IS ALREADY UPDATED IN THE HUMANS SIDE
            goblinTurn(humans, goblins, land);//goblin move(human position)
            //attack if close by
        }
        if(noOfHumans(humans) > 0)
            message("!!!!!!!  HUMANS WON  !!!!!!");
        else
            message("€€€€€€€  GOBLINS won  €€€€€€€");
    }

    /******
     * goblinTurn takes the array of humans and array of goblins and the land (grid they are on) and based on the proximity the goblins chase the humans
     *
     *
     */
    public void goblinTurn(Human[] humans,Goblin[] goblins, Land land){
        for (Goblin goblin:goblins) {
             goblinMove(goblin.getPrey(humans, land),goblin,land);
        }
    }

    /******
     * goblinMove method takes the human, goblin and the land. It checks if they are next to other
     * if they are next to each other then the combat is intiated by calling the combat method.
     * Else the postions are updated in the direction of the human prey
     *
     * @param -hPos   -int[] This Integer array stores the postion of the human
     * @param -gPos   -int[] This Integer array stores the postion of the goblin
     * @param -x      -int stores the difference between the x coordinates of the goblin and human
     * @param -y      -int stores the difference between the y coordinates of the goblin and human
     */
    public void goblinMove(Human human, Goblin goblin, Land land){

        int [] hPos = human.getPosition();
        int [] gPos = goblin.getPosition();
        int x = gPos[0] - hPos[0];
        int y = gPos[1] - hPos[1];

        //Check to see the direction of the human from the goblin
        if((x == 0 || y == 0) & (Math.abs(x) == 1 || Math.abs(y) == 1))       //We do that by comapring the x and y cooridnates
            combat(human,goblin,land);// it either x or y is same call combat()
        else if(x != 0 & ((Math.abs(x) > Math.abs(y)))){ // if they are not same then change the x or the y value in that direction
            if( x < 0 )
                gPos[0] += 1;
            else
                gPos[0] -= 1;
        }
        else{
            if( y < 0 )
                gPos[1] += 1;
            else
                gPos[1] -= 1;
        }

        if(land.getGridMatrix()[gPos[0]][gPos[1]] == 0) {
            land.setGridMatrixValue(goblin.getPosition(),0);
            goblin.setPosition(gPos[0], gPos[1], land.getGridSize());
            //System.out.println(goblin);TO BE REMOVED
        }
    }

    /******
     * humanPLayerTurn takes the array of humans and array of goblins and the land (grid they are on) and gets an input from user to move (n/s/e/w)
     *
     * @param -input   -String that stores the user choice should be(n/s/e/w) or else player losses turn
     */
    public void humanPlayerTurn(Human[] human, Land land, Goblin[] goblin){
        String input;
        for(Human h:human){
            if(!h.isDead()){
                message("Player "+h.getiD()+" this is your turn to move choose (n/s/e/w)");
                input = scan.next();
                if(input.contains("N")|input.contains("n")|input.contains("S")|input.contains("s")|input.contains("E")|input.contains("e")|input.contains("W")|input.contains("w")){
                    humanMove(input,h,land, goblin);
                }
                else
                    message("This is not the correct input you loose your chance better luck next time !!!!");
            }
        }
    }

    /******
     * humanMove takes the correct input, the human player object, the terrain and the goblins array and it moves the
     * human to the box if there is no character in the box. If there is a Goblin the human attacks and the attack method
     * is called on the goblin.
     *
     * @param -currPos   -int[] This stores the x and y position of human
     * @param -newPos    -int[] This stores the new x and y position of human
     */
    public void humanMove(String input, Human human, Land land , Goblin[] goblins){
        int[] currPos = human.getPosition();
        int[] newPos = new int[2];
        newPos[0] = currPos[0];
        newPos[1] = currPos[1];
        //find the new postion
        if(input.contains("N")| input.contains("n"))
            newPos[1] = currPos[1] - 1;
        else if (input.contains("S")| input.contains("s"))
            newPos[1] = currPos[1] + 1;
        else if (input.contains("E")| input.contains("e"))
            newPos[0] = currPos[0] + 1;
        else if (input.contains("W")| input.contains("w"))
            newPos[0] = currPos[0] - 1;

        //check if the new postion is within the grid
        if(newPos[0] < 0 | newPos[0] >= land.getGridSize()[0])
             newPos = currPos;
        if(newPos[1] < 0 | newPos[1] >= land.getGridSize()[1] )
             newPos = currPos;
        //check if there is something in the box
        if( land.getGridMatrix()[newPos[0]][newPos[1]] > 0) {
            if (land.getGridMatrix()[newPos[0]][newPos[1]] > 200)
                combat(human,goblins[land.getGridMatrix()[newPos[0]][newPos[1]] - 201],land);//human.attackGoblin(goblins[land.getGridMatrix()[newPos[0]][newPos[1]] - 201]); The goblins health should change here
            newPos = currPos;
        }
        //if it is same creature we cant kill, if it is different creature call attack command this will update the group
        land.setGridMatrixValue(human.getPosition(),0);
        human.setPosition(newPos[0],newPos[1],land.getGridSize());
        land.setGridMatrixValue(human.getPosition(),human.getiD());
    }

    /******
     * GetNumberOfCharacters get the input from the user for the number of humans an gobins and returns an array with numbers
     *
     * @param -hval            -integer that stores the max number of humans possible
     * @param -gval            -integer that stores the max number of goblins possible
     * @param -numOfCharacters -integer array that stores the max number of humans and goblins chosen by the user
     * @return returns the array of the number of characters
     */
    public int[] getNumberOfCharacters(int[] gridSize){

        int[] numOfCharacters = new int[2];
        int hVal, gVal;
        hVal = (int)((gridSize[0]*gridSize[1])*0.3);
        message("Enter the number of humans between 1 and " + hVal);
        numOfCharacters[0] = getNumber(hVal);
        gVal = (int)((gridSize[0]*gridSize[1])*0.3);
        message("Enter the number of goblins between 1 and " + gVal);
        numOfCharacters[1] = getNumber(gVal);

        return numOfCharacters;
    }

    /******
     * GetNumberOfCharacters get the input from the user for the number of humans an gobins and returns an array with numbers
     *
     * @param -hval            -integer that stores the max number of humans possible
     * @param -gval            -integer that stores the max number of goblins possible
     * @param -numOfCharacters -integer array that stores the max number of humans and goblins chosen by the user
     * @return returns the array of the number of characters
     */
    public int[][] getThePositionOfCharacters(int[] numOfCharacters, int[] gridSize){

        int totalCharacters = numOfCharacters[0]+numOfCharacters[1];
        int[][] positions = new int[totalCharacters][2];
        int[] rawPos = Stream.generate(Math::random).mapToInt(x -> (int)Math.floor(x*100)).filter(x ->(x >= 10 & x < gridSize[0]*10)).filter(x->(x%10) < gridSize[1]).distinct().limit(totalCharacters).toArray();

        for(int i = 0; i < totalCharacters; i++){
            positions[i][0] = (int)Math.floor(rawPos[i]/10);
            positions[i][1] = rawPos[i]%10;
        }

        return positions;
    }

    /******
     * GetHumans creates an array of humans using the available grid spaces
     *
     * @param -hval -integer that stores the max number of humans possible
     */
    public Human[] getHumans(int hVal, int[][] position){

        Human[] human = new Human[hVal];

        for(int i = 0; i < hVal; i++)
            human[i] = new Human(position[i][0],position[i][1]);

        return human;
    }
    /******
     * Combat takes in the human and goblin and uses the math.random to decide who is the attacker
     *
     */
    public void combat(Human human, Goblin goblin, Land land){
        boolean humanAttack = true;
        if(Math.random() > 0.5)
            humanAttack = false;
        if(humanAttack)
            goblin = human.attackGoblin(goblin);
        else
            human = goblin.attackHuman(human);
        if(human.getHealth() <=0 )
            land.setGridMatrixValue(human.getPosition(),0);
        if(goblin.getHealth() <=0 )
            land.setGridMatrixValue(goblin.getPosition(),0);
    }

    /******
     * GetHumans creates an array of humans using the available grid spaces
     *
     * @param -hval -integer that stores the max number of humans possible
     */
    public Goblin[] getGoblines(int[] numOfCharacters, int[][] position){

        Goblin[] goblins = new Goblin[numOfCharacters[1]];

        for(int i = 0; i < numOfCharacters[1]; i++)
            goblins[i] = new Goblin(position[i+numOfCharacters[0]][0],position[i+numOfCharacters[0]][1]);

        return goblins;
    }


    /******
     * Welcome message for the game
     *
     *
     */
    public void welcome(){
        message("**********************************************************");
        message("*                                                        *");
        message("*                  W E L C O M E                         *");
        message("*                                                        *");
        message("*                       T  O                             *");
        message("*                                                        *");
        message("*                    G O B L I N                         *");
        message("*                       V  S                             *");
        message("*                    H U M A N S                         *");
        message("*                                                        *");
        message("*                                                        *");
        message("**********************************************************");
    }

    /******
     * Set spacing for new page
     *
     *
     */
    public void newPage(){
        message("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    /******
     * chooseGridSize method asks the uer to set the gird size between 1 and 20.
     *
     * @param  - x        -int     Contains the X value for the grid size
     * @param  - y        -int     Contains the Y value for the grid size
     * @param  - land     -Land    Constricutor called to create new land with the grid size
     * @return returns the new land object that would be used for the game
     */
    public Land chooseGridSize(){
        int  x, y;
        Land land;

      //  newPage();
        message("**********************************************************");
        message("*                                                        *");
        message("*      C H O O S E   T H E   G R I D   S I Z E           *");
        message("*                                                        *");
        message("**********************************************************");
        message("\n\n\n                Choose x between 1 and 10:          ");
        x = getNumber(10);
        message("                Choose y between 1 and 10:                ");
        y = getNumber(10);
        land = new Land(x,y);

        return land;
    }

    /******
     * Getsize method gets the x and the y size an interger between 1 and  20
     *
     * @param  - input      -String     Takes the input for the value
     * @param  - val        -int        Contains the integer value for the grid size
     * @return returns the integer value between 1 and 20
     */
    public int getNumber(int limit){
        String input;
        int val;
        while(true) {
            input = scan.next();
            try {
                val = Integer.parseInt(input);
                if (1 <= val & val <= limit)
                    return val;
                else
                    message("The value has to between 1 and " + limit);
                message("Please Try again ");
            } catch (Exception e) {
                message("This is not a valid input it has to an integer between 1 and  " + limit);
                message("Please Try again ");
            }
        }
    }

    /******
     * noOfHumans method counts the number of humans alive
     *
     * @param -count -integer varaiable that stores the no of living humans
     */
    public int noOfHumans(Human[] humans){
        int count = 0;
        for(Human human:humans){
            if(!human.isDead())
                count++;
        }
        return count;
    }

    /******
     * noOfHumans method counts the number of humans alive
     *
     * @param -count -integer varaiable that stores the no of living humans
     */
    public int noOfGoblins(Goblin[] goblins){
        int count = 0;
        for(Goblin goblin: goblins){
            if(!goblin.isDead())
                count++;
        }
        return count;
    }

    /******
     * Message method prints all the messages that would be used, using a switch case
     *
     */
    public void message(String ...msg){
        String print;
        switch (msg[0]){
            default:
                print = msg[0];
                break;
        }
        System.out.println(print);
    }
}
