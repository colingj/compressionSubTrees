package compressionsubtrees;

import java.util.*;

public class Forest
{
    int numberOfTrees;
    Problem prob;
    BooleanTree[] treeList;
    boolean[][] inputList;


    public Forest(int numberOfTreesp, Problem probp)
    {
        numberOfTrees = numberOfTreesp;
        prob = probp;
        treeList = new BooleanTree[numberOfTrees];
        //inputList = new boolean[(int)Math.pow(2,length)][length];
        inputList = createInputList(prob.getNumberOfVariables());
    }

    public void createRandom()
    {
        for (int i=0;i<numberOfTrees;i++)
        {
            treeList[i] = new BooleanTree();
            treeList[i].generateRandomTree(prob);
        }
    }

    public ArrayList<BooleanTree> evaluateQuality_Comp()
            //this is quality evaluation using compression
    {
        ArrayList<BooleanTree> perfectSolutions = new ArrayList<>();
        int length = prob.getNumberOfVariables();
        boolean[][] outputList = new boolean[numberOfTrees][(int)Math.pow(2,length)];
        boolean[] target = prob.getTarget();
        boolean[][] diffList = new boolean[numberOfTrees][(int) Math.pow(2, length)];
        //difflist will be the difference between target and output
        /** iterate through the trees **/
        for (int i=0;i<numberOfTrees;i++)
        {
            /** iterate through the inputs 
             * to see whether this is a perfect solution **/
            /** wibble: there might be a better way of doing this
             * by checking on compression first **/         
            int numberOfErrors = 0;
            for (int j=0;j<(int)Math.pow(2,length);j++)
            {
                outputList[i][j] = treeList[i].eval(inputList[j]);
                diffList[i][j] = outputList[i][j]==target[j];
                if (outputList[i][j]!=target[j]) { numberOfErrors++; }
            }
            if (numberOfErrors==0)
            {
                perfectSolutions.add(treeList[i]);
            }
            treeList[i].setQuality(gzipCompression.compress(diffList[i]));        
        }
      Arrays.sort(treeList);
      return perfectSolutions;
    }
    
    public BooleanTree getBest()
    {
        this.evaluateQuality_Comp();
        return treeList[0];
    }

    public ArrayList<BooleanTree> evaluateQuality_IG()
            //this is quality evaluation using information gain
    {
        //create a list to store perfect solutions if any are found
        ArrayList<BooleanTree> perfectSolutions =  new ArrayList<>();
        int length = prob.getNumberOfVariables();
        boolean[][] outputList = new boolean[numberOfTrees][(int)Math.pow(2,length)];
        boolean[] target = prob.getTarget();
        boolean[][] diffList = new boolean[numberOfTrees][(int) Math.pow(2, length)];
        //difflist will be the difference between target and output
        for (int i=0;i<numberOfTrees;i++)
        {
            /** iterate through the inputs 
             * to see whether this is a perfect solution **/
            /** wibble: there might be a better way of doing this
             * by checking on compression first **/ 
            int numberOfErrors = 0 ;
            for (int j=0;j<(int)Math.pow(2,length);j++)
            {
                outputList[i][j] = treeList[i].eval(inputList[j]);
                diffList[i][j] = outputList[i][j]==target[j];
                if (outputList[i][j]!=target[j]) { numberOfErrors++; }
            }
            if (numberOfErrors==0)
            {
                perfectSolutions.add(treeList[i]);
            }
            /** now calculate the quality measure **/
            Set<Integer> variableList = treeList[i].getVariableList();
            treeList[i].setQuality(calculateIG(diffList[i],variableList));
        }
      Arrays.sort(treeList);
      return perfectSolutions;
    }
       
    //input list. first parameter is entry number, second is the variable index
    private boolean[][] createInputList(int length)
    {
        boolean[][] inputListl = new boolean[(int)Math.pow(2,length)][length];
        for (int val = 0; val < (int)Math.pow(2, length); val++)
        {
            for (int i = length - 1; i >= 0; i--)
            {
                inputListl[val][i] = (val & (1 << i)) != 0;
            }
        }
        return inputListl;
    }
    
    private double calculateIG(boolean[] diffList, Set<Integer> variableList)
    {       
        System.out.println("Variables");
        for (Integer v: variableList) { System.out.println("variable: "+v); }
        
        /********************************************************************/
        int length = prob.getNumberOfVariables();
        // we want the variables _not_ used, i.e. not those in variableList
        int[] variables = new int[prob.getNumberOfVariables()-variableList.size()];
        int j=0;
        for (int i=0;i<length;i++)
        {
            if (!variableList.contains(i)) { variables[j]=i; j++; }
        }
        
        System.out.println("Variables remaining");
        for (int v: variables) { System.out.println("variable: "+v); }
        
        int[] whichPA = new int[(int)Math.pow(2,length)];
        
        for (int i=0;i<(int)Math.pow(2,length);i++)
        {
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
