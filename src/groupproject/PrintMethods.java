/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

/**
 *
 * @author Sarah E. Beggs
 */
import java.io.*;

public class PrintMethods {

 
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
  public static void printPPMFile(PrintWriter outfile, double densityTotal, double[][] map, int colourIndex){
  //if colorindex not 0,1,2 throws exception (must be either red, green, blue)
  if((colourIndex<0) || colourIndex>2){
  throw new IllegalArgumentException("colour index must be 0 (red) or 1(green) or 2(blue) only");
  }
  //Initialize plain ppm
  outfile.println("P3");
  outfile.println("");
  //print dimension of map (hence pixels)
  outfile.println(map.length+" "+map[0].length);
  //This is the largest value rgb values can take
  outfile.println(255);
  //Print map to file with each square represented by a number that indicates a colour
  //Create a new matrix that has 3x as many columns
  int[][] outMap = new int[map.length][3*map[0].length];
 
  //Loop over input density map columns
  for(int i =0; i< map[0].length;i++){
  //Loop over rows
  for(int k=0; k<map.length;k++){
  //Assign to relevant column (based on colorIndex choice)
  outMap[k][(3*i)+colourIndex] = PrintMethods.convertDensityToRGB(map[k][i], densityTotal, 255);
  }
 
  }
  //Prints result to file
  outfile.print(PrintMethods.toString(outMap)); 
  }
 
 
 
 
  //To convert to RBG value take density, normalize (divide by total) * multiply by
  public static int convertDensityToRGB(double number, double total, int maxRGB){
 
  double colour = (number/total)*maxRGB;
  return (int)colour;

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

 
