import java.util.HashMap;
import java.util.Set;

public class Land {

    private int sizeX, sizeY;
    private String grid;
    private int[][] gridMatrix;

    public Land(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void setGridSize(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int[][] getGridMatrix(){
        return gridMatrix;
    }

    public int[] getGridSize(){
        int[] size = {sizeX, sizeY};
        return size;
    }

    public void createGridMatrix(){
        int[] gridSize = getGridSize();
        this.gridMatrix = new int[gridSize[0]][gridSize[1]];
    }

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
        for (Human h : human) {// Go through the humans array and set the positions in the grid matrix using the cnt
            if(!h.isDead()) {//If the human is alive
                pos = h.getPosition();
                gridMatrix[pos[0]][pos[1]] = h.getiD();//Check the x value and make corections if we dont get the correct value
            }
        }
        for (Goblin g : goblin){
            if(!g.isDead()) {// If the goblin is alive
                pos = g.getPosition();
                gridMatrix[pos[0]][pos[1]] = g.getiD() + 200;
                ;//Check the y value and make corections if we dont get the correct value
            }
        }
    }

    /******
     * setGrid method sets the grid String with the String that will be printed with the Goblins and Humans
     *
     * @return returns a String that will print the grid with all the required information
     */
    public String setGrid(Goblin[] goblin, Human[] human){
        grid = "";
        for(int i = 1 ; i <= sizeX; i++)
            grid += "     "+i+"   ";
        grid += "\n";
        for(int i = 0 ; i < sizeX; i++)
            grid += "---------";
        grid += "\n";
        for(int i = 0 ; i < sizeY; i++){
            for(int j = 0; j < sizeX; j++) {
                if(gridMatrix[j][i] >= 1 & gridMatrix[j][i] <= 200){
                    if(gridMatrix[j][i] > 9)
                        grid += "|%"+gridMatrix[j][i]+" ";
                    else
                        grid += "|%"+gridMatrix[j][i]+"  ";
                    if(human[gridMatrix[j][i]-1].getHealth() > 99)
                        grid += human[gridMatrix[j][i]-1].getHealth() + " ";
                    else if(human[gridMatrix[j][i]-1].getHealth() > 9)
                        grid += human[gridMatrix[j][i]-1].getHealth() + "  ";
                    else
                        grid += human[gridMatrix[j][i]-1].getHealth() + "   ";
                }
                else if(gridMatrix[j][i] >= 201 & gridMatrix[j][i] <= 400){
                    if(gridMatrix[j][i] > 209)
                        grid += "|€"+(gridMatrix[j][i]-200)+" ";
                    else
                        grid += "|€"+(gridMatrix[j][i]-200)+"  ";
                    if(goblin[gridMatrix[j][i]-201].getHealth() > 99)
                        grid += goblin[gridMatrix[j][i]-201].getHealth()+ " ";
                    else if(goblin[gridMatrix[j][i]-201].getHealth() > 9)
                        grid += goblin[gridMatrix[j][i]-201].getHealth() + "  ";
                    else
                        grid += goblin[gridMatrix[j][i]-201].getHealth() + "   ";
                }
                else
                    grid += "|        ";
            }
            grid += "|"+ (i+1) +"\n";
            for(int j = 0; j < sizeX; j++) {
                grid += "---------";
            }
            grid += "\n";
        }
        return grid;
    }

    @Override
    public String toString(){
        return grid;
    }
}
