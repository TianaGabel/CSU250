import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class BinaryTreeDriverCode {
    public static ArrayList<Integer> nodesToRemove;
    public static ArrayList<Integer> nodesToGet;
    public static TreeStructure tree;
    public static TreeMap expectedTree;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        nodesToGet = new ArrayList<>();
        nodesToRemove = new ArrayList<>();
        tree = new BinaryTree();

        try {
            setup(args);
            //Expected output
            outputGetNodes(nodesToGet, tree);
            // Nodes are then removed
            for (Integer num : nodesToRemove) {
                tree.remove(num);
            }
            outputGetNodes(nodesToGet, tree);

            //Test output
            outputGetNodes(nodesToGet, tree);
            for (Integer num : nodesToRemove) {
                tree.remove(num);
            }
            outputGetNodes(nodesToGet, tree);
        } catch (Exception e) {
            System.out.println("Not cool dude");
            e.printStackTrace();
        }
    }

    public static void setup(String[] args) throws Exception {
        File file = new File(args[0]);
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        Random rng = new Random(42);
        String line = bufferedReader.readLine();
        while (line != null) {
            Integer lineInt = Integer.parseInt(line);
            tree.insert(lineInt);
            Integer rand = rng.nextInt(10);
            if (rand < 5)
                nodesToRemove.add(lineInt);
            else if (rand >= 5)
                nodesToGet.add(lineInt);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    public static void outputGetNodes(ArrayList<Integer> nodesToGet, TreeStructure tree) {
        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }
        System.out.println("Max depth: " + tree.findMaxDepth());
        System.out.println("Min depth: " + tree.findMinDepth());
    }
}
