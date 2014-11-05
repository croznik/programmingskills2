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
    
    int[][] inMap = new int[][]{{1,1,1},{1,1,1},{1,1,1}};
    GridMap gridMap = new GridMap(inMap);
    
    
    @Test
    public void testDryNeighborsOutSideMap(){
       
        assertEquals("Check that neighbours outside of map area are 0.", 0, gridMap.getDryNeighbors(-1,-2));
    }
    
    @Test
    public void testDryNeighborsOnCorner(){
        
        assertEquals("Check that dry neighbors on a corner is 2 for this map.", 2, gridMap.getDryNeighbors(0,0));
    }
    
    @Test
    public void testIsInMap(){
        assertEquals("Check is in map", true, gridMap.isInMap(0,0));
    }
        
    
    
    
    
    
    
}
