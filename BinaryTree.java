/*
Driver
Bruk Mulatu
COSC 2203
Contrast Equalization
Due Date: October 7, 2020
Approved by: Robel Tadele
 */
import java.io.Serializable;

public class BinaryTree <E> implements Serializable {

    BNode root = null;

    public BinaryTree (){ }

    //add method
    void insertNode (int intensityVal){

        BNode currentNode = new BNode(intensityVal);

        //Check if the root is null
        if(root==null){
            root = currentNode;
        }
        else {
            //If the current node intensity is less, add a left child
            if(currentNode.intensity < root.intensity){
                root.left = insertN(root.left,currentNode);
            }else { // else add a right child
                root.right = insertN(root.right,currentNode);
            }
        }
    }

    //Recursive add method
    public BNode insertN(BNode reference, BNode number){
        if (reference == null) {
            reference = number;
            return reference;
        } else {
            //Recursive statement to build tree
            if(number.intensity < reference.intensity){
                reference.left = insertN(reference.left,number);
                // Add right child
            }else {
                reference.right = insertN(reference.right,number);
            }
            return reference;
        }

    }

    //Inorder traversal method that calculates the new intensity
    public int intensityCalculator(double cumulativeCount){

        return rIntensityCalculator(root, 0,cumulativeCount);
    }

    public int rIntensityCalculator(BNode currentNode, int currentCount, double intensityTotal){

        if(currentNode == null){
            return currentCount; // this will help preserve the currentCount when we visit a null node
        }

        else {
            currentNode.cumulativeCount = rIntensityCalculator(currentNode.left, currentCount, intensityTotal) + currentNode.pixelCount;
            // New Intensity formula
            double newIntensity;
            newIntensity = (currentNode.cumulativeCount / intensityTotal) * 255;
            currentNode.newIntensity = (int) newIntensity;

            return rIntensityCalculator(currentNode.right, currentNode.cumulativeCount, intensityTotal);
        }
    }
    //Method to find the height of the tree
    public int height(){
        return rHeight(root);
    }

    public int rHeight(BNode node){

        if (node == null){
            return 0;
        }
        // Return the height of the tree plus one for the root.
        return 1 + (Math.max(rHeight(node.left), rHeight(node.right)));
    }

    //find method
    public BNode search(int target){

        return rSearch(root,target);
    }
    //Recursive search method
    public BNode rSearch(BNode localRoot, int target) {

        if (localRoot == null)
            return null;

        else {
            //Check if the current value is equal to the target
            if (localRoot.intensity == target) {
                return localRoot;
            } else {
                //If greater than target move to the right, else move to the left
                if (target > localRoot.intensity) {
                    return rSearch(localRoot.right, target);
                } else {
                    return rSearch(localRoot.left, target);
                }
            }
        }

    }
}
