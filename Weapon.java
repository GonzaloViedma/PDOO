/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author gonzaalovd
 */
public class Weapon extends CombatElement{
    
    public Weapon(float power, int uses){
        super(power,uses);
    }
    
    
    public float attack(){
        return this.produceEffect();
    }
    
    public String toString(){
        return "W" + super.toString();
    }
}
