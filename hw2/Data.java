import java.util.ArrayList;

public class Data {
    ArrayList<Long> individualTimes;
    double totalTime;
    long totalSum;

    //for task 1 and 2
    public Data(ArrayList<Long> individualTimes, long totalTime, long totalSum){
        this.individualTimes = individualTimes;
        //in Nano seconds we need to convert to seconds
        this.totalTime = totalTime * (Math.pow(10,-9));
        this.totalSum = totalSum;
    }

    //for task 3
    public Data(ArrayList<Long> individualTimes, long totalTime){
        this.individualTimes = individualTimes;
        this.totalTime = totalTime;
    }


    public ArrayList<Long> getTimeData(){
        return individualTimes;
    }

    public double getTotalTime(){
        return totalTime;
    }

    public long getTotalSum(){
        return totalSum;
    }

    public double getAverageTime(int numExperiments){
        return (double) this.totalTime / numExperiments;
    }

    public double getAverageSum(int numExperiments){
        return (double) this.totalSum / numExperiments;
    }
}
