import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class BinaryTreeDriverCode{

public static void main(String[] args) throws FileNotFoundException, IOException {
File file = new File("data.txt");
FileReader fReader = new FileReader(file);
BufferedReader bufferedReader = new BufferedReader(fReader);
TreeStructure tree = new BinaryTree();
Random rng = new Random(42);
ArrayList<Integer> nodesToRemove = new ArrayList<>();
ArrayList<Integer> nodesToGet = new ArrayList<>();
String line = bufferedReader.readLine();
while (line != null) {
Integer lineInt = Integer.parseInt(line);
tree.insert(lineInt);
Integer rand = rng.nextInt(10);
if (rand < 5) nodesToRemove.add(lineInt);
else if (rand >= 5) nodesToGet.add(lineInt);
line = bufferedReader.readLine();
}
bufferedReader.close();
for (int i = 0; i < 10; i++) {
System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
}
System.out.println("Max depth: " + tree.findMaxDepth());
System.out.println("Min depth: " + tree.findMinDepth());
for (Integer num : nodesToRemove) {
tree.remove(num);
}
for (int i = 0; i < 10; i++) {
System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
}
System.out.println("Max depth: " + tree.findMaxDepth());
System.out.println("Min depth: " + tree.findMinDepth());
}
}