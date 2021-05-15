/*
Driver
Bruk Mulatu
COSC 2203
Contrast Equalization
Due Date: October 7, 2020
Approved by: Robel Tadele
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    public static void main(String [] args){

        int rows = 0;// number of rows
        int columns = 0; // number of columns
        int intensityNumber = 0;
        int intensityValue [];
        int totalNodes= 0;
        int upperBound=0;
        int lowerBound=0;
        int cumulativePixCount;

        double intensityTotal = 0;

        BNode returnedNode;
        BNode currentNode;
        BNode bNode;

        ArrayList<Integer> storeValues = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        //Tree variable to create tree
        BinaryTree <Integer> tree = new BinaryTree<>();

        //Enter the rows and columns
        rows = input.nextInt();
        columns = input.nextInt();

        intensityValue = new int [rows*columns];
        intensityTotal = rows*columns; // set the total table to rows to columns

        //Enter the current Histogram
        for (int i = 0; i< rows*columns; i++){
           intensityNumber = input.nextInt();

            intensityValue[i]= intensityNumber;

            returnedNode = tree.search(intensityNumber);

            if(returnedNode == null){
                storeValues.add(intensityNumber); // Add value to array list if it is not already in the tree
                tree.insertNode(intensityNumber); // Add node to tree
                totalNodes++;
            }
            else
                returnedNode.pixelCount = returnedNode.pixelCount +1; //Increment pixel count by 1
        }

        tree.intensityCalculator(intensityTotal);

        //Print original histogram
        System.out.println();
        System.out.println("INTENSITY HISTOGRAM");
        System.out.println("Intensity      Pixel");
        System.out.println("Range          Count       Markers");

        for(int j = 1; j <17; j++){

            cumulativePixCount = 0;
            upperBound = lowerBound + 15; // For every 15 values add a bound

            for(int l=0; l <storeValues.size(); l++) {

                currentNode = tree.search(storeValues.get(l)); //search through the tree to find individual intensity numbers
                if(currentNode.newIntensity<=upperBound && currentNode.newIntensity >= lowerBound){
                    cumulativePixCount = currentNode.pixelCount + cumulativePixCount ; //Set pixel count to increased number
                }
            }
            System.out.println(lowerBound + "-"+ upperBound +"           "+ cumulativePixCount + "            " +numberOfPixels(cumulativePixCount));
            lowerBound = upperBound+ 1;
        }

        System.out.println("\nHeight: " + tree.height());
        System.out.println("Number of nodes: " + totalNodes);

        tree.intensityCalculator(intensityTotal);

        System.out.println();
        System.out.println("NEW HISTOGRAM");
        //Output new histogram
        for(int j =0; j <intensityTotal; j++){

            bNode = tree.search(intensityValue[j]);

            if(j % columns ==0){
                System.out.println();
            }
            System.out.print(bNode.newIntensity + " ");
        }
    }

    //Find the number of pixels
    public static String numberOfPixels(int pixelCount) {

        int count = 0;
        String numPix = "";

        if (pixelCount % 20 == 0) {
            count = pixelCount / 20;
        } else
            count = (pixelCount / 20) + 1;

        for (int i = 0; i < count; i++){
            numPix = new StringBuilder().append(numPix).append("*").toString();
    }
        return numPix;
    }

}
