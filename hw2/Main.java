import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static volatile int v = 0;

    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
        int numExperiments = Integer.parseInt(args[1]);
        Long seed = Long.parseLong(args[2]);
        Data volatileData;
        Data nonVolatileData;
        Data firstTenData;
        Data lastTenData;

        //TASK 1: Performance of programs with and without caching
        volatileData = task1(numExperiments,size,"Volatile");
        nonVolatileData = task1(numExperiments,size,"Non-Volatile");

        System.out.println("Task 1");
        //Regular: time seconds
        //Avg regular sum sum

        //TASK 2
        Integer[] ronaldo = generateRandomArray(size, seed);
        //populate the array with random numbers

        firstTenData = task2(numExperiments, "First Ten");
        lastTenData = task2(numExperiments, "Last Ten");

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

    public static Integer[] generateRandomArray(int size, long seed){
        Integer[] ray = new Integer[size];
        Random rand = new Random(seed);

        return ray;
    }

    public static Data task2(int numExperiments, String accessPart){
        Long totalTime = (long)0;
        Long totalSum = (long)0;
        ArrayList<Long> timeData = new ArrayList<>();
        //Experiment counter
        if (accessPart.equals("First Ten")){
            for (int j = 0; j < numExperiments; j++){
                //Start timer
                long endTime;
                long startTime = System.nanoTime();
                //Access first 10%


                //Stop timer
                endTime = System.nanoTime();
                timeData.add(new Long(endTime-startTime));
                totalTime += (endTime-startTime);
            }
        }else if (accessPart.equals("Last Ten")){
            for (int j = 0; j < numExperiments; j++){
            //Start timer
            long endTime;
            long startTime = System.nanoTime();
            //Access element in last 10%


            //Stop timer
            endTime = System.nanoTime();
            timeData.add(new Long(endTime-startTime));
            totalTime += (endTime-startTime);
            }
        }
        Data data = new Data(timeData,totalTime,totalSum);
        return data;
    }
}
