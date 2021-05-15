/*
Driver
Bruk Mulatu
COSC 2203
Contrast Equalization
Due Date: October 7, 2020
Approved by: Robel Tadele
 */
public class BNode {

    BNode left, right , parent;

    int pixelCount;
    int intensity;
    int newIntensity;
    int cumulativeCount;

    BNode (int intensityValue){
        this.intensity = intensityValue;
        this.left = null;
        this.right = null;
        pixelCount = 1;
    }
}
