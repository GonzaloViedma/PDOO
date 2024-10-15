
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author carlos
 */
public class Player {
    
    static private int MAX_WEAPON = 2;
    static private int MAX_SHIELDS = 3;
    static private int INITIAL_HEALTH = 10;
    static private int HITS2LOSE = 3;
    
    private String name;
    private char number;
    private float intelligence;
    private float strenght;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    
    //atributos de relaciones
    private ArrayList<Weapon> weapons;
    private ArrayList<Shield> shields;
    
    public Player(char number, float intelligence, float strength){
        
        this.name = "Player #" + number;
        this.number = number;
        this.intelligence = intelligence;
        this.strenght = strength;
        this.health = Player.INITIAL_HEALTH;
        this.row = -1;
        this.col = -1;
        this.consecutiveHits = 0;
        
        //atribuyos relaciones
        this.weapons = new ArrayList<>();
        this.shields = new ArrayList<>();    
    }
    
     float sumWeapons(){
        float sum = 0.0f;
        
        for (Weapon weapon: this.weapons ){
            sum += weapon.attack();
        }
        return sum;
    } 
}
