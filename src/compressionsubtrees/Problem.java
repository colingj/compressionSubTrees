package compressionsubtrees;

import java.util.*;

public class Problem
{
    int numberOfVariables;
    boolean[] target;
    ArrayList<BooleanNode> addedNodes;
    int[] functionList;

    public void create4ParityAllFunctions()
    {
        numberOfVariables = 4;
        target = new boolean[]{false,true,true,false,
                    true, false, false, true,
                    true, false, false, true,
                    false, true, true, false};
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    }
    
    public void create4ParitySomeFunctions()
    {
        numberOfVariables = 4;
        target = new boolean[]{false,true,true,false,
            true, false, false, true,
            true, false, false, true,
            false, true, true, false};
        addedNodes = new ArrayList<BooleanNode>();
        functionList = new int[]{1,7,10,12};
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
