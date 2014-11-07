package groupproject;
 

import java.util.*;
import java.io.*;


/**
 *
 * @author Sarah Beggs, Xiao Li, and Colum Roznik
 * @version 7 November 2014
 */
public class ReadingMethods 
{
    
         private Random random;
         private int printTime;
         private GridMap landscape;
         private Population population;
         private Puma puma;
         private Hare hare;
         
       //Not sure about throwing the FileNoFoundException might not be best way to do this.
       
        /**
         * This constructor creates creates a new ReadingMethods object which holds the
         * landscape's dimensions and creates new Population and GridMap objects.
         * 
         * @param landscapeFile The String value holding the extension to the landscape file.
         */
        public ReadingMethods(String landscapeFile) throws FileNotFoundException
        {
              random = new Random();
              printTime = 50;
              File file = new File(landscapeFile);
              Scanner landscapeScanner = new Scanner(file);
              int nCol = landscapeScanner.nextInt();
              int nRow = landscapeScanner.nextInt();
              landscapeScanner.close();
              landscape = new GridMap(nCol, nRow, landscapeFile);
              population = new Population(landscape);
              puma = new Puma();
              hare = new Hare();
        }
        
        public ReadingMethods()
        {
            random = new Random();
            printTime  = 50;
            puma = new Puma();
            hare = new Hare();
            population = null;
            landscape = null;
        
            
        }
        
        /**
         * This method returns the printTime field.
         * 
         * @return The printTime field.
         */
        public int getPrintTime()
        {
            return printTime;
        }
        
        /**
         * This setter method sets the printTime field.
         * 
         * @param printTime The new value for the printTime field.
         */
        public void setPrintTime(int printTime)
        {
            this.printTime = printTime;
        }
        
        /**
         * This method returns the population field.
         * 
         * @return The population field. 
         */
        public Population getPopulation()
        {
            return population;
        }
        
        /**
         * This method sets a new Population object to the population field.
         * 
         * @param population The new Population object for the population field.
         */
        public void setPopulation(Population population)
        {
            this.population = population;
        }
        
        /**
         * This method returns the value held in the landscape field.  
         * 
         * @return The landscape field.
         */
        public GridMap getLandscape()
        {
            return landscape;
        }
        
        /**
         * This method sets a new value to the landscape field.
         * 
         * @param landscape The new GridMap object for the landscape field. 
         */
        public void setLandscape(GridMap landscape)
        {
            this.landscape = landscape;
        }
        
        /**
         * This method returns the hare field
         * 
         * @return The Hare object held in the hare field.
         */
        public Hare getHare()
        {
            return hare;
        }
        
        /**
         * This method assigns a new value to the hare field. 
         * 
         * @param hare The new Hare object that is assigned to the hare field.
         */
        public void setHare(Hare hare)
        {
            this.hare = hare;
        }
        
        /**
         * This method returns the value in the puma field.
         * 
         * @return The value in the puma field.
         */
        public Puma getPuma()
        {
            return puma;
        }
        
        /**
         * This method assigns a new Puma object to the puma field.
         * 
         * @param puma The new Puma object that is assigned to the puma field. 
         */
        public void setPuma(Puma puma)
        {
            this.puma = puma;
        }
        
