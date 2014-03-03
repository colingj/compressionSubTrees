package compressionsubtrees;

import java.util.*;

public class Problem
{
    int numberOfVariables;
    boolean[] target;
    ArrayList<BooleanNode> addedNodes;
    int[] functionList;
    
    public void createParitySomeFunctions(int n)
    {
        numberOfVariables = n;
        int noInputs = (int) Math.pow(2, numberOfVariables);
        boolean[][] listOfInputs = BoolUtils.generateBoolSequences(numberOfVariables);
        
        target = new boolean[noInputs];
        for (int i=0;i<noInputs;i++)
        {
            boolean parity = false;
            for (int j=0;j<numberOfVariables;j++)
            {
                if (listOfInputs[i][j]) { parity = !parity; }
            }
            target [i] = parity;
        }
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{1,6,7,14};     
    }
    
        public void createParityAllFunctions(int n)
    {
        numberOfVariables = n;
        int noInputs = (int) Math.pow(2, numberOfVariables);
        boolean[][] listOfInputs = BoolUtils.generateBoolSequences(numberOfVariables);
        
        target = new boolean[noInputs];
        for (int i=0;i<noInputs;i++)
        {
            boolean parity = false;
            for (int j=0;j<numberOfVariables;j++)
            {
                if (listOfInputs[i][j]) { parity = !parity; }
            }
            target [i] = parity;
        }
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
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
        BooleanNode ans;
        Random rnd = new Random();
        int r = rnd.nextInt(numberOfVariables+addedNodes.size());
        if (r<numberOfVariables)
        {
            ans = new BooleanNode(this,r);//a terminal
        }
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
}

/** for reference:
    { 0:"allF",1:"AND","f2",3:"2nd","f4",5:"1st",6:"XOR",7:"OR",
    8:"NOR",9:"XNOR",10:"NOT1","f11",12:"NOT2","f13",14:"NAND",15:"allT"};
 */