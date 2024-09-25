package Practo_pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import base.base;

public class HomePage extends base{
	By city=By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/input");
	By InitialcityRemove=By.xpath("//*[@id=\"c-omni-container\"]/div/div[1]/div[1]/span[2]/span/i");
	By Speciality=By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input");
	By doctorname=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[1]/div/div[1]/div[2]/a/div/h2");
	By slotbook =By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/div/button");
	By AvailabilityMsg=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[1]/div/div[2]/div/div/div[1]/span");
	By TomorrowAvailableDate=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/button/span");
	By TomorrowButton=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/button"); 
	By TimeButton=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/span");
	By DoctorPageTime=By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");
	By DoctorPageDate=By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[1]/span[2]");
	By doctorsList=By.xpath("//h2[starts-with(text(),'Dr.')]");
	By DoctorSpecialityList=By.xpath("//div[@class=\"u-d-flex\"]/span");
	By FiltersButton=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[1]/div/div[4]/i");
	By FiltersSelection=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[2]/div/div[1]/div/label[1]/div");
	By ConsultationFees=By.xpath("//span[text()=\"₹\"]");
	
	
	public void citySelection() throws Exception {
	        WebElement from = driver.findElement(city);
	        from.click();
	        driver.findElement(InitialcityRemove).click();

	        from.sendKeys(config.getProperty("cityName"));
	        Thread.sleep(3000);

	        from.sendKeys(Keys.ARROW_DOWN);
	        Thread.sleep(1000);
	        from.sendKeys(Keys.ARROW_DOWN);
	        from.sendKeys(Keys.ARROW_DOWN);
	        from.sendKeys(Keys.ENTER);
	    }

