package groupproject; 

/**
 * The population class stores the population maps for predator(pumas) and prey(hares).
 * 
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
 * @version 7 November 2014
 */
public class Population
{
   private double delta_t = .4; //the change in time
   private double numberTimeSteps = 1250; //the number of time steps between outputs
   private double t; //the current time
   private int nRows; //the number of rows
   private int nCols; //the number of columns
   private double[][] preyMap; //a 2D array representing the prey population densities for each square
   private double[][] predatorMap; //a 2D array representing the predator population densities for each square
   private GridMap map; //the geographic map showing what's land and water
   
    /**
     * This constructor creates a new object that is attached to a GridMap object that shows
     * the geography of the map.
     * 
     * @param map The GridMap object representing the map of the area. 
     */
    public Population(GridMap map)
    {
        this.map = map;
        nCols = map.getNCols();
        nRows = map.getNRows();
        preyMap = new double[nRows][nCols];
        predatorMap = new double[nRows][nCols];

    }
    
    /**
     * This method updates the population maps according to the equation given in the assignment. 
     */
    
    public void updatePop(Animal predatorObj,Animal preyObj)
    {
        //these 2D arrays will temporarily hold the new population maps
        double[][] newPreyMap = new double[nRows][nCols];
        double[][] newPredatorMap = new double[nRows][nCols];
        
        /*
         * Below the new prey population map is calculated
         */
        
        for(int i = 1; i < (preyMap.length - 1); i++)
        {
            for(int j = 1; j < (preyMap[0].length - 1); j++)
            {
                newPreyMap[i][j] = preyMap[i][j] + delta_t * (preyObj.getBirthRate() * preyMap[i][j] -
                predatorObj.getPredationRate() * preyMap[i][j] * predatorMap[i][j] + 
                preyObj.getDiffusionRate() * (getAdjPops(preyMap,i,j) - map.getDryNeighbors(i,j) * preyMap[i][j]));
            }
        }
        
        /*
         * Below the new predator population map is calculated
         */
        
        for(int i = 1; i < (predatorMap.length - 1); i++)
        {
            for(int j = 1; j < (predatorMap[0].length - 1); j++)
            {
                newPredatorMap[i][j] = predatorMap[i][j] + delta_t * (predatorObj.getBirthRate() * preyMap[i][j] * predatorMap[i][j] -
                predatorObj.getMortalityRate() * predatorMap[i][j] + 
                predatorObj.getDiffusionRate() * (getAdjPops(predatorMap,i,j) - map.getDryNeighbors(i,j) * predatorMap[i][j]));
            }
        }
        
        preyMap = newPreyMap;
        predatorMap = newPredatorMap;
    }
    //We don't need two methods really preyMap and predatorMap are both double[][] 
    //So can make one method that does both these things
    //Haven't implemented it yet.
    /**
     * This method returns the combined populations of all the squares adjacent to a given square.
     * @param double[][] population map
     * @param row The row of the given square's coordinates.
     * @param col The column of the given square's coordinates.
     * 
     * @return The sum of the adjacent squares' populations. 
     */
    public double getAdjPops(double[][] map, int row, int col)
    {
        double adjPops = 0;
      
      //Maybe throw exception instead??
      if(row < 0 || row > map.length || col <0 || col> map[0].length){
        adjPops = 0;
      }
             
      else{
      double up = 0;
      double down = 0;
      double left = 0;
      double right = 0;
      //Special cases if on edges of grid
      if(col == 0)
      {
      left=0;
      }
      
      if(col == map[0].length)
      {
          right =0;
      }
      
      if(row == 0)
      {
          up = 0;
      }
      
      if(row == map.length)
      {
          down = 0;
      }
      
      if(col != 0)
      {
          left = getPop(map,row, col -1);
      }
 
      if(col != map[0].length)
      {

          right = getPop(map,row, col +1);
      }    

      if(row != 0){
            up = getPop(map,row-1,col);
      }

      if(row != map.length){
           down = getPop(map,row+1, col);
      }
      
        adjPops = up+left+down+right;
      }
        return adjPops;
        
        
        
    }
    

    
    /**
     * This get method returns the average predator density.
     * 
     * @return The average predator density.
     */
    public double getPredatorAverageDensity()
    {
        double predDensity = 0;
        if(getTotalAnimalPopulation() != 0){
        predDensity = getTotalPop(predatorMap) / getTotalAnimalPopulation();
        }
        
      return predDensity;
    }
    
    /**
     * This get method returns the average prey density.
     * 
     * @return The average prey density.
     */
    public double getPreyAverageDensity()
    {
      double preyDensity =0;
      if(getTotalAnimalPopulation() != 0){
      preyDensity = getTotalPop(preyMap) / getTotalAnimalPopulation();
      }
      return preyDensity;
    }
    
    /**
     * This get method returns the total population for the entire landscape.
     * 
     * @return The total animal population for the landscape. 
     */
    public double getTotalAnimalPopulation()
    {
        return getTotalPop(preyMap) + getTotalPop(predatorMap); 
    }
    
    /**
     * This method sets the time to a new time.
     * 
     * @param t The new time.
     */
    public void setTime(double t)
    {
       this.t = t;
    }
    
    /**
     * This value returns the value of t.
     * 
     * @return The t field. 
     */
    public double getTime() 
    {
        return t;
    }
    
    /**
     * This static method prints out a String representation of an incoming
     * 2-dimensional array. 
     * 
     * @param map The incoming 2-dimensional array.
     * 
     * @return A String holding a a representation of the 2-dimensional array.
     */
    public static String toString(double[][] map)
    {
        StringBuilder string = new StringBuilder();
        
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                string.append(map[i][j] + " "); //adds the number to the StringBuilder
            }
            string.append("\n"); //starts a new line for the next row
        }
        
        return new String(string);

    }
 
    /**
     * This method sets the size of the time step.
     * 
     * @param delta_t The new time step.
     */
    public void setDeltaT(double delta_t)
    {
        this.delta_t = delta_t;
        
        numberTimeSteps = getTime() / delta_t; //changing delta_t changes the value of T
    }
    
    /**
     * This method sets the number of time steps between outputs.
     * 
     * @param T The new number of time steps.
     */
    public void setNumberTimeSteps(double T)
    {
        numberTimeSteps = T;
        
        delta_t = getTime() / numberTimeSteps; //changing the value of T changes the value of delta_t
    }
    
    /**
     * This method returns the numberTimeSteps field.
     * 
     * @return The numberTimeSteps field.
     */
    public double getNumberTimeSteps()
    {
        return numberTimeSteps;
    }
    
    /**
     * This method gets the population for a given row and column of one of the density maps.
     * 
     * @param map Either the preyMap or the predatorMap matrices.
     * @param row The row of the square.
     * @param column The column of the square.
     */
    public double getPop(double[][] map, int row, int column)
    {
        return map[row][column];
    }
    
    public double getTotalPop(double[][] map)
    {
        double totalPop = 0;
        for(int i = 0; i < nCols;i++)
        {
            for(int j = 0; j < nRows;j++)
            {
                totalPop += map[i][j];
            }
        }
        //Ensure if there is a negative population this is reset to 0
        if(totalPop < 0){
            totalPop =0;
        }
        return totalPop;
    }
   
    /**
     * This set method method assigns a new population to a particular square on the map.
     * 
     * @param map Either the preyMap or the predatorMap matrices.
     * @param row The row of the square.
     * @param column The column of the square.
     */
    public void setSquarePop(String predatorOrPrey, double newPop, int row, int column)
    {
        if(predatorOrPrey == "predator")
        {
            predatorMap[row][column] = newPop;
        }
        else 
        {
            preyMap[row][column] = newPop;
        }
          
    
    }
    
    /**
     * This get method returns the matrix holding the predator densities.
     * 
     * @return The field predator map holding the predator densities across the landscape.
     */
    public double[][] getPredatorMap()
    {
        return predatorMap; 
    }
    
    /**
     * This get method returns the matrix holding the prey densities.
     * 
     * @return The field preyMap holding the prey densities across the landscape.
     */
    public double[][] getPreyMap()
    {
        return preyMap; 
    }
    
    /**
     * Method to see if a square is populated
     * Static as can refer to general map and used without the population object as a whole
     * @param double[][] inMap
     * @param int i row
     * @param int j column 
     * @return whether or not the grid has a population in it.
     */
     
  
  public static boolean squareIsPopulated(double[][] inMap, int i, int j){
       boolean hasPop = false;
        if(inMap[i][j] > 0){
            hasPop = false;
        }
        return hasPop;
    }
    
            
    
    
}
