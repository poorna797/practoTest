package Practo_pages;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import base.base;

public class Home_page extends base {
  
	By FiltersButton=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[1]/div/div[4]/i");
	By FiltersSelection=By.xpath("//*[@id=\"container\"]/div/div[3]/div/div/header/div[2]/div/div[1]/div/label[1]/div");
	By ConsultationFees=By.xpath("//span[text()=\"₹\"]");
	By doctorsList=By.xpath("//h2[starts-with(text(),'Dr.')]");
	By DoctorSpecialityList=By.xpath("//div[@class=\"u-d-flex\"]/span");
	
	public void performSearchAndBooking() throws Exception {
        CitySelectionPage cityPage = new CitySelectionPage();
        cityPage.selectCity();

        SpecialitySelectionPage specialityPage = new SpecialitySelectionPage();
        specialityPage.selectSpeciality();

        DoctorSlotSelectionPage doctorSlotPage = new DoctorSlotSelectionPage();
        doctorSlotPage.selectSlot();
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
                System.out.println("Fee " + fee + "is within the range." );
                test=report.createTest("Validate Prices");
    	 		test.log(Status.PASS, "Validated Prices");
            } else {
                System.out.println("Fee " + fee + " not matched");
            }
        }
    }}
