/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

/**
 *Unit tests for PrintMethods class
 * @author Sarah E. Beggs
 */

import groupproject.*;
import java.io.*;
import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

        
public class PrintMethodsTest{
    
   
    Random random = new Random();


    
     @Test(expected = IllegalArgumentException.class)
     public void testColorIndexNotGreaterThanTwo() throws IOException{
      PrintWriter outfile = new PrintWriter(new FileWriter("UnitTestFile.ppm"));
      double[][] testMap = new double[5][5];
       for(int i =0; i<testMap.length;i++){
          for(int j =0; j<testMap[0].length;j++){
              testMap[i][j] = random.nextDouble();
          }
}
         PrintMethods.produceOneColorRGBMatrix(testMap, 100.0, 3,255);
     }
     
     @Test(expected = IllegalArgumentException.class)
     public void testColorIndexNotNegative() throws IOException{
         PrintWriter outfile = new PrintWriter(new FileWriter("UnitTestFile.ppm"));
       double[][] testMap = new double[5][5];
       for(int i =0; i<testMap.length;i++){
          for(int j =0; j<testMap[0].length;j++){
              testMap[i][j] = random.nextDouble();
          }
       }
          PrintMethods.produceOneColorRGBMatrix(testMap, 100.0, 3,255);
     }   
    
     
            
    
}

