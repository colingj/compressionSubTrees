package compressionsubtrees;

import java.util.*;

/** this is the main loop which finds solutions **/
public class FindSolution 
{
    static final int numberOfExamples = 100; //wibble: make a parameter
    Problem pp;
    Forest ff;
    
    public FindSolution()
    {
        pp = new Problem();
        pp.create4Parity(); // wibble: make this general
        //ff = new Forest(numberOfExamples,pp);
        //ff.createRandom();
    }
    
    public void findIt()
    {
        boolean perfectSolutionFound = false;
        int t=0; //generation count ("time")
        while (!perfectSolutionFound)
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
