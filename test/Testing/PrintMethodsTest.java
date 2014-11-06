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
    
     public void testCombinedPPM() throws IOException{
        PrintWriter test1 = new PrintWriter(new FileWriter("Test1.ppm"));
        PrintWriter test2 = new PrintWriter(new FileWriter("Test2.ppm"));
        PrintWriter test3 = new PrintWriter(new FileWriter("Test3.ppm"));
        int[][] testMat1 = new int[][]{{155,0,0,150,0,0,155,0,0},{255,255,255,255,255,255,255,255,255},{40,0,0,40,0,0,40,0,0},{155,0,0,150,0,0,155,0,0},{255,255,255,255,255,255,255,255,255},{40,0,0,40,0,0,40,0,0}};
        System.out.println(testMat1.length+" "+testMat1[0].length);
        int[][] testMat2 = new int[][]{{0,0,150,0,0,155,0,0,155},{255,255,255,255,255,255,255,255,255},{0,0,40,0,0,40,0,0,40},{0,0,150,0,0,155,0,0,155},{255,255,255,255,255,255,255,255,255},{0,0,40,0,0,40,0,0,40}};
         System.out.println(testMat2.length+" "+testMat2[0].length);
        int[][] testMat3 = PrintMethods.addTwoOneColorMatrices(testMat1,testMat2);
        PrintMethods.printPPMFile(test1,testMat1);
        PrintMethods.printPPMFile(test2,testMat2);
        PrintMethods.printPPMFile(test3,testMat3);
        test1.close();
        test2.close();
        test3.close();
        System.exit(0);
     }
     
            
    
}

