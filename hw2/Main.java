import java.util.ArrayList;
import java.util.List;

public class Main {
    private static volatile int v = 0;

    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
        int numExperiments = Integer.parseInt(args[1]);
        String seed = args[2];
        List<Long> volatileData;
        List<Long> nonVolatileData;

        //TASK 1: Performance of programs with and without caching

        //loop 1: Volatile
        long totalSum = 0;
        long totalTime = 0;
        volatileData = new ArrayList<Long>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Start timer
            long endTime;
            long runningTotal = 0;
            //v must be declared as part of the class
            long startTime = System.nanoTime();
            
            while(v < size){
                //running total of addition and subtraction operations using the loop variable
                if(v % 2 == 0){
                    runningTotal += v;
                } else {
                    runningTotal -= v;
                }
                v++;
            }
            //End Timer
            endTime = System.nanoTime();
            volatileData.add(new Long(endTime-startTime)); //Store individual data
            totalTime += (endTime-startTime); // in Nano seconds
            totalSum += runningTotal;
        }
        //TODO it might be worth writing this information to a .csv file so I can format it
        long averageTimeVolatile = totalTime / numExperiments;
        long averageSumVolatile = totalSum / numExperiments;

        //loop 2: Non-Volatile
        totalSum = 0;
        totalTime = 0;
        nonVolatileData = new ArrayList<Long>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Start timer
            long endTime;
            long runningTotal = 0;
            int i = 0; //Variable is declared here
            long startTime = System.nanoTime();
            
            while(i < size){
                //running total of addition and subtraction operations using the loop variable
                if(i % 2 == 0){
                    runningTotal += i;
                } else {
                    runningTotal -= i;
                }
                i++;
            }
            //End Timer
            endTime = System.nanoTime();
            volatileData.add(new Long(endTime-startTime)); //Store individual data
            totalTime += (endTime-startTime); // in Nano seconds
            totalSum += runningTotal;
        }
        //TODO it might be worth writing this information to a .csv file so I can format it
        long averageTimeNonVolatile = totalTime / numExperiments;
        long averageSumNonVolatile = totalSum / numExperiments;

        System.out.println("Average");

        //TASK 2
        Integer[] ronaldo = new Integer[size];
        //populate the array with random numbers

        //Experiments loop
        //Start timer
        //Access first 10%
        //Stop timer
        //start other timer
        //Access random element in the last 10% of the array
        //end other timer

        //Average sum of the elements ???

        //TASK 3
        

    }
}
