package groupproject; 

/**
 *
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
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
     * The default constructor where the mortality 0.06 and predation 0.04 rates are 
     * the default values
     */
    public Puma()
    {
        super("predator", 0.2, 0.02,0.06,0.04);
         
    }
    
    public Puma(double dRate, double bRate, double mRate, double pRate){
        super("predator", dRate, bRate, mRate, pRate);
    }
        
}
