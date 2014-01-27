package compressionsubtrees;

import java.util.*;

public class BooleanFunction 
{
    int functionType; //[0,15] representing the function
    boolean[] functionLUT;
    Problem prob;
    int[] functionList;
    String name;

    private static final String[] nameLookUp = new String[]
    { "allF","AND","f2","2nd","f4","1st","XOR","OR",
    "NOR","XNOR","f10","f11","f12","f13","NAND","allT"};
    
    public BooleanFunction(Problem prob, int functionTypep)
    {
        //wibble: is this ever used?
        functionList = prob.getFunctionList();
        functionType = functionTypep;
        functionLUT = findLUT(functionType);
        name = nameLookUp[functionType];
    }
    
    /* return a randomly chosen function */
    public BooleanFunction(Problem prob)
    {
        functionList = prob.getFunctionList();
        Random rand = new Random();
        functionType = rand.nextInt(functionList.length);
        functionLUT = findLUT(functionList[functionType]);
        name = nameLookUp[functionType];
    }
    
    public boolean eval(boolean input1, boolean input2)
    {
        return functionLUT[2*(input2?0:1)+(input1?0:1)];
    }
    
    private boolean[] findLUT(int ft)
    {
        //4 means 00,01,10,11
        boolean[] lut = new boolean[4];
        for (int i=0;i<4;i++)
        {
            lut[i] = (ft%2==1)?true:false;//wibble: what?
            ft /= 2;
        }
        return lut;
    }
    
    @Override
    public String toString()
    {
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