	    public void specialitySelection() throws Exception {
	        WebElement specialist = driver.findElement(Speciality);
	        specialist.sendKeys(config.getProperty("specialty"));
	        specialist.click();
	        Thread.sleep(3000);

	        specialist.sendKeys(Keys.ARROW_DOWN);
	        specialist.sendKeys(Keys.ARROW_DOWN);
	        specialist.sendKeys(Keys.ARROW_DOWN);
	        specialist.sendKeys(Keys.ARROW_DOWN);
	        specialist.sendKeys(Keys.ARROW_DOWN);
	        specialist.sendKeys(Keys.ENTER);
	        Thread.sleep(3000);
	    }
	
	
	public void slotSelection() throws Exception{
		WebElement name=driver.findElement(doctorname);
		
		String strname=name.getText();
		System.out.println(strname);
		Thread.sleep(2000);
		
		driver.findElement(slotbook).click();
		Thread.sleep(3000);
		
		WebElement NoslotsToday=driver.findElement(AvailabilityMsg);
		String NotodaySlots=NoslotsToday.getText();
		System.out.println(NotodaySlots);
		
		WebElement NoslotsToday1=driver.findElement(TomorrowAvailableDate);
		String NotodaySlots1=NoslotsToday1.getText().substring(21);

		
		driver.findElement(TomorrowButton).click();
		
		WebElement time=driver.findElement(TimeButton);
		String strtime=time.getText();
		System.out.println(strtime);
    	time.click();
    	Thread.sleep(2000);
		
		
		WebElement time2=driver.findElement(DoctorPageTime);
		String strtime2=time2.getText();
		System.out.println(strtime2);
		
		WebElement date2 = driver.findElement(DoctorPageDate);
        String strdate = date2.getText();
        
        
        
        
        SimpleDateFormat inputFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH); // Input format without leading zero
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH); // Output format with leading zero
        
        try {
            // Parse the input times into Date objects
            Date date = inputFormat.parse(strtime);
            Date date1 = inputFormat.parse(strtime2);
            
            // Format the Date objects into the output format with leading zero
            String formattedTime1 = outputFormat.format(date);  // This will add the leading zero if necessary
            String formattedTime2 = outputFormat.format(date1);  // Already has a leading zero
            
            // Print the formatted times
            System.out.println("Formatted Time 1: " + formattedTime1);  // Output: 01:00 PM
            System.out.println("Formatted Time 2: " + formattedTime2);  // Output: 09:30 AM
            
            
    	    if (formattedTime1.equals(formattedTime2)) {
    	 		System.out.println("Time is matching");
    	 		test=report.createTest("Validate Time");
    	 		test.log(Status.PASS, "Validated");
    	 	}
    	 	    else {
    	 	    	System.out.println("The time slots are not matching");
    	 	    }
            
        } catch (ParseException e) {
            // Handle parsing errors
            System.out.println("Error parsing the time: " + e.getMessage());
        }
	
		
		
		        // Example input dates (these will be replaced with your WebElement.getText())
		        String dateString1 = NotodaySlots1;      // Format 1
		        String dateString2 = strdate;     // Format 2



		        // Convert both dates to the same format
		        String formattedDate1 = convertDateFormat(dateString1);
		        String formattedDate2 = convertDateFormat(dateString2);

		        // Check if both dates are equal
		        if (formattedDate1 != null && formattedDate2 != null) {
		            if (formattedDate1.equals(formattedDate2)) {
		                System.out.println("The two dates are equal: " + formattedDate1);
		            } else {
		                System.out.println("The two dates are NOT equal.");
		                System.out.println("Date 1: " + formattedDate1);
		                System.out.println("Date 2: " + formattedDate2);
		            }
		        }
		    }

		    public static String convertDateFormat(String dateString) {
		        // Clean up the date string if necessary (remove non-printable characters)
		        dateString = dateString.replaceAll("[^\\x20-\\x7E]", "").trim();

		        // Define the possible input formats
		        SimpleDateFormat format1 = new SimpleDateFormat("E, dd MMM", Locale.ENGLISH); // Format: Wed, 25 Sep
		        SimpleDateFormat format2 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH); // Format: Sep 25, 2024

		        // Define the desired output format: "dd/MM/yyyy"
		        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

		        Date date = null;
		        String outputDate = "";

		        try {
		            // Try parsing with the first format ("E, dd MMM")
		            date = format1.parse(dateString);
		            
		            // Get the current year if not provided in the date
		            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		            
		            // Format the parsed date and append the current year
		            outputDate = outputFormat.format(date) + "/" + currentYear;
		            outputDate = outputDate.replace("/1970", ""); // In case 1970 gets appended, handle it
		        } catch (ParseException e1) {
		            try {
		                // If parsing fails, try the second format ("MMM dd, yyyy")
		                date = format2.parse(dateString);
		                
		                // Format the date to the desired format without appending any extra year
		                outputDate = outputFormat.format(date);
		            } catch (ParseException e2) {
		                // Handle the case where neither format could parse the date
		                System.out.println("Error parsing the date: " + dateString);
		                return null; // Return null if parsing failed
		            }
		        }

		        return outputDate;

		
	
		
		

			}
	
	
	
	
	public void doctorsList() {
		List<WebElement> doctorList = driver.findElements(doctorsList);
		List<WebElement> specialityList = driver.findElements(DoctorSpecialityList);

		if (doctorList.size() == specialityList.size()) {
		    for (int i = 0; i < doctorList.size(); i++) {
		        // Print each doctor's name with the corresponding specialty
		        System.out.println(doctorList.get(i).getText() + " - " + specialityList.get(i).getText());
		    }
		} else {
		    System.out.println("The number of doctors and specialties do not match.");
		}
		
		
		

		
		if (doctorList.size()==specialityList.size()) {
			System.out.println("All doctors are related to the speciality");
			test=report.createTest("Validate Doctors Specialization");
	 		test.log(Status.PASS, "Successfully Validate Doctors Specialization ");
		}
		
		
//		WebElement sp= driver.findElement(By.xpath("//*[@id=\"c-omni-container\"]/div/div[2]/div[1]/input"));
//		System.out.println(sp.getText());
	}
	
	
	
	public void ToVerifyFilteredValues() throws Exception {
		WebElement filters = driver.findElement(FiltersButton);
		filters.click();
		Thread.sleep(3000);
		WebElement prices= driver.findElement(FiltersSelection);
		prices.click();
		

		
		
		List<WebElement> consultation_fees = driver.findElements(ConsultationFees);

//		
		List<Integer> feeList = new ArrayList<>(); // List to store fees as Integers

        // Collect fees into the list
        for (WebElement w1 : consultation_fees) {
            String feeStr = w1.getText().substring(1, 4); // Extract the fee amount
            int fee = Integer.parseInt(feeStr); // Convert string to integer
            feeList.add(fee); // Add the fee to the list
        }

        // Print the list to verify
        System.out.println("Consultation Fees: " + feeList);
        
        for (int fee : feeList) {
            if (fee >= 0 && fee <= 500) {
                System.out.println("Fee " + fee );
                test=report.createTest("Validate Prices");
    	 		test.log(Status.PASS, "Validated Prices");
            } else {
                System.out.println("Fee " + fee + " not matched");
            }
        }
		
		
	}
	
	
	
	

}