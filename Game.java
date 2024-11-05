package irrgarten;

import java.util.ArrayList;
import irrgarten.Dice;

public class Game {
    
    static private int MAX_ROUNDS = 10;
    
    private int currentPlayerIndex;
    private String log ="";
    
    private Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;
    
    private int nRows = 3;
    private int nCols = 6;
    private int exitRow = 2;
    private int exitCol = 5;
    private int rounds = 0;
    
    public Game(int nplayers){
   
        for(int i=0; i<nplayers; i++){
            char c = (char)(i + '0');
            
            Player p = new Player(c,Dice.randomIntelligence(),Dice.randomStrenght());
            players.set(i, p);
            
        }
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.log = this.players.get(this.currentPlayerIndex).getNumber() + " ";
        this.labyrinth = new Labyrinth (this.nRows, this.nCols, this.exitRow, this.exitCol);
        this.labyrinth.spreadPlayers(players);
        
    }
    
    public boolean finished(){
        return this.labyrinth.haveAWinner();
    }
    
    public boolean nexStep(Directions preferredDirection){
        this.log = "";
        boolean dead = this.currentPlayer.dead();
        
        if(!dead){
            Directions direction = this.actualDirection(preferredDirection);
            
            if(direction != preferredDirection)
                this.logPlayerNoOrders();
            Monster monster = this.labyrinth.putPLayer(direction, this.currentPlayer);
            if(monster == null){
                this.logNoMonster();
            }else{
                GameCharacter winner = this.combat(monster);
                this.manageReward(winner);
            }
        }else{
            this.manageResurrection();
        }
        boolean endGame = this.finished();
        
        if(!endGame)
            this.nextPlayer();
        
        return endGame;
    }
    
    public GameState getGameState(){
        GameState g = new GameState(this.labyrinth.toString(), this.players.toString(), this.monsters.toString(), this.currentPlayer.getNumber(), this.finished(), this.log);
        return g;
    }
    
    private void configureLabyrinth(){
        
    }
    
    private void nextPlayer(){
        if(this.currentPlayerIndex == this.players.size() -1)
            this.currentPlayerIndex = 0;
        else
            this.currentPlayerIndex ++;
        this.log = this.players.get(this.currentPlayerIndex).getNumber() + " " ;
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = this.currentPlayer.getRow(); 
        int currentCol = this.currentPlayer.getCol();
        ArrayList<Directions> validMoves = this.labyrinth.validMoves(currentRow, currentCol);
        
        return this.currentPlayer.move(preferredDirection, validMoves);
    }
    
    private GameCharacter combat(Monster monster){
        this.rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = this.currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        
        while(!lose && this.rounds < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            this.rounds++;
            float monsterAttack = monster.attack();
            lose = this.currentPlayer.defend(monsterAttack);
            if(!lose){
                playerAttack = this.currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        this.logRounds(rounds, MAX_ROUNDS);
        return winner;
    }
    
    private void manageReward(GameCharacter winner){
        
        if(winner == GameCharacter.PLAYER){
            this.currentPlayer.recivedReward();
            this.logPlayerWon();
        }else{
            this.logMonsterWon();
        }
    }
    
    private void manageResurrection(){
        
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            this.currentPlayer.resurrect();
            this.logResurrected();
        }else{
            this.logPlayerSkipTurn();
        }
    }
    
    private void logPlayerWon(){
        this.log = this.log + " Ha ganado el combate. \n";
    }
    
    private void logMonsterWon(){
        this.log = this.log + ". El monstruo ganado el combate. \n";
    }
    
    private void logResurrected(){
        this.log = this.log + " Ha resucitado. \n";
    }
    
    private void logPlayerSkipTurn(){
        this.log = this.log + " Ha perdido el turno. Esta muerto \n";
    }
    
    private void logPlayerNoOrders(){
        this.log = this.log + " No ha seguido las instrucciones. \n";
    }
    
    private void logNoMonster(){
        this.log = this.log + " Se ha movido a una celda vacia o no se ha podido mover. \n";
    }
    
    private void logRounds(int rounds,int max){
        this.log = this.log + " Se han jugado " + rounds +" rondas de un máximo de " + max + " rondas.\n";    
    }
    
}

