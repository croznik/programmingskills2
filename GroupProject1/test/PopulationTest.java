/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import groupproject.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author s1023011
 */
public class PopulationTest {
    
    private GridMap gridMap;
    private Population pop;
    private double[][] testMat;
    private static double TOLERANCE = 1e-4;
    
    @Before
    public void setUpPopTest()
    {
       gridMap = new GridMap();
       pop = new Population(gridMap);
       testMat = new double[][]{{2,0,1},{2,2,2},{0,0,0}};
      
      
    }
    
    @Test
    public void testGetSquarePop(){
        assertEquals("Test the population of a square(0,0) correct(2)",2,pop.getPop(testMat, 0,0),TOLERANCE);
    }
    
    @Test
    public void testPopOffMapZero(){
        assertEquals("Test the pop of square off map is 0", 0, pop.getPop(testMat, 0, 0), TOLERANCE);
    }
    
    @Test
    public void testNoOccupiedSquares(){
        assertEquals("Test number of occupied squares is 5",5, pop.noOccupiedSquares(testMat));
    }
    
    @Test
    public void testGetTotalPop(){
        assertEquals("Test total pop of matrix correct(9)",9,pop.getTotalPop(testMat),TOLERANCE);
    }
    
    @Test
    public void testAdjPop(){
        assertEquals("Test adjacent pop of a square(0,0) is two.", 2, pop.getAdjPops(testMat, 0, 0), TOLERANCE);
    
    }
   
    @Test
    public void testAdjPopOffMap(){
        assertEquals("Test no adj pop for squares off map",0,pop.getAdjPops(testMat, -1, -4),TOLERANCE);
    }
    
    @Test
    public void testDensityWhenNoPop(){
        assertEquals("Test no density when no pop (rather than infinite)",0,pop.getPreyAverageDensity(),TOLERANCE);
    } 
    
    @Test
    public void testSquareIsPopulated(){
        assertEquals("Test square (0,0) is occupied.", true, pop.squareIsPopulated(testMat, 0, 0));
    }
    
}
