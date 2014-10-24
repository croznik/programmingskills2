/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

/**
 *
 * @author s1023011
 */
public class Puma extends Animal{
    
    //Puma constructor with mortality and predation rates given by user
    public Puma(double mRate, double pRate){
        super("predator", 0.2, 0.02);
        this.mRate = mRate;
        this.pRate = pRate;
    }
    
    //Default constructor where mortality and predation rates as set in question
    public Puma(){
        super("predator", 0.2, 0.02);
         this.mRate = 0.06;
         this.pRate =0.04;
    }
        
}
