package compressionsubtrees;

import java.util.*;

public class Problem
{
    int numberOfVariables;
    boolean[] target;
    ArrayList<BooleanNode> addedNodes;

    public void create4Parity()
    {
        numberOfVariables = 4;
        target = new boolean[]{false,true,true,false,
                    true, false, false, true,
                    true, false, false, true,
                    false, true, true, false};
        addedNodes = new ArrayList<BooleanNode>();
    }

    public BooleanNode generateRandomStub()
    {
        BooleanNode ans;
        Random rnd = new Random();
        int r = rnd.nextInt(numberOfVariables+addedNodes.size());
        if (r<numberOfVariables)
        {
            ans = new BooleanNode(r);//a terminal
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
}
