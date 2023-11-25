import java.time.LocalTime;

public class Node {
    //TODO what data type should I use there.
    private Long timeCreated;
    private int num;
    public Node leftChild;
    public Node rightChild;

    public Node(int num){
        this.num = num;
        timeCreated = System.nanoTime();
    }

    public int getNum(){
        return num;
    }

    public Long getTimeCreated(){
        return timeCreated;
    }

}
