package TestPrograms;

import compressionsubtrees.*;

public class Test4App 
{
    public static void main(String[] args)
    {
        FindSolution fs = new FindSolution("parity_all",4,"Comp");
        //FindSolution fs = new FindSolution("multiplexer_all",1,"IGR");
        fs.findIt();
    }
}
