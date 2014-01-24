package compressionsubtrees;

import java.util.*;

/** this will represent the sub-trees eventually **/

public class BooleanTree implements Comparable<BooleanTree>
{
    BooleanNode root;
    int quality;
    
    /** just for now, single-node trees **/
    public void generateRandomTree(int numberOfInputs)
    {
        Random rnd = new Random();
        BooleanNode l = new BooleanNode(rnd.nextInt(numberOfInputs));
        BooleanNode r = new BooleanNode(rnd.nextInt(numberOfInputs));
        root = new BooleanNode(0,l,r);
        root.randomizeFunction();
        quality = 0;
    }
        
    /** a new constructor **/
    public void generateRandomTree(Problem pp)
    {
        Random rnd = new Random();
        BooleanNode l = pp.generateRandomStub();
        BooleanNode r = pp.generateRandomStub();
        root = new BooleanNode(0,l,r);
        root.randomizeFunction();
        quality = 0;
    }
    
    public boolean eval(boolean[] inputList)
    {
        return root.eval(inputList);
    }

    public void setQuality(int qualityp)
    {
        quality = qualityp;
    }

    public int getQuality()
    {
        return quality;
    }
    
    public BooleanNode getRootNode()
    {
        return root;
    }

    @Override
    public String toString()
    {
        return root.toString()+" with quality "+quality;
    }

    public int compareTo(BooleanTree compareTree)
    {
        return this.getQuality() - compareTree.getQuality();
    }
}
