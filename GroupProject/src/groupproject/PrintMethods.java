/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
import java.io.*;

/**
 *
 * @author s1023011
 * @version 7 November 2014
 */
public class PrintMethods {
    
  
   //Prints average population at given time
   public static void printDensityFile(PrintWriter outfile, Population p){
       double predatorDens = p.getPredatorAverageDensity();
       double preyDens = p.getPreyAverageDensity();
       //Prints to file 1st column time, 2nd column predator density, 3rd column prey density
       outfile.println(+p.getT()+" "+predatorDens+" "+preyDens);   
       
   }
   
   //PPM file writer method. Almost complete just need to work out colour conversions.
   //Will get rid of totalDensity just place hol
   public static void printPPMFile(PrintWriter outfile, double densityTotal, double[][] map){
       outfile.println("P3");
       outfile.println("");
       outfile.println(map.length+" "+map[0].length);
       //This is the largest value rgb values can take
       outfile.println(255);
       //Print map to file with each square represented by a number that indicates a colour
       //double densityTotal = p.getTotalPop(map);
       
       for(int i =0; i< map.length;i++){
           PrintMethods.convertDensityMapToColorMap(map, densityTotal, 1, 255);
           outfile.print(PrintMethods.toString(map)+" ");
       }
       
           
   }
   
   //To convert to RBG value take density, normalize (divide by total) * multiply by 
   public static int convertDensityToRGB(double number, double total, int maxRGB){
       double colour = (number/total)*maxRGB;
       return (int)colour;
       
   }
   
   //Only converts one column to a color
    public static int[][] convertDensityMapToColorMap(double[][] map, double total, int rgbColumn, int colorMax){
        int[][] outArray = new int[map.length][3];
        for(int i=0; i<map.length;i++){
           outArray[i][rgbColumn] = PrintMethods.convertDensityToRGB(map[i][rgbColumn],total,colorMax);
    }
        return outArray;
    }
    
       public static String toString(double[][] map)
    {
        StringBuilder string = new StringBuilder();
             
        
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                string.append(map[i][j] + " ");     //adds the number to the StringBuilder
            }
            
            string.append("\n"); //starts a new line for the next row
        }
     
        
        return new String(string);
    }
        
       
   
}
