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
        ArrayList<BooleanTree> perfectSolutions = new ArrayList<BooleanTree>();
        int length = prob.getNumberOfVariables();
        boolean[][] inputList;
        inputList = createInputList(length);

        boolean[][] outputList = new boolean[numberOfTrees][(int)Math.pow(2,length)];
        boolean[] target = prob.getTarget();
        boolean[][] diffList = new boolean[numberOfTrees][(int) Math.pow(2, length)];
        //difflist will be the difference between target and output
        /** iterate through the trees **/
        for (int i=0;i<numberOfTrees;i++)
            //iterate through the trees in the forest
        {
            /** iterate through the inputs 
             * to see whether this is a perfect solution **/
            /** wibble: there might be a better way of doing this
             * by checking on compression first **/         
            int numberOfErrors = 0;
            for (int j=0;j<(int)Math.pow(2,length);j++)
                //iterate through the inputs
            {
                outputList[i][j] = treeList[i].eval(inputList[j]);
                diffList[i][j] = outputList[i][j]==target[j];
                if (outputList[i][j]!=target[j]) { numberOfErrors++; }
            }
            treeList[i].setQuality(gzipCompression.compress(diffList[i]));
            if (numberOfErrors==0)
            {
                perfectSolutions.add(treeList[i]);
            }
            treeList[i].setQuality(gzipCompression.compress(diffList[i]));        
        }
      Arrays.sort(treeList);
      return perfectSolutions;
    }
    
    //Wibble: in Java 8 we can use functions-as-first-class-types
    // to pass in which function to use for the line where we evaluate
    // the quality
    
    public BooleanTree getBest()
    {
        this.evaluateQuality_Comp();
        return treeList[0];
    }

    public ArrayList<BooleanTree> evaluateQuality_IG()
            //this is quality evaluation using information gain
    {
        //create a list to store perfect solutions if any are found
        ArrayList<BooleanTree> perfectSolutions =  new ArrayList<BooleanTree>();
        int length = prob.getNumberOfVariables();
        boolean[][] inputList;
        inputList = createInputList(prob.getNumberOfVariables());
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
        boolean[][] inputList = new boolean[(int)Math.pow(2,length)][length];
        //these are [index_of_input][variable_number]
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
            //variablelList is the list of all variables that are
            //  included in the tree under consideration
    {        
        int length = prob.getNumberOfVariables();
        // we want the variables _not_ used, i.e. not those in variableList
        int[] absentVariables 
                = new int[prob.getNumberOfVariables()-variableList.size()];
        //note: absentVariables is the list of all variables that are
        //  not in the current tree
        //wibble: if the size of this this is _zero_ then we are going
        //  to have to do something different!
        //note: We need to check whether "absentVariables" draws correctly
        //  on variables that are part of "theories" as well as raw
        //  input variables
        int k=0;
        for (int i=0;i<length;i++)
        {
            if (!variableList.contains(i)) { absentVariables[k]=i; k++; }
        }
        
        //print out the absent variableList
        System.out.println("Absent variable list:");
        for (int ii: absentVariables) { System.out.println(ii+", "); }
        System.out.println("There are "+absentVariables.length
                +" absent variables.");
        System.out.println("\n");
        
        /** calculate the PA classification map **/
        
        int avLength = absentVariables.length;
        int numberOfPAs = (int)Math.pow(2,avLength);
        HashMap<String,Integer> avMap = new HashMap<String,Integer>();
        boolean[][] keys = createInputList(avLength);        
        int index = 0;
        for (boolean[] b: keys)
        {
            String kk = new String();
            for (int j=0;j<b.length;j++) { kk += b[j]; }
            //System.out.println(kk);
            avMap.put(kk,index);
            index++;
        }

        /** now calculate the groups **/
        
        ArrayList[] PAs = new ArrayList[numberOfPAs];
        for (int i=0;i<numberOfPAs;i++) { PAs[i] = new ArrayList(); }
          //should be arrayList<boolean> in later Java versions
        for (int i=0;i<(int)Math.pow(2,length);i++)
        {
          String theKey = new String();
          for (int j=0;j<avLength;j++)
          {
              theKey += inputList[i][absentVariables[j]];
          }
          //System.out.println("theKey "+theKey);
          Integer idx = avMap.get(theKey);
          //System.out.println("idx is "+idx);
          PAs[idx].add((Boolean)diffList[i]);
        }
        
        System.out.println("Here are the PAs");
        for (int j=0;j<numberOfPAs;j++)
        {
            System.out.println("PA number "+j);
            for (Object o: PAs[j]) { System.out.print((Boolean)o+", "); }
            System.out.println();
        }        
        //current working position
        
        
        //wibble: to be completed
        
        /** now calculate the IG based on the groups **/
        
        //wibble: to be done
        
        /** closing **/
        
        //wibble: to be done
        
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

/** 
 * a possible refactoring for this might be to make the quality-evaluation
 *   methods part of the BooleanTree class, so that all that this class does
 *   is to iterate through the Forest and calls the appropriate quality
 *   evaluation method from that class.
 **/
