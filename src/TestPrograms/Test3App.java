package TestPrograms;

import compressionsubtrees.*;

public class Test3App
{
    public static void main(String[] args)
    {
        Problem pp = new Problem();
        pp.createParityAllFunctions(4,false);
        //Forest ff = new Forest(100,pp);
        Forest ff = new Forest(1,pp);
        ff.createRandom();

        System.out.println(ff);
        ff.evaluateQuality_IG();

        
        //BooleanTree tt = ff.getBest();
        //System.out.println("\n And the best is "+tt);
    }
}
