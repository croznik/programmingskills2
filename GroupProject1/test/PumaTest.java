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
 * @author Main
 */
public class PumaTest {
 
    private Puma puma = new Puma();
    private static double TOLERANCE = 1e-10;
    
    @Test
    public void testIsPredator(){
       assertEquals("Test is predator",true, puma.isPredator());
       
    }
    
    @Test
    public void testDefaultDiffusionRate(){
        assertEquals("Test default diffusion rate is 0.2",0.2,puma.getDiffusionRate(),TOLERANCE);
    }
    
    @Test
    public void testDefaultBirthRate(){
        assertEquals("Test defaul birth rate is 0.02",0.02,puma.getBirthRate(),TOLERANCE);
    }
    
    @Test 
    public void testDefaultMortalityRate(){
        assertEquals("Test default mortality rate is 0.06",0.06,puma.getMortalityRate(),TOLERANCE);
           }
    
   @Test
   public void testDefaultPredationRate(){
       assertEquals("Test default predation rate is 0.04 ",0.04,puma.getPredationRate(),TOLERANCE);
   }
}