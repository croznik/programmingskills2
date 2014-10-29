package groupproject;

import java.io.*;
import java.util.Random;
public class TestDriver{
    
   public static void main(String[] arg)throws IOException
    {
        GridMap map = new GridMap(20,20);
        
        //GridMap map = new GridMap(50,50,"small.dat");//small.dat is in LREAN, just use this to test
        System.out.print(map.toString());
        Random random = new Random();
        PrintWriter out = new PrintWriter(new FileWriter("outTest.ppm"));
        double[][] testMap = new double[500][500];
        for(int i=0; i<testMap.length;i++){
            for(int j=0; j<testMap[0].length; j++){
                testMap[i][j] = random.nextDouble();
            }
        }
        System.out.println("");
        PrintMethods.printPPMFile(out,1.0,testMap,2);
        //System.out.print(PrintMethods.printPPMFile(out,1.0,testMap));
    out.close();  
    }
   
}
