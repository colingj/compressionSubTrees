package TestPrograms;

import compressionsubtrees.*;

public class Test3App
{
    public static void main(String[] args)
    {
        Problem pp = new Problem();
        pp.createParityAllFunctions(6,false);

        //Forest ff = new Forest(100,pp);
        Forest ff = new Forest(100,pp);
        ff.createRandom();

        //ff.evaluateQuality_IGR();
        BooleanTree tt = ff.getBest_IGR();
        
        System.out.println(ff);
        System.out.println("\n And the best is "+tt);
    }
}
