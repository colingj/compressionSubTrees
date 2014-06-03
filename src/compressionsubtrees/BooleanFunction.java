package compressionsubtrees;
import java.util.*;

public class BooleanFunction 
{
    int functionArity;
    int functionType; //[0,15] representing the function
    boolean[] functionLUT;
    Problem prob;
    int[][] functionList;
    String name;
    static final private String[][] nameLookUp;

    static 
    {
        nameLookUp = new String[4][256];
        for (int i=1;i<3;i++)
        {
            for (int j=0;j<(int)Math.pow(2,Math.pow(2,i));j++)
            {
                nameLookUp[i][j] = "f"+j;
            }
        }
        nameLookUp[2][0] = "allF";
        nameLookUp[2][1] = "AND";
        nameLookUp[2][3] = "2nd";
        nameLookUp[2][5] = "1st";
        nameLookUp[2][6] = "XOR";
        nameLookUp[2][7] = "OR";
        nameLookUp[2][8] = "NOR";
        nameLookUp[2][9] = "XNOR";
        nameLookUp[2][10] = "NOT1";
        nameLookUp[2][12] = "NOT2";
        nameLookUp[2][14] = "NAND";
        nameLookUp[2][15] = "allT";        
    }
    
    //private static final String[] nameLookUp2Inputs = new String[]
    //{ "allF","AND","f2","2nd","f4","1st","XOR","OR",
    //"NOR","XNOR","NOT1","f11","NOT2","f13","NAND","allT"};
    
    public BooleanFunction(Problem prob, int functionTypep)
    {
        functionList = prob.getFunctionList();
        functionType = functionTypep;
        functionArity = functionList[0][functionType];
        functionLUT = findLUT(functionList[1][functionType]);
        name = nameLookUp[functionArity][functionList[1][functionType]];
    }
    
    /* return a randomly chosen function */
    public BooleanFunction(Problem prob)
    {
        functionList = prob.getFunctionList();
        Random rand = new Random();
        functionType = rand.nextInt(functionList[1].length);
        functionArity = functionList[0][functionType];
        functionLUT = findLUT(functionList[1][functionType]);
        name = nameLookUp[functionArity][functionList[1][functionType]];
    }
    
    public boolean eval(boolean input1, boolean input2)
    {
        return functionLUT[2*(input2?0:1)+(input1?0:1)];
    }
    
    public boolean eval(boolean[] inputs)
    {
        int lookUpIndex = 0;
        for (int i=0;i<inputs.length;i++)
        {
            lookUpIndex += ((int)Math.pow(2,i))*(inputs[i]?0:1);
        }
        return functionLUT[lookUpIndex];
    }
    
    /*
    private boolean[] findLUT(int ft)
    {
        //4 means 00,01,10,11
        boolean[] lut = new boolean[4];
        for (int i=0;i<4;i++)
        {
            lut[i] = (ft%2==1);
            ft /= 2;
        }
        return lut;
    }
    */
    
    private boolean[] findLUT(int ft)
    {
        //noPossible inputs means e.g. 4: 00,01,10,11
        int noPossibleInputs = (int)Math.pow(2,functionArity);
        boolean[] lut = new boolean[noPossibleInputs];
        for (int i=0;i<noPossibleInputs;i++)
        {
            lut[i] = (ft%2==1);
            ft /= 2;
        }
        return lut;
    }
    
    @Override
    public String toString()
    {
        //wibble this is still only for an arity-2 function
        String ans = new String();
        ans += "Name is "+name;
        ans += " which is of type "+functionType+"\n";
        ans += " " + (functionLUT[0]?1:0) + " " + (functionLUT[1]?1:0) +"\n";
        ans += " " + (functionLUT[2]?1:0) + " " + (functionLUT[3]?1:0) +"\n";
        return ans;
    }

    public String functionName()
    {
        return name;
    }

}
