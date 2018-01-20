/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shanjer_gol;

/**
 *
 * @author Minhal
 */
public class Life implements LifeInterface{

    public static int [][] grid;
    static int rows = 10;
    static int cols = 10;
    static int [][] nGrid;
    
    public Life(){
        grid = new int [rows][cols];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 1){
                    grid[rows - 1][j] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }  
        }
        
        for (int i = 1; i < rows-1; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1){
                    grid[i][cols-1] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }
        }
        
    }
    
    public Life(int x, int y){
        rows = x;
        cols = y;
        grid = new int [rows][cols];
        
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 1){
                    grid[rows - 1][j] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }  
        }
        
        for (int i = 1; i < rows-1; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1){
                    grid[i][cols-1] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }
        }
    }
    
    public void killAllCells(){
        grid = new int [rows][cols];
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 1){
                    grid[rows - 1][j] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }  
        }
        
        for (int i = 1; i < rows-1; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1){
                    grid[i][cols-1] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }
        }
    }
    
    /**
     * Loads a start pattern to the grid
     * @param startGrid a  int [][] loaded with 1's and 0's
     */
    public void setPattern(int [][] startGrid){
        grid = new int [startGrid.length][startGrid[0].length];
        for (int i = 0; i < startGrid.length; i++) {
            for (int j = 0; j < startGrid[0].length; j++) {
                grid[i][j] = startGrid[i][j];
            }
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 1){
                    grid[grid.length - 1][j] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }  
        }
        
        for (int i = 1; i < grid.length-1; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1){
                    grid[i][grid[0].length-1] = 3;
                }
                else{
                    grid[i][j] = 3;
                }
            }
        }
    }
    
    /**
     * Counts how many adjacent cells are alive
     * @param cellRow = row address of test cell
     *  0 < cellRow < gridSize - 1
     * @param cellCol = column address of test cell
     * 0 < cellCol < gridSize - 1
     * @return int count of adjacent live cells
     */
    public int countNeighbours(int cellRow, int cellCol){
        int n = 0;
        if (grid[cellRow][cellCol] != 3){
        
            if (grid[cellRow -1][cellCol -1] == 1){   //Top left
                n += 1;
            }
            if (grid[cellRow -1 ][cellCol] == 1){   //Cell Above
                n += 1;
            }
            if (grid[cellRow -1][cellCol +1] == 1){ // Top right
                n += 1;
            }
            if (grid[cellRow][cellCol - 1] == 1){  // Cell left
                n += 1;
            }
    //        if (grid[cellRow][cellCol] == 1){         Cell we are testing
    //            n += 1;
    //        }
            if (grid[cellRow][cellCol +1] == 1){  //Cell right
                n += 1;
            }
            if (grid[cellRow +1][cellCol -1] == 1){ //Down left
                n += 1;
            }
            if (grid[cellRow +1][cellCol] == 1){  //Cell Below
                n += 1;
            }
            if (grid[cellRow +1][cellCol + 1] == 1){ //Down right
                n += 1;
            }
        
        return n;
        }
        return -1;
    }
    
    /**
     * @param cellRow = row address of test cell     * 
     *  0 < cellRow < gridSize - 1
     * @param cellCol = column address of test cell
     * 0 < cellCol < gridSize - 1
     * @return int = state of cell,  1 for live, 0 for dead
     */
    public int applyRules(int cellRow, int cellCol) {

        int n = countNeighbours(cellRow, cellCol);
        if (grid[cellRow][cellCol] == 0) {
            if (n == 3) {
                return 1;
            } else if (n == -1) {
                return 3;
            } else {
                return 0;
            }
        } else if (grid[cellRow][cellCol] == 1) {
            if (n > 1 && n < 4) {
                return 1;
            } else if (n == -1) {
                return 3;
            } else {
                return 0;
            }
        } else {
            return 3;
        }

    }
    
    /**
     * Moves the game ahead one step by reading the
     * previous grid, applying the rules, and creating
     * a new grid.
     */
    public void takeStep(){
        nGrid = new int [grid.length][grid[0].length];
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                nGrid [i][j] = applyRules(i,j);
            }
        }
        
        grid = nGrid;
    }
    
    
    /**
     * Creates a string representation of the grid
     * @return String
     */
    @Override
    public String toString(){
        String g = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                g = g.concat(String.valueOf(grid[i][j]));
            }
            g = g.concat("\n");
        }
        return g;
    } 
}
