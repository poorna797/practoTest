




package practo_tests;


//import Practo_pages.Consultation_details_page;
import Practo_pages.HomePage;
import base.base;
import org.testng.*;
import org.testng.annotations.*;
public  class test1  extends base{

		public static HomePage home_object=new HomePage();
//		public static Consultation_details_page ConsultationPage= new Consultation_details_page();
		
		@Test
		public void Test1() throws Exception {
			Browser_Setup();
			openurl();
			home_object.citySelection();
			home_object.specialitySelection();
//			home_object.slotSelection();
			home_object.doctorsList();
			close_browser();
		}
		
//		@Test
//		public void Test2() throws Exception{
//			Browser_Setup();
//			openurl();
//			home_object.citySelection();
//			home_object.specialitySelection();
//			home_object.slotSelection();
//			ConsultationPage.ConsultationtimeCheck();
//			close_browser();
//			
//		}
		
		
		
}

