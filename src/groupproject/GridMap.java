package groupproject;   

import java.util.*;
import java.io.*;

/**
 * 
 * This class creates a map using a 2D array data structure where dry portions of land in the map are coded
 * with a 1 and water areas are coded with a zero.
 * 
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
 * @version 7 November 2014
 */

public class GridMap
{
    
    private int[][] map;
    private int numRows; //the number of rows
    private int numCols; //the number of columns
    
    /**
     * The default constructor creates a GridMap object representing a random map of minimum 
     * dimensions 10x10 but no more than 2000x2000.
     */
    
    public GridMap()
    {
        Random rand = new Random();
        
        numRows = rand.nextInt(1991) + 10;
        numCols = rand.nextInt(1991) + 10;
        
        map = new int[numRows][numCols];
        
        /*
         *The map is surrounded by a border of water and only about 20% of the interior is water.
         */
        
        for(int i = 1; i < (map.length - 1); i++)
        {
            for(int j = 1; j < (map.length - 1); j++)
            {
                if(rand.nextInt(5) == 0)
                {
                    map[i][j] = 0;
                }
                else
                {
                    map[i][j] = 1;
                }
            }
        }
    }
    
    /**
     * This constructor creates a new GridMap object based on an incoming file. 
     * 
     * @param nrow The number of rows for the GridMap object.
     * @param ncol The number of columns for the GridMap object. 
     * @param fileName The name of the file containing the data for the landscape. 
     */
    
        public GridMap(int nrow, int ncol,String fileName)
        {
            numRows = nrow;
            numCols = ncol;
            map = new int[numRows][numCols];
            File file = new File(fileName);
            BufferedReader reader = null;  
            try
             { 
                  reader = new BufferedReader(new FileReader(file));
                  String tempString = null;
                  int line = -1;
             while ((tempString = reader.readLine()) != null)
             {
             if (line == -1)
                 {
                     line++;
                     continue;
                 } 
             String [] sArray = tempString.split(" ");//store the file to temp array
            for(int j = 0; j < numRows; j++)
                {
                    map[line][j] = Integer.parseInt(sArray[j]);//change string to int
                }
            line++;
             }
             reader.close();
             } catch (IOException e) 
             {
                e.printStackTrace();
             } finally 
             {
                if (reader != null) 
                {
              try 
              {
                  reader.close();
             } catch (IOException e1) {}
          }
         }
    }
    
    /**
     * Constructor where a map can be created from a given number of input columns and rows and it
     * then randomly generates a map with those dimensions where (roughly) 25% of the map is water. 
     * 
     * @param nrow The number of rows for the map.
     * @param ncol The number of columns for the map.
     */
    public GridMap(int nrow, int ncol)
    {
        Random rand = new Random();
        
        numRows = nrow;
        numCols = ncol;
        
        //the map must dimensions must not exceed 2000 x 2000
        if(numRows > 2000)
            numRows = 2000;
        if(numCols > 2000)
            numCols = 2000;
        
        map = new int[numRows][numCols];
        
        /*
         * Creating a map using the random number generator
         */
        
        for(int i = 0; i < map.length; i++) //the i represents the row
        {
            for(int j = 0; j < map[0].length; j++) //the j represents the column
            {
                //randomly assigns a numbers to the map
                if(i == 0 || i == (map.length - 1) || j == 0 || j == (map[0].length - 1))
                {
                    map[i][j] = 0;
                }
                else if(rand.nextInt(5) == 0) //if the number is 0 then the space becomes water
                    map[i][j] = 0;
                else                    //if the number is 1, 2, 3, or 4 then the space becomes land
                    map[i][j] = 1;
            }
        }
        
    }
    
    /**
     * New constuctor (mostly for testing purposes)
     * @param int[][] map of 1, 0 's to GridMap object
     * */
    
    public GridMap(int[][] inMap)  
    {
       
        if(inMap.length > 2000 || inMap[0].length < 0){
            throw new IllegalArgumentException("Grid map row and col dimenstion cannot exceed 2000");
        }
        
        //If input map is not ones or zeroes numbers > 1 Go to 1 and Numbers less than <0
        for(int i=0; i<inMap.length; i++){
            for(int j=0; j<inMap[0].length; j++){
                if(inMap[i][j] > 1){
                    inMap[i][j] = 1;
                     System.out.println("Warning. Map contains numbers greater than one these have been set to 1");
                      }
                if(inMap[i][j] < 0){
                    inMap[i][j] =0;
                    System.out.println("Warning. Map contains numbers less than 0 these have been set to 0");
                }
            }
        }
        
        numRows = inMap.length;
        numCols = inMap[0].length;
        map= inMap;
        
    }
        
        
        
    
    
    
    /**
     * This method returns the number of dry squares adjacent to the square at map[row][col]
     * using the square's data coordinates.
     * 
     * @param row The row of the square's data coordinates.
     * @param col The column of the square's data coordinates.
     * 
     * @return The number of dry neighbors
     */
    
    public boolean isInMap(int row, int col){
        boolean isInMap = true;
        
       if(row < 0 || row > getNRows() || col < 0 || col > getNCols())
       {
           System.out.println(row+" "+col);
          
       }
       
       return isInMap;
    }
    
    public int getDryNeighbors(int row, int col)
    {
      int neighbors = 0;
      //Checks is actually in grid before 
      
      if(isInMap(row,col)){
      
      int up = 0;
      int down = 0;
      int left = 0;
      int right = 0;
      if((col != 0) && isDry(row,col - 1))
      {
          left++;
      }
 
      if((col != getNCols()) && isDry(row,col + 1))
      {

          right++;
      }    

      if((row != 0) && isDry(row - 1,col)){
            up++;
      }

      if((row != getNRows()) && isDry(row + 1,col)){
           down++;
      }
        neighbors = up+left+down+right;
      
      }
        return neighbors;
    }
    
    /**
     * This method returns true if the square is dry land.
     * 
     * @param row The row of the square in terms of map coordinates.
     * @param col The column of the square in terms of map coordinates.
     * 
     * @return True is the neighbor is dry. 
     */
    
    public boolean isDry(int row, int col)
    {
        if(map[row][col] == 1)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    /**
     * This method prints out a graphical representation of the map. 
     * 
     * @return Graphic representation of the map.
     */
   
    public String toString()
    {
        StringBuilder string = new StringBuilder();
        
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                string.append(map[i][j] + " ");     //adds the number to the StringBuilder
            }
            
            string.append("\n"); //starts a new line for the next row
        }
        
        return new String(string);
    }
    
    /**
     * This method returns the number of columns in the map.
     * 
     * @return The number of columns in the map.
     */
    
    public int getNCols()
    {
        return numCols;
    }
    
    /**
     * This method returns the number of rows in the map.
     * 
     * @return The number of rows in the map.
     */
    
    public int getNRows()
    {
        return numRows;
    }
}

