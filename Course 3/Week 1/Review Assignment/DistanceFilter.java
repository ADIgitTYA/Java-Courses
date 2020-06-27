
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location myLoc;
    private double maxDist;
    private String myName;
    public DistanceFilter(Location loc , double dist , String name) {
        myLoc = loc;
        maxDist = dist;
        myName = name;        
    }
    
    public boolean satisfies(QuakeEntry qe) {
        Location quakeLoc = qe.getLocation();
        return quakeLoc.distanceTo(myLoc) < maxDist;
    }
    
    public String getName() {
        return myName;
    }    
}
