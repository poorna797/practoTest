package practo_tests;


import Practo_pages.Consultation_details_page;
import Practo_pages.HomePage;
import base.base;
import org.testng.*;
import org.testng.annotations.*;
public class Practo_test extends base{

		public static HomePage home_object=new HomePage();
		public static Consultation_details_page ConsultationPage= new Consultation_details_page();
		
		
		@BeforeClass
		public void report_set() {
			report_setup();
		}
		
		
		@Test(priority =1)
		public void Test1() throws Exception {
			Browser_Setup();
			openurl();
			home_object.citySelection();
			home_object.specialitySelection();
//			home_object.slotSelection();
			
			home_object.doctorsList();
			close_browser();
			
		}
		
		@Test(priority = 2)
		public void Test2() throws Exception{
			Browser_Setup();
			openurl();
			home_object.citySelection();
			home_object.specialitySelection();
			home_object.slotSelection();
//			ConsultationPage.ConsultationtimeCheck();
			
			close_browser();
//			
		}
		
		@Test(priority = 3)
		public void Test3() throws Exception {
			Browser_Setup();
			openurl();
			home_object.citySelection();
			home_object.specialitySelection();
			home_object.ToVerifyFilteredValues();
			
			close_browser();
//			report_flush();
		}
		
		@AfterClass
		public void reports() {
			report_flush();
		}
		
		
		
		
		
		
}
