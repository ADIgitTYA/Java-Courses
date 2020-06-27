
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter {
    private String req;
    private String sentence;
    private String myName;    
    public PhraseFilter(String request , String phrase , String name) {
        req = request;
        sentence = phrase;
        myName = name;        
    }
    
    public boolean satisfies(QuakeEntry qe) {
        String title = qe.getInfo();
        if (req.equals("start") && title.startsWith(sentence)) {
            return true;   
        }
        if (req.equals("end") && title.endsWith(sentence)) {
            return true;
        }
        if (req.toLowerCase().equals("any") && title.indexOf(sentence) != -1) {
            return true;
        } 
        return false;
    }
    
    public String getName() {
        return myName;
    }    
}
