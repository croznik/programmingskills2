 
/**
 * The population class stores the population maps for pumas and hares.
 * 
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
 * @version 7 November 2014
 */
public class Population
{
    
    //    private double[][] hares; //the density of hares (prey)
    //    private double[][] pumas; //the density of pumas (predators)
   private double delta_t = .4; //the change in time
   private double numberTimeSteps = 1250; //the number of time steps between outputs
   private double t = 500; //the current time
   private int ny; //the number of rows
   private int nx; //the number of columns
   private double[][] hareMap; //a 2D array representing the hare population densities for each square
   private double[][] pumaMap; //a 2D array representing the puma population densities for each square
   private GridMap map; //the geographic map showing what's land and water
   private Puma pumaObj;
   private Hare hareObj;
   
    /**
     * This constructor creates a new object that is attached to a GridMap object that shows
     * the geography of the map.
     * 
     * @param map The GridMap object representing the map of the area. 
     */
    public Population(GridMap map)
    {
        this.map = map;
        nx = map.getNCols();
        ny = map.getNRows();
        hareMap = new double[ny][nx];
        pumaMap = new double[ny][nx];
        //         pumaObj = new Puma();
        //         hareObj = new Hare();
    }
    
    /**
     * This method updates the population maps according to the equation given in the assignment. 
     */
    
    public void updatePop(Puma pumaObj,Hare hareObj)
    {
        //these 2D arrays will temporarily hold the new population maps
        double[][] newHareMap = new double[ny][nx];
        double[][] newPumaMap = new double[ny][nx];
        
        /*
         * Below the new hare population map is calculated
         */
        
        for(int i = 1; i < (hareMap.length - 1); i++)
        {
            for(int j = 1; j < (hareMap[0].length - 1); j++)
            {
                newHareMap[i][j] = hareMap[i][j] + delta_t * (hareObj.getBirthRate() * hareMap[i][j] -
                pumaObj.getPredationRate() * hareMap[i][j] * pumaMap[i][j] + 
                hareObj.getDiffusionRate() * (getAdjHarePops(i,j) - map.getDryNeighbors(i,j) * hareMap[i][j]));
            }
        }
        
        /*
         * Below the new puma population map is calculated
         */
        
        for(int i = 1; i < (pumaMap.length - 1); i++)
        {
            for(int j = 1; j < (pumaMap[0].length - 1); j++)
            {
                newPumaMap[i][j] = pumaMap[i][j] + delta_t * (pumaObj.getBirthRate() * hareMap[i][j] * pumaMap[i][j] -
                pumaObj.getMortalityRate() * pumaMap[i][j] + 
                pumaObj.getDiffusionRate() * (getAdjPumaPops(i,j) - map.getDryNeighbors(i,j) * pumaMap[i][j]));
            }
        }
        
        hareMap = newHareMap;
        pumaMap = newPumaMap;
        
        
        t += delta_t; //updatting the time
    }
    
    /**
     * This method returns the combined hare populations of all the squares adjacent to a given square.
     * 
     * @param row The row of the given square's coordinates.
     * @param col The column of the given square's coordinates.
     * 
     * @return The sum of the adjacent squares' hare populations. 
     */
    
    public double getAdjHarePops(int row, int col)
    {
        //the coordinates are translated to the coordinates in the data structure
        double adjPops = 0; //the sum of the adjacent hare populations
        
        /*
         * Finally for the general case where the square is not in a corner or on one of the sides
         * of the map
         */
        
        if(map.isDry(row,col - 1))
            adjPops += getPop(hareMap,row,col - 1); //hareMap was hares before
        if(map.isDry(row,col + 1))
            adjPops += getPop(hareMap,row,col + 1); //hareMap was hares before
        if(map.isDry(row - 1,col))
            adjPops += getPop(hareMap,row - 1,col); //hareMap was hares before
        if(map.isDry(row + 1,col))
            adjPops += getPop(hareMap,row + 1,col); //hareMap was hares before
        
        return adjPops;
    }  
    
