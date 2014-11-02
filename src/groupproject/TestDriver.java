 

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
        char anotherSimulation = 'Y';
        String fileName;
        Scanner cin = new Scanner(System.in);
        Random rand =  new Random();
        String printLocation;
        //GridMap map = new GridMap(20,20);
        //System.out.print(map.toString());
        GridMap landscape;
        Population population;
        long startTimer;
        Puma puma = new Puma();
        Hare hare = new Hare();
        int row, column;
        
        while(Character.toUpperCase(anotherSimulation) == 'Y')
        {
        
        
            /*
             * Below here when the user enters a file extension I want to read only the first line and
             * see how many rows and columns there are so that that I can send the number of rows and 
             * columns to the GridMap class
             * 
             * TestDriver step 1
             */
            
            //         System.out.print("Do you want to read in a file to represent the landscape? Y for yes and N for no:\t");
            //         yesNo = cin.nextLine().charAt(0);
            //         if(Character.toUpperCase(yesNo) == 'Y')
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
                
                /*
                 * TestDriver step 2: Create GridMap Object
                 */
                landscape = new GridMap(nRow, nCol, fileName); 
                
                /*
                 * TestDriver step 3: Create Population object
                 */
                population = new Population(landscape);        
            }
            
            /*
             * TestDriver step 4: Lists the default values and asks user if he/she wants to change them.
             */
            
            System.out.print("Do you want to change the hare birth rate from the default of 0.08?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new hare birth rate: \t");
                hare.setBirthRate(cin.nextDouble());
            }
            
            System.out.print("Do you want to change the predation rate at which pumas eat hares from the default of 0.04?\nY for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new predation rate rate: \t");
                puma.setPredationRate(cin.nextDouble());
            }
            
            System.out.print("Do you want to change the puma birth rate from the default of 0.02?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new puma birth rate: \t");
                puma.setBirthRate(cin.nextDouble());
            }
            
            System.out.print("Do you want to change the puma mortality rate from the default of 0.06?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new puma mortality rate: \t");
                puma.setMortalityRate(cin.nextDouble());
            }
            
            System.out.print("Do you want to change the hare diffusion rate from the default of 0.2?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            { 
                System.out.print("Enter the new hare diffusion rate: \t");
                hare.setDiffusionRate(cin.nextDouble());
            }
            
            System.out.print("Do you want to change the puma diffusion rate from the default of 0.2?\nEnter Y for yes and N for no:\t");
            yesNo = cin.nextLine().charAt(0);
            if(Character.toUpperCase(yesNo) == 'Y')
            {
                System.out.print("Enter the new puma diffusion rate: \t");
                puma.setDiffusionRate(cin.nextDouble());
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
                population.set_numberTimeSteps(cin.nextDouble()); 
            }
            
            /*
             * TestDriver step 6: How does the user want to initialize the simulation
             */
            
            System.out.print("How do you want to start the simulation? Do you want to start by assigning hares and\n" +
                             "pumas into a particular square? If so enter 1. If you want to start the simulation by\n" +
                             "assigning random populations to each square in the landscape between 1 and 5 then enter\n" +
                             "another value besides 1:\t");
                             
            if(cin.nextLine().charAt(0) == '1')
            {
                /*
                 * Enter the puma population
                 */
                System.out.print("What is the row of the square where you want to assign a puma population?\t");
                row = cin.nextInt();
                
                System.out.print("What is the column of the square?\t");
                column = cin.nextInt();
                
                System.out.print("And what population do you want to assign to that square?\t");
                population.setSquarePop("puma", cin.nextDouble(), row, column);
                
                /*
                 * Enter the hare population
                 */
                System.out.print("What is the row of the square where you want to assign a hare population?\t");
                row = cin.nextInt();
                
                System.out.print("What is the column of the square?\t");
                column = cin.nextInt();
                
                System.out.print("And what population do you want to assign to that square?\t");
                population.setSquarePop("hare", cin.nextDouble(), row, column);
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
                            population.setSquarePop("hare", rand.nextInt(5) + 1, i, j);
                            population.setSquarePop("puma", rand.nextInt(5) + 1, i, j);
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
            
            for(int i = 0; i < 500; i++)
            {
                population.updatePop(puma, hare);
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
    }
}
