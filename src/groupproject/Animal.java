package groupproject; 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

/**
 * Superclass for an animal, which creates the basic properties of an animal: death rate, 
 * birth rate, mortality rate, predation rate.
 * 
 * @author 
 */
public class Animal 
{
    
    public String type; //whether the animal is predator or prey (if neither isPredator prey method gives error)
    public double dRate; //diffusion rate
    public double bRate; //birth rate
    public double mRate; //mortality rate
    public double pRate; //predation rate
    

    
    /**
     * Constructor for super class -- applicable to prey, for predator can have another constructor
     *  
     * @param type Whether the animal is predator or prey.
     * @param dRate The animal diffusion rate.
     * @param bRate The animal birth rate.
     */
    public Animal(String type, double dRate, double bRate)
    {

        setType(type);
        setDiffusionRate(dRate);
        setBirthRate(bRate);
        setMortalityRate(0.0);
        setPredationRate(0.0);
       

    }
    
    
    /** 2nd constructor all variables specified
     * @param type (predator/prey)
     * @param dRate diffusion rate
     * @param bRate birth rate
     * @param mRate mortality rate
     * @param pRate predation rate
     * */
    
    
    public Animal(String type, double dRate, double bRate, double mRate, double pRate)
    {
        setType(type);
        setDiffusionRate(dRate);
        setBirthRate(bRate);
        setMortalityRate(mRate);
        setPredationRate(pRate);
        
    }
    
    /**
     * A get method for the animal's diffusion rate.
     * 
     * @return The animal's diffusion rate.
     */   
    public double getDiffusionRate()
    {
        return dRate;
    }
    
    /**
     * A get method for the animal's birth rate.
     * 
     * @return The animal's birth rate.
     */
    public double getBirthRate()
    {
        return bRate;
    }
    
    /**
     * A get method for the animal's mortality rate. 
     * 
     * @return The animal's mortality rate. 
     */
    public double getMortalityRate()
    {
        return mRate;
    }
    
    /**
     * A get method for the animal's predation rate.
     * 
     * @return The animal's predation rate. 
     */
    public double getPredationRate()
    {
        return pRate;
    }


    /**
     * This method tells whether the animal is a predator or prey.
     * 
     * @return True is the animal is a predator and false if it is prey. 
     */
    public boolean isPredator()
    {
       boolean result = false;
      
       if(type.compareTo("prey") != 0 && type.compareTo("predator") != 0)
       {
           //Possibly should be a different sort of error?
           throw new IllegalArgumentException("Animal must be 'predator' or 'prey'");
       }
       
       if(type.compareTo("predator") == 0)
       {
           result = true;
       }
       
       return result;
    }
    
    /**
     * This set method changes the animal's diffusion rate.
     * 
     * @param dRate The new diffusion rate. 
     */
    public void setDiffusionRate(double dRate)
    {
        if(dRate<0){
            throw new IllegalArgumentException("Diffusion rate must be positive");
        }
        this.dRate = dRate;
    }
    
    /**
     * This set method changes the animal's birth rate.
     * 
     * @param bRate The new birth rate. 
     */
    public void setBirthRate(double bRate)
    {

        if(bRate<0){
            throw new IllegalArgumentException("Birth rate must be positive. For death rate use mortality rate");
        }
        this.bRate = bRate;
    }
    
    /**
     * This set method changes the animal's mortality rate.
     * 
     * @param mRate The new mortality rate. 
     */
    public void setMortalityRate(double mRate)
    {
        if(mRate<0){
            throw new IllegalArgumentException("Mortality Rate must be positive. Use birth rate for births.");
        }
        
        if(isPredator() == false)
        {
            mRate = 0.0;
        }
        else
        {
            this.mRate = mRate;
        }
    }
    
    /**
     * This set method change's the animal's predation rate; 
     * (This is kind of a personal choice, either the predation rate is applied to prey so goes with prey
     * OR the predatation rate is something which the predator does to the prey hence goes with predator
     * I'm defining it using the latter for now but it can change)
     * 
     * @param pRate The animal's new predation rate.
     */
    public void setPredationRate(double pRate)
    {
        if(pRate<0){
            throw new IllegalArgumentException("Predation rate cannot be negative");
        }
        if(isPredator() == false)
        {
            pRate = 0.0;
        }   
        else
        {
            this.pRate = pRate;
        }
    }
    
    /**
     * This set method changes the type of animal to prey or predator.
     * 
     * @param type The animal's new type, either predator or prey.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    



}