    /**
     * This method returns the combined puma populations of all the squares adjacent to a given square.
     * 
     * @param row The row of the given square's coordinates. 
     * @param col The column of the given square's coordinates.
     * 
     * @return The sum of the adjacent squares' puma populations. 
     */
    ///
    public double getAdjPumaPops(int row, int col)
    {
        //the coordinates are translated to the coordinates in the data structure
        double adjPops = 0; //the sum of the adjacent hare populations
        
        /*
         * Finally for the general case where the square is not in a corner or on one of the sides
         * of the map
         */
        
        if(map.isDry(row,col - 1))
            adjPops += getPop(pumaMap,row,col - 1); //pumaMap was pumas before
        if(map.isDry(row,col + 1))
            adjPops += getPop(pumaMap,row,col + 1); //pumaMap was pumas before
        if(map.isDry(row - 1,col))
            adjPops += getPop(pumaMap,row - 1,col); //pumaMap was pumas before
        if(map.isDry(row + 1,col))
            adjPops += getPop(pumaMap,row + 1,col); //pumaMap was pumas before
        
        return adjPops;
    }  
    
    /**
     * This get method returns the average predator density.
     * 
     * @return The average predator density.
     */
    public double getPredatorAverageDensity()
    {
      return getTotalPop(pumaMap) / (getTotalPop(pumaMap) + getTotalPop(hareMap)); //hareMap was hares before //pumaMap was pumas before
    }
    
    /**
     * This get method returns the average prey density.
     * 
     * @return The average prey density.
     */
    public double getPreyAverageDensity()
    {
      return getTotalPop(hareMap) / (getTotalPop(hareMap) + getTotalPop(pumaMap)); //hareMap was hares before //pumaMap was pumas before
    }
    
    /**
     * This get method returns the total density for the entire landscape.
     * 
     * @return The total density for the landscape. 
     */
    public double getTotalDensity()
    {
        return getTotalPop(hareMap) + getTotalPop(pumaMap); //hareMap was hares before //pumaMap was pumas before
    }
              

    /**
     * This method prints out a graphical representation of the two population maps. 
     * 
     * @return Graphic representation of the two population maps.
     */
    
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        
        string.append("Hare Populations\n\n");
        
        for(int i = 0; i < hareMap.length; i++)
        {
            for(int j = 0; j < hareMap[0].length; j++)
            {
                string.append(hareMap[i][j] + " ");     //adds the number to the StringBuilder
            }
            
            string.append("\n"); //starts a new line for the next row
        }
        
        string.append("\nPuma Populations\n\n");
        
        for(int i = 0; i < pumaMap.length; i++)
        {
            for(int j = 0; j < pumaMap[0].length; j++)
            {
                string.append(pumaMap[i][j] + " ");     //adds the number to the StringBuilder
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
    
    public void set_delta_t(double delta_t)
    {
        this.delta_t = delta_t;
        
        numberTimeSteps = get_t() / delta_t; //changing delta_t changes the value of T
    }
    
    /**
     * This method sets the number of time steps between outputs.
     * 
     * @param T The new number of time steps.
     */
    
    public void set_numberTimeSteps(double T)
    {
        numberTimeSteps = T;
        
        delta_t = get_t() / numberTimeSteps; //changing the value of T changes the value of delta_t
    }
   
    /**
     * This value returns the value of t.
     * 
     * @return The t field. 
     */
    public double get_t() 
    {
        return t;
    }
    
    /**
     * This method gets the population for a given row and column of one of the density maps.
     * 
     * @param map Either the hareMap or the pumaMap matrices.
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
        for(int i = 0; i < nx;i++)
        {
            for(int j = 0; j < ny;j++)
            {
                totalPop += map[i][j];
            }
        }
        return totalPop;
    }
   
    /**
     * This set method method assigns a new population to a particular square on the map.
     * 
     * @param map Either the hareMap or the pumaMap matrices.
     * @param row The row of the square.
     * @param column The column of the square.
     */
    public void setSquarePop(String pumaOrHare, double newPop, int row, int column)
    {
        if(pumaOrHare == "puma")
        {
            pumaMap[row][column] = newPop;
        }
        else 
        {
            hareMap[row][column] = newPop;
        }
    }
    
    /**
     * This get method returns the matrix holding the puma densities.
     * 
     * @return The field pumas holding the puma densities across the landscape.
     */
    public double[][] getPredatorMap()
    {
        return pumaMap; //pumaMap was pumas before
    }
}