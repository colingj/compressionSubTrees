package TestPrograms;

import compressionsubtrees.*;

public class Test2App 
{
    public static void main(String[] args)
    {
        Problem prob = new Problem();
        prob.createParityAllFunctions(4,false);
        //prob.createParityAllFunctions(1);
        BooleanTree t = new BooleanTree();
        t.generateRandomTree(prob);
        System.out.println(t);
        boolean[] inputs = new boolean[]{true,false,true,false};
        System.out.println(t.eval(inputs));
    }

}
