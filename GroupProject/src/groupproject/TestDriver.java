 

import java.io.*;
import java.util.*;

/**
 * @version 7 November 2014
 */
public class TestDriver
{
    
   public static void main(String[] arg) throws IOException
    {
        char yesNo;
        String fileName;
        Scanner cin = new Scanner(System.in);
        //GridMap map = new GridMap(20,20);
        //System.out.print(map.toString());
        GridMap landscape;
        
        
        /*
         * Below here when the user enters a file extension I want to read only the first line and
         * see how many rows and columns there are so that that I can send the number of rows and 
         * columns to the GridMap class
         */
        
        System.out.print("Do you want to read in a file to represent the landscape? Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.println("What is the path of the file that you want to read in? \nThe file must be a .txt file.");
            fileName = cin.nextLine();
            
            //I've built the two paths into the code here so that I can test it on my own computer; Colum Roznik
            fileName = new String("//Users/croznik/Desktop//Flashdrive//Fall 2014//Programming Skills//Group Project//Example Data Files//islands.txt");
            //fileName = new String("//Users/croznik/Desktop//Flashdrive//Fall 2014//Programming Skills//Group Project//Example Data Files//small.txt");
            
            File file = new File(fileName);
            Scanner inputFile = new Scanner(file);
            
            int nCol = inputFile.nextInt();
            int nRow = inputFile.nextInt();
            inputFile.close();
            
            landscape = new GridMap(nRow, nCol, fileName);
        }
        
        System.out.print("Do you want to change the hare birth rate? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new hare birth rate. \t");
        }
        
        System.out.print("Do you want to change the predation rate at which pumas eat hares? Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new predation rate rate. \t");
        }
        
        System.out.print("Do you want to change the puma birth rate? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new puma birth rate. \t");
        }
        
        System.out.print("Do you want to change the puma mortality rate? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new puma mortality rate. \t");
        }
        
        System.out.print("Do you want to change the hare diffusion rate? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new hare diffusion rate. \t");
        }
        
        System.out.print("Do you want to change the puma diffusion rate? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new puma diffusion rate. \t");
        }
        
        System.out.print("Do you want to change the number to timesteps in the program? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new number of timesteps. \t");
        }
        
        System.out.print("Do you want to change the puma population in a given square? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new puma diffusion rate. \t");
            
            System.out.print("Enter the row:\t");
            
            System.out.print("Enter the column:\t");
        }
        
        System.out.print("Do you want to change the hare population in a given square? Enter Y for yes and N for no:\t");
        yesNo = cin.nextLine().charAt(0);
        if(Character.toUpperCase(yesNo) == 'Y')
        {
            System.out.print("Enter the new puma diffusion rate. \t");
            
            System.out.print("Enter the row:\t");
            
            System.out.print("Enter the column:\t");
        }
        
        
        double[][] randomMap = new double[3][3];
        for(int i = 0; i < 3;i++)
        {
            for(int j = 0; j < 3;j++)
            {
                randomMap[i][j] = Math.random();
            }
        }
        
        int[][] randomMap1 = PrintMethods.convertDensityMapToColorMap(randomMap,10.0,1,200);
         for(int i = 0; i < 3;i++)
         {
            for(int j = 0; j < 3;j++)
            {
                System.out.print(randomMap1[i][j]+" ");
            }
            System.out.println("");
        }
         
        PrintWriter out = new PrintWriter(new FileWriter("Test.txt"));
        PrintMethods.printPPMFile(out, 5.0, randomMap);
        out.close();
    }
}
