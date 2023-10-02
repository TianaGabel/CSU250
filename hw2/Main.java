import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

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
        Data treeSetData;
        Data linkedListData;

        //TASK 1: Performance of programs with and without caching
        volatileData = task1(numExperiments,size,"Volatile");
        nonVolatileData = task1(numExperiments,size,"Non-Volatile");

        System.out.println("Task 1");
        System.out.println("Regular:  " + (nonVolatileData.getAverageTime(numExperiments) * (Math.pow(10,-9))) + " seconds");
        System.out.println("Volatile: " + (volatileData.getAverageTime(numExperiments) * (Math.pow(10,-9))) + " seconds");
        System.out.println("Avg regular sum: " + nonVolatileData.getAverageSum(numExperiments));
        System.out.println("Avg volatile sum: " + volatileData.getAverageSum(numExperiments));
        System.out.println("\n");

        //TASK 2
        Integer[] randomArray = generateRandomArray(size, seed);
        //populate the array with random numbers
        firstTenData = task2(numExperiments, randomArray, "First Ten");
        lastTenData = task2(numExperiments, randomArray,"Last Ten");

        System.out.println("Task 2");
        System.out.println("Avg time to access known element:  ");
        System.out.println("Avg time to access random element: ");
        System.out.println("Sum: " + (firstTenData.getAverageSum(numExperiments)+lastTenData.getAverageSum(numExperiments)));
        System.out.println("\n");

        //Average sum of the elements ???

        //TASK 3
        treeSetData = task3(numExperiments,size,generateTreeSet(size));
        linkedListData = task3(numExperiments,size,generateLinkedList(size));

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

    public static TreeSet<Integer> generateTreeSet(int size){
        //This generates a tree set of size size and filled with numbers 0 to size
        TreeSet<Integer> unorderedList = new TreeSet<Integer>();
        for (int i = 0; i < size; i++){
            unorderedList.add(new Integer(i));
        }
        return unorderedList;
    }

    public static LinkedList<Integer> generateLinkedList(int size){
        //This generates a linkedlist of size size and filled with those numbers
        LinkedList<Integer> orderedList = new LinkedList<Integer>();
        for (int i = 0; i < size; i++){
            orderedList.add(new Integer(i));
        }
        return orderedList;
    }

    public static Data task3(int numExperiments, int size, Collection<Integer> numberSet){

        //experiments loop
        //generate random number
        //start timer
        //TreeSet.contains(number)
        //End timer
        //TODO THIS IS COPY PASTE FROM AN OLD METHOD!!!!
        Long totalTime = (long)0;
        Long totalSum = (long)0;
        ArrayList<Long> timeData = new ArrayList<>();
        //Experiment counter
            for (int i = 0; i < numExperiments; i++){
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
        Data data = new Data(timeData,totalTime,totalSum);
        return data;
    }
}
