import java.util.ArrayList;

public class Data {
    ArrayList<Long> individualTimes;
    long totalTime;
    long totalSum;

    //for task 1 and 2
    public Data(ArrayList<Long> individualTimes, long totalTime, long totalSum){
        this.individualTimes = individualTimes;
        this.totalTime = totalTime;
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

    public long getTotalTime(){
        return totalTime;
    }

    public long getTotalSum(){
        return totalSum;
    }

    public float getAverageTime(int numExperiments){
        return (float) this.totalTime / numExperiments;
    }

    public float getAverageSum(int numExperiments){
        return (float) this.totalSum / numExperiments;
    }
}
