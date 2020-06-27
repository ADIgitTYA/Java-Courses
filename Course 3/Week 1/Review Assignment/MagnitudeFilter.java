
/**
 * Write a description of MagnitudeFillter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter {
    private double magMax;
    private double magMin;
    private String myName;
    public MagnitudeFilter(double min , double max , String name) {
        magMin = min;
        magMax = max;
        myName = name;
    }
    
    public boolean satisfies(QuakeEntry qe) {
        double mag = qe.getMagnitude();
        return mag >= magMin &&  mag <= magMax;
    }
    
    public String getName() {
        return myName;
    }
}
