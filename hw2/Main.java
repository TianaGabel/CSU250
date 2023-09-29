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
        List<Long> firstTenData;
        List<Long> lastTenData;

        //TASK 1: Performance of programs with and without caching
        task1(numExperiments,size,"Volatile"); //TODO this will not work
        task1(numExperiments,size,"Non-Volatile");

        System.out.println("Average");

        //TASK 2
        Integer[] ronaldo = new Integer[size];
        //populate the array with random numbers

        Long firstTenTotalTime = (long)0;
        Long lastTenTotalTime = (long)0;
        firstTenData = new ArrayList<Long>();
        lastTenData = new ArrayList<Long>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Part 1: Accessing the first 10%
            //Start timer
            long endTime;
            long startTime = System.nanoTime();
            //Access first 10%
            //Stop timer
            endTime = System.nanoTime();
            firstTenData.add(new Long(endTime-startTime));
            firstTenTotalTime += (endTime-startTime);

            //Part 2: Accessing a random value in the last 10%
            //start other timer
            startTime = System.nanoTime();
            //Access random element in the last 10% of the array
            //end other timer
            endTime = System.nanoTime();
            lastTenData.add(new Long(endTime-startTime));
            lastTenTotalTime += (endTime-startTime);
        }

        //Average sum of the elements ???

        //TASK 3
        //Allocate TreeSet for size 
        //Allocate LinkedList with size and fill with 0 to size

        //experiments loop
        //generate random number
        //start timer
        //TreeSet.contains(number)
        //End timer

        //start other timer
        //Ll contains
        //End other timer
        //Record data to totals + file
        //End experiments

    }

    public static Data task1(int numExperiments, int size, String loopVar){
        //loop
        long totalSum = 0;
        long totalTime = 0;
        ArrayList<Long> timeData = new ArrayList<Long>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Start timer
            long endTime;
            long startTime = 0;
            long runningTotal = 0;
            //v must be declared as part of the class
            if (loopVar.equals("Volatile")){
                startTime = System.nanoTime();
                while(v < size){
                    //running total of addition and subtraction operations using the loop variable
                    if(v % 2 == 0){
                        runningTotal += v;
                    } else {
                        runningTotal -= v;
                    }
                    v++;
                }
            } else if (loopVar.equals("Non-Volatile")){
                int i = 0;
                startTime = System.nanoTime();
                while(i < size){
                    //running total of addition and subtraction operations using the loop variable
                    if(i % 2 == 0){
                        runningTotal += i;
                    } else {
                        runningTotal -= i;
                    }
                    i++;
                }
            }
            //End Timer
            endTime = System.nanoTime();
            timeData.add(new Long(endTime-startTime)); //Store individual data
            totalTime += (endTime-startTime); // in Nano seconds
            totalSum += runningTotal;
        }
        Data data = new Data(timeData,totalTime,totalSum);
        return data;
    }
}
