package groupproject;   

import java.io.*;
import java.util.*;

/**
 * @version 7 November 2014
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
 */
public class TestDriver
{
   public static void main(String[] args) throws IOException
    {
       
        ReadingMethods ivReadMethod = new ReadingMethods();   
        long startTimer;
        int time;
        
         
        if(args.length == 0)
        {
            
            ivReadMethod.readInitialValuesFromUserTerminal();
        }
       
        //Only need to give it landscape file and input file
        else if(args.length == 2)
        {
            ivReadMethod = new ReadingMethods(args[0]);
            ivReadMethod.readInitialValuesFromFile(args[1]);
        }
        
        
        
        else
        {
            System.out.println("Illegal Input type. Either have use ant run for to enter initial values through terminal. \n Or use TestDriver(landscapeFileName, inputFileName) to get the initial values from a file.");
            System.exit(-1);
        }
                               
         
        
            /*
             * TestDriver step 8: The timer of the execution of the simulation starts here. 
             */
            startTimer = System.currentTimeMillis();
            
            /*
             * TestDriver step 9: Run a loop updating the population each time, printing it to the file,
             * and printing average densities each time. 
             */

            time = 0;
            
            //I've changed the constructor of population to make the default time 0 this is now redundant. 
            //population.setTime(0);
            Puma puma = ivReadMethod.getPuma();
            Hare hare = ivReadMethod.getHare();
            Population population = ivReadMethod.getPopulation();
            
            String densityFileName = "AverageDensityFile.txt";
            PrintWriter densityOutFile = new PrintWriter(new FileWriter(densityFileName));
            int printTime = ivReadMethod.getPrintTime();
            
            int currentPrintTime = 0;
            for(int timeStep = 0; timeStep < population.getNumberTimeSteps(); timeStep++)
            {
                population.updatePop(puma, hare);
                //Only prints to file when timestep is a multiple of print time
                
                if(timeStep % printTime == 0)
                {
                    //Prints densities to file
                    PrintMethods.printDensityFile(densityOutFile, population);
                    //Creates a name for this prints PPM files
                    String hareFileName = "HarePPM" + Integer.toString(currentPrintTime) +".ppm";
                    String pumaFileName = "PumaPPM" + Integer.toString(currentPrintTime) +".ppm";
                    
                    PrintWriter harePPMFile = new PrintWriter(new FileWriter(hareFileName));
                    PrintWriter pumaPPMFile = new PrintWriter(new FileWriter(pumaFileName));
                    //Prints PPM files
                    PrintMethods.printPPMFile(pumaPPMFile, population.getTotalAnimalPopulation(), population.getPredatorMap(), 0);
                    PrintMethods.printPPMFile(harePPMFile, population.getTotalAnimalPopulation(), population.getPreyMap(), 2);
                    
                    
                    currentPrintTime++;
                    pumaPPMFile.close();
                    harePPMFile.close();
                }
                
                time++;
                population.setTime(time);
            }
            
            /*
             * TestDriver step 10: Stop the timer and show how long the simulation took to run 
             * The simulation ends right before this timer belows ends.
             */
            
            System.out.print("It took " + (System.currentTimeMillis() - startTimer) * 1000 +
            " seconds to execute the simulation. ");
        
            
             //TestDriver step 11: Ask user if they want to run another simulation
             
            /*
            System.out.print("Do you want to run another simulation? Enter Y for yes and N for no:\t");
            anotherSimulation = cin.nextLine().charAt(0);
            runNo++; 
            */
            
            densityOutFile.close();
        }
    }

