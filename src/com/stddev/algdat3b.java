//package com.stddev;

/*public class algdat3b {
    public static void main(String[] args) {
        BinaryTree words = BinaryTree.createBinaryTree(args);
        // it will print the tree from left to right
        words.print2D(words.root);
    }
}

class Node {
    String word;
    Node left;
    Node right;

    Node(String word) {
        this.word = word;
        right = null;
        left = null;
    }

    public String toString() {
        return word;
    }
}

//  Class for binary trees, sorts words alphabetically in the order of ASCII ext table
//  Starting with the root as the first word
class BinaryTree {
    static final int COUNT = 10;
    Node root;

    //retrieved from geeks for geeks
    public void add(String word) {
        root = addRecursive(root, word);
    }

    public static BinaryTree createBinaryTree(String[] args) {
        BinaryTree bt = new BinaryTree();
        for (String arg : args) {
            bt.add(arg);
        }
        return bt;
    }

    //retrieved from geeks for geeks
    private Node addRecursive(Node current, String word) {
        if (current == null) {
            return new Node(word);
        }

        if (word.compareTo(current.word) < 0) {
            current.left = addRecursive(current.left, word);
        } else if (word.compareTo(current.word) > 0) {
            current.right = addRecursive(current.right, word);
        } else {
            // value already exists
            return current;
        }

        return current;
    }

    // Function to print binary tree in 2D
    // It does reverse inorder traversal
    // Retrieved from geeks for geeks
    static void print2DUtil(Node root, int space)
    {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        print2DUtil(root.right, space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.word + "\n");

        // Process left child
        print2DUtil(root.left, space);
    }

    // Wrapper over print2DUtil()
    static void print2D(Node root)
    {
        // Pass initial space count as 0
        print2DUtil(root, 0);
    }
}
*/

