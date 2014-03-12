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
        inputList = createInputList(prob.getNumberOfVariables());
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
                treeList[i].setQuality(gzipCompression.compress(diffList[i]));
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

    public ArrayList<BooleanTree> evaluateQuality_IG()
    {
        //create a list to store perfect solutions if any are found
        ArrayList<BooleanTree> perfectSolutions =  new ArrayList<>();
        int length = prob.getNumberOfVariables();
        boolean[][] inputList; // = new boolean[(int)Math.pow(2,length)][length];
        inputList = createInputList(prob.getNumberOfVariables());
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
                Set<Integer> variableList = treeList[i].getVariableList();
                treeList[i].setQuality(calculateIG(diffList[i],variableList));
            }
            if (numberOfErrors==0)
            {
                perfectSolutions.add(treeList[i]);
            }
        }
      Arrays.sort(treeList);
      return perfectSolutions;
    }
       
    //input list. first parameter is entry number, second is the variable index
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
    
    private double calculateIG(boolean[] diffList, Set<Integer> variableList)
    {        
        int length = prob.getNumberOfVariables();
        boolean[][] inputList = createInputList(prob.getNumberOfVariables());
        // we want the variables _not_ used, i.e. not those in variableList
        int[] variables = new int[prob.getNumberOfVariables()-variableList.size()];
        int j=0;
        for (int i=0;i<length;i++)
        {
            if (!variableList.contains(i)) { variables[j]=i; j++; }
        }
        
        int[] whichPA = new int[(int)Math.pow(2,length)];
        
        for (int i=0;i<(int)Math.pow(2,length);i++)
        {
            int paIndex = 0;
            for (j=0;j<variables.length;j++)
            {
                paIndex += (int)Math.pow(2,inputList[i][variables[j]]?j:0);
            }
            whichPA[i] = paIndex;
        }
        
        //boolean partialApps = new boolean[(int}Math.pow(2,))]
        
        return -999.0;//wibble scratch value
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
