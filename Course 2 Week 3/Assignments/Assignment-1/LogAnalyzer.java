
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
     /*In the LogAnalyzer class, write the void method printAllHigherThanNum that has one integer parameter num. This method should 
       examine all the web log entries in records and print those LogEntrys that have a status code greater than num. Be sure to add code
       in the Tester class to test out this method with the file short-test_log.*/
     public void printAllHigherThanNum(int num) {
         ArrayList<String> uniqueStatus = new ArrayList<String>();;
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num) {
                 System.out.println(le);
                 if (uniqueStatus.indexOf(String.valueOf(statusCode)) == -1) {
                     uniqueStatus.add(String.valueOf(statusCode));
                 }
             }
         }
         System.out.println("\n");
         uniqueStatus.forEach((a) -> System.out.println(a));
     }  
     /*n the LogAnalyzer class, write the method uniqueIPVisitsOnDay that has one String parameter named someday in the format “MMM DD”
       where MMM is the first three characters of the month name with the first letter capitalized and the others in lowercase, and DD
       is the day in two digits (examples are “Dec 05” and “Apr 22”). This method accesses the web logs in records and returns an
       ArrayList of Strings of unique IP addresses that had access on the given day. (Note that the dates in LogEntrys are stored
       as a Date object, but using toString will allow you to access the characters in the Date. For example, consider that d is a Date.
       String str = d.toString(); allows you to now use a String representation of the date.) Be sure to test your program with code in
       the Tester class. Using the file weblog-short_log you should see that the call to uniqueIPVisitsOnDay(“Sep 14”) returns an
       ArrayList of 2 items and uniqueIPVisitsOnDay(“Sep 30”) returns an ArrayList of 3 items.*/
     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueVisitors = new ArrayList<String>();
         for (LogEntry le : records) {
             Date date = le.getAccessTime();
             String realDate = date.toString();
             String IPAddr = le.getIpAddress();
             if (realDate.contains(someday) && !uniqueVisitors.contains(IPAddr)) {
                 uniqueVisitors.add(IPAddr);
             }
         }
         return uniqueVisitors;
     }
     /*In the LogAnalyzer class, write the method countUniqueIPsInRange that has two integer parameters named low and high. This method
       returns the number of unique IP addresses in records that have a status code in the range from low to high, inclusive. Be sure to
       test your program on several ranges. For example, using the file short-test_log, the call countUniqueIPsInRange(200,299) returns
       4, as there are four unique IP addresses that have a status code from 200 to 299. The call countUniqueIPsInRange(300,399) returns
       2. In this case, note that there are three entries in the file that have a status code in the 300 range, but two of them have the
       same IP address.*/
     public int countUniqueIPsInRange(int low , int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > low && statusCode < high) {
                uniqueIPs.add(le.getIpAddress());
             }
         }
         return uniqueIPs.size();
     }  
}
