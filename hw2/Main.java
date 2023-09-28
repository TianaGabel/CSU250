import java.util.List;

public class Main {
    
    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
        int numExperiments = Integer.parseInt(args[1]);
        String seed = args[2];
        List<Float> volatileData;
        List<Float> nonVolatileData;



        //TASK 1: Performance of programs with and without caching

        //loop 1: Volatile
        long totalSum = 0;
        long totalTime = 0;
        volatileData = 
        //Experiment counter
        for (int j = 0; j < numExperiments; j++){
            //Start timer
            long runningTotal = 0;
            for(int i = 0; i < size; i++){
                //running total of addition and subtraction operations using the loop variable
                if(i % 2 == 0){
                    runningTotal += i;
                } else {
                    runningTotal -= i;
                }
            }
            //End Timer
            //Add timer to data about experiments
            totalTime += 0; //TODO ADD TIME
            totalSum += runningTotal;
        }
        //TODO it might be worth writing this information to a .csv file so I can format it
        int averageTimeVolatile; //= totalTime / experiments
        long averageSumVolatile = totalSum / (long) numExperiments;

        //loop 2: Non-Volatile
        // will be the same just copy later

        //TASK 2
        

        //TASK 3

    }
}
