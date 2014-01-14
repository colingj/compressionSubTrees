package TestPrograms;

import compressionsubtrees.*;
import lzwCompression.*;

/** this is just a little test harness to check that particular 
 * parts of the implementation work in isolation **/

public class Test1App 
{
    public static void main(String[] args)
    {
        System.out.println("\n ****************************************** \n");
        
        BooleanFunction b = new BooleanFunction();
        System.out.println(b);
        System.out.println("FF\t"+b.eval(false,false));
        System.out.println("FT\t"+b.eval(false,true));
        System.out.println("TF\t"+b.eval(true,false));
        System.out.println("TT\t"+b.eval(true,true));
        
        System.out.println("\n ****************************************** \n");
        
        ProblemDefinition d = new ProblemDefinition(4);
        d.setUp4BitParity();
        boolean[] in = new boolean[] {true,false,true,true};
        System.out.println("is it odd? "+d.lookup(in));
        
        System.out.println("\n ****************************************** \n");
        
        //test some compression
        boolean[] diff1 = new boolean[] {true,false,false,true,true,false,false,
            true,true,false,false,true,true,false,false,true};
        boolean[] diff2 = new boolean[] {false,true,true,false,true,false,false,
            true,true,false,false,true,true,false,false,true};
        System.out.println("diff1 "+lzwCompression.compress(diff1));
        System.out.println("diff2 "+lzwCompression.compress(diff2));
        System.out.println(lzwCompression.compress("");
    }
}
