
/**
 * The population class stores the population maps for pumas and hares.
 * 
 * Note that this class like the GridMap class distinguishes between map coordinates and data
 * (structure) coordinates where map coordinates, which are not defined the same. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Population
{
   private double[][] hares; //the density of hares (prey)
   private double[][] pumas; //the density of pumas (predators)
   private double r = .08; //hare birth rate
   private double a = .04; //predation rate at which pumas eat hares
   private double b = .02; //birth rate of pumas per one hare eaten
   private double m = .06; //puma mortality rate
   private double k = .2; //hare diffusion rate
   private double l = .2; //puma diffusion rate 
   private double delta_t = .4; //the change in time
   private double T = 1250; //the number of time steps between outputs
   private double t = 0; //the current time
   private int ny; //the number of rows
   private int nx; //the number of columns
   private double[][] hareMap; //a 2D array representing the hare population densities for each square
   private double[][] pumaMap; //a 2D array representing the puma population densities for each square
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
        nx = map.getNCols();
        ny = map.getNRows();
        hareMap = new double[ny][nx];
        pumaMap = new double[ny][nx];
    }
    
    /**
     * This method updates the population maps according to the equation given in the assignment. 
     */
    
    public void updatePop()
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
                newHareMap[i][j] = hareMap[i][j] + delta_t * (r * hareMap[i][j] -
                a * hareMap[i][j] * pumaMap[i][j] + 
                k * (getAdjHarePops(i,j) - map.getDryNeighbors(i,j) * hareMap[i][j]));
            }
        }
        
        /*
         * Below the new puma population map is calculated
         */
        
        for(int i = 1; i < (pumaMap.length - 1); i++)
        {
            for(int j = 1; j < (pumaMap[0].length - 1); j++)
            {
                newPumaMap[i][j] = pumaMap[i][j] + delta_t * (b * hareMap[i][j] * pumaMap[i][j] -
                m * pumaMap[i][j] + 
                l * (getAdjPumaPops(i,j) - map.getDryNeighbors(i,j) * pumaMap[i][j]));
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
            adjPops += getHarePop(row,col - 1);
        if(map.isDry(row,col + 1))
            adjPops += getHarePop(row,col + 1);
        if(map.isDry(row - 1,col))
            adjPops += getHarePop(row - 1,col);
        if(map.isDry(row + 1,col))
            adjPops += getHarePop(row + 1,col);
        
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
            adjPops += getPumaPop(row,col - 1);
        if(map.isDry(row,col + 1))
            adjPops += getPumaPop(row,col + 1);
        if(map.isDry(row - 1,col))
            adjPops += getPumaPop(row - 1,col);
        if(map.isDry(row + 1,col))
            adjPops += getPumaPop(row + 1,col);
        
        return adjPops;
    }  
    
    /**
     * This method returns the hare population of a given square, but only the data structure 
     * coordinates can be used as inputs,
     * 
     * @param row The row.
     * @param column The column.
     * 
     * @return The hare population of the square.
     */
    public double getHarePop(int row, int column)
    {
        return hareMap[row][column];
    }
    
    /**
     * This method returns the puma population of a given square, but only the data structure 
     * coordinates can be used as inputs. 
     * 
     * @param row The row.
     * @param col The column.
     * 
     * @return The puma population of the square.
     */
    public double getPumaPop(int row, int col)
    {
        return pumaMap[row][col];
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
     * This method sets the hare birth rate.
     * 
     * @param birthRate The new birth rate.
     */
    
    public void set_r(double birthRate)
    {
        r = birthRate;
    }
    
    /**
     * This method sets the predation rate at which pumas eat hares.
     * 
     * @param predRate The new predation rate.
     */
    
    public void set_a(double predRate)
    {
        a = predRate;
    }
    
    /**
     * This method sets the birth rate of pumas per one hare eaten.
     * 
     * @param birthRate The new puma birth rate.
     */
    
    public void set_b(double birthRate)
    {
        b = birthRate;
    }
    
    /**
     * This method sets the puma mortality rate.
     * 
     * @param deathRate The new puma mortality rate.
     */
    
    public void set_m(double deathRate)
    {
        m = deathRate;
    }
    
    /**
     * This method sets the diffusion rate for hares.
     * 
     * @param diffusionRate The new diffusion rate for hares.
     */
    public void set_k(double diffusionRate)
    {
        k = diffusionRate;
    }
    
    
    /**
     * This method sets the diffusion rate for pumas.
     * 
     * @param diffusionRate The new diffusion rate for pumas.
     */
    
    public void set_l(double diffusionRate)
    {
        l = diffusionRate;
    }
    
    /**
     * This method sets the size of the time step.
     * 
     * @param delta_t The new time step.
     */
    
    public void set_delta_t(double delta_t)
    {
        this.delta_t = delta_t;
        
        T = t / delta_t; //changing delta_t changes the value of T
    }
    
    /**
     * This method sets the number of time steps between outputs.
     * 
     * @param T The new number of time steps.
     */
    
    public void set_T(int T)
    {
        this.T = T;
        
        delta_t = t / T; //changing the value of T changes the value of delta_t
    }
    
    /**
     * This method sets the hare population at some given map coordinates.
     * 
     * @param newPop The new hare population.
     * @param row The row of the map.
     * @param col The column of the map. 
     */
    
    public void set_H(double newPop, int row, int col)
    {
        hareMap[row][col] = newPop;
    }
    
    /**
     * This method sets the puma population at some given map coordinates.
     * 
     * @param newPop The new puma population.
     * @param row The row of the map.
     * @param col The column of the map. 
     */
    
    public void set_P(double newPop, int row, int col)
    {
        pumaMap[row][col] = newPop;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}