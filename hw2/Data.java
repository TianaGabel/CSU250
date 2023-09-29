import java.util.ArrayList;

public class Data {
    ArrayList<Long> individualTimes;
    long totalTime;
    long totalSum;
    public Data(ArrayList<Long> individualTimes, long totalTime, long totalSum){
        this.individualTimes = individualTimes;
        this.totalTime = totalTime;
        this.totalSum = totalSum;
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
