/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * @author gonzaalovd & carlitros_gamer20

 */
package irrgarten;

import java.util.ArrayList;

public class Game {

    static private final int MAX_ROUNDS = 10;

    private int currentPlayerIndex;
    private String log ="";

    private Player currentPlayer;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth labyrinth;

    private final int nRows = 8;
    private final int nCols = 12;
    private final int exitRow = 6;
    private final int exitCol = 11;

    public Game(int nplayers){
        
        this.players = new ArrayList<>();
        this.monsters = new ArrayList<>();
        
        for(int i=0; i<nplayers; i++){
            char c = (char)(i + '0');

            Player p = new Player(c,Dice.randomIntelligence(),Dice.randomStrength());
            this.players.add(p);
        }
        
        this.monsters.add(new Monster("Creeper", Dice.randomIntelligence(), Dice.randomStrength()));
        this.monsters.add(new Monster("Enderman", Dice.randomIntelligence(), Dice.randomStrength()));
        this.monsters.add(new Monster("Zombie", Dice.randomIntelligence(), Dice.randomStrength()));
        this.monsters.add(new Monster("Arquero", Dice.randomIntelligence(), Dice.randomStrength()));

        
        this.currentPlayerIndex = Dice.whoStarts(nplayers);
        this.log = this.currentPlayer.getNumber() + " ";
        this.labyrinth = new Labyrinth (this.nRows, this.nCols, this.exitRow, this.exitCol);
        this.configureLabyrinth();
        this.labyrinth.spreadPlayers(players);
        this.currentPlayer = this.players.get(this.currentPlayerIndex);


    }
    
    //BORRAR LUEGO
    public String showLab(){
        return this.labyrinth.toString();
    }

    public boolean finished(){
        return this.labyrinth.haveAWinner();
    }

    public boolean nextStep(Directions preferredDirection){
        this.log = "";
        boolean dead = this.currentPlayer.dead();

        if(!dead){
            Directions direction = this.actualDirection(preferredDirection);

            if(direction != preferredDirection)
                this.logPlayerNoOrders();
            
            Monster monster = this.labyrinth.putPlayer(direction, this.currentPlayer);
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
        this.labyrinth.addMonster(1, 2, this.monsters.get(0));
        this.labyrinth.addMonster(1, 8, this.monsters.get(1));
        this.labyrinth.addMonster(4, 5, this.monsters.get(2));
        this.labyrinth.addMonster(4, 9, this.monsters.get(3));
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 0, 1, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 0, 4, 3);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 0, 11, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 1, 4, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 1, 9, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 2, 0, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 2, 3, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 2, 6, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 2, 9, 3);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 3, 1, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 3, 3, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 3, 6, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 4, 8, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 4, 10, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 5, 0, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 5, 3, 4);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 5, 10, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 6, 1, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 6, 3, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 6, 8, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 7, 3, 2);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 7, 6, 1);
        this.labyrinth.addBlock(Orientation.HORIZONTAL, 7, 8, 4);

    }

    private void nextPlayer(){
        if(this.currentPlayerIndex == this.players.size() -1)
            this.currentPlayerIndex = 0;
        else
            this.currentPlayerIndex ++;
        this.log = this.currentPlayer.getNumber() + " " ;
    }

    private Directions actualDirection(Directions preferredDirection){
        int currentRow = this.currentPlayer.getRow(); 
        int currentCol = this.currentPlayer.getCol();
        ArrayList<Directions> validMoves = this.labyrinth.validMoves(currentRow, currentCol);

        return this.currentPlayer.move(preferredDirection, validMoves);
    }

    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = this.currentPlayer.attack();
        boolean lose = monster.defend((float)playerAttack);

        while(!lose && rounds < MAX_ROUNDS){
            winner = GameCharacter.MONSTER;
            rounds++;
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
            FuzzyPlayer fp = new FuzzyPlayer(this.currentPlayer);
            this.currentPlayer = fp;
            this.players.set(this.currentPlayerIndex, fp);
            this.labyrinth.setFuzzyPlayer(fp);
            
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
