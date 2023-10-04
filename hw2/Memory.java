import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;

public class Memory {
    private static volatile int v = 0;

    public static void main(String[] args){
        //TODO delete this later
        //args = new String[3];
        //args[0] = "2500000";
        //args[1] = "20";
        //args[2] = "42";
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
        volatileData = task1Volatile(numExperiments,size);
        nonVolatileData = task1NonVolatile(numExperiments,size);

        System.out.println("Task 1");
        System.out.println("Regular:  " + (nonVolatileData.getAverageTime(numExperiments) * (Math.pow(10,-9))) + " seconds");
        System.out.println("Volatile: " + (volatileData.getAverageTime(numExperiments) * (Math.pow(10,-9))) + " seconds");
        System.out.println("Avg regular sum:  " + nonVolatileData.getAverageSum(numExperiments));
        System.out.println("Avg volatile sum: " + volatileData.getAverageSum(numExperiments));
        System.out.println("\n");

        //TASK 2
        Integer[] randomArray = generateRandomArray(size, seed);
        //populate the array with random numbers
        firstTenData = task2FirstTen(numExperiments, randomArray);
        lastTenData = task2LastTen(numExperiments, randomArray);

        System.out.println("Task 2");
        System.out.println("Avg time to access known element:  " + firstTenData.getAverageTime(numExperiments) + " nanoseconds");
        System.out.println("Avg time to access random element: " + lastTenData.getAverageTime(numExperiments) + " nanoseconds");
        System.out.println("Sum: " + (firstTenData.getAverageSum(numExperiments)+lastTenData.getAverageSum(numExperiments)));
        System.out.println("\n");

        //TASK 3
        treeSetData = task3(numExperiments,size,generateTreeSet(size));
        linkedListData = task3(numExperiments,size,generateLinkedList(size));

        System.out.println("Task 3");
        System.out.println("Avg time to find in set:  " + treeSetData.getAverageTime(numExperiments) + " nanoseconds");
        System.out.println("Avg time to find in list: " + linkedListData.getAverageTime(numExperiments) + " nanoseconds");

    }

    public static Data task1Volatile(int numExperiments, int size){
        //loop
        long totalSum = 0;
        double totalTime = 0;
        ArrayList<Long> timeData = new ArrayList<Long>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Start timer
            double endTime;
            double startTime = 0;
            long runningTotal = 0;
            //v must be declared as part of the class
            v=0;
            startTime = System.nanoTime();
            while(v < size){
                //running total of addition and subtraction operations using the loop variable
                if((v % 2) == 0){
                    runningTotal += v;
                } else {
                    runningTotal -= v;
                }
                v++;
            }
            //End Timer
            endTime = System.nanoTime();
            //timeData.add((endTime-startTime)); //Store individual data
            totalTime += (endTime-startTime); // in Nano seconds
            totalSum += runningTotal;
        }
        Data data = new Data(timeData,totalTime,totalSum);
        return data;
    }

    public static Data task1NonVolatile(int numExperiments, int size){
        //loop
        long totalSum = 0;
        double totalTime = 0;
        ArrayList<Long> timeData = new ArrayList<Long>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Start timer
            double endTime;
            double startTime = 0;
            long runningTotal = 0;
            int i = 0;
            //v must be declared as part of the class
            startTime = System.nanoTime();
            while(i < size){
                //running total of addition and subtraction operations using the loop variable
                if((i % 2) == 0){
                    runningTotal += i;
                } else {
                    runningTotal -= i;
                }
                i++;
            }
            //End Timer
            endTime = System.nanoTime();
            //timeData.add((endTime-startTime)); //Store individual data
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

    public static Data task2FirstTen(int numExperiments, Integer[] randArray){
        Double totalTime = (double) 0;
        Long totalSum = (long)0;
        ArrayList<Long> timeData = new ArrayList<>();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Rather than using integer division, to account for smaller numbers
            int endIndex = (int)Math.round(randArray.length/10.0);
            long sum = 0;
            //Start timer
            double endTime;
            double startTime = System.nanoTime();
            //Access element in first 10%
            for(int i=0; i< endIndex;i++){
                sum += randArray[i];
            }
            //Stop timer
            endTime = System.nanoTime();
            //timeData.add(new Long(endTime-startTime));
            totalTime += (endTime-startTime)/ endIndex * 1.0; //This is the average to access one element
            totalSum += sum;
            System.out.println(totalTime);
        }
        //long transfer = totalTime.longValue();
        Data data = new Data(timeData,totalTime,totalSum);
        return data;
    }

    public static Data task2LastTen(int numExperiments, Integer[] randArray){
        double totalTime = (long)0;
        Long totalSum = (long)0;
        ArrayList<Long> timeData = new ArrayList<>();
        Random rand = new Random();
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            int endIndex = (int)Math.round(randArray.length/10.0);
            int randomIndex = (int)(randArray.length * 0.9) + rand.nextInt(endIndex);
            long sum = 0;
            //Start timer
            double endTime;
            double startTime = System.nanoTime();
            //Access element in last 10%
            sum = randArray[randomIndex];

            //Stop timer
            endTime = System.nanoTime();
            //timeData.add((endTime-startTime));
            totalTime += (endTime-startTime);
            totalSum += sum;
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
        double totalTime = 0;
        ArrayList<Long> timeData = new ArrayList<>();
        Random rand = new Random();
        //Experiment counter
        for (int i = 0; i < numExperiments; i++){
            //Get random number
            Integer j = rand.nextInt(size);
            //Start timer
            double endTime;
            double startTime = System.nanoTime();

            numberSet.contains(j);
            
            //Stop timer
            endTime = System.nanoTime();
            //timeData.add((endTime-startTime));
            totalTime += (endTime-startTime);
        }
        Data data = new Data(timeData,totalTime);
        return data;
    }
}
