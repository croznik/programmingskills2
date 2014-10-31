
/**
 * Write a description of class TestDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestDriver
{
   public static void main(String[] arg)
    {
        //GridMap map = new GridMap(20,20);
		GridMap map = new GridMap(50,50,"small.dat");//small.dat is in LREAN, just use this to test
        System.out.print(map.toString());
    }
}
