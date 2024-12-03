/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package irrgarten;
import java.util.ArrayList;
import irrgarten.Dice;

/**
 *
 * @author gonzaalovd
 */
public class FuzzyPlayer extends Player{
    public FuzzyPlayer(Player other){
        super(other);
    }
    
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions move = super.move(direction, validMoves);
        return Dice.nextStep(move, validMoves, super.getIntelligence());
    }
    
    public float attack(){
        return super.attack();
    }
    
    protected float defensiveEnergy(){
        return super.defensiveEnergy();
    }
    
    public String toString(){
        return "Fuzzy " + super.toString();
        
        
    }
}
