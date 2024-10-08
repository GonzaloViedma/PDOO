/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author gonzaalovd
 */
public class Weapon {
    private float power;
    private int uses;
    
     Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
     Weapon(float power){
        this (power, (int)power*2);
    }
    
    public float attack(){
        if(this.uses > 0){
            this.uses--;
            return this.power;
        }
        return 0;
    }
    
    public String toString(){
        String cad;
        cad = "W[" + this.power + "," + this.uses + "]";
        return cad;
    }
}
