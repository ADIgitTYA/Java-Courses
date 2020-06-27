
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
/*In the Tester class you will need to complete the testLogAnalyzer method, which creates a LogAnalyzer object, calls readFile on
  the data file short-test_log, and then calls printAll to print all the web logs.*/
public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer analyze = new LogAnalyzer();
        analyze.readFile("short-test_log");
        analyze.printAll();
    }
}
