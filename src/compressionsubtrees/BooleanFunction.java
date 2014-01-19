package compressionsubtrees;

import java.util.*;

public class BooleanFunction 
{
    int functionType; //[0,15] representing the function
    boolean[] functionLUT;
    String name;

    private static final String[] nameLookUp = new String[]
    { "allF","AND","f2","2nd","f4","1st","XOR","OR",
    "NOR","XNOR","f10","f11","f12","f13","NAND","allT"};
    
    public BooleanFunction(int functionTypep)
    {
        functionType = functionTypep;
        functionLUT = findLUT(functionType);
        name = nameLookUp[functionType];
    }
    
    /* return a randomly chosen function */
    public BooleanFunction()
    {
        Random rand = new Random();
        functionType = rand.nextInt(16);
        functionLUT = findLUT(functionType);
        name = nameLookUp[functionType];
    }
    
    public boolean eval(boolean input1, boolean input2)
    {
        return functionLUT[2*(input2?0:1)+(input1?0:1)];
    }
    
    private boolean[] findLUT(int ft)
    {
        boolean[] lut = new boolean[4];
        for (int i=0;i<4;i++)
        {
            lut[i] = (ft%2==1)?true:false;
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
