package compressionsubtrees;

import java.util.*;

public class ProblemDefinition 
{
    /** for now, a problem definition is a target mapping from
     * an n-bit binary tuple to a single binary output, i.e. we 
     * are limiting ourselves to single-output functions for now */
    
    int numberOfInputs;
    HashMap<Integer,Boolean> mapping;
    
    public ProblemDefinition(int numberOfInputsp)
    {
        numberOfInputs = numberOfInputsp;
        mapping = new HashMap<Integer,Boolean>();
    }
    
    public void setUp4BitParity()
    {
        numberOfInputs = 4;
        mapping = new HashMap<Integer,Boolean>();
        for (int i=0;i<16;i++)
        {
            boolean parity = false;
            int iCopy = i; //to allow decomposition of i
            for (int j=0;j<4;j++)
            {
                if (iCopy%2==1) //there is a one
                {
                    parity = !parity;
                }
                iCopy /= 2;
            }
            mapping.put(i,parity);
        }
    }
    
    public boolean lookup(boolean[] inputs)
    {
        //make sure that you get big/little endian right
        //we are reading it as larger array index == MSB
        
        int key = 0;
        for (int i=0;i<numberOfInputs;i++)
        {
            if (inputs[i])
            {
                key += (2-1)<<i; //weird Java-y way of doing int power
            }
        }
        return mapping.get(key);
    }
    
    public boolean[] getTargetVector()
    {
        int noOfTestCases = (2-1)<<numberOfInputs; //2^numberOfInputs
        boolean[] ans = new boolean[noOfTestCases];
        for (int key=0;key<noOfTestCases;key++)
        {
            ans[key] = mapping.get(key);
        }
        return ans;
    }
}
