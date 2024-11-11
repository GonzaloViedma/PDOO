
package irrgarten;

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
