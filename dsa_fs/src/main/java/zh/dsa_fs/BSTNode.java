package zh.dsa_fs;

import java.util.ArrayList;

public class BSTNode {
    private int self;
    private BSTNode left = null;
    private BSTNode right = null;
    private int level = 1;

    //constructors
    public BSTNode(int self) {
        this.self = self;
    }

    public BSTNode(int self, int level) {
        this.self = self;
        this.level = level;
    }

    //getters and setters
    public int getSelf() {
        return self;
    }

    public int getLevel() {
        return level;
    }


    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    //methods
    public BSTNode addRecursive(BSTNode root, int value, int level) {
        if (root == null) {
            root = new BSTNode(value, level);
            return root;
        }

        if (value < root.self) {
            root.left = addRecursive(root.left, value, level + 1);
        } else if (value > root.self) {
            root.right = addRecursive(root.right, value, level + 1);
        } else {
            // value already exists
            return root;
        }

        return root;
    }


    public void searchByLevel(BSTNode root, ArrayList<BSTNode> queue, ArrayList<int[]> binaryTreeArray, boolean addedRootToQueue){
        if (queue.size() == 0) {
            if (addedRootToQueue == false) {
                queue.add(root);
                root.searchByLevel(root, queue, binaryTreeArray, true);
            } else {
                return;
            }
        } else {
            int[] numberAndLevel = {queue.getFirst().getSelf(), queue.getFirst().getLevel()};
            binaryTreeArray.add(numberAndLevel);
            if (queue.getFirst().getLeft() != null) {
                queue.add(queue.getFirst().getLeft());
            }
            if (queue.getFirst().getRight() != null) {
                queue.add(queue.getFirst().getRight());
            }
            
            queue.removeFirst();
            root.searchByLevel(root, queue, binaryTreeArray, true);
        }
    }
}
