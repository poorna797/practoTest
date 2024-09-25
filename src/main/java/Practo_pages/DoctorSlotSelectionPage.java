package Practo_pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.Status;
import base.base;

public class DoctorSlotSelectionPage extends base {
    By doctorname = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[1]/div/div[1]/div[2]/a/div/h2");
    By slotbook = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[1]/div/div[2]/div/div/div[2]/div/button");
    By AvailabilityMsg = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[1]/div/div[2]/div/div/div[1]/span");
    By TomorrowAvailableDate = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/button/span");
    By TomorrowButton = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div/div[2]/div[2]/button");
    By TimeButton = By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/span");
    By DoctorPageTime = By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[2]/span[2]");
    By DoctorPageDate = By.xpath("//*[@id=\"container\"]/div[2]/div/div[1]/div/div[1]/div[2]/div[1]/div[1]/span[2]");
    By SlotTime=By.xpath("//*[@id=\"container\"]/div/div[4]/div/div[1]/div/div[3]/div[2]/div/div/div/div[2]/div[2]/div[1]/div[2]/div/span");
    public void selectSlot() throws Exception {
        WebElement name = driver.findElement(doctorname);
        String strname = name.getText();
        System.out.println(strname);
        Thread.sleep(2000);

        driver.findElement(slotbook).click();
        Thread.sleep(3000);

        WebElement NoslotsToday = driver.findElement(AvailabilityMsg);
        String NotodaySlots = NoslotsToday.getText();
        System.out.println(NotodaySlots);
        
        if (NotodaySlots.contains("Tomorrow")){

        WebElement NoslotsToday1 = driver.findElement(TomorrowAvailableDate);
        String NotodaySlots1 = NoslotsToday1.getText().substring(21);

        driver.findElement(TomorrowButton).click();

        WebElement time = driver.findElement(TimeButton);
        String strtime = time.getText();
        System.out.println(strtime);
        time.click();
        Thread.sleep(2000);

        WebElement time2 = driver.findElement(DoctorPageTime);
        String strtime2 = time2.getText();
        System.out.println(strtime2);

        WebElement date2 = driver.findElement(DoctorPageDate);
        String strdate = date2.getText();

        verifyTimeAndDate(strtime, strtime2, NotodaySlots1, strdate);
    }
        
        else {
        	
        	WebElement time=driver.findElement(SlotTime);
    		String strtime=time.getText();
    		System.out.println(strtime);
        	time.click();
        	Thread.sleep(2000);
        	
        	 WebElement date2 = driver.findElement(DoctorPageDate);
             String strdate = date2.getText().trim(); 
             System.out.println("Original Date: '" + strdate + "'"); 

             WebElement time2=driver.findElement(DoctorPageTime);
     		String strtime2=time2.getText();
     		System.out.println(strtime2);
         	time2.click();
         	
         	
         	 LocalDate today = LocalDate.now();

            
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM", Locale.ENGLISH);

             // Format today's date into a string
             String TodayDate = today.format(formatter);
         	
         	
         	
         	verifyTimeAndDate(strtime, strtime2, TodayDate, strdate);
             
        }
        
    }

    public void verifyTimeAndDate(String strtime, String strtime2, String NotodaySlots1, String strdate) throws Exception {
        SimpleDateFormat inputFormat = new SimpleDateFormat("h:mm a", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);

        try {
            Date date = inputFormat.parse(strtime);
            Date date1 = inputFormat.parse(strtime2);

            String formattedTime1 = outputFormat.format(date);
            String formattedTime2 = outputFormat.format(date1);

            System.out.println("Formatted Time 1: " + formattedTime1);
            System.out.println("Formatted Time 2: " + formattedTime2);

            if (formattedTime1.equals(formattedTime2)) {
                System.out.println("Time is matching");
                test = report.createTest("Validate Time");
                test.log(Status.PASS, "Validated");
            } else {
                System.out.println("The time slots are not matching");
            }

        } catch (Exception e) {
            System.out.println("Error parsing the time: " + e.getMessage());
        }

        String formattedDate1 = convertDateFormat(NotodaySlots1);
        String formattedDate2 = convertDateFormat(strdate);

        if (formattedDate1 != null && formattedDate2 != null) {
            if (formattedDate1.equals(formattedDate2)) {
                System.out.println("The two dates are equal: " + formattedDate1);
            } else {
                System.out.println("The two dates are NOT equal.");
            }
        }
    }

    public static String convertDateFormat(String dateString) {
        dateString = dateString.replaceAll("[^\\x20-\\x7E]", "").trim();
        SimpleDateFormat format1 = new SimpleDateFormat("E, dd MMM", Locale.ENGLISH);
        SimpleDateFormat format2 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = null;
        String outputDate = "";

        try {
            date = format1.parse(dateString);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            outputDate = outputFormat.format(date) + "/" + currentYear;
            outputDate = outputDate.replace("/1970", "");
        } catch (Exception e1) {
            try {
                date = format2.parse(dateString);
                outputDate = outputFormat.format(date);
            } catch (Exception e2) {
                System.out.println("Error parsing the date: " + dateString);
                return null;
            }
        }

        return outputDate;
    }
}

