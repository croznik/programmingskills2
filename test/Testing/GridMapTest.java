/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

/**
 *
 * @author s1023011
 */

import groupproject.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GridMapTest {
    
  
    
    @Test
    public void testIsDry(){
    int[][] inMap = new int[3][3];
   
     for(int i =0; i<3; i++)
     {
        for(int j=0; j<3 ; j++)
        {
            
           inMap[i][j] =1;
       }
     }
       GridMap gridMap = new GridMap(inMap);
       assertEquals("Check is dry for single square", true, gridMap.isDry(2,2));
    }
    
    @Test
    public void testDryNeighborsOnCorner(){
    int[][] inMap = new int[3][3];
   
       for(int i =0; i<3; i++)
       {
        for(int j=0; j<3 ; j++)
        {
            
           inMap[i][j] = 1;
         }
       }
         
   
        GridMap gridMap = new GridMap(inMap);
     

        assertEquals("Check that dry neighbors on a corner is 2 for this map.", 2, gridMap.getDryNeighbors(0,0));
    }
    
    @Test
    public void testIsInMap(){
    int[][] inMap = new int[3][3];
   
     for(int i =0; i<3; i++)
     {
        for(int j=0; j<3 ; j++)
        {
            
           inMap[i][j] =1;
       }
     }

    GridMap gridMap = new GridMap(inMap);
    
        assertEquals("Check is in map", true, gridMap.isInMap(1,1));
    }
        
    
  
 
    @Test
    public void testDryNeighborsOutSideMap(){
      int[][] inMap = new int[3][3];
   
     for(int i =0; i<3; i++)
     {
        for(int j=0; j<3 ; j++)
        {
            
           inMap[i][j] =1;
       }
     }
         
   
    GridMap gridMap = new GridMap(inMap);
       
       
        assertEquals("Check that neighbours outside of map area are 0.", 0, gridMap.getDryNeighbors(-1,-2));
    }
    
   
       
    
}
