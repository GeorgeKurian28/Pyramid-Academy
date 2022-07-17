import java.util.HashMap;
import java.util.Set;

public class Land {
    /******
     * The Land Object has these attributes and methods,
     *
     * @param -sizeX       -Integer value that stores the X value of the grid size
     * @param -sizeY       -Integer value that stores the Y value of the grid size
     * @param -grid        -String that stores the grid to be printed on the console
     * @param -gridMatrix  -2D Integer array that stores the ids of the Humans 200+ids of Goblins 0 if there is nothing
     * @param -HU          -String value containing UTF characters for the human face
     * @param -GB          -String value containing UTF characters for the goblin face
     * @param -INV         -String value containing UTF characters for the Inventory
     * @param -TD          -String value containing UTF characters for the Drops
     * @param -TC          -String value containing UTF characters for the Treasure Chest
     */
    private int sizeX, sizeY;
    private String grid;
    private int[][] gridMatrix;
    private final String HU = "\ud83d\udc68";//Human face
    private final String GB = "\ud83d\udc7e";//Goblin Face
    private final String INV ="\u2667";//Inventory
    private final String TD = "\u2693";//TearDrop
    private final String TC = "\u25fb";//Treasure Chest

    /*****
     * The two argument constructor for the land object that takes the X and Y grid size
     *
     */
    public Land(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /*****
     * getGridMatrix getter meothod returns the 2D array gridMatrix
     *
     */
    public int[][] getGridMatrix(){
        return gridMatrix;
    }

    /*****
     * getGridSize getter method returns an array with the X and Y values of the grid at the index 0 and 1 of the array
     *
     */
    public int[] getGridSize(){
        int[] size = {sizeX, sizeY};
        return size;
    }

    /*****
     * createGridMatrix creates the gridMatrix using the values of the grid size
     *
     */
    public void createGridMatrix(){
        int[] gridSize = getGridSize();
        this.gridMatrix = new int[gridSize[0]][gridSize[1]];
    }

    /*****
     * The setGridMatrixValue, changes the value of the gridMatrix at a the position given by the pos array using the value
     *
     */
    public void setGridMatrixValue(int[] pos, int value){
        gridMatrix[pos[0]][pos[1]] = value;
    }

    /******
     * setGridMatrix method will take the goblin and the human hashmap that contain the coordinate the keys will have to be divided by the sizeY and floored
     *
     * @param  - grdiSize         -int[]     Contains the X and Y values for the grid size
     * @param  - gkeys            -Set       Stores the keys for the goblin map
     * @param  - hkeys            -Set       Stores the keys for the human  map
     */
    public void setGridMatrix(Goblin[] goblin, Human[] human) {
        int[] pos;

        for (Human h : human) {                              // Iterate through the human array and set the positions in the grid matrix using the cnt
            pos = h.getPosition();                           //Get the position of the human
            if(!h.isDead())                                  //If the human is alive
                gridMatrix[pos[0]][pos[1]] = h.getiD();      //Enter the id in the matrix
            else                                             //If the human is dead
                gridMatrix[pos[0]][pos[1]] = 0;              //Enter 0 in the matrix

        }

        for (Goblin g : goblin) {                            // Iterate through the GOBLIN array and set the positions in the grid matrix using the cnt
            pos = g.getPosition();                           // Get the position of the goblin
            if (!g.isDead())                                 // If the goblin is alive
                gridMatrix[pos[0]][pos[1]] = g.getiD() + 200;//Set the value at the position = ID+200
            else                                             // If teh goblin is dead
                gridMatrix[pos[0]][pos[1]] =  0;             //Enter the value at position to 0
        }

    }

    /******
     * setGrid method sets the grid String with the String that will be printed with the Goblins and Humans
     *
     * @return returns a String that will print the grid with all the required information
     */
    public String setGrid(Goblin[] goblin, Human[] human, HashMap<Integer,Inventory> inventoryHashMap, HashMap<Integer, Drops> dropsHashMap, HashMap<Integer,TreasureChest> treasureChestHashMap){
        //First print the symbols and what they represent
        grid="     HUMANS - "+HU+ "      GOBLINS - "+GB+"        INVENTORY - "+INV+"        DROPS - "+TD+ "           TREASURE CHEST - "+TC+"\n\n" ;

        for(int i = 1 ; i <= sizeX; i++)            //Print the first line of the grid with the numbers for the cells in the row
            grid += "     "+i+"   ";
        grid += "\n";
        for(int i = 0 ; i < sizeX; i++)             //Print the horizontal line for the ceiling of the row
            grid += "---------";
        grid += "\n";
        for(int i = 0 ; i < sizeY; i++){            //print the characters in the cell
            grid += printFirstLine(i,goblin,human); //First line contains only the health of the characters
            grid += printSecondLine(i,goblin,human);//Second line contains only the symbol for the characters and their ID
            grid += printThirdLine(i,inventoryHashMap, dropsHashMap, treasureChestHashMap);//Third line contains the symbol for Treasure chest, Drops and Inventory

            for(int j = 0; j < sizeX; j++) {        //Print the floor line for the cells in this level
                grid += "---------";
            }
            grid += "\n";
        }

        return grid;
    }

    /******************
     * printFirstLine Method prints the first line of the cell that contains the health of the characters
     *
     * @param i         -The loop variable i for the row
     * @param goblin    -The array of goblin objects
     * @param human     -The array of human objects
     * @return this returns a string which has the health of the characters to be printed
     */
    public String printFirstLine(int i,Goblin[] goblin, Human[] human){

        String grid="" ;

        for(int j = 0; j < sizeX; j++) {                                    //Iterate through the loop
            if(gridMatrix[j][i] >= 1 & gridMatrix[j][i] <= 200){            // If this is a Human
                grid += "|   ";
                if(human[gridMatrix[j][i]-1].getHealth() > 99)              //If the health is triple digit
                    grid += human[gridMatrix[j][i]-1].getHealth() + "  ";   //Leave space accordingly
                else if(human[gridMatrix[j][i]-1].getHealth() > 9)          //If the health is double-digit
                    grid += human[gridMatrix[j][i]-1].getHealth() + "   ";  //Leave space accordingly
                else                                                        //IF the health is single digit
                    grid += human[gridMatrix[j][i]-1].getHealth() + "    "; //Leave space accordingly
            }
            else if(gridMatrix[j][i] >= 201 & gridMatrix[j][i] <= 400){     //If this is a Goblin
                grid += "|   ";
                if(goblin[gridMatrix[j][i]-201].getHealth() > 99)           //If the health is triple digit
                    grid += goblin[gridMatrix[j][i]-201].getHealth()+ "  "; //Leave space accordingly
                else if(goblin[gridMatrix[j][i]-201].getHealth() > 9)       //If the health is double-digit
                    grid += goblin[gridMatrix[j][i]-201].getHealth() + "   ";//Leave space accordingly
                else                                                         //if the health is single digit
                    grid += goblin[gridMatrix[j][i]-201].getHealth() + "    ";//Leave space accordingly
            }
            else
                grid += "|        ";
        }
        grid += "|"+ (i+1) +"\n";

        return grid;
    }

    /******************
     * printSecondLine prints the second line that contains the symbol of the character and the ID
     *
     * @param i         -The loop variable i for the row
     * @param goblin    -The array of goblin objects
     * @param human     -The array of human objects
     * @return this returns a string which has the health of the characters to be printed
     */
    public String printSecondLine(int i,Goblin[] goblin, Human[] human){

        String grid="";

        for(int j = 0; j < sizeX; j++) {                                // Iterate through the loop
            if(gridMatrix[j][i] >= 1 & gridMatrix[j][i] <= 200){        // If this is a Human
                if(gridMatrix[j][i] > 9)                                // If the ID is double-digit
                    grid += "   "+HU+gridMatrix[j][i];                  // Leave space accordingly
                else                                                    // If the ID is single-digit
                    grid += "    "+HU+gridMatrix[j][i];                 // Leave space accordingly
                grid += "   ";
            }
            else if(gridMatrix[j][i] >= 201 & gridMatrix[j][i] <= 400){ //If this is a Goblin
                if(gridMatrix[j][i] > 209)                              // If the ID is double-digit (ID + 200)
                    grid += "   "+GB+(gridMatrix[j][i]-200);            // Leave space accordingly
                else                                                    // If the ID is single-digit
                    grid += "    "+GB+(gridMatrix[j][i]-200);           // Leave space accordingly
                grid += "   ";
            }
            else                                                        // There are no characters in the cell
                grid += "         ";
        }
        grid += " \n";

        return grid;
    }

    /*******************
     * printThirdLine prints the third line that contains the symbols for the treasure chest, the inventory and the drops
     *
     * @param i                     -Integer value that contains the iteration number for the loop
     * @param inventoryHashMap      -HashMap of the Inventory
     * @param dropsHashMap          -HashMap of the drops
     * @param treasureChestHashMap  -HashMap of the treasureChest
     * @return a String that contains all the information to be printed
     */
    public String printThirdLine(int i,HashMap<Integer,Inventory> inventoryHashMap, HashMap<Integer, Drops> dropsHashMap, HashMap<Integer,TreasureChest> treasureChestHashMap){
        String grid="";
        for(int j = 0; j < sizeX; j++){                                      //Iterate through the loop

            if(dropsHashMap.containsKey(Integer.parseInt(j+""+i)))         //If there are drops in the cell, i.e. the drops hashmap contains the key that is the cell number
                grid += "|"+TD+"   ";
            else                                                              //If there is no drops in the cell
                grid += "|    ";

            if(treasureChestHashMap.containsKey(Integer.parseInt(j+""+i))) //If there is treasure chest in the cell, i.e. the drops hashmap contains the key that is the cell number
                grid += TC+"  ";
            else                                                              //If there is no treasure chest in the cell
                grid += "   ";

            if(inventoryHashMap.containsKey(Integer.parseInt(j+""+i)))     //If there is inventory in the cell, i.e. the drops hashmap contains the key that is the cell number
                grid += INV;
            else                                                              //If there is no inventory in the cell
                grid += " ";
        }
        grid += "|"+ (i+1) +"\n";

        return grid;
    }

    @Override
    public String toString(){
        return grid;
    }
}
