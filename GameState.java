/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 * @author gonzaalovd & carlitros_gamer20

 */
package irrgarten;
public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;
    
    public GameState(String labyrinth, String players, String monsters,
            int currentPlayer, boolean winner, String log){
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }
    
    public String getLabyrinth(){
        return this.labyrinth;
    }
    
    public String getPlayers(){
        return this.players;
    }
    
    public String getMonsters(){
        return this.monsters;
    }
    
    public int getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public boolean getWinner(){
        return this.winner;
    }
    
    public String getLog(){
        return this.log;
    }
}