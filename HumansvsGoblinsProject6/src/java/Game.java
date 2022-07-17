import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game {
    /******
     * The Game Class is where the game is played
     */
    Scanner scan = new Scanner(System.in);

    /**************
     * THe main method only calls the game method using an instance of the game class
     *
     * @param args
     */
    public static void main(String ...args){
       Game game = new Game();
        game.game();
    }

    /**************************
     * THe game method doesnot take any arguments and doesnot return any variables
     *
     * @param -humans                -The humans array stores all the human objects for the game in an array
     * @param -goblins               -The goblins array stores all the goblin objects for the game in an array
     * @param -positions             -The positions array stores all the random position in the gridmatrix
     * @param -numberOfCharacters    -The numberOfCharacters stores the max number of goblins and humans that can be entered for a given grid size
     * @param -inventoryHashMap      -The inventoryHashMap  is a Hash Map that stores the inventory objects that are randomly created on the grid
     * @param -dropsHashMap          -The dropsHashMap is a Hash Map that stores the drops objects that are randomly created on the grid
     * @param -treasureChestHashMap  -The treasurecHestHashMap is a Hash Map that stores the treasure chests that are added to the grid
     * @param -land                  -The land is the Land object that is used for this game
     */
    public void game(){

        Human[] humans;
        Goblin[] goblins;
        int[][] positions;
        int[] numberOfCharacters;
        HashMap<Integer,Inventory> inventoryHashMap;
        HashMap<Integer, Drops> dropsHashMap;
        HashMap<Integer,TreasureChest> treasureChestHashMap = new HashMap<>(); ;
        Land land;

        welcome();              //Call the Welcome method that will welcome user to the game
        land = chooseGridSize();//Call the method to choose the Grid Size to be used for the game
        land.createGridMatrix();//Call the method in land that creates teh Grid
        numberOfCharacters = getNumberOfCharacters(land.getGridSize());//Get number of humans and Goblins and pass that number to the number of Characters variable

        positions = getThePositionOfCharacters(numberOfCharacters,land.getGridSize());//Generate the positions of the characters;
        humans = getHumans(numberOfCharacters[0],positions);                          //Using the number from the numberOFCharacters choose the first set of positions and create the array of human objects and store it in the humans array
        goblins = getGoblines(numberOfCharacters,positions);                          //Using the number from the numberOFCharacters choose the first set of positions and create the array of goblin objects and store it in the goblins array
        inventoryHashMap = getInventory(land.getGridSize());                          //Using the grid size of the land create a HashMap if Inventory objects and store in inventoryHashMap
        dropsHashMap = getDrops(land.getGridSize());                                  //Using the grid size of the land create a HashMap if drops objects and store in dropsHashMap

        while( noOfHumans(humans)> 0 & noOfGoblins(goblins)>0){//The loop runs as long as we have humans and goblins
            land.setGridMatrix(goblins, humans);                //put the humans and goblins on the gridMatrix variable (2D int array) in the land class
            land.setGrid(goblins, humans,inventoryHashMap,dropsHashMap,treasureChestHashMap);//update the grid String variable with the humans, goblins, inventory,drops and treasture chest
            System.out.println(land);                           //print the land object which will print the grid
            message("The total number of points scored by humans  -"+humans[0].getPoints());//print the total number of points scored by humans
            humanPlayerTurn(humans, land, goblins,inventoryHashMap, treasureChestHashMap);//human turn to play
            goblinTurn(humans, goblins, land, dropsHashMap, treasureChestHashMap);//goblin Turn to play
        }

        if(noOfHumans(humans) > 0)//If only humans are left
            message("!!!!!!!  HUMANS WON  !!!!!!");
        else                      //If only goblins ae left
            message("€€€€€€€  GOBLINS won  €€€€€€€");
    }

    /******
     * goblinTurn takes the array of humans and array of goblins and the land (grid they are on) and based on the proximity the goblins chase the humans
     *
     * @return reutrns the hasmap of the treasure chest that comes out of the combat
     */
    public HashMap<Integer, TreasureChest> goblinTurn(Human[] humans,Goblin[] goblins, Land land, HashMap<Integer, Drops> dropsHashMap, HashMap<Integer, TreasureChest> treasureChestHashMap){
        for (Goblin goblin:goblins) {   //Run through all the goblins in the array
            if(!goblin.isDead())        //If goblins are not dead
             treasureChestHashMap = goblinMove(goblin.getPrey(humans, land),goblin,land, dropsHashMap, treasureChestHashMap);// call the Goblin move if the goblin is alive
        }
        return treasureChestHashMap;    //return the treasure chest map
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
     * @return the Hashmap of the treasure chest
     */
    public HashMap<Integer, TreasureChest> goblinMove(Human human, Goblin goblin, Land land, HashMap<Integer, Drops> dropsHashMap, HashMap<Integer, TreasureChest> treasureChestHashMap){

        int [] hPos = human.getPosition();
        int [] gPos = goblin.getPosition();
        int x = gPos[0] - hPos[0];
        int y = gPos[1] - hPos[1];
        Drops drops;

        drops = goblinLookForDrops(gPos, dropsHashMap);//Goblin looks for drops if it finds drops

        if(drops != null)//If there are drops in the cell
            goblin.takeDrops(drops.getNum());//goblin takes the drops

        //Check to see the direction of the human from the goblin
        if((x == 0 || y == 0) & (Math.abs(x) == 1 || Math.abs(y) == 1)) {            //If the goblins are either in the same column or the same row and human is on the column or row beside the goblin
            treasureChestHashMap = combat(human, goblin, land, treasureChestHashMap);// it either x or y is same call combat()
        }
        else if(x != 0 & ((Math.abs(x) > Math.abs(y)))){                             // if they are not same then change the x or the y value in that direction
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

        if(land.getGridMatrix()[gPos[0]][gPos[1]] == 0) {// Only if there is no character in the grid cell
            land.setGridMatrixValue(goblin.getPosition(),0);//Update the gridMatrix position to 0
            goblin.setPosition(gPos[0], gPos[1], land.getGridSize());//Update the postion of the goblin to this new position
            land.setGridMatrixValue(goblin.getPosition(),goblin.getiD()+200);//Update the gridMatrix position to 200+goblin Id
        }

        return treasureChestHashMap;//Return the chest map
    }

    /******
     * humanPLayerTurn takes the array of humans and array of goblins and the land (grid they are on) and gets an input from user to move (n/s/e/w)
     *
     * @param -input   -String that stores the user choice should be(n/s/e/w) or else player losses turn
     */
    public void humanPlayerTurn(Human[] human, Land land, Goblin[] goblin, HashMap<Integer, Inventory> inventoryHashMap, HashMap<Integer, TreasureChest> treasureChestHashMap){

        String input = "";
        boolean validInput = false;

        for(Human h:human){             // Loop through all the humans
            if(!h.isDead()){            // If the human is alove
                message("Player " + h.getiD() + " this is your turn to move choose (n/s/e/w)");//request player input for the human
                while (!validInput) {   // If the input is not valid
                    input = scan.next();// ask for anohter input
                    validInput = correctInput("String",input,"n","N","s","S","e","E","w","W");//validate the input
                }
                validInput = false;     //rest the boolean to galse
                treasureChestHashMap = humanMove(input,h,land, goblin, inventoryHashMap, treasureChestHashMap);//update the treasure chest after human move

                if(h.hasWeaponInHand()) //If the human has weapon in hand
                    message("Your Weapon"+h.getWeaponInHand());
                if(wantToUseWeapon(h)){ //If they want to change or use a weapon when they never had to start with
                    message("Enter the weapon name from the following list you have in stock");
                    message(h.getWeaponInStock().toString()); //Show all the weapons in stock
                    while(!validInput){  //If the input is not valid continue asking for an input
                        input = scan.next();
                        validInput = correctInput("String",input,h.getWeaponInStock().keySet().toArray(new String [0]));//validate the  input
                    }validInput = false;
                    h.useWeapon(input);  //If the input is correct use the weapon
                }
            }
        }
    }

    /******
     * wanttoUseWeapon asks if the human wants to use weapons
     *
     * @param -currPos   -int[] This stores the x and y position of human
     * @param -newPos    -int[] This stores the new x and y position of human
     */
    public boolean wantToUseWeapon(Human human){
        
        boolean decision = false;
        String choice = "";
        boolean validInput = false;


        if(human.hasWeaponInStock()){ // then ask if wants to use
            if(human.hasWeaponInHand()){
                message("Do you want to change your weapon (Y/N) ");
            }
            else {
                message("Do you want to use a weapon (Y/N) " + "");
            }
            while(!validInput){
                choice = scan.next();
                validInput = correctInput("String",choice,"y","Y","n","N");
            }
            if(!(choice.contains("y") || choice.contains("Y"))){
                decision = false;
            }
            else
                decision = true;
        }
        return decision;
    }

    /******
     * humanMove takes the correct input, the human player object, the terrain and the goblins array and it moves the
     * human to the box if there is no character in the box. If there is a Goblin the human attacks and the attack method
     * is called on the goblin.
     *
     * @param -currPos   -int[] This stores the x and y position of human
     * @param -newPos    -int[] This stores the new x and y position of human
     */
    public HashMap<Integer, TreasureChest> humanMove(String input, Human human, Land land , Goblin[] goblins, HashMap<Integer,Inventory> inventoryHashMap, HashMap<Integer, TreasureChest> treasureChestHashMap){

        int[] currPos = human.getPosition();
        int[] newPos = new int[2];
        newPos[0] = currPos[0];
        newPos[1] = currPos[1];
        Inventory inventory;
        TreasureChest treasureChest;

        //check if there is inventory in the box
        inventory = humanLookForInventory(newPos,inventoryHashMap);//Human looks for inventory in the cell
        if(inventory != null)
            human.takeInventory(inventory);//If he finds he takes

        //check if there is treasureChest in the box
        treasureChest = humanLookForTreasureChest(newPos,treasureChestHashMap);//Human looks for treasureChest in the cell
        if(treasureChest != null)
            human.setPoints(treasureChest.getPoints()+human.getPoints());//If he finds he takes

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
            if (land.getGridMatrix()[newPos[0]][newPos[1]] > 200) {
                treasureChestHashMap = combat(human, goblins[land.getGridMatrix()[newPos[0]][newPos[1]] - 201], land, treasureChestHashMap);//human.attackGoblin(goblins[land.getGridMatrix()[newPos[0]][newPos[1]] - 201]); The goblins health should change here
            }
            newPos = currPos;
        }


        //if it is same creature we cant kill, if it is different creature call attack command this will update the group
        land.setGridMatrixValue(human.getPosition(),0);//Sets the land Grid matrix to 0
        human.setPosition(newPos[0],newPos[1],land.getGridSize());//Sets the new position for human
        land.setGridMatrixValue(human.getPosition(),human.getiD());//Sets the Land Grid matrix to new position

        return treasureChestHashMap;
    }
    /*****************
     * humanLookforTreasureChest method takes the human postion and the treasureChest has map and returns null
     * if there is no treasureChest in the cell and if there is treasureChest in the cell it returns it
     *
     * @return It returns an treasureChest object, which is the treasureChest in the cell
     */
    public TreasureChest humanLookForTreasureChest(int[] humanPosition,HashMap<Integer, TreasureChest> treasureChestHashMap){
        TreasureChest treasureChest = null;
        int key = Integer.parseInt(humanPosition[0]+""+humanPosition[1]);
        if(treasureChestHashMap.containsKey(key)){
            treasureChest = treasureChestHashMap.get(key);
            treasureChestHashMap.remove(key);
        }
        return treasureChest;
    }

    /*****************
     * humanLookforInventory method takes the human postion and the inventory has map and returns null
     * if there is no inventory in the cell and if there is inventory in the cell it returns it
     *
     * @return It returns an Inventory object, which is the inventory in the cell
     */
    public Inventory humanLookForInventory(int[] humanPosition,HashMap<Integer, Inventory> inventoryHashMap){
        Inventory inventory = null;
        int key = Integer.parseInt(humanPosition[0]+""+humanPosition[1]);
        if(inventoryHashMap.containsKey(key)){
            inventory = inventoryHashMap.get(key);
            inventoryHashMap.remove(key);
        }
        return inventory;
    }

    /*****************
     * gobinLookforDrops method takes the goblin postion and the drops has map and returns null
     * if there is no drops in the cell and if there is drops in the cell it returns it
     *
     * @return It returns a Drop object, which is the drop in the cell
     */
    public Drops goblinLookForDrops(int[] goblinPosition,HashMap<Integer, Drops> dropsHashMap){
        Drops drop = null;
        int key = Integer.parseInt(goblinPosition[0]+""+goblinPosition[1]);
        if(dropsHashMap.containsKey(key)){
            drop = dropsHashMap.get(key);
            dropsHashMap.remove(key);
        }
        return drop;
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
     * getInventory method generates inventory for 10% of the grid cells and the positions are determined randomly
     *
     * @return returns the HashMap of the inventory where the Key is the XcoorinateYcoordinate and the Key is the Inventory object
     */
    public HashMap<Integer, Inventory> getInventory(int[] gridSize){
        HashMap<Integer, Inventory> inventoryMap =  new HashMap<>();
        int[] arr = Stream.generate(Math::random).mapToInt(i -> (int)(Math.floor(i*99))).filter(x ->(x >= 10 & x < gridSize[0]*10)).distinct().filter(i ->(i%10) < gridSize[1]).limit((long)(0.1*gridSize[0]*gridSize[1])).toArray();
        for(int i : arr){
            inventoryMap.put(i,new Inventory((i/10),(i%10)));
        }
        return inventoryMap;
    }

    /******
     * getDrops method generates Set of Drops for 10% of the grid cells and the positions are determined randomly
     *
     * @return returns the HashMap of the Drops where the Key is the XcoorinateYcoordinate and the Key is the Inventory object
     */
    public HashMap<Integer, Drops> getDrops(int[] gridSize){
        HashMap<Integer, Drops> dropsHashMap =  new HashMap<>();
        int[] arr = Stream.generate(Math::random).mapToInt(i -> (int)(Math.floor(i*99))).filter(x ->(x >= 10 & x < gridSize[0]*10)).distinct().filter(i ->(i%10) < gridSize[1]).limit((long)(0.1*gridSize[0]*gridSize[1])).toArray();
        for(int i : arr){
            dropsHashMap.put(i,new Drops((i/10),(i%10)));
        }
        return dropsHashMap;
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
    public HashMap<Integer, TreasureChest> combat(Human human, Goblin goblin, Land land,HashMap<Integer, TreasureChest> treasureChestHashMap ){
        boolean humanAttack = true;
        if(Math.random() < 0.5)
            humanAttack = false;
        if(humanAttack)
            goblin = human.attackGoblin(goblin);
        else
            human = goblin.attackHuman(human);
        if(human.getHealth() <=0 )
            land.setGridMatrixValue(human.getPosition(),0);
        if(goblin.getHealth() <=0 ) {
            land.setGridMatrixValue(goblin.getPosition(), 0);
        }
        treasureChestHashMap = generateTreasureChest(treasureChestHashMap, land.getGridSize());
        return treasureChestHashMap;
    }

    /***********
     * generateTreasureChest method, creates TreasureChest at a random location in the grid that is appended to a Hash Table
     *
     */
    public HashMap<Integer, TreasureChest> generateTreasureChest(HashMap<Integer, TreasureChest> treasureChestHashMap, int[] gridSize){
        int positionX, positionY, key;
        positionX = (int)(Math.floor(Math.random()*gridSize[0]));
        positionY = (int)(Math.floor(Math.random()*gridSize[1]));
        key = Integer.parseInt(positionX+""+positionY);
        if(treasureChestHashMap.containsKey(key))
            treasureChestHashMap.get(key).setPoints(treasureChestHashMap.get(key).getPoints()+100);
        else
            treasureChestHashMap.put(Integer.parseInt(positionX+""+positionY), new TreasureChest(100,positionX,positionY));
        return treasureChestHashMap;
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
    /***************
     * correctInput method takes the input type, the input criteria and the input and returns a boolean value if the
     * input is correct
     */
    public boolean correctInput(String inputType, String input, String ...criteria){
        String result = "";

        if(inputType == "int"){
            try{

                if(Integer.parseInt(input) >= Integer.parseInt(criteria[0]) && Integer.parseInt(input) <= Integer.parseInt(criteria[1])){
                    return true;
                }
                else{
                    System.out.println("This is outside the range "+ criteria[0] + criteria[1]);
                }
            }
            catch(Exception e){
                System.out.println("This is not a number. Please try again and enter a  number in the range "+criteria[0] +" ,"+ criteria[1]);
                return false;
            }
        }
        else if(inputType == "String"){
            for(String str: criteria){
                if(input.contains(str)){
                    return  true;
                }
            }
            System.out.print("This is incorrect choice, choose from ");
            for(String str: criteria){
                System.out.print(str + " / ");
            }
        }
        return false;
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
