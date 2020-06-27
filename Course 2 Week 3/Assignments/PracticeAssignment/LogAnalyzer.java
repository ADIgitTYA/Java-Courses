
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
/*In the LogAnalyzer class you will need to complete the constructor to initialize records to an empty ArrayList and complete the 
  readFile method to create a FileResource and to iterate over all the lines in the file. For each line, create a LogEntry and store
  it in the records ArrayList. (Refer to the lecture on parsing log files for help.)*/
public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs() {
         //uniqueIPs starts as an empty list
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         //for each element le in records 
         for (LogEntry le : records) {
            //ipAddr is le's ipAddress
            String ipAddr = le.getIpAddress();
            ///if ipAddr is not in uniqueIPs
            if (!uniqueIPs.contains(ipAddr)) {
                //add ipAddr to uniqueIPs
                uniqueIPs.add(ipAddr);
            }
         }
         return uniqueIPs.size();
     }
}
