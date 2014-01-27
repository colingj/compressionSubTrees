package compressionsubtrees;

/** class to represent 2-input boolean nodes **/
public class BooleanNode
{
    BooleanFunction f;
    BooleanNode left,right;
    boolean isTerminal;
    int terminalIndex;
    Problem prob;

    /** complete constructor - non-terminal **/
    public BooleanNode(Problem probp, int functionType, BooleanNode leftp,  BooleanNode rightp)
    {
        prob = probp;
        isTerminal = false;
        f = new BooleanFunction(prob, functionType);
        terminalIndex = -999;
        left = leftp;
        right = rightp;
    }

    /** constructor - terminal **/
    public BooleanNode(Problem probp, int terminalIndexp)
    {
        prob = probp; 
        isTerminal = true;
        f = null;
        left = null;
        right = null;
        terminalIndex = terminalIndexp;
    }
    
    public void randomizeFunction()
    {
        f = new BooleanFunction(prob);
    }

    public boolean eval(boolean[] inputList)
    {
        if (isTerminal)
        {
            return inputList[terminalIndex];
        }
        else //is non-terminal
        {
            return f.eval(left.eval(inputList), right.eval(inputList));
        }
    }

    @Override
    public String toString()
    {
        if (isTerminal)
        {
            return "v_"+terminalIndex;
        }
        else
        {
            return "("+f.functionName()+" "+left+" "+right+")";
        }
    }

}
