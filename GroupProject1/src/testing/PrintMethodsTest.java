/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

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
    
    private Random random;
    
    private double[][] testMap1;
    private int[][] testMap2, testMap3, testMap4;
    
   @Before
    public void setUP(){
       
   random = new Random();
   testMap1 = new double[10][10];
   testMap2 = new int[8][8];
   testMap3 = new int[3][2];
   testMap4 = new int[8][8];
   
   for(int i =0; i<testMap1.length; i++)
   {
       for(int j=0; j<testMap1[0].length;j++)
       {
           testMap1[i][j] = random.nextDouble();
       }
   }
   }
       
  /**
    * 
    * Tests to make sure color index can only be 0,1,2 by testing index cannot 
    * be greater than 2 or less than 0.
    * */
   
     @Test(expected = IllegalArgumentException.class)
     public void testColorIndexNotGreaterThanTwo() {

         PrintMethods.produceOneColorRGBMatrix(testMap1, 100.0, 3);
     }
     
     @Test(expected = IllegalArgumentException.class)
     public void testColorIndexNotNegative() throws IOException{
 
          PrintMethods.produceOneColorRGBMatrix(testMap1, 100.0, -1);
     }   
     
     
     @Test(expected = IllegalArgumentException.class)
     public void cannotAddDiffSizedMatrices(){
         PrintMethods.addTwoOneColorMatrices(testMap2, testMap3);
     }
     
     @Test(expected = IllegalArgumentException.class)
     public void ppmMatrixColNoMustDivideby3Exactly(){
         PrintMethods.addTwoOneColorMatrices(testMap2,testMap4);
     }
     /*
     
    
     //Not sure this is strictly speaking a proper unit test more of a run test.
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
     
     */
     
            
    
}

