public class BinaryTree implements TreeStructure {
    Node root;

    public BinaryTree() {
    }

    @Override
    public void insert(Integer num) {
        Node nodeToInsert = new Node(num);
        if (root == null) {
            root = new Node(num);
        } else {
            Node currNode = root;
            while (currNode != null) {
                if (num < currNode.getNum()) {
                    if (currNode.leftChild == null) {
                        currNode.leftChild = nodeToInsert;
                        return;
                    }
                    currNode = currNode.leftChild;
                } else if (num > currNode.getNum()) {
                    if (currNode.rightChild == null) {
                        currNode.rightChild = nodeToInsert;
                        return;
                    }
                    currNode = currNode.rightChild;
                }
            }
            // TODO what if the same number
        }
    }

    @Override
    public Boolean remove(Integer num) {
        return false;
    }

    @Override
    public Long get(Integer num) {
        Node currNode = root;
        while (currNode != null) {
            if (num < currNode.getNum()) {
                currNode = currNode.leftChild;
            } else if (num > currNode.getNum()) {
                currNode = currNode.rightChild;
            } else {
                return currNode.getTimeCreated();
            }
        }
        return null;
    }

    @Override
    public Integer findMaxDepth() {
        return maxDepth(root);
    }
    
    private Integer maxDepth(Node n){
        if (n == null){
            return 0;
        }
        return Math.max(maxDepth(n.leftChild), maxDepth(n.rightChild)) + 1;
    }

    @Override
    public Integer findMinDepth() {
        return -1;
        
    }

    // implements treeStructure interface

}