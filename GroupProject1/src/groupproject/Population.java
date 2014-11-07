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
   private double numberTimeSteps = 1250; //the number of time steps to run program for
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
        
        for(int i = 0; i < preyMap.length; i++)
        {
            for(int j = 0; j < preyMap[0].length; j++)
            {
                if(map.isDry(i,j) ){
                   
                double newPop = getPreyMap()[i][j] + delta_t * ((preyObj.getBirthRate() * getPreyMap()[i][j] -
                predatorObj.getPredationRate() * getPreyMap()[i][j] * getPredatorMap()[i][j]) + 
                (preyObj.getDiffusionRate() * (getAdjPops(preyMap,i,j) - map.getDryNeighbors(i,j) * getPreyMap()[i][j])));
                newPreyMap[i][j]= (int) (newPop+0.5);
           }
        }
        }
        
        /*
         * Below the new predator population map is calculated
         */
        
        for(int i = 0; i < predatorMap.length; i++)
        {
            for(int j = 0; j < predatorMap[0].length; j++)
            {
                if(map.isDry(i,j)){
                    
                newPredatorMap[i][j] = getPredatorMap()[i][j] + delta_t * ((predatorObj.getBirthRate() * getPreyMap()[i][j] * getPredatorMap()[i][j] -
                predatorObj.getMortalityRate() * getPredatorMap()[i][j] )+ 
                (predatorObj.getDiffusionRate() * (getAdjPops(predatorMap,i,j) - map.getDryNeighbors(i,j) * getPredatorMap()[i][j])));
                //newPredatorMap[i][j] = (int) (newPredPop+0.5);
           }
        }
        }
        
        setPreyMap(newPreyMap);
        setPredatorMap(newPredatorMap);
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
       // System.out.println(getTotalPop(predatorMap));
        if(getTotalAnimalPopulation() != 0){
         int dens = getTotalPop(predatorMap);
         double total = getTotalAnimalPopulation();
        predDensity = (double) getTotalPop(predatorMap) / getTotalAnimalPopulation();
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
      //System.out.println(getTotalPop(preyMap));
      if(getTotalAnimalPopulation() != 0){
      int dens = getTotalPop(preyMap);
      double total = getTotalAnimalPopulation();
      preyDensity = (double) getTotalPop(preyMap)/getTotalAnimalPopulation();
      }
 
      return preyDensity;
    }
    
    public double[][] densityOverLand(double[][] grid)
    {
        double[][] densityOverLand = new double[grid.length][grid[0].length];
        for(int i =0; i<grid[0].length; i++)
        {
            for(int j=0; j<grid.length; j++){
                if(map.isDry(i,j)){
                densityOverLand[i][j] = (double) grid[i][j]/map.totalDryLand();
                }
            }
        }
        return densityOverLand;
    }
    
    public double avDensityOverLand(int[][] landDensGrid)
    {
       double totalDensity =0;
       for(int i=0; i<landDensGrid[0].length; i++)
       {
           for(int j=0; j<landDensGrid.length;j++)
           {
               totalDensity += landDensGrid[i][j];
           }
       }
       double avDens = (double) totalDensity/ map.totalDryLand();
    
       return avDens;
    }
        
       public double[][] densityOverAnimals(int[][] grid)
    {
        
        double[][] densityOverAnimals = new double[grid.length][grid[0].length];
        for(int i =0; i<grid[0].length; i++)
        {
            for(int j=0; j<grid.length; j++){
                if(map.isDry(i,j)){
                densityOverAnimals[i][j] = (double) grid[i][j]/getTotalAnimalPopulation();
                //System.out.println(grid[i][j]);
                //System.out.println(getTotalAnimalPopulation());
                }
            
        }
        }
        return densityOverAnimals;
    }
    
    public double avDensityOverAnimals(double[][] landDensGrid)
    {
       double totalDensity =0;
       
       for(int i=0; i<landDensGrid[0].length; i++)
       {
           for(int j=0; j<landDensGrid.length;j++)
           {
               totalDensity += landDensGrid[i][j];
           }
       }
       double avDens = (double) totalDensity/ noOccupiedSquares(landDensGrid);
    
       return avDens;
    }
    
    public int noOccupiedSquares(double[][] map)
    {
    
        int noOccupied =0;
        for(int i=0; i<map.length; i++)
        {
            for(int j =0; j<map[0].length; j++){
                if(squareIsPopulated(map,i,j)){
                    noOccupied++;
                }
            }
        }
         return noOccupied;
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
     * @return The time field. 
     */
    public double getTime() 
    {
        return t;
    }
    

    /**
     * This method sets the size of the time step.
     * 
     * @param delta_t The new time step.
     */
    public void setDeltaT(double delta_t)
    {
        this.delta_t = delta_t;
    }
    
    /**
     * This method sets the number of time steps between outputs.
     * 
     * @param T The new number of time steps.
     */
    public void setNumberTimeSteps(double T)
    {
        numberTimeSteps = T;
        
     
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
    
    public double getDeltaT()
    {
      return delta_t;
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
    
    public int getTotalPop(double[][] map)
    {
        int totalPop = 0;
        for(int i = 0; i < nCols;i++)
        {
            for(int j = 0; j < nRows;j++)
            {
                totalPop += map[i][j];
                //System.out.println(totalPop);
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
    public void setSquarePop(String predatorOrPrey, int newPop, int row, int column)
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
    
     public void setPredatorMap(double[][] predatorMap){
         this.predatorMap = predatorMap;
     }
     
     public void setPreyMap(double[][] preyMap){
         this.preyMap = preyMap;
     }
     
    
    /**
     * Method to see if a square is populated
     * Static as can refer to general map and used without the population object as a whole
     * @param double[][] inMap
     * @param int i row
     * @param int j column 
     * @return whether or not the grid has a population in it.
     */
    
   
  
  public boolean squareIsPopulated(int[][] inMap, int i, int j){
       boolean hasPop = false;
        if(inMap[i][j] > 0){
            hasPop = true;
        }
        return hasPop;
    }
    
  public boolean squareIsPopulated(double[][] inMap, int i, int j)
  {
      boolean hasPop = false;
      if(inMap[i][j]>0){
          hasPop = true;
      }
      return hasPop;
  }
    
    
}