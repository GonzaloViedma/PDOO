package irrgarten;

/**
 *
 * @author carlos
 */
public class Monster {
    
    static private int INITIAL_HEALTH = 5;
    
    
   private String name;
   private float intelligence;
   private float strenght;
   private float health;
   private int row;
   private int col;
   
   
   public Monster(String name, float intelligence, float health){
       
       this.name = name;
       this.intelligence = intelligence;
       this.health = health;
   }
   
   public boolean dead(){
       return health == 0;
   }
   
   
    public float attack(){
        float damage = Dice.intensity(this.strenght);
        return damage;
    }
    
    //public boolean defend(float recivedAttack){  
    //}
    
    public void setPos(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public String toString(){
        
        String str;
        str ="M[" + this.name + "," + this.intelligence + "," + this.strenght + "," + this.health + "]";
        return str;
    }
    
    private void gotWounded(){
        this.health--;
    }
}
