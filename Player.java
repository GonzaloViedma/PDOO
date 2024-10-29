
package irrgarten;


import java.util.ArrayList;
import irrgarten.Dice;

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
    
    public void resurrect(){
        this.weapons.clear();
        this.shields.clear();
        this.health = INITIAL_HEALTH;
        this.consecutiveHits = 0;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
    
    public char getNumber(){
        return this.number;
        
    }
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean dead(){
        return health == 0;
    }
    
    //public Directions move(Directiosn direction, Directions[] validMoves){
        
    //}
    
    public float attack(){
        float sum = 0.0f;
        sum += this.strenght;
        sum += this.sumWeapons();
        return sum;
    }
    
    //public boolean defend(float receivedAttack){
        
    //} 
    
    //public void recivedReward(){
        
    //}
    
    public String toString(){
        String str;
        str = "P[" + this.name + "," + this.number + "," + this.intelligence + "," + this.strenght + "," + this.health + "]";
        return str;
    }
    
    //private void receiveWeapon( Weapon w){
        
    //}
    
    //private void receiveShield(Shield s){
        
    //}
    
    private Weapon newWeapon(){
        Weapon w1 = new Weapon (Dice.weaponPower(), Dice.usesLeft());
        
        return w1;
    }
    
    private Shield newShield(){
        Shield s1 = new Shield (Dice.shieldPower(), Dice.usesLeft());
        
        return s1;
    }
    
     private float sumWeapons(){
        float sum = 0.0f;
        
        for (Weapon weapon: this.weapons ){
            sum += weapon.attack();
        }
        return sum;
    }
     
     private float sumShields(){
         float sum = 0.0f;
         
         for(Shield shield: this.shields){
             sum += shield.protect();
         }
         return sum;
     }
     
     private float defensiveEnergy(){
         float sum= 0.0f;
         sum += this.intelligence;
         sum += this.sumShields();
         return sum;
     }
     
     //private boolean manageHit(float receivedAttack){
         
     //}
     
     private void resetHits(){
         this.consecutiveHits = 0;
     }
     
     private void gotWounded(){
         this.health--;
     }
     
     private void incConsecutiveHits(){
         this.consecutiveHits++;
     }
}
