package compressionsubtrees;
import java.util.*;

/** class to represent 2-input boolean nodes **/
public class BooleanNode
{
    BooleanFunction f;
    BooleanNode left,right;
    boolean isTerminal;
    int terminalIndex;
    Problem prob;
    Set<Integer> variableList;

    /** complete constructor - non-terminal **/
    public BooleanNode(Problem probp, int functionType, BooleanNode leftp,  BooleanNode rightp)
    {
        prob = probp;
        isTerminal = false;
        f = new BooleanFunction(prob, functionType);
        terminalIndex = -999;
        left = leftp;
        right = rightp;
        variableList = new HashSet<Integer>();
        variableList.addAll(left.getVariableList());
        variableList.addAll(right.getVariableList());
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
        variableList = new HashSet<Integer>();
        variableList.add(terminalIndex);
    }
    
    Set<Integer> getVariableList()
    {
        return variableList;
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
