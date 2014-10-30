 

import java.io.*;

/**
 * @version 7 November 2014
 */
public class TestDriver{
    
   public static void main(String[] arg)throws IOException
    {
        GridMap map = new GridMap(20,20);
        System.out.print(map.toString());
        double[][] randomMap = new double[3][3];
        for(int i =0; i <3;i++){
            for(int j=0; j<3;j++){
                randomMap[i][j] = Math.random();
            }
        }
        int[][] randomMap1 = PrintMethods.convertDensityMapToColorMap(randomMap,10.0,1,200);
         for(int i =0; i <3;i++){
            for(int j=0; j<3;j++){
                System.out.print(randomMap1[i][j]+" ");
            }
            System.out.println("");
        }
         
        PrintWriter out = new PrintWriter(new FileWriter("Test.txt"));
        PrintMethods.printPPMFile(out, 5.0, randomMap);
        out.close();
    }
}
