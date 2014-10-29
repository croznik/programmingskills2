/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

/**
 *Superclass for an animal
 * Creates the basic properties of an animal: death rate, birth rate, mortality rate, predation rate
 * @author 
 */
public class Animal {
    
    public String type; //whether is predator or prey (if neither isPredator prey method gives error)
  
    public double dRate; //diffusion rate
    public double bRate; //birth rate
    public double mRate; //mortality rate
    public double pRate; //predation rate
    public GridMap map;
    
    //Constructor for super class -- applicable to prey, for predator can have another constructor
    public Animal(String type, double dRate, double bRate){
        
        this.type=type;
        this.dRate=dRate;
        this.bRate=bRate;
        mRate=0.0;
        pRate=0.0;
        map = null;
        
    }
    
       
    public double getDiffusionRate()
    {
        return dRate;
    }
    public double getBirthRate()
    {
        return bRate;
    }
    public double getMortalityRate()
    {
        return mRate;
    }
    public double getPredationRate()
    {
        return pRate;
    }
    
    public GridMap getMap()
    {
        return map;
    }

    //This is probably an ugly way to differentiate the types
    public boolean isPredator()
    {
       boolean result = false;
      
       if(type.compareTo("prey")!=0 && type.compareTo("predator")!=0){
           //Possibly should be a different sort of error?
           throw new IllegalArgumentException("Animal must be 'predator' or 'prey'");
       }
       
       if(type.compareTo("predator")==0){
           result = true;
       }
       
       return result;
    }
    
    
  
    public void setDiffusionRate(double dRate)
    {
        this.dRate=dRate;
    }
    public void setBirthRate(double bRate)
    {
        this.bRate=bRate;
    }
    public void setMortalityRate(double mRate)
    {
        if(isPredator()==false){
            mRate=0.0;
            
        }
        else{
            this.mRate=mRate;
        }
    }
    
    //This is kind of a personal choice, either the predation rate is applied to prey so goes with prey
    //OR the predatation rate is something which the predator does to the prey hence goes with predator
    //I'm defining it using the latter for now but it can change
    public void setPredationRate(double pRate)
    {
        if(isPredator()==false){
          pRate=0.0;
        }
        else{
            this.pRate=pRate;
        }
    }
    
    public void setType(String type)
    {
        this.type=type;
        }
    
    public void setMap(GridMap map)
    {
        this.map=map;
    }
}
