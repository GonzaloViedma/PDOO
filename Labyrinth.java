
package irrgarten;

import java.util.ArrayList;
import irrgarten.Dice;

public class Labyrinth {
    
    static private char BLOCK_CHAR = 'X';
    static private char EMPTY_CHAR = '-';
    static private char MONSTER_CHAR = 'M';       
    static private char COMBAT_CHAR = 'C';
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
    
    public boolean haveAWinner(){
        return this.players[this.exitRow][this.exitCol] != null;
    }
    
    public void spreadPlayers(ArrayList<Player> players){
        for (int i = 0; i<this.players.length; i++){
            players.get(i);
        }
    }
    
    public String toString(){
        String str;
        str = "";
        for(int i = 0; i<this.nRows; i++){
            for(int j = 0; j<this.nCols; j++ ){
                if(j == this.nCols -1 )
                    str += this.labyrinth[i][j] + "\n";
                else
                    str += this.labyrinth[i][j];
            }
        }
        return str;
    }
    
    public void addMonster(int row,int col, Monster monster){
        posOK(row, col);
        
        if(emptyPos(row,col)){
            this.labyrinth[row][col] = Labyrinth.MONSTER_CHAR;
            this.monsters[row][col] = monster;
            monster.setPos(row, col);
        }
        
    }
    
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        
        int newPos[] = this.dir2pos(oldRow, oldCol, direction);
        
        Monster monster = this.putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);
        
        return monster;
    }

    public void addBlock(Orientation orientation, int startRow, int startCol, int length){
        int incRow, incCol;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }
        int row = startRow;
        int col = startCol;
        
        while(this.posOK(row, col) && this.emptyPos(row, col) && length > 0){
            this.labyrinth[row][col] = BLOCK_CHAR;
            length--;
            row += incRow;
            col += incCol;
        }
    }
    
    public ArrayList<Directions> validMoves(int row,int col){
        ArrayList<Directions> output;
        
        output = new ArrayList<>();
        
        if(this.canStepOn(row+1, col))
            output.add(Directions.DOWN);
        if(this.canStepOn(row-1, col))
            output.add(Directions.UP);
        if(this.canStepOn(row, col+1))
            output.add(Directions.RIGHT);
        if(this.canStepOn(row, col-1))
            output.add(Directions.LEFT);
        
        return output;
    }
    
    private boolean posOK(int row, int col){
        return row < this.nRows && row >= 0 && col < this.nCols && col >= 0;
    }
    
    private boolean emptyPos(int row, int col){
        return this.labyrinth[row][col] == Labyrinth.EMPTY_CHAR;
    }
    
    private boolean monsterPos(int row, int col){
        return this.labyrinth[row][col] == Labyrinth.MONSTER_CHAR;
    }
    
    private boolean exitPos(int row, int col){
        return this.labyrinth[row][col] == Labyrinth.EXIT_CHAR;
    }
    
    private boolean combatPos(int row, int col){
        return this.labyrinth[row][col] == Labyrinth.COMBAT_CHAR && this.players[row][col]!= null;
    }
    
    private boolean canStepOn(int row, int col){
        return (this.posOK(row,col) && this.emptyPos(row, col) && this.monsterPos(row, col) && this.exitPos(row, col));
    }
    
    private void updateOldPos(int row, int col){
        if(this.posOK(row, col)){
            if(this.combatPos(row,col)){
                this.labyrinth[row][col] = Labyrinth.MONSTER_CHAR;
            }else{
                this.labyrinth[row][col] = Labyrinth.EMPTY_CHAR;
            }
        }
    }
    
    public int[] dir2pos(int row, int col, Directions direction){
        
        int aux[]={row,col};
        if(direction == Directions.DOWN)
            aux[0]=row+1;
        if(direction == Directions.UP)
            aux[0]=row-1;
        if(direction == Directions.LEFT)
            aux[1]=col-1;
        if(direction == Directions.RIGHT)
            aux[1]=col+1;
        
        return aux;
    }
    
    private int [] randomEmptyPos(){
        int col = Dice.randomPos(this.nCols);
        int row = Dice.randomPos(this.nRows);
        
        while(!this.emptyPos(row, col)){
            
            col = Dice.randomPos(this.nCols);
            row = Dice.randomPos(this.nRows);
        }
        
        int [] pos = {row, col};
        return pos;
    }
    
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        
        Monster output = null;
        
        if(this.canStepOn(row, col)){
            if(this.posOK(oldRow, oldCol)){
                Player p = this.players[oldRow][oldCol];
                if(p == player){
                    this.updateOldPos(oldRow, oldCol);
                    this.players[oldRow][oldCol]=null;
                }
            }
            
            boolean monsterPos = this.monsterPos(row, col);
            if(monsterPos){
                this.labyrinth[row][col] = COMBAT_CHAR;
                output = monsters[row][col];
            }else{
                char number = player.getNumber();
                this.labyrinth[row][col] = number;
            }
            
            this.players[row][col] = player;
            player.setPos(row, col);
        }
        
        return output;
    }
}
