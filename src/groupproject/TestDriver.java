  

import java.io.*;
import java.util.*;

/**
 * @version 7 November 2014
 * @author Colum Roznik
 */
public class TestDriver
{
   public static void main(String[] arg) throws IOException
    {
        char yesNo;
        char anotherSimulation = 'Y';
        char assignMorePops = 'Y';
        String fileName;
        Scanner cin = new Scanner(System.in);
        Random rand =  new Random();
        String printLocation;
        //Will add something to allow the user to change this name
        PrintWriter densityOutFile = new PrintWriter(new FileWriter("Density.txt"));
        GridMap landscape;
        Population population;
        long startTimer;
        Puma puma = new Puma();
        Hare hare = new Hare();
        int row, column;
        int time;
        //Default to 50, user can change
        int printTime = 50;

        while(Character.toUpperCase(anotherSimulation) == 'Y')
        {
        
        
            /*
             * Below here when the user enters a file extension I want to read only the first line and
             * see how many rows and columns there are so that that I can send the number of rows and 
             * columns to the GridMap class
             * 
             * TestDriver step 1
             */
            
            
            System.out.println("What is the path of the file that you want to read in?.");
            fileName = cin.nextLine();
            
            //I've built the two paths into the code here so that I can test it on my own computer; Colum Roznik
            fileName = new String("//Users/croznik/Desktop//Flashdrive//Fall 2014//Programming Skills//Group Project//Example Data Files//islands.dat");
            //fileName = new String("//Users/croznik/Desktop//Flashdrive//Fall 2014//Programming Skills//Group Project//Example Data Files//small.txt");
            
            File file = new File(fileName);
            Scanner inputFile = new Scanner(file);
            
            int nCol = inputFile.nextInt();
            int nRow = inputFile.nextInt();
            inputFile.close();
            
            /*
             * TestDriver step 2: Create GridMap Object
             */
            landscape = new GridMap(nRow, nCol, fileName); 
            
            /*
             * TestDriver step 3: Create Population object
             */
            population = new Population(landscape);        
            
            
            /*
             * TestDriver step 4: Lists the default values and asks user if he/she wants to change them.
             */
            
            System.out.print("Do you want to change the hare birth rate from the default of 0.08?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new hare birth rate: \t");
                hare.setBirthRate(cin.nextDouble());
                cin.nextLine();
            }
            
            System.out.print("Do you want to change the predation rate at which pumas eat hares from the default of 0.04?\nY for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new predation rate rate: \t");
                puma.setPredationRate(cin.nextDouble());
                cin.nextLine();
            }
            
            System.out.print("Do you want to change the puma birth rate from the default of 0.02?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new puma birth rate: \t");
                puma.setBirthRate(cin.nextDouble());
                cin.nextLine();
            }
            
            System.out.print("Do you want to change the puma mortality rate from the default of 0.06?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new puma mortality rate: \t");
                puma.setMortalityRate(cin.nextDouble());
                cin.nextLine();
            }
            
            System.out.print("Do you want to change the hare diffusion rate from the default of 0.2?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            { 
                System.out.print("Enter the new hare diffusion rate: \t");
                hare.setDiffusionRate(cin.nextDouble());
                cin.nextLine();
            }
            
            System.out.print("Do you want to change the puma diffusion rate from the default of 0.2?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new puma diffusion rate: \t");
                puma.setDiffusionRate(cin.nextDouble());
                cin.nextLine();
            }
            
            /*
             * TestDriver step 5: Ask user to choose T (# of time steps)
             */
            
            System.out.print("Do you want to change the number to timesteps in the program from 1,250?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new number of timesteps; it must be an integer: \t");
                //it takes in a double below instead of an integer so that some of the calculations in the Population class are coherent and work properly
                population.setNumberTimeSteps(cin.nextDouble()); 

            }
            
            System.out.println("Do you want to change how often the program prints to file");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo)== 'Y')
            {
                System.out.print("Enter a number of times to print to file. This must be an integer and less than the total number of time steps: \t");
                //Need to check this works correctly 
                printTime = cin.nextInt();

                cin.nextLine();

            }
            
            /*
             * TestDriver step 6: How does the user want to initialize the simulation
             */
            
            System.out.print("How do you want to start the simulation? Do you want to start by assigning hares and\n" +
                             "pumas into particular squares? If so enter 1. If you want to start the simulation by\n" +
                             "assigning random populations to each square in the landscape between 1 and 5 then enter\n" +
                             "another value besides 1:\t");
                             
            if(cin.nextLine().charAt(0) == '1')
            {
                while(Character.toUpperCase(assignMorePops) == 'Y')
                {
                    /*
                     * Enter the puma population
                     */
                    System.out.print("What is the row of the square where you want to assign a puma population?\t");
                    row = cin.nextInt();
                    
                    System.out.print("What is the column of the square?\t");
                    column = cin.nextInt();
                    
                    System.out.print("And what population do you want to assign to that square?\t");
                    if(landscape.isDry(row, column)) //only assigns the population to the square if it's dry
                    {
                        population.setSquarePop("puma", cin.nextDouble(), row, column);
                    }
                    
                    System.out.print("Do you want to assign more puma populations into particular square?\nEnter Y for yes and N for no:\t");
                    assignMorePops = cin.nextLine().charAt(0);
                }
                
                assignMorePops = 'Y';
                
                while(Character.toUpperCase(assignMorePops) == 'Y')
                {
                    
                    /*
                     * Enter the hare population
                     */
                    System.out.print("What is the row of the square where you want to assign a hare population?\t");
                    row = cin.nextInt();
                    
                    System.out.print("What is the column of the square?\t");
                    column = cin.nextInt();
                    
                    System.out.print("And what population do you want to assign to that square?\t");
                    if(landscape.isDry(row, column)) //only assigns the population to the square if it's dry 
                    {
                        population.setSquarePop("hare", cin.nextDouble(), row, column);
                    }
                    
                    System.out.print("Do you want to assign more hare populations into particular square?\nEnter Y for yes and N for no:\t");
                    assignMorePops = cin.nextLine().charAt(0);
                }
                
                assignMorePops = 'Y';
            }
            else
            {
                /*
                 * Randomly assign a population to each land square on the map
                 */
                
                for(int i = 0; i < landscape.getNRows(); i++)
                {
                    for(int j = 0; j < landscape.getNCols(); j++)
                    {
                        /*
                         * If the square is dry then a population of between 1 and 5 will be assigned
                         */
                        if(landscape.isDry(i,j))
                        {
                            population.setSquarePop("hare", rand.nextInt(6), i, j);
                            population.setSquarePop("puma", rand.nextInt(6), i, j);
                        }
                    }
                }
            }
            
            /*
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
                    String hareFileName = "HarePPM" + Integer.toString(currentPrintTime) +".ppm";
                    String pumaFileName = "PumaPPM" + Integer.toString(currentPrintTime) +".ppm";
                    
                    PrintWriter harePPMFile = new PrintWriter(new FileWriter(hareFileName));
                    PrintWriter pumaPPMFile = new PrintWriter(new FileWriter(pumaFileName));
                    //Prints PPM files
                    PrintMethods.printPPMFile(pumaPPMFile, population.getTotalDensity(), population.getPredatorMap(), 0);
                    PrintMethods.printPPMFile(harePPMFile, population.getTotalDensity(), population.getPreyMap(), 2);
                    
                    
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
        }
        
        densityOutFile.close();
    }
}
