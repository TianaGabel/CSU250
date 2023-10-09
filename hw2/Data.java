import java.util.ArrayList;

public class Data {
    ArrayList<Double> individualTimes;
    double totalTime;
    long totalSum;

     //for task 1 and 2
    public Data(ArrayList<Double> individualTimes, double totalTime, long totalSum){
        this.individualTimes = individualTimes;
        this.totalTime = totalTime; //this is stored in nano seconds
        this.totalSum = totalSum;
    }

    //for task 3
    public Data(ArrayList<Double> individualTimes, double totalTime){
        this.individualTimes = individualTimes;
        this.totalTime = totalTime;
    }

    public ArrayList<Double> getTimeData(){
        return individualTimes;
    }

    public double getTotalTime(){
        return totalTime;
    }

    public long getTotalSum(){
        return totalSum;
    }

    public double getAverageTime(int numExperiments){
        return (double) this.totalTime / numExperiments * 1.0;
    }

    public double getAverageSum(int numExperiments){
        return (double) this.totalSum / numExperiments * 1.0;
    }
}
