package TestPrograms;

import compressionsubtrees.*;

public class Test6App 
{
    public static void main(String[] args)
    {
        Problem pp = new Problem();
        pp.createMultiplexer(1);
        Forest ff = new Forest(1,pp);
        ff.createRandom();

        ff.evaluateQuality_IG();
        System.out.println(ff);
        
        //BooleanTree tt = ff.getBest();
        //System.out.println("\n And the best is "+tt);
    }
}
