import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1 , QuakeEntry q2) {
        String[] q1List = q1.getInfo().trim().split("\\s+");
        String q1Last = q1List[q1List.length - 1];
        String[] q2List = q2.getInfo().trim().split("\\s+");
        String q2Last = q2List[q2List.length - 1];
        int var = q1Last.compareTo(q2Last);
        if (var == 0) {
            double q1Mag = q1.getMagnitude();
            double q2Mag = q2.getMagnitude();
            return Double.compare(q1Mag , q2Mag);
        }
        return var;
    }
}
