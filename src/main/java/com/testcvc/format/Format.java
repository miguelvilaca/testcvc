package com.testcvc.format;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Format {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	public static Long getDayBetweenDates(String d1, String d2) throws Exception{
		
		try{
		    LocalDate date1 = LocalDate.parse(d1, formatter);
		    LocalDate date2 = LocalDate.parse(d2, formatter);
		    
			return ChronoUnit.DAYS.between(date1, date2);
		}catch (Exception e){
			throw new Exception("formato inválido");
		}

	}

}
