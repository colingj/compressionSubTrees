package compressionsubtrees;

import java.util.*;

/** this will represent the sub-trees eventually **/

public class BooleanTree implements Comparable<BooleanTree>
{
    BooleanNode root;
    Problem prob;
    double quality;
    
    /** just for now, single-node trees **/
    public void generateRandomTree(Problem probp, int numberOfInputs)
    {
        prob = probp; //wibble: make this a clone()?
        Random rnd = new Random();
        BooleanNode l = new BooleanNode(prob, rnd.nextInt(numberOfInputs));
        BooleanNode r = new BooleanNode(prob, rnd.nextInt(numberOfInputs));
        root = new BooleanNode(prob,0,l,r);
        root.randomizeFunction();
        quality = 0.0;
    }
        
    /** a new constructor **/
    public void generateRandomTree(Problem probp)
    {
        prob = probp;
        Random rnd = new Random();
        BooleanNode l = prob.generateRandomStub();
        BooleanNode r = prob.generateRandomStub();
        root = new BooleanNode(prob,0,l,r);
        root.randomizeFunction();
        quality = 0.0;
    }
    
    public boolean eval(boolean[] inputList)
    {
        return root.eval(inputList);
    }

    public void setQuality(double qualityp)
    {
        quality = qualityp;
    }

    public double getQuality()
    {
        return quality;
    }
    
    public BooleanNode getRootNode()
    {
        return root;
    }
    
    public Set<Integer> getVariableList()
    {
        return root.getVariableList();
    }

    @Override
    public String toString()
    {
        return root.toString()+" with quality "+quality;
    }

    @Override
    public int compareTo(BooleanTree compareTree)
    {
        return (int)Math.signum(this.getQuality() - compareTree.getQuality());
    }
}
