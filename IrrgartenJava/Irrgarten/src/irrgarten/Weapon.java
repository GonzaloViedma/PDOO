/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author gonzaalovd
 */
public class Weapon {
    private float power;
    private int uses;   
    
    public Weapon(float power, int uses){
        this.power = power;
        this.uses = uses;
    }
    
    public float attack(){
        if(this.uses > 0){
            this.uses--;
            return this.power;
        }
        return 0;
    }
    
    public String toString(){  //Funcionaria poniendo solo return??
        String cad;
        cad = "W[" + this.power + "," + this.uses + "]";
        return cad;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
