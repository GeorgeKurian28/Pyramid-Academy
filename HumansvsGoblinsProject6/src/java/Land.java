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
     */
    private int sizeX, sizeY;
    private String grid;
    private int[][] gridMatrix;

    private final String HU = "\ud83d\udc68";//Human face
    private final String GB = "\ud83d\udc7e";//Goblin Face
    private final String INV ="\u2667";//Inventory
    private final String TD = "\u2693";//"\ud83d\udd39"; //TearDrop

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
        for (Human h : human) {// Go through the humans arra----------
            // y and set the positions in the grid matrix using the cnt
            pos = h.getPosition();
            if(!h.isDead()) //If the human is alive
                gridMatrix[pos[0]][pos[1]] = h.getiD();//If the human is alive enter the id in the matrix
            else
                gridMatrix[pos[0]][pos[1]] = 0;//if the human is dead then enter 0 in the matrix

        }
        for (Goblin g : goblin) {// Go through the Goblin array and set the positions in the grid matrix using the cnt
            pos = g.getPosition();
            if (!g.isDead()) // If the goblin is alive
                gridMatrix[pos[0]][pos[1]] = g.getiD() + 200;//If the goblin is alive then set the value at the position = ID+200
            else
                gridMatrix[pos[0]][pos[1]] =  0;//If goblin is dead set the value at position to 0
        }

    }

    /******
     * setGrid method sets the grid String with the String that will be printed with the Goblins and Humans
     *
     * @return returns a String that will print the grid with all the required information
     */
    public String setGrid(Goblin[] goblin, Human[] human, HashMap<Integer,Inventory> inventoryHashMap, HashMap<Integer, Drops> dropsHashMap){
        grid="     HUMANS - "+HU+ "      GOBLINS - "+GB+"        INVENTORY - "+INV+"        DROPS - "+TD+ "\n\n" ;
        for(int i = 1 ; i <= sizeX; i++)
            grid += "     "+i+"   ";
        grid += "\n";
        for(int i = 0 ; i < sizeX; i++)
            grid += "---------";
        grid += "\n";
        for(int i = 0 ; i < sizeY; i++){
            //This section of the loop prints the type of Character (Human / Goblin), their serial number and health
            grid += printFirstLine(i,goblin,human);

            grid += printSecondLine(i,goblin,human);

            grid += printThirdLine(i,inventoryHashMap, dropsHashMap);

            //Print the floor line for the cells in this level
            for(int j = 0; j < sizeX; j++) {
                grid += "---------";
            }
            grid += "\n";

        }

        return grid;
    }

    public String printFirstLine(int i,Goblin[] goblin, Human[] human){
        String grid="" ;
        for(int j = 0; j < sizeX; j++) {
            if(gridMatrix[j][i] >= 1 & gridMatrix[j][i] <= 200){// If this is a Human
                grid += "|   ";
                if(human[gridMatrix[j][i]-1].getHealth() > 99)//If the health is triple digit
                    grid += human[gridMatrix[j][i]-1].getHealth() + "  ";
                else if(human[gridMatrix[j][i]-1].getHealth() > 9)//If the health is double-digit
                    grid += human[gridMatrix[j][i]-1].getHealth() + "   ";
                else
                    grid += human[gridMatrix[j][i]-1].getHealth() + "    ";//if the health is single digit
            }
            else if(gridMatrix[j][i] >= 201 & gridMatrix[j][i] <= 400){//If this is a Goblin
                grid += "|   ";
                if(goblin[gridMatrix[j][i]-201].getHealth() > 99)//If the health is triple digit
                    grid += goblin[gridMatrix[j][i]-201].getHealth()+ "  ";
                else if(goblin[gridMatrix[j][i]-201].getHealth() > 9)//If the health is double-digit
                    grid += goblin[gridMatrix[j][i]-201].getHealth() + "   ";
                else
                    grid += goblin[gridMatrix[j][i]-201].getHealth() + "    ";//if the health is single digit
            }
            else
                grid += "|        ";
        }
        grid += "|"+ (i+1) +"\n";
        return grid;
    }

    public String printSecondLine(int i,Goblin[] goblin, Human[] human){
        String grid="";
        for(int j = 0; j < sizeX; j++) {
            if(gridMatrix[j][i] >= 1 & gridMatrix[j][i] <= 200){// If this is a Human
                if(gridMatrix[j][i] > 9)
                    grid += "   "+HU+gridMatrix[j][i];
                else
                    grid += "    "+HU+gridMatrix[j][i];
                grid += "   ";
            }
            else if(gridMatrix[j][i] >= 201 & gridMatrix[j][i] <= 400){//If this is a Goblin
                if(gridMatrix[j][i] > 209)
                    grid += "   "+GB+(gridMatrix[j][i]-200);
                else
                    grid += "    "+GB+(gridMatrix[j][i]-200);
                grid += "   ";
            }
            else
                grid += "         ";
        }
        grid += " \n";
        return grid;
    }

    public String printThirdLine(int i,HashMap<Integer,Inventory> inventoryHashMap, HashMap<Integer, Drops> dropsHashMap){
        String grid="";
        for(int j = 0; j < sizeX; j++){//ADDITIONAL CELLS FOR INVENTORY AND DROPS
            if(dropsHashMap.containsKey(Integer.parseInt(j+""+i)))
                grid += "|"+TD+"      ";
            else
                grid += "|       ";
            if(inventoryHashMap.containsKey(Integer.parseInt(j+""+i)))
                grid += INV;
            else
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
