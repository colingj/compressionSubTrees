package compressionsubtrees;

import java.util.*;

/** this is the main loop which finds solutions **/
public class FindSolution 
{
    static final int numberOfExamples = 500; //wibble: make a parameter
    Problem pp;
    Forest ff;
    
    public FindSolution(String problemType)
    {
        pp = new Problem();
        //the following is rather crass
        switch (problemType)
        {
            case "parity4_all":   
                pp.create4ParityAllFunctions();
                break;
            case "parity4_some":
                pp.create4ParitySomeFunctions();
                break;
            case "parity4_some_bis":
                pp.createParitySomeFunctions(4);
                break;
            case "parity8_some_bis":
                pp.createParitySomeFunctions(8);
                break;
            case "majority8_some":
                pp.createMajoritySomeFunctions(8);
                break;
            default:
                System.err.println("Problem type not defined.");
                System.exit(1);
        }
    }
    
    public void findIt()
    {
        boolean perfectSolutionFound = false;
        int t=0; //generation count ("time")
        while (!perfectSolutionFound && t<100)
        {
            t++;
            System.out.println("***************** Generation "+t);
                    
            //create a new forest of trees, evaluate, add the best
            // to the cache
            ff = new Forest(numberOfExamples,pp);
            ff.createRandom();
            ArrayList<BooleanTree> perfectSolutions = ff.evaluateQuality();
            BooleanTree currentBest = ff.getBest();
            System.out.println(currentBest);
            pp.addNodeToCache(currentBest.getRootNode());
            
            //check for perfect solutions
            if (!perfectSolutions.isEmpty())
            {
                perfectSolutionFound = true;
                System.out.println("\n######################################");
                int nps = perfectSolutions.size();
                System.out.println(nps+" perfect solution"+(nps>1?"s":"")
                        +" found, which "+(nps>1?"are:":"is:"));
                for (BooleanTree perfectExample: perfectSolutions)
                {
                    System.out.println(perfectExample);
                }
            }
        }
    }
}
