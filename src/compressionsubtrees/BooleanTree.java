package compressionsubtrees;

import java.util.*;

/** this represents the sub-trees **/

public class BooleanTree implements Comparable<BooleanTree>
{
    BooleanNode root;
    Problem prob;
    double quality;
        
    /** a new constructor **/
    public void generateRandomTree(Problem probp)
    {
        prob = probp;
        BooleanNode[] children = new BooleanNode[2];
        children[0] = prob.generateRandomStub();
        children[1] = prob.generateRandomStub();
        root = new BooleanNode(prob,0,children);
        root.randomizeFunction();
        quality = 0.0;
        /*
        wibble - eventually we need to refactor this
        so that we know the arity _first_ and then
        can generate the correct number of child nodes
        */
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
        //System.out.println("**********");
        //System.out.println("This: "+this.getQuality());
        //System.out.println("That: "+compareTree.getQuality());
        return (int)Math.signum(this.getQuality() - compareTree.getQuality());
    }
}
