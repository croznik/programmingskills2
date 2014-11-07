/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author s1023011
 */

import groupproject.*;
import org.junit.*;
import static org.junit.Assert.*;

public class GridMapTest {
    
  GridMap gridMap;
    
    @Before
    public void setUpGridMapTest(){
         int[][] inMap = new int[3][3];
   
     for(int i =0; i<3; i++)
     {
        for(int j=0; j<3 ; j++)
        {
            
           inMap[i][j] =1;
       }
        gridMap = new GridMap(inMap);
    }
    }
  
    
    @Test
    public void testIsDry(){
             assertEquals("Check is dry for single square", true, gridMap.isDry(2,2));
    }
    
    @Test
    public void testDryNeighborsOnCorner(){
   
          assertEquals("Check that dry neighbors on a corner is 2 for this map.", 2, gridMap.getDryNeighbors(0,0));
    }
    
    @Test
    public void testIsInMap(){
         assertEquals("Check is in map", true, gridMap.isInMap(1,1));
    }
        
    
  
 
    @Test
    public void testDryNeighborsOutSideMap(){
  
        assertEquals("Check that neighbours outside of map area are 0.", 0, gridMap.getDryNeighbors(-1,-2));
    }
    
    @Test
    public void testTotalDrySquares(){
        
        assertEquals("Check total dry land calculation.", 9, gridMap.totalDryLand());
    }
   
       
    
}
