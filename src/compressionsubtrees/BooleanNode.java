package compressionsubtrees;
import java.util.*;

/** class to represent n-input boolean nodes **/
public class BooleanNode
{
    BooleanFunction f;
    BooleanNode[] children;
    boolean isTerminal;
    int terminalIndex;
    Problem prob;
    Set<Integer> variableList;

    /** complete constructor - non-terminal - any arity **/
    public BooleanNode(Problem probp, int functionType, BooleanNode[] childrenIn)
    {
        prob = probp;
        isTerminal = false;
        f = new BooleanFunction(prob, functionType);
        terminalIndex = -999;
        
        children = new BooleanNode[childrenIn.length];
        System.arraycopy(childrenIn, 0, children, 0, childrenIn.length);
        variableList = new HashSet<Integer>();
        for (int i=0;i<childrenIn.length;i++)
        {
            variableList.addAll(children[i].getVariableList());
        }
    }

    /** constructor - terminal **/
    public BooleanNode(Problem probp, int terminalIndexp)
    {
        prob = probp; 
        isTerminal = true;
        f = null;
        children = null;
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
            boolean[] inputsToEval = new boolean[children.length];
            for (int i=0;i<children.length;i++)
            {
                inputsToEval[i] = children[i].eval(inputList);
            }
            return f.eval(inputsToEval);
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
            String childListStr = new String();
            for (int i=0;i<children.length-1;i++)
            {
                childListStr += children[i]+" ";
            }
            childListStr += children[children.length-1];
            return "("+f.functionName()+" "+childListStr+")";
        }
    }

}
