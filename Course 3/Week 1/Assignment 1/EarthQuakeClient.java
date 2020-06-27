import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            double currMag = qe.getMagnitude();
            if (currMag > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData) {
            Location currLoc = qe.getLocation();
            double currDist = currLoc.distanceTo(from); 
            if (currDist < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> fbm = filterByMagnitude(list , 5.0);
        int count = 0;
        for (QuakeEntry qe : fbm) {
            count++;
            System.out.println(qe);
        }
        System.out.println("Found " + count + " quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        
        // This location is Bridgeport, CA
        //Location city =  new Location(38.17, -118.82);
        
        //This location is New Delhi , Delhi , India
        Location city = new Location(28.644800 , 77.216721);
        // TODO
        int count = 0;
        ArrayList<QuakeEntry> qes = filterByDistanceFrom(list , 1000000 , city);
        for (QuakeEntry qe : qes) {
            System.out.print(qe.getLocation().distanceTo(city));
            System.out.println(" " + qe.getInfo());
            count++;
        }
        System.out.println("Found " + count + " quakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData , double minDepth , double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double currDepth = qe.getDepth();
            if (currDepth < minDepth && currDepth > maxDepth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double minDepth = -2000.00;
        double maxDepth = -4000.00;
        ArrayList<QuakeEntry> quakes = filterByDepth(list, minDepth, maxDepth);
        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        }
        System.out.println("Found " + quakes.size() + " quakes that match that criteria");        
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData , String where , String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            if (where.toLowerCase().equals("start") && title.startsWith(phrase)) {
                answer.add(qe);
            } else if (where.toLowerCase().equals("end") && title.endsWith(phrase)) {
                answer.add(qe);
            } else if (where.toLowerCase().equals("any") && title.indexOf(phrase) != -1) {
                answer.add(qe);
            }
        }
        return answer;
    }
    
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        String place = "Can";
        String pos = "any";
        ArrayList<QuakeEntry> quakes = filterByPhrase(list, pos , place);
        for (QuakeEntry qe : quakes) {
            System.out.println(qe);
        }
        if (!pos.equals("end") && !pos.equals("start")) {
            System.out.println("Found " + quakes.size() + " quakes that match " + place);
        } else {
            System.out.println("Found " + quakes.size() + " quakes that match " + place + " at the " + pos.toLowerCase());
        }
    }
}
