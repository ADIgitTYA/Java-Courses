import java.util.*;
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> fList;
    public MatchAllFilter() {
        fList = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f) {
        fList.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : fList) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }
        return true;
    }
    
    public String getName() {
        StringBuilder sb = new StringBuilder();
        for (Filter f : fList) {
            sb.append(f.getName() + " ");
        }
        return sb.toString();
    }
}
