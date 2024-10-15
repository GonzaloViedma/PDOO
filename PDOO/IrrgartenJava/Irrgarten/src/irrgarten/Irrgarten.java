/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package irrgarten;

/**
 *
 * @author gonzaalovd
 */
public class Irrgarten {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Weapon w1 = new Weapon(100,50);
        String info;
        
        info = w1.toString();
        System.out.println("Caracteristicas del arma:" + info);*/
        //Directions dir = Directions.DOWN;
        //System.out.println("La direccion es: " + dir);
        int descarta=0;
        for (int i = 0; i<100; i++){
            boolean discar = Dice.discardElement(4);
            System.out.println("discard " + i + ": " + discar);
            if(discar == true)
                descarta++;
        }
        System.out.println(descarta);
    }
    
}
