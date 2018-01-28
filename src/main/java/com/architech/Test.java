package com.architech;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Scanner;

public class Test {

	
/*	{
		"flights": [
		{
		"flight": "Air Canada 8099",
		"departure": "7:30AM"
		},
		{
		"flight": "United Airline 6115",
		"departure": "10:30AM"
		},
		{
		"flight": "WestJet 6456",
		"departure": "12:30PM"
		},
		{
		"flight": "Delta 3833",
		"departure": "3:00PM"
		}
		]
	}
*/	
	
	public static void main(String[] args) {
		String s = "07:30.000";
		parseTimeNanos(s, 0, s.length(), false);
		
		/*
		 * { "flights": [ { "flight": "Air Canada 8099", "departure": "7:30AM" }, {
		 * "flight": "United Airline 6115", "departure": "10:30AM" }, { "flight":
		 * "WestJet 6456", "departure": "12:30PM" }, { "flight": "Delta 3833",
		 * "departure": "3:00PM" } ] }
		 */
		// private List<Flight> FLIGHTS =
		// new ArrayList<Flight>(Arrays.asList(
		// new Flight("Air Canada 8099", LocalTime.of(7, 30)),
		// new Flight("United Airline 6115", LocalTime.of(10, 30)),
		// new Flight("WestJet 6456", LocalTime.of(12, 30)),
		// new Flight("Delta 3833", LocalTime.of(15, 0))
		// )
		// );

		// @formatter:on

//		DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern("hh:mm").toFormatter();
//		LocalTime start = LocalTime.of(9, 0);
//		LocalTime end = start.plusMinutes(45);
//
//		System.out.println(dtf.format(start) + " to " + dtf.format(end));
//
//		
//		LocalDateTime nextTime = currentTime.plusHours(12);
//	    Instant instant2 = nextTime.toInstant(ZoneOffset.UTC);
//	    Date expiryDate = Date.from(instant2);
//	    System.out.println("After 12 Hours = " + expiryDate);;
		
//        Scanner sc = new Scanner(System.in);
//        while(sc.hasNextInt()){
//            int n = sc.nextInt();
//            System.out.println((n%2 == 1 || (n >= 6 && n <= 20)) ? "Weird" : "Not Weird");
//        }		
	}
	
	
/*	public static void main(String[] args) {
		int[] x = {1, 2, 10, -1};
		int k = 9;//Integer.parseInt(args[0]);
		boolean y = false;
		for (int i = 0; i < x.length; ++i)
		{
			int next = i + 1;
		    if (next < x.length && x[i] + x[next] == k) {
		    	y = true;
		    	break;
		    }
		}
		System.out.println(y);
	}
*/
/*	public static void main(String[] args) {
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\tempctrl.txt"))) {
			
			String line;
			Integer tempMin = 0;
			Integer tempMax = 0;
			double tempSum = 0.00; 
			int count = 0;
			
			while ((line = br.readLine()) != null) {
				
				Integer value = Integer.valueOf(line);
				
				tempMin = count == 0 ? value : tempMin;
				tempMax = count == 0 ? value : tempMax;
				tempSum += value;
				
				if (value < tempMin) {
					tempMin = value;
				}
				
				if (value > tempMax) {
					tempMax = value;
				}
				
				count++;
			}
			System.out.println("Minimum: " + tempMin);
			System.out.println("Maximum: " + tempMax);
			
			double average = tempSum/count;
			BigDecimal avg = new BigDecimal(average);
			avg = avg.setScale(2, BigDecimal.ROUND_UP);
			System.out.println("Average: " + avg);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
*/	
	
    public static long parseTimeNanos(String s, int start, int end,
            boolean timeOfDay) {
        int hour = 0, minute = 0, second = 0;
        long nanos = 0;
        int s1 = s.indexOf(':', start);
        int s2 = s.indexOf(':', s1 + 1);
        int s3 = s.indexOf('.', s2 + 1);
        if (s1 <= 0 || s2 <= s1) {
            // if first try fails try to use IBM DB2 time format
            // [-]hour.minute.second[.nanos]
            s1 = s.indexOf('.', start);
            s2 = s.indexOf('.', s1 + 1);
            s3 = s.indexOf('.', s2 + 1);

            if (s1 <= 0 || s2 <= s1) {
                throw new IllegalArgumentException(s);
            }
        }
        boolean negative;
        hour = Integer.parseInt(s.substring(start, s1));
        if (hour < 0) {
            if (timeOfDay) {
                throw new IllegalArgumentException(s);
            }
            negative = true;
            hour = -hour;
        } else {
            negative = false;
        }
        minute = Integer.parseInt(s.substring(s1 + 1, s2));
        if (s3 < 0) {
            second = Integer.parseInt(s.substring(s2 + 1, end));
        } else {
            second = Integer.parseInt(s.substring(s2 + 1, s3));
            String n = (s.substring(s3 + 1, end) + "000000000").substring(0, 9);
            nanos = Integer.parseInt(n);
        }
        if (hour >= 2000000 || minute < 0 || minute >= 60 || second < 0
                || second >= 60) {
            throw new IllegalArgumentException(s);
        }
        if (timeOfDay && hour >= 24) {
            throw new IllegalArgumentException(s);
        }
        nanos += ((((hour * 60L) + minute) * 60) + second) * 1000000000;
        return negative ? -nanos : nanos;
    }

}
