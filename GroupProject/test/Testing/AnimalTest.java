/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import groupproject.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author 
 */
public class AnimalTest {
    
    private Animal animalObj;
    private static final double TOLERANCE = 1e-10;
    
 
  //Test to see whether isPredator throws illegal argument exception if not a predator or prey
  @Test(expected = IllegalArgumentException.class)
  public void testIsNotPredatorOrPrey(){
      animalObj = new Animal("blah", 0.3, 0.2);
      animalObj.isPredator();
     
  }
  
  @Test
  public void preyMortalityRate(){
      animalObj = new Animal("prey", 0.3, 0.2);
      animalObj.setMortalityRate(0.3);
      assertEquals("Test prey mortality rate is 0.0", 0.0, animalObj.getMortalityRate(),TOLERANCE);
      
  }
  
   @Test
  public void preyPredationRate(){
      animalObj = new Animal("prey", 0.3, 0.2);
      animalObj.setPredationRate(0.3);
      assertEquals("Test prey predation rate is 0.0", 0.0, animalObj.getPredationRate(),TOLERANCE);
      
  }
    
}

