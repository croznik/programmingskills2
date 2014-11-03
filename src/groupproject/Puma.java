 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 *
 * @author s1023011
 * @version 7 November 2014
 */
public class Puma extends Animal
{
    /**
     * This constructor creates a Puma object with input from the user.
     * 
     * @param mRate The puma mortality rate.
     * @param pRate The puma predation rate. 
     */
    public Puma(double mRate, double pRate)
    {
        super("predator", 0.2, 0.02);
        this.mRate = mRate;
        this.pRate = pRate;
    }
    
    //Default constructor where mortality and predation rates as set in question
    
    /**
     * The default constructor where the mortality and predation rates are 
     * the default values
     */
    public Puma()
    {
        super("predator", 0.2, 0.02);
         this.mRate = 0.06;
         this.pRate = 0.04;
    }
        
}
