package groupproject;  

import java.io.*;
import java.util.*;

/**
 * @version 7 November 2014
 * @author Colum Roznik
 */
public class TestDriver
{
   public static void main(String[] args) throws IOException
    {
        char yesNo;
        char anotherSimulation = 'Y';
        Scanner cin = new Scanner(System.in);
        String printLocation;
      
        //Landscape file is always first argument
        ReadingMethods ivReadMethod = new ReadingMethods(args[0]);   
        long startTimer;
        
        int row, column;
        int time;
        int runNo = 0;
         
        if(args[1].compareTo("terminal")==0 && args.length == 2){
            ivReadMethod.readInitialValuesFromUserTerminal();
        }
        
        else if(args[1].compareTo("file")==0 && args.length == 3){
            ivReadMethod.readInitialValuesFromFile(args[2]);
        }
        
        //Need to add a default action (set up Map from landscape file, set up Population, Puma, Hare in random squares -- reader)
        else if(args.length == 1){
            
        }
        
        else{
            System.out.println("Illegal Input type. Either have use TestDriver(landscapeFile, terminal) for to enter initial values through terminal. \n Or use TestDriver(landscapeFile, file, inputFile) to get the initial values from a file. \n Else use TestDriver(landscapeFile) to use the default valiues for everything");
            System.exit(-1);
        }
                               
        while(Character.toUpperCase(anotherSimulation) == 'Y')
        {
            /*I've left this here for now think will need to move.
             * TestDriver step 7: Ask what path extension they want to send the files to.
             */
            
            System.out.println("What is the path extension of the folder where you want to print the data\n" +
                                "from the simulation?");
            printLocation = cin.nextLine();
        
            /*
             * TestDriver step 8: The timer of the execution of the simulation starts here. 
             */
            startTimer = System.currentTimeMillis();
            
            /*
             * TestDriver step 9: Run a loop 500 times updates the population each time, printing it to the file,
             * and printing average densities each time. 
             */

            time = 0;
            
            //I've changed the constructor of population to make the default time 0 this is now redundant. 
            //population.setTime(0);
            Puma puma = ivReadMethod.getPuma();
            Hare hare = ivReadMethod.getHare();
            Population population = ivReadMethod.getPopulation();
            String stringRunNo = Integer.toString(runNo);
            String densityFileName = "AverageDensityFile"+stringRunNo+".txt";
            PrintWriter densityOutFile = new PrintWriter(new FileWriter(densityFileName));
            int printTime = ivReadMethod.getPrintTime();
            
            
            for(int timeStep = 0; timeStep < population.getNumberTimeSteps(); timeStep++)
            {
                population.updatePop(puma, hare);
                //Only prints to file when timestep is a multiple of print time
                int currentPrintTime = 0;
                if(timeStep % printTime == 0)
                {
                    //Prints densities to file
                    PrintMethods.printDensityFile(densityOutFile, population);
                    //Creates a name for this prints PPM files
                    String hareFileName = "Run"+stringRunNo+"HarePPM" + Integer.toString(currentPrintTime) +".ppm";
                    String pumaFileName = "Run"+stringRunNo+"PumaPPM" + Integer.toString(currentPrintTime) +".ppm";
                    
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
            
            /*
             * TestDriver step 11: Ask user if they want to run another simulation
             */
            
            System.out.print("Do you want to run another simulation? Enter Y for yes and N for no:\t");
            anotherSimulation = cin.nextLine().charAt(0);
            runNo++;
            
            densityOutFile.close();
        }
    }
}

