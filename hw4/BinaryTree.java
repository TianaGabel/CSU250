public class BinaryTree implements TreeStructure {
    Node root;

    public BinaryTree() {
    }

    @Override
    public void insert(Integer num) {
        //TODO this is basically done
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
        //TODO this is done
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
        //TODO this is done
        if (n == null){
            return 1;
        }
        return Math.max(maxDepth(n.leftChild), maxDepth(n.rightChild));
    }

    @Override
    public Integer findMinDepth() {
        //TODO this is done but a breadth first search makes more sense
        return minDepth(root);
    }

    private Integer minDepth(Node n){
        if (n == null){
            return 1;
        }
        return Math.min(maxDepth(n.leftChild), maxDepth(n.rightChild));
    }

    // implements treeStructure interface

}