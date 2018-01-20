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
public class main {
    
    public static void main(String[] args){
        
        int [][] start = new int [10][10];
        start[2][3] = 1;
        start[2][4] = 1;
        start[2][5] = 1;
        start[3][5] = 1;
        start[4][5] = 1;
        start[4][4] = 1;
       
        
        Life board = new Life();
        
        //System.out.println(board);
        
        board.setPattern(start);
        
        
        for (int i = 0; i < 10; i++) {
            System.out.println(board);
        
        
            System.out.println();


            board.takeStep();
        
        }
        
    }
    
}
