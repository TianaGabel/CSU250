import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static volatile int v = 0;

    public static void main(String[] args){
        //TODO delete this later
        args = new String[3];
        args[0] = "2500000";
        args[1] = "1";
        args[2] = "42";
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

        //TODO this is in nanoseconds not seconds
        System.out.println("Task 1");
        System.out.println("Regular: " + nonVolatileData.getAverageTime(numExperiments));
        System.out.println("Volatile: " + volatileData.getAverageTime(numExperiments));
        System.out.println("Avg regular sum: " + nonVolatileData.getAverageSum(numExperiments));
        System.out.println("Avg volatile sum: " + volatileData.getAverageSum(numExperiments));
        System.out.println("\n");

        //TASK 2
        Integer[] randomArray = generateRandomArray(size, seed);
        //populate the array with random numbers
        firstTenData = task2(numExperiments, randomArray, "First Ten");
        lastTenData = task2(numExperiments, randomArray,"Last Ten");

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
        for (int i =0; i<size;i++){
            ray[i] = rand.nextInt();
        }
        return ray;
    }

    public static Data task2(int numExperiments, Integer[] randArray, String accessPart){
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
                //TODO

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
                //TODO

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
