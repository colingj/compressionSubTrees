package compressionsubtrees;

public class Problem
{
    int numberOfVariables;
    boolean[] target;

    public void create4Parity()
    {
        numberOfVariables = 4;
        target = new boolean[]{false,true,true,false,
                    true, false, false, true,
                    true, false, false, true,
                    false, true, true, false};
    }

    public boolean[] getTarget()
    {
        return target;
    }

    public int getNumberOfVariables()
    {
        return numberOfVariables;
    }
}