        /**
         * This method reads in the initial values from the user 
         */
        public void readInitialValuesFromUserTerminal() throws IOException
        {
            //Never use printlocation??
            String printLocation;
            int row, column;
            char yesNo;
            String fileName;
            
            char assignMorePops = 'Y';
            Scanner cin = new Scanner(System.in);
          
           
                       /*
             * Below here when the user enters a file extension I want to read only the first line and
             * see how many rows and columns there are so that that I can send the number of rows and 
             * columns to the GridMap class
             */
            
            
            System.out.println("What is the name of the file that you want to read in?.");
            fileName = cin.nextLine();
            
            //I've built the two paths into the code here so that I can test it on my own computer; Colum Roznik
            //
            //fileName = new String("//Users/croznik/Desktop//Flashdrive//Fall 2014//Programming Skills//Group Project//Example Data Files//islands.dat");
            //fileName = new String("//Users/croznik/Desktop//Flashdrive//Fall 2014//Programming Skills//Group Project//Example Data Files//small.txt");
            
            File file = new File(fileName);
            Scanner inputFile = new Scanner(file);
            
            int nCol = inputFile.nextInt();
            int nRow = inputFile.nextInt();
            inputFile.close();
            
            /*
             * Create GridMap Object
             */
            landscape = new GridMap(nRow, nCol, fileName); 
            
            /*
             *  Create Population object
             */
            population = new Population(landscape);  
             
           
               
                /*
                 *  Lists the default values and asks user if he/she wants to change them.
                 */
                
                System.out.print("Do you want to change the hare birth rate from the default of 0.08?\nEnter Y for yes and N for no:\t");
                yesNo = cin.nextLine().charAt(0);
                if(Character.toUpperCase(yesNo) == 'Y')
                {
                    System.out.print("Enter the new hare birth rate: \t");
                    hare.setBirthRate(cin.nextDouble());
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
                
                System.out.print("Do you want to change the puma birth rate from the default of 0.02?\nEnter Y for yes and N for no:\t");
                yesNo = cin.nextLine().charAt(0);
                if(Character.toUpperCase(yesNo) == 'Y')
                {
                    System.out.print("Enter the new puma birth rate: \t");
                    puma.setBirthRate(cin.nextDouble());
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
                
                System.out.print("Do you want to change the predation rate at which pumas eat hares from the default of 0.04?\nY for yes and N for no:\t");
                yesNo = cin.nextLine().charAt(0);
                if(Character.toUpperCase(yesNo) == 'Y')
                {
                    System.out.print("Enter the new predation rate rate: \t");
                    puma.setPredationRate(cin.nextDouble());
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
                
                /*
                 *  Ask user to choose T (# of time steps)
                 */
                
                
                System.out.print("Do you want to change deltaT from 0.4?\nEnter Y for yes and N for no:\t");
                yesNo = cin.nextLine().charAt(0);
                if(Character.toUpperCase(yesNo) == 'Y')
                {
                    System.out.print("Enter the new number of timesteps; it must be an integer: \t");
                    //it takes in a double below instead of an integer so that some of the calculations in the Population class are coherent and work properly
                    population.setDeltaT(cin.nextDouble()); 
                    cin.nextLine();
    
                }
                
                System.out.print("Do you want to change the number to timesteps in the program from 1,250?\nEnter Y for yes and N for no:\t");
                yesNo = cin.nextLine().charAt(0);
                if(Character.toUpperCase(yesNo) == 'Y')
                {
                    System.out.print("Enter the new number of timesteps; it must be an integer: \t");
                    //it takes in a double below instead of an integer so that some of the calculations in the Population class are coherent and work properly
                    population.setNumberTimeSteps(cin.nextDouble()); 
                    cin.nextLine();
    
                }
                
                System.out.println("Do you want to change how often the program prints to file\t");
                yesNo = cin.nextLine().charAt(0);
                if(Character.toUpperCase(yesNo)== 'Y')
                {
                    System.out.print("Enter a number of times to print to file. This must be an integer and less than the total number of time steps: \t");
                    //Need to check this works correctly 
                    printTime = cin.nextInt();
    
                    cin.nextLine();
    
                }
                
                /*
                 *  How does the user want to initialize the simulation
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
                                population.setSquarePop("prey", random.nextInt(6), i, j);
                                population.setSquarePop("predator", random.nextInt(6), i, j);
                            }
                        }
                    }
                }
                
                /*
                 *  Ask what path extension they want to send the files to.
                 */
                
                System.out.println("What is the path extension of the folder where you want to print the data\n" +
                                    "from the simulation?");
                printLocation = cin.nextLine();
            }   
        

        
        /**
         * The user can choose to read in the initial hare and puma population values across 
         * the landscape from a file and this method assigns the respective populations across
         * the landscape.
         * 
         * @param inputFile A String that hold the extension of the input file. 
         */
        public void readInitialValuesFromFile(String inputFile) throws IOException
        {
        BufferedReader file = new BufferedReader(new FileReader(inputFile));
        Scanner inputs = new Scanner(file);
       
      
        //Read rates from file
        double hareBirthRate = inputs.nextDouble();
        double hareDiffRate = inputs.nextDouble();
        double pumaBirthRate = inputs.nextDouble();
        double pumaDiffRate = inputs.nextDouble();
        double pumaPredRate = inputs.nextDouble();
        double pumaMortRate = inputs.nextDouble();
        puma = new Puma(pumaDiffRate,pumaBirthRate,pumaMortRate,pumaPredRate);
        hare = new Hare(hareDiffRate, hareBirthRate);
        //Need to read time steps and no print intervals from file.
        double deltaT = inputs.nextDouble();
        population.setDeltaT(deltaT);
        double noTimeSteps = inputs.nextDouble();
        population.setNumberTimeSteps(noTimeSteps);
        printTime = inputs.nextInt();
        
        //Assign Puma pop to certain square? 
        //Way to check whether want to assign multiple animal pops to the grid
        
        double fractionPuma = inputs.nextDouble();
        
        double fractionHare = inputs.nextDouble();
        double newPop =0;
        int row = 0;
        int col = 0;
        double percentPuma =  fractionPuma *100;
       // System.out.println(percentPuma);
        double percentHare = fractionHare *100;
        //System.out.println(percentHare);
         for(int i=0; i<percentPuma; i++){
             
             for(int k=0; k<landscape.getNRows(); k++)
            {
                for(int l =0; l<landscape.getNCols(); l++)
                {
                    if(landscape.isDry(k,l)){
                        population.setSquarePop("predator", random.nextInt(6), k, l);
              }
                }
            }                   
              
                
         }
         
         for(int j=0; j<percentHare; j++){
             
            for(int k=0; k<landscape.getNRows(); k++)
            {
                for(int l =0; l<landscape.getNCols(); l++)
                {
                    if(landscape.isDry(k,l)){
                        population.setSquarePop("prey", random.nextInt(6), k, l);
              }
                }
            }
         }

        }
}


        
      

