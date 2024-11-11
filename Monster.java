package irrgarten;


public class Monster {
    
    static private int INITIAL_HEALTH = 5;
    
    
   private String name;
   private float intelligence;
   private float strenght;
   private float health = Monster.INITIAL_HEALTH;
   private int row = -1;
   private int col = -1;
   
   
   public Monster(String name, float intelligence, float strenght){
       
       this.name = name;
       this.intelligence = intelligence;
       this.strenght = strenght;
   }
   
   public boolean dead(){
       return health == 0;
   }
   
   
    public float attack(){
        float damage = Dice.intensity(this.strenght);
        return damage;
    }
    
    public boolean defend(float recivedAttack){  
        boolean isDead = this.dead();
        
        if(!isDead){
            float defensiveEnergy = Dice.intensity(intelligence);
            if(defensiveEnergy < recivedAttack){
                this.gotWounded();
                isDead = this.dead();
            }
        }
        return isDead;
    }
    
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
