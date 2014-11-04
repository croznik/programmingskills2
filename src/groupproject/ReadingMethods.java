/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.util.*;
import java.io.*;


/**
 *
 * @author s1023011
 */
public class ReadingMethods {
    
         private Random random;
         private int printTime;
        
         private GridMap landscape;
         private Population population;
         private Puma puma;
         private Hare hare;
         
       //Not sure about throwing the FileNoFoundException might not be best way to do this.
        public ReadingMethods(String landscapeFile) throws FileNotFoundException{
            
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
        
        public int getPrintTime(){
            return printTime;
        }
        
        public void setPrintTime(int printTime){
            this.printTime = printTime;
        }
        
        public Population getPopulation(){
            return population;
        }
        
        public void setPopulation(Population population){
            this.population = population;
        }
        
        public GridMap getLandscape(){
            return landscape;
        }
        
        public void setLandscape(GridMap landscape){
            this.landscape = landscape;
        }
        
        public Hare getHare(){
            return hare;
        }
        
        public void setHare(Hare hare){
            this.hare = hare;
        }
        
        public Puma getPuma(){
            return puma;
        }
        
        public void setPuma(Puma puma){
            this.puma = puma;
        }
        
    
        public void readInitialValuesFromUserTerminal() throws IOException{
            

          
        String printLocation;
  
        int row, column;
        char yesNo;
        char anotherSimulation = 'Y';
        char assignMorePops = 'Y';
        Scanner cin = new Scanner(System.in);

        while(Character.toUpperCase(anotherSimulation) == 'Y')
        {
             //Sarah: I've made the constructor do the first 3 stages;
            //Create class with input file (if not in 
       
           
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
                            population.setSquarePop("hare", random.nextInt(6), i, j);
                            population.setSquarePop("puma", random.nextInt(6), i, j);
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
        }
    
}
        
        
        public void readInitialValuesFromFile(String inputFile) throws IOException{
        
        Scanner inputs = new Scanner(inputFile);
       
      
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
        double noTimeSteps = inputs.nextDouble();
        population.setNumberTimeSteps(noTimeSteps);
        printTime = inputs.nextInt();
        //Assign Puma pop to certain square? 
        //Way to check whether want to assign multiple animal pops to the grid
        int noPumaInitSquares = inputs.nextInt();
        int row=0;
        int col=0;
        if(noPumaInitSquares ==0){
              for(int i = 0; i < landscape.getNRows(); i++)
                {
                    for(int j = 0; j < landscape.getNCols(); j++)
                    {
                        /*
                         * If the square is dry then a population of between 1 and 5 will be assigned
                         */
                        if(landscape.isDry(i,j))
                        {
                          population.setSquarePop("puma", random.nextInt(6), i, j);
                        }
                     }
                }
        }
        else{
        for(int i=0; i<noPumaInitSquares; i++){
            row = inputs.nextInt();
            col = inputs.nextInt();
            if(landscape.isDry(row,col)){
            population.setSquarePop("puma", inputs.nextDouble(), row, col);
            }
        }
        }
        
        int noHareInitSquares = inputs.nextInt();
        if(noHareInitSquares == 0){
                     for(int i = 0; i < landscape.getNRows(); i++)
                {
                    for(int j = 0; j < landscape.getNCols(); j++)
                    {
                        /*
                         * If the square is dry then a population of between 1 and 5 will be assigned
                         */
                        if(landscape.isDry(i,j))
                        {
                            population.setSquarePop("hare", random.nextInt(6), i, j);
                           
                        }
                     }
                }
        }
        else{
        
        for (int j=0; j<noHareInitSquares; j++){
            row = inputs.nextInt();
            col = inputs.nextInt();
            if(landscape.isDry(row,col)){
            population.setSquarePop("hare", inputs.nextDouble(), row, col);
            }
        }
        }
     }
}
