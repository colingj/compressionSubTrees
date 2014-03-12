package TestPrograms;

import compressionsubtrees.*;

public class Test5App 
{
    public static void main(String[] args)
    {
        Problem prob = new Problem();
        prob.createParityAllFunctions(4,false);
        for (int i=0;i<15;i++)
        {
            System.out.println("****** i="+i+" ************");
            BooleanFunction bf = new BooleanFunction(prob,i);
            System.out.println(bf);
            System.out.println(bf.eval(true, true));
            System.out.println();
        }
    }
}
