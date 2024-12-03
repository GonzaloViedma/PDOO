
/**
 *
 * @author gonzaalovd & carlitros_gamer20
 */
package irrgarten;


public class Monster extends LabyrinthCharacter{

    static private int INITIAL_HEALTH = 5;


   public Monster(String name, float intelligence, float strength){
       super(name, intelligence, strength, INITIAL_HEALTH);
   }

    public float attack(){
        float damage = Dice.intensity(super.getStrength());
        return damage;
    }

    public boolean defend(float receivedAttack){  
        boolean isDead = this.dead();
        
        if(!isDead){
            float defensiveEnergy = Dice.intensity(super.getIntelligence());
            
            if(defensiveEnergy < receivedAttack){
                super.gotWounded();
                isDead = super.dead();
            }
        }
        return isDead;
    }

}