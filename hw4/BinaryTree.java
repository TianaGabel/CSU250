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
        Node currNode = root;
        Node parentNode = root;
        while (currNode != null){
            if (num < currNode.getNum()) {
                parentNode = currNode;
                currNode = currNode.leftChild;
            } else if (num > currNode.getNum()) {
                parentNode = currNode;
                currNode = currNode.rightChild;
            } else {
                break;
            }
        }
                //We found the node we're looking to remove
                
                if((currNode.leftChild == null) && (currNode.rightChild == null)){
                    //Case no children
                    if (parentNode.equals(root)) {
                        root = null;
                        return true;
                    }
                    if (num < parentNode.leftChild.getNum()){
                        parentNode.leftChild = null;
                        return true;
                    } else {
                        parentNode.rightChild = null;
                    }
                } else if (currNode.leftChild == null){
                    if (parentNode.leftChild.getNum() == num){
                        parentNode.leftChild = currNode.rightChild;
                    } else {
                        parentNode.rightChild = currNode.rightChild;
                    }
                    return true;
                } else if(currNode.rightChild == null){
                    if (parentNode.leftChild.getNum() == num){
                        parentNode.leftChild = currNode.leftChild;
                    } else {
                        parentNode.rightChild = currNode.leftChild;
                    }
                    return true;
                } else {
                    Node successor = currNode.rightChild;
                    while (successor.leftChild != null){
                        successor = successor.leftChild;
                    }
                    //Update parent
                    if (parentNode.leftChild.getNum() == num){
                        parentNode.leftChild = successor;
                    } else {
                        parentNode.rightChild = successor;
                    }
                    //update new children
                    successor.leftChild = currNode.leftChild;
                    successor.rightChild = currNode.rightChild;
                    return true;
                }         
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
            return 0;
        }
        return Math.max(maxDepth(n.leftChild), maxDepth(n.rightChild)) + 1;
    }

    @Override
    public Integer findMinDepth() {
        //TODO this is done but a breadth first search makes more sense
        return minDepth(root);
    }

    private Integer minDepth(Node n){
        if (n == null){
            return 0;
        }
        return Math.min(minDepth(n.leftChild), minDepth(n.rightChild))+ 1;
    }

    // implements treeStructure interface

}