
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class Labyrinth {
    
    static private char BLOCK_CHAR = 'X';
    static private char EMPTY_CHAR = '-';
    static private char MONSTER_CHAR = 'M';       
    static private char COMBAR_CHAR = 'C';
    static private char EXIT_CHAR = 'E';
    static private int ROW = 0;
    static private int COL = 1;
    
    private int nRows;
    private int nCols;
    private int exitRow;
    private int exitCol;
    
    //atributos de relación
    
    private Monster[][] monsters; //tamb se podría poner Monster mosters[][]
    private char[][] labyrinth;
    private Player[][] players;
    
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol){
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        
        this.labyrinth = new char [this.nRows][this.nCols];
        this.players = new Player [this.nRows][this.nCols];
        this.monsters = new Monster [this.nRows][this.nCols];
        
        for(int i = 0; i < this.nRows; i++){
            for(int j = 0 ; j < this.nCols; j++){
                
                this.labyrinth[i][j] = Labyrinth.EMPTY_CHAR;
                this.players[i][j] = null;
                this.monsters[i][j] = null;
                
            }
        }
        
        this.labyrinth[this.exitRow][this.exitCol] = Labyrinth.EXIT_CHAR;
    }
    
}
