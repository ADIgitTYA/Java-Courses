
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData , int from) {
        int minIndex = from;
        for (int i = from + 1; i < quakeData.size(); i++) {
            if (quakeData.get(i).getDepth() > quakeData.get(minIndex).getDepth()) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData , int numSorted) {
        for (int i = 0; i < quakeData.size() - 1 - numSorted; i++) {
            QuakeEntry currQe = quakeData.get(i);
            QuakeEntry nextQe  = quakeData.get(i + 1);
            if (currQe.getMagnitude() > nextQe.getMagnitude()) {
                quakeData.set(i + 1 , currQe);
                quakeData.set(i , nextQe);
            }
        }
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "http://www.dukelearntoprogram.com/course4/data/earthquakeDataSampleSix1.atom";
        //String source = "http://www.dukelearntoprogram.com/course4/data/earthquakeDataSampleSix2.atom";
        //String source = "http://www.dukelearntoprogram.com/course4/data/earthQuakeDataDec6sample1.atom";
        //String source = "http://www.dukelearntoprogram.com/course4/data/earthQuakeDataDec6sample2.atom";
        //String source = "http://www.dukelearntoprogram.com/course4/data/earthQuakeDataWeekDec6sample1.atom";
        String source = "http://www.dukelearntoprogram.com/course4/data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortCheck(list);
        //sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < 50; i++) {
            int maxIndex = getLargestDepth(in , i);
            QuakeEntry currQuake = in.get(i);
            QuakeEntry maxQuake = in.get(maxIndex);
            in.set(i , maxQuake);
            in.set(maxIndex , currQuake);
        }
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        //in.forEach((a) -> System.out.println(a));
        System.out.println("\n");
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in , i);
            System.out.println("Printing after pass " + i);
            in.forEach((a) -> System.out.println(a));
        }
    }
    
    public boolean checkSortedInOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size() - 1; i++) {
            QuakeEntry currQe = quakes.get(i);
            QuakeEntry nextQe = quakes.get(i + 1);
            if (currQe.getMagnitude() > nextQe.getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    
    public void sortByMagnitudeWithBubbleSortCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size() - 1; i++) {
            onePassBubbleSort(in , i);
            boolean TF = checkSortedInOrder(in);
            if (TF) {
                System.out.println("Sorted in " + (i + 1) + " passes");
                break;
            }
        }
    }
    
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i = 0; i < in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            boolean check = checkSortedInOrder(in);
            if (check) {
                if (i + 1 == 1) {
                    System.out.println("Sorted in " + (i + 1) + " pass");
                } else {
                    System.out.println("Sorted in " + (i + 1) + " passes");
                }
                break;
            }
        }
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
}
