import java.util.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    /* A method named indexOfLargest that has one parameter, an ArrayList of type QuakeEntry named data.
     * This method returns an integer representing the index location in data of the earthquake with the largest magnitude.
     */
    private int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;
        double magnitude = 0;
        for (QuakeEntry qe : data) {
            double currMag = qe.getMagnitude();
            if (currMag > magnitude) {
                index = data.indexOf(qe);
                magnitude = currMag;
            }
        }
        System.out.println(magnitude);
        return index;
    }
    
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        //This is jakarta , Indonesia
        //Location jakarta  = new Location(-6.211,106.845);
        //This is New Dehli , Delhi , India
        //Location NewDelhi = new Location(28.644800 , 77.216721);
        //int large = indexOfLargest(list);
        /*for(int k=0; k < close.size(); k++){
            QuakeEntry entry = close.get(k);
            double distanceInMeters = NewDelhi.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", distanceInMeters/1000,entry);
        }*/
        //System.out.println(large);
        ArrayList<QuakeEntry> quakes = getLargest(list , 50);
        quakes.forEach((a) -> System.out.println(a));
    }
    
    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData , int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        int total = howMany;
        if (quakeData.size() < howMany) {
            total = quakeData.size();
        }
        for (int i = 0; i < total; i++) {
            int maxIndex = 0;
            for (int k = 0; k < copy.size(); k++) {
                double currMag = copy.get(k).getMagnitude();
                double magnitude = copy.get(maxIndex).getMagnitude();
                if (currMag > magnitude) {
                    maxIndex = k;
                }
            }
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;        
    }
}
