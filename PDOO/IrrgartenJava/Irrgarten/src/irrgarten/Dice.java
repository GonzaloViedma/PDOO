/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.Random;
/**
 *
 * @author gonzaalovd & carlitros_gamer20
 */
public class Dice {
    static private final int MAX_USES = 5;
    static private final float MAX_INTELLIGENCE = (float) 10.0; //si da fallo hay que poner (float) o 10.0f
    static private final float MAX_STRENGTH = (float)10.0;
    static private final float RESURRECT_PROB = (float)0.3;
    static private final int WEAPONS_REWARD = 2;
    static private final int SHIELD_REWARD = 3;
    static private final int HEALTH_REWARD = 5;
    static private final int MAX_ATTACK = 3;
    static private final int MAX_SHIELD = 2;
    
    static private final Random generator = new Random(); //el new asigna una direccion de memoria a generator que se le pedirán
                                                         // valores aleatorios pero nunca cambia
    
    static public int randomPos(int max){
        int pos = generator.nextInt(max);
        return pos;
    }
    
    static public int whoStarts(int nplayers){
        int starts = generator.nextInt(nplayers);
        return starts;
    }
    
    static public float randomIntelligence(){
        return Dice.generator.nextFloat()*Dice.MAX_INTELLIGENCE;
        //float intel = generator.nextFloat()*MAX_INTELLIGENCE;
        //return intel;
    }
    
    static public float randomStrenght(){
        return Dice.generator.nextFloat()*Dice.MAX_STRENGTH;
        //float str = generator.nextFloat()*MAX_STRENGTH;
        //return str;
    }
    
    static public boolean resurrectPlayer(){
        float prob = generator.nextFloat();
        return prob <= RESURRECT_PROB;
    }
    
    static public int weaponsReward(){
        return Dice.generator.nextInt(WEAPONS_REWARD+1);
        //int nweapons = generator.nextInt(WEAPONS_REWARD+1);
        //return nweapons;
    }
    
    static public int shieldsReward(){
        return Dice.generator.nextInt(Dice.SHIELD_REWARD+1);
        //int nshield = generator.nextInt(SHIELD_REWARD+1);
        //return nshield;
    }
    
    static public int healthReward(){
        return Dice.generator.nextInt(Dice.HEALTH_REWARD+1);
        //int nhealth = generator.nextInt(HEALTH_REWARD+1);
        //return nhealth;
    }
    
    static public float weaponPower(){
        float attack = generator.nextFloat()*MAX_ATTACK;
        
        while(attack == MAX_ATTACK){
        attack = generator.nextFloat()*MAX_ATTACK;
        }
        
        return attack;
    }
    
    static public int usesleft(){
        return Dice.generator.nextInt(Dice.MAX_USES+1);
    }
}
