
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter {
    private double maxDepth;
    private double minDepth;
    private String myName;
    public DepthFilter(double min , double max , String name) {
        maxDepth = max;
        minDepth = min;
        myName = name;        
    }
    
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= minDepth && qe.getDepth() <=
        maxDepth; 
    }
    
    public String getName() {
        return myName;
    }    
}
