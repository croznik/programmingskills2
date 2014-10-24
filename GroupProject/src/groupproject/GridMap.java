package groupproject;
/**
 * This class creates a map using a 2D array data structure; the assignment specifies that the grid has
 * ny rows and nx columns and each element is referred to by (nx,ny), the rows range from 1 to ny and
 * the columns range from 1 to nx, and that the grid "starts" at the bottom left corner so that the
 * element at column 1 and row 1 is in the bottom left corner. Dry portions of land in the map are coded
 * with a 1 and water areas are coded with a zero.
 * 
  * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.io.*;

public class GridMap{
    
    private int[][] map;
    private int ny; //the number of rows
    private int nx; //the number of columns
    
    /**
     * This constructor takes in a bitmask ASCII file to create a new map.
     * 
     * @param nrow The number of rows for the map.
     * @param ncol The number of columns for the map.
     * @param file The incoming bitmask ASCII file.
     */
    
    public GridMap(int nrow, int ncol,File file)
    {
    }
    
    public GridMap(int nrow, int ncol,String filename){
     nx = nrow;
     ny = ncol;
     map = new int[nx][ny];
     File file = new File(filename);
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
       for(int j=0; j<nx; j++)
    {
       map[line][j]=Integer.parseInt(sArray[j]);//change string to int
      }
      line++;
      }
    reader.close();
    } catch (IOException e) {
    e.printStackTrace();
    } finally {
   if (reader != null) {
      try {
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
        
        nx = nrow;
        ny = ncol;
        
        //the map must dimensions must not exceed 2000 x 2000
        if(nx > 2000)
            nx = 2000;
        if(ny > 2000)
            ny = 2000;
        
        map = new int[nx][ny];
        
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
     * This method returns the number of dry squares adjacent to the square at map[row][col]
     * using the square's data coordinates.
     * 
     * @param row The row of the square's data coordinates.
     * @param col The column of the square's data coordinates.
     * 
     * @return The number of dry neighbors
     */
    
    public int getDryNeighbors(int row, int col)
    {
       //the number of dry neighbors
        int up = 0;
        int down =0;
        int left =0;
        int right =0;
   
        /*
         * Finally for the general case where the square is not in a corner or on one of the sides
         * of the map
         */
    
        if(isDry(row,col - 1)){
             if(col==0){
            left=0;
             }
             else left++;
        }     
        if(isDry(row,col + 1)){
             if(col==getNCols()){
            right=0;
             }
             else right++;
        }    
        
        
        if(isDry(row - 1,col)){
            if(row==0){
            up=0;
            }
            else up++;
        }
        
        if(isDry(row + 1,col)){
            if(row==getNRows()){
            down=0;
        }
            else down++;
        }
        int neighbors = up+down+left+right;
   
        
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
        return nx;
    }
    
    /**
     * This method returns the number of rows in the map.
     * 
     * @return The number of rows in the map.
     */
    
    public int getNRows()
    {
        return ny;
    }
    
    
    

}

