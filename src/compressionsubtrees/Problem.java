package compressionsubtrees;

import java.util.*;

public class Problem
{
    int numberOfVariables;
    boolean[] target;
    //addedNodes are the "theories". Together with the variables,
    // these form the construction set.
    ArrayList<BooleanNode> addedNodes;
    int[] functionList;
    
    public void createParitySomeFunctions(int n, boolean even)
    {
        numberOfVariables = n;
        int noInputs = (int) Math.pow(2, numberOfVariables);
        boolean[][] listOfInputs = BoolUtils.generateBoolSequences(numberOfVariables);
        
        target = new boolean[noInputs];
        for (int i=0;i<noInputs;i++)
        {
            boolean parity = even;//initialised from parameter
            for (int j=0;j<numberOfVariables;j++)
            {
                if (listOfInputs[i][j]) { parity = !parity; }
            }
            target [i] = parity;
        }
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{1,6,7,14};     
    }
    
    public void createParityAllFunctions(int n,boolean even)
    {
        numberOfVariables = n;
        int noInputs = (int) Math.pow(2, numberOfVariables);
        boolean[][] listOfInputs = BoolUtils.generateBoolSequences(numberOfVariables);
        
        target = new boolean[noInputs];
        for (int i=0;i<noInputs;i++)
        {
            boolean parity = even;
            for (int j=0;j<numberOfVariables;j++)
            {
                if (listOfInputs[i][j]) { parity = !parity; }
            }
            target [i] = parity;
        }
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    }
    
    public void createMultiplexer(int n)
            //n will be the number of bits in the selector
            //so e.g. n=1 is the "3-bit multiplexer"
    {
        //the first _n_ variables are the address, the rest the data
        
        numberOfVariables = n+(int)Math.pow(2,n);
        int noInputs = (int) Math.pow(2, numberOfVariables);
        boolean[][] listOfInputs = BoolUtils.generateBoolSequences(numberOfVariables);
        
        target = new boolean[noInputs];
        for (int i=0;i<noInputs;i++)
        {
            int lookup = 0;
            for (int j=0;j<n;j++)//look at the first n variables
            {
                lookup += (int)Math.pow(2,listOfInputs[i][j]?1:0);
            }
            target[i] = listOfInputs[i][lookup];
        }
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{1,6,7,14};
    }
        
    public void createMajoritySomeFunctions(int n)
    {
        numberOfVariables = n;
        int noInputs = (int) Math.pow(2, numberOfVariables);
        boolean[][] listOfInputs = BoolUtils.generateBoolSequences(numberOfVariables);
        
        target = new boolean[noInputs];
        for (int i=0;i<noInputs;i++)
        {
            int count = 0;
            for (int j=0;j<numberOfVariables;j++)
            {
                if (listOfInputs[i][j]) { count++; }
            }
            if (count>=numberOfVariables/2) { target [i] = true; }
            else { target[i] = false; }
        }
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{1,7,10};
    }
    
    public BooleanNode generateRandomStub()
    {
        //this function picks a random member from the construction set
        BooleanNode ans;
        Random rnd = new Random();
        int r = rnd.nextInt(numberOfVariables+addedNodes.size());
        // if r<number of variables, then we add a node from the 
        //  variable list
        if (r<numberOfVariables)
        {
            ans = new BooleanNode(this,r);//a terminal
        }
        // otherwise, we add a node from the theories
        else
        {
            ans = addedNodes.get(r-numberOfVariables);//from the cache
        }
        return ans;
    }
    
    public void addNodeToCache(BooleanNode bb)
    {
        addedNodes.add(bb);
    }
    
    public boolean[] getTarget()
    {
        return target;
    }

    public int getNumberOfVariables()
    {
        return numberOfVariables;
    }
    
    public int[] getFunctionList()
    {
        return functionList;
    }
    
    public String printCache()
    {
        String ans = new String();
        for (BooleanNode bb: addedNodes)
        {
            ans += bb+"; ";
        }
        return ans;
    }
}

/** for reference:
    { 0:"allF",1:"AND","f2",3:"2nd","f4",5:"1st",6:"XOR",7:"OR",
    8:"NOR",9:"XNOR",10:"NOT1","f11",12:"NOT2","f13",14:"NAND",15:"allT"};
 */