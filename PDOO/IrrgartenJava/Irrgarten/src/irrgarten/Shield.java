/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;

/**
 *
 * @author gonzaalovd
 */
public class Shield {
    private float protection;
    private int uses;
    
    public Shield(float protection, int uses){
        this.protection = protection;
        this.uses = uses;
    }
    
    public float protect(){
        if(this.uses > 0){
            this.uses--;
            return this.protection;
        }
        return 0;
    }
    
    public String toString(){
        String cad;
        cad = "S[" + this.protection + "," + this.uses + "]";
        return cad;
    }
    
    public boolean discard(){
        return Dice.discardElement(uses);
    }
}
