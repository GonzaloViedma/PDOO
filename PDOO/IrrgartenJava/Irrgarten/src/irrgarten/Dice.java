/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.Random;
/**
 *
 * @author gonzaalovd
 */
public class Dice {
    private final int MAX_USES = 5;
    private final float MAX_INTELLIGENCE = (float) 10.0;
    private final float MAX_STRENGTH = (float)10.0;
    private final float RESURRECT_PROB = (float)0.3;
    private final int WEAPONS_REWARD = 2;
    private final int SHIELD_REWARD = 3;
    private final int HEALTH_REWARD = 5;
    private final int MAX_ATTACK = 3;
    private final int MAX_SHIELD = 2;
    
    private Random generator = new Random();
    
    public int randomPos(int max){
        int pos = generator.nextInt(max);
        return pos;
    }
    
    public int whoStarts(int nplayers){
        int starts = generator.nextInt(nplayers);
        return starts;
    }
    
    public float randomIntelligence(){
        float intel = generator.nextFloat()*MAX_INTELLIGENCE;
        return intel;
    }
    
    public float randomStrenght(){
        float str = generator.nextFloat()*MAX_STRENGTH;
        return str;
    }
    
    public boolean resurrectPlayer(){
        float prob = generator.nextFloat();
        
        return prob <= RESURRECT_PROB;
    }
    
    public int weaponsReward(){
        int nweapons = generator.nextInt(WEAPONS_REWARD+1);
        return nweapons;
    }
    
    public int shieldsReward(){
        int nshield = generator.nextInt(SHIELD_REWARD+1);
        return nshield;
    }
    
    public int healthReward(){
        int nhealth = generator.nextInt(HEALTH_REWARD+1);
        return nhealth;
    }
    
    public float weaponPower(){
        float attack = generator.nextFloat()*MAX_ATTACK;
        
        while(attack == MAX_ATTACK)){
        attack = generator.nextFloat()*MAX_ATTACK;
    }
    }
}
