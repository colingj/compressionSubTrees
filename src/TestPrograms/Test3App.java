/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPrograms;

import compressionsubtrees.*;


public class Test3App
{
    public static void main(String[] args)
            throws java.io.IOException
    {
        Problem pp = new Problem();
        pp.create4Parity();
        Forest ff = new Forest(100,pp);
        ff.createRandom(4);

        ff.evaluateQuality();
        System.out.println(ff);
    }
}
