package compressionsubtrees;

import java.io.*;
import java.util.zip.*;

public class gzipCompression
{

    public static int compress(boolean[] boolArray)
            throws IOException
    {
        String str = new String();
        for (boolean b : boolArray)
        {
            str += b?"0":"1";
        }
        //System.out.println(str);
        return compress(str);
    }

    public static int compress(String str)
            throws IOException
    {
        //System.out.println("String length : " + str.length());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        String outStr = out.toString("ISO-8859-1");
        //System.out.println("Output String length : " + outStr.length());
        return outStr.length();
     }
}
