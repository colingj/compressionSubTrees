package TestPrograms;

import compressionsubtrees.*;

public class Test2App 
{
    public static void main(String[] args)
    {
        BooleanTree t = new BooleanTree();
        t.generateRandomTree(4);
        System.out.println(t);
        boolean[] inputs = new boolean[]{true,false,true,false};
        System.out.println(t.eval(inputs));
    }

}
