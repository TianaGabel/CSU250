public class Main {
    
    public static void main(String[] args){
        int size = Integer.parseInt(args[0]);
        String numExperiments = args[1];
        String seed = args[2];


        //TASK 1: Performance of programs with and without caching

        //loop 1: Volatile
        //Experiment counter
        for (int j = 0; j < 0; j++){
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
            //end experiment loop
        }

        int averageTimeVolatile; //= totalTime / experiments
        int averageSumVolatile; // = totalSum / experiments

        //loop 2: Non-Volatile

        //TASK 2

        //TASK 3

    }
}
