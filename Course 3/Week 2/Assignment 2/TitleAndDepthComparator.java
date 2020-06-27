import java.util.*;
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1 , QuakeEntry q2) {
        String q1Title = q1.getInfo();
        String q2Title = q2.getInfo();
        int var = q1Title.compareTo(q2Title);
        if (var == 0) {
            double q1Depth = q1.getDepth();
            double q2Depth = q2.getDepth();
            return Double.compare(q1Depth, q2Depth);
        }
        return var;
    }
}
