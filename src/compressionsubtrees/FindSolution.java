package compressionsubtrees;

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
        BooleanTree currentBest;
        for (int t=0;t<10;t++) //wibble: eventually replace with check
        {
            ff = new Forest(numberOfExamples,pp);
            ff.createRandom();
            ff.evaluateQuality();
            currentBest = ff.getBest();
            System.out.println(currentBest);
            pp.addNodeToCache(currentBest.getRootNode());
        }
    }
}
