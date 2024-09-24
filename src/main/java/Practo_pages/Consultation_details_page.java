package Practo_pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Consultation_details_page extends HomePage{
	
	
	
		public void ConsultationtimeCheck() throws Exception {
			
			WebElement time=driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div/span"));
			String strtime=time.getText();
			System.out.println(strtime);
	    	time.click();
	    	Thread.sleep(2000);
	    	
	    	WebElement time2=driver.findElement(By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]"));
			String strtime2=time2.getText();
			System.out.println(strtime2);
	    	time2.click();
//			System.out.println(strname);
			
			
			
			 SimpleDateFormat inputFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH); // Input format without leading zero
	         SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH); // Output format with leading zero
	         
	         try {
	             // Parse the input times into Date objects
	             Date date1 = inputFormat.parse(strtime);
	             Date date2 = inputFormat.parse(strtime2);
	             
	             // Format the Date objects into the output format with leading zero
	             String formattedTime1 = outputFormat.format(date1);  // This will add the leading zero if necessary
	             String formattedTime2 = outputFormat.format(date2);  // Already has a leading zero
	             
	             // Print the formatted times
	             System.out.println("Formatted Time 1: " + formattedTime1);  // Output: 01:00 PM
	             System.out.println("Formatted Time 2: " + formattedTime2);  // Output: 09:30 AM
	             
	             
	     	    if (formattedTime1.equals(formattedTime2)) {
	     	 		System.out.println("Time is matching");
	     	 	}
	     	 	    else {
	     	 	    	System.out.println("The time slots are not matching");
	     	 	    }
	             
	         } catch (ParseException e) {
	             // Handle parsing errors
	             System.out.println("Error parsing the time: " + e.getMessage());
	         }
		}
		
		
		
		
		

}
