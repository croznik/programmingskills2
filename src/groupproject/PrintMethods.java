package groupproject;

/**
 *
 * @author Sarah E. Beggs
 */
import java.io.*;

public class PrintMethods 
{

 
  //Prints average population densities at given time (can graph these to see behaviour
  /**
  *
  * @param output file
  * @param population class
  * Prints time (first column), predator density (2nd column), prey density (3rd column)
  *
  */
 
  public static void printDensityFile(PrintWriter outfile, Population p){
     double predatorDens = p.getPredatorAverageDensity();
     double preyDens = p.getPreyAverageDensity();
      //Prints to file 1st column time, 2nd column predator density, 3rd column prey density
      outfile.println(+p.getTime()+" "+predatorDens+" "+preyDens);

  }
 
  //Prints density grids to a file at a given time. Not used?
  /**
  * @param output file
  * @param Population class
  * Prints time on first line of file
  * Prints a blank line
  * Prints Predator map to file
  * Prints blank line
  * Prints Prey map to file
  * */
 
  public static void printDensityGridsFile(PrintWriter outfile, Population p){
          outfile.println(p.getTime());
          outfile.println("");
          outfile.print(PrintMethods.toString(p.getPredatorMap()));
          outfile.println("");
          outfile.print(PrintMethods.toString(p.getPreyMap()));
  }
 
  //PPM file writer method.
  /**
  * Method to print out a density grid to ppm
  * Have 1 grid cell = 1 pixel
  * Only have one color for picture (seems clearest way to visualize)
  * @param output file
  * @param total density (across grid)
  * @param map density map to be converted
  * @param colourIndex 0=red, 1=green, 2=blue
  *
  * */
  //Still don't like having to give the densitytotal the whole time (would be nice to just call get density)?
  public static void printPPMFile(PrintWriter outfile, int[][] map){
      
      if(map[0].length % 3 != 0){
           throw new IllegalArgumentException("Must convert to PPM matrix before printing PPM.");
      }
             //Initialize plain ppm
             outfile.println("P3");
             outfile.println("");
             //print dimension of map (hence pixels)
             outfile.println(map.length+" "+(map[0].length%3)); 
             //This is the largest value rgb values can take
             outfile.println(255);
             //Print map to file with each square represented by a number that indicates a colour
             outfile.print(PrintMethods.toString(map)); 
  }
  
  /**
   * Method to create an rgb matrix of one color with white squares for no population
   * @param double[][] map to be converted
   * @param double total density e.g.
   * @param color of matrix
   * @param maxRGB value 
   * return matrix with shades of one color red/ green/ blue
   * 
   * 
*/ 
  
  public static int[][] produceOneColorRGBMatrix(double[][] map, double total, int colorIndex, int maxRGB){
    if((colorIndex<0) || colorIndex>2){
            throw new IllegalArgumentException("colour index must be 0 (red) or 1(green) or 2(blue) only");
           }
    //Create a new matrix that has 3x as many columns
   int[][] outMap = new int[map.length][3*map[0].length];
    //Loop over input density map columns
        for(int i =0; i< map[0].length;i++)
        {
         //Loop over rows
           for(int k=0; k<map.length;k++)
           {
              //If there is no population in a square its rgb value is now set to 255, 255, 255 (white)
                if(map[k][i]==0){
                      for(int l =0; l <3; l++){
                          outMap[k][(3*1)+l] = 255;
            }
           } 
            else{    
                outMap[k][(3*i)+colorIndex] = PrintMethods.convertDensityToRGB(map[k][i], total, maxRGB);
              }
                
        }
     }
        
     return outMap;
     }
  
  public static int[][] addTwoOneColorMatrices(int[][] matrix1, int[][] matrix2){
     
   if(matrix1.length != matrix2.length ||matrix1[0].length != matrix2.length){
       throw new IllegalArgumentException("Cannot add matrices of different sizes.");
   }
     
   int[][] outMap = new int[matrix1.length][matrix1[0].length];
    
        for(int i =0; i< matrix1[0].length;i++)
        {
         
           for(int k=0; k<matrix1.length;k++)
           {
              //Two white cells added together stay white
                if(matrix1[k][i]== 255 && matrix2[k][i] ==255){
                     
                          outMap[k][i] = 255;
                   }
                //One white cell added to another color stays as non white color
                else if(matrix1[k][i] == 255 && matrix2[k][i] != 255){
                     outMap[k][i] = matrix2[k][i];
                }
                 //One white cell added to another color stays as non white color        
                else if(matrix1[k][i] != 255 && matrix2[k][i] == 255){
                    outMap[k][i] = matrix1[k][i];
                }
                 //Else can add the two one color matrices. If were mixed colors this method would give > 255 values but won't for two single color matrices
                 else{
                    outMap[k][i] = matrix1[k][i] + matrix2[k][i];
                }
           }
           } 
        return outMap;
  }
      
      
 
  //Reversed color order -- before very dense dark (black), no density (white)
  //Have changed to light color for lower density and dark for high density as more logical;
 /*To convert to RBG value take density, normalize (divide number by total) 
  then divide maxRGB value by normalization to get 0 color. 
  */
 public static int convertDensityToRGB(double number, double total, int maxRGB){

     double colorFraction = (maxRGB*total)/ number;
          
     return (int) colorFraction;

  }


 //To String method
 //Same as in GridMap class but has spaces before (as first need to space for ppm)
//Possibly condense/ put string methods into a class to make more elegant?
  public static String toString(int [][] map)
  {
  StringBuilder string = new StringBuilder();

  for(int i = 0; i < map.length; i++)
  {
  for(int j = 0; j < map[0].length; j++)
  {
   string.append(" "+map[i][j]); //adds the number to the StringBuilder
   }
   string.append("\n"); //starts a new line for the next row
   }

   return new String(string);
  }
  
  /**
   * Method to convert double array (e.g. density map) into a string
   * @param map
   * @return String representation of a map
   */
  public static String toString(double[][] map)
  {
   StringBuilder string = new StringBuilder();
   for(int i = 0; i < map.length; i++)
   {
    for(int j = 0; j < map[0].length; j++)
     {
       string.append(map[i][j] + " "); //adds the number to the StringBuilder
         }
       string.append("\n"); //starts a new line for the next row
        }
   return new String(string);
 } 
  




}

 
