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
public class HareTest {
    
private Hare hare = new Hare();
private static double TOLERANCE = 1e-10;
    
 @Test
 public void testIsPrey(){
     assertEquals("Test hare is prey", false, hare.isPredator());
      
 
 }
 
 @Test
 public void testDefaultBirthRate(){
     assertEquals("Test Default birth rate is 0.08",0.08,hare.getBirthRate(),TOLERANCE);
 }
 
 @Test
 public void testDefaultDiffusionRate(){
     assertEquals("Test Default diffusion rate is 0.2",0.2,hare.getDiffusionRate(),TOLERANCE);
 }
 
  @Test 
    public void testDefaultMortalityRate(){
        assertEquals("Test default  mortality rate is 0.00",0.00,hare.getMortalityRate(),TOLERANCE);
           }
    
   @Test
   public void testDefaultPredationRate(){
       assertEquals("Test default predation rate is 0.00 ",0.00,hare.getPredationRate(),TOLERANCE);
   }
        
    
}