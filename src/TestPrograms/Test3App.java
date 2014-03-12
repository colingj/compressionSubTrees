package TestPrograms;

import compressionsubtrees.*;

public class Test3App
{
    public static void main(String[] args)
    {
        Problem pp = new Problem();
        pp.createParityAllFunctions(4,false);
        Forest ff = new Forest(100,pp);
        ff.createRandom();

        ff.evaluateQuality();
        System.out.println(ff);
        
        BooleanTree tt = ff.getBest();
        System.out.println("\n And the best is "+tt);
    }
}
