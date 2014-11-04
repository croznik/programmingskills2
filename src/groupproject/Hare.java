package groupproject; 
 

/**
 *
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
 * @version 7 November 2014
 */
public class Hare extends Animal 
{
    
   /**
    * This default constructor creates a new Hare object using the constructor from the Animal class
    * and set the diffusion rate to 0.2 and the birth rate to 0.08.
    */
    public Hare()
    {
        super("prey", 0.2, 0.08);
    }
    
    public Hare(double dRate, double bRate){
        
        
        super("prey",dRate, bRate);
        
    }
        
        
        
    
}
