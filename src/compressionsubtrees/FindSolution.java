package compressionsubtrees;

import java.util.*;

/** this is the main loop which finds solutions **/
public class FindSolution 
{
    static final int numberOfExamples = 500; //wibble: make a parameter
    Problem pp;
    String fitnessType;
    Forest ff;
    
    public FindSolution(String problemType, int size, String fitnessTypep)
    {
        if (fitnessTypep.equals("Comp")||fitnessTypep.equals("IGR"))
        {
            fitnessType = fitnessTypep;
        }
        else
        {
            System.err.println("In FindSolution: Fitness method not defined (1)");
            System.exit(1);
        }
        pp = new Problem();
        if (problemType.equals("parity_all")) 
          { pp.createParityAllFunctions(size, false); }
        else if (problemType.equals("parity_some")) 
          { pp.createParitySomeFunctions(size, false); }
        else if (problemType.equals("majority_some")) 
          { pp.createMajoritySomeFunctions(size); }
        else if (problemType.equals("multiplexer_all")) 
          { pp.createMultiplexer(size); }
        else
        {
            System.err.println("Problem type not defined.");
            System.exit(1);
        }
    }
    
    public void findIt() //this is TDF_comp: the main program
    {
        boolean perfectSolutionFound = false;
        int t=0; //generation count ("time")
        while (!perfectSolutionFound && t<100)
        {
            t++;
            System.out.println("***************** Iteration "+t);
                    
            //create a new forest of trees, evaluate, add the best
            // to the cache
            ff = new Forest(numberOfExamples,pp);
            ff.createRandom();
            ArrayList<BooleanTree> perfectSolutions = new ArrayList<BooleanTree>();
            BooleanTree currentBest = new BooleanTree();
            if (fitnessType.equals("Comp"))
            {
                perfectSolutions = ff.evaluateQuality_Comp();
                currentBest = ff.getBest_Comp();
            }
            else if (fitnessType.equals("IGR"))
            {
                perfectSolutions = ff.evaluateQuality_IGR();
                currentBest = ff.getBest_IGR();
            }
            else
            {
                System.err.println("In FindSolution: Fitness method not defined (2)");
                System.exit(1);
            }
            System.out.println(currentBest);
            pp.addNodeToCache(currentBest.getRootNode());
            System.out.println("Current Construction Set: "+pp.printCache());
            
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
    
    public void tdfEntropy()
            //this will TDF_IG - the other main program
    {
        boolean perfectSolutionFound = false;
        int t=0;
        ff = new Forest(numberOfExamples,pp);
        ff.createRandom();
        //ArrayList<BooleanTree> perfectSolutions = ff.evaluateQualityIG();
        //wibble - incomplete
    }
}
