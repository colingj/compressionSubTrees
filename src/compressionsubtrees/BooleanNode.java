package compressionsubtrees;

/** class to represent 2-input boolean nodes **/
public class BooleanNode
{
    BooleanFunction f;
    BooleanNode left,right;
    boolean isTerminal;
    int terminalIndex;

    /** complete constructor - non-terminal **/
    public BooleanNode(int functionType, BooleanNode leftp,  BooleanNode rightp)
    {
        isTerminal = false;
        f = new BooleanFunction(functionType);
        terminalIndex = -999;
        left = leftp;
        right = rightp;
    }

    /** constructor - terminal **/
    public BooleanNode(int terminalIndexp)
    {
        isTerminal = true;
        f = null;
        left = null;
        right = null;
        terminalIndex = terminalIndexp;
    }

    /**  constructor - relative to problem **/
    public BooleanNode(Problem pp)
    {
        pp.generateRandomStub();
    }
    
    public void randomizeFunction()
    {
        f = new BooleanFunction();
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
