package compressionsubtrees;

import java.util.*;

public class Forest
{
    int numberOfTrees;
    BooleanTree[] treeList;
    Problem prob;

    public Forest(int numberOfTreesp, Problem probp)
    {
        numberOfTrees = numberOfTreesp;
        treeList = new BooleanTree[numberOfTrees];
        prob = probp;
    }

    public void createRandom()
    {
        for (int i=0;i<numberOfTrees;i++)
        {
            treeList[i] = new BooleanTree();
            treeList[i].generateRandomTree(prob);
        }
    }

    public ArrayList<BooleanTree> evaluateQuality()
    {
        ArrayList<BooleanTree> perfectSolutions = new ArrayList<BooleanTree>();
        int length = prob.getNumberOfVariables();
        boolean[][] inputList; // = new boolean[(int)Math.pow(2,length)][length];
        inputList = createInputList(4);
        boolean[][] outputList = new boolean[numberOfTrees][(int)Math.pow(2,length)];
        boolean[] target = prob.getTarget();
        boolean[][] diffList = new boolean[numberOfTrees][(int) Math.pow(2, length)];
        //difflist will be the difference between target and output
        for (int i=0;i<numberOfTrees;i++)
        {
            int numberOfErrors = 0 ;
            for (int j=0;j<(int)Math.pow(2,length);j++)
            {
                outputList[i][j] = treeList[i].eval(inputList[j]);
                diffList[i][j] = outputList[i][j]==target[j];
                if (outputList[i][j]!=target[j]) { numberOfErrors++; }
                try
                {
                treeList[i].setQuality(gzipCompression.compress(diffList[i]));
                }
                catch (java.io.IOException ee) 
                { 
                    System.err.println("Error in evaluateQuality.");
                    System.exit(1);
                }
            }
            if (numberOfErrors==0)
            {
                perfectSolutions.add(treeList[i]);
            }
        }
      Arrays.sort(treeList);
      return perfectSolutions;
    }
    
    public BooleanTree getBest()
    {
        this.evaluateQuality();
        return treeList[0];
    }

    private boolean[][] createInputList(int length)
    {
        boolean[][] inputList = new boolean[(int)Math.pow(2,length)][length];
        for (int val = 0; val < (int)Math.pow(2, length); val++)
        {
            for (int i = length - 1; i >= 0; i--)
            {
                inputList[val][i] = (val & (1 << i)) != 0;
            }
        }
        return inputList;
    }

    @Override
    public String toString()
    {
        String ans = new String();
        for (int i = 0; i < numberOfTrees; i++)
        {
            ans += i + ": " +treeList[i]+"\n";
        }
        return ans;
    }

}
