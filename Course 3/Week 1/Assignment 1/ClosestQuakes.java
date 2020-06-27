
/**
 * Find N-closest quakes
 * 
 * @author Duke Software/Learn to Program
 * @version 1.0, November 2015
 */

import java.util.*;

public class ClosestQuakes {
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData, Location current, int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for (int i = 0; i < howMany; i++) {
            int minIndex = 0;
            for (int k = 0; k < copy.size(); k++) {
                Location currLoc = copy.get(k).getLocation();
                Location loc = copy.get(minIndex).getLocation();
                if (currLoc.distanceTo(current) < loc.distanceTo(current)) {
                    minIndex = k;
                }
            }
            ret.add(copy.get(minIndex));
            copy.remove(minIndex);
        }
        return ret;
    }

    public void findClosestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        //This is jakarta , Indonesia
        //Location jakarta  = new Location(-6.211,106.845);
        //This is New Dehli , Delhi , India
        Location NewDelhi = new Location(28.644800 , 77.216721);
        ArrayList<QuakeEntry> close = getClosest(list,NewDelhi,10);
        for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = NewDelhi.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }
        System.out.println("number found: "+close.size());
    }
    
}
