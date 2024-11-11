
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
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        /*1.1*/ int size = validMoves.size();
        /*1.2*/ boolean contained = validMoves.contains(direction);
        /*alt*/if(size > 0 && (!contained)){
            /*1.3*/ Directions firstElement = validMoves.get(0);
            /*1.4*/ return firstElement;
        }else{
            /*1.5*/ return direction;
        }
    }
    
    public float attack(){
        float sum = 0.0f;
        sum += this.strenght;
        sum += this.sumWeapons();
        return sum;
    }
    
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    } 
    
    public void recivedReward(){
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i = 0; i < wReward; i++){
            Weapon wnew = newWeapon();
            this.receiveWeapon(wnew);
        }
        
        for(int i = 0; i < sReward; i++){
            Shield snew = newShield();
            this.receiveShield(snew);
        }
        
        int extraHealth = Dice.healthReward();
        this.health += extraHealth;
    }
    
    public String toString(){
        String str;
        str = "P[" + this.name + "," + this.number + "," + this.intelligence + "," + this.strenght + "," + this.health + "]";
        return str;
    }
    
    private void receiveWeapon( Weapon w){
        for(int i = 0; i < this.weapons.size(); i++){
            Weapon wi = this.weapons.get(i);
            boolean discard = wi.discard();
            
            if(discard)
                this.weapons.remove(wi);
        }
        
        int size = this.weapons.size();
        
        if(size < MAX_WEAPON)
            this.weapons.add(w);
    }
    
    private void receiveShield(Shield s){
       
        for(int i = 0; i < this.shields.size(); i++){
            Shield si = this.shields.get(i);
            boolean discard = si.discard();
            
            if(discard)
                this.shields.remove(si);
        }
        
        int size = this.shields.size();
        
        if(size < MAX_SHIELDS)
            this.shields.add(s);
    }
    
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
     
     private boolean manageHit(float receivedAttack){
         float defense = this.defensiveEnergy();
         
         if(defense < receivedAttack){
             this.gotWounded();
             this.incConsecutiveHits();
         }else{
             this.resetHits();
         }
         boolean lose;
         if(this.consecutiveHits == HITS2LOSE || this.dead()){
             this.resetHits();
             lose = true;
         }else{
             lose = false;
         }
         
         return lose;
     }
     
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
