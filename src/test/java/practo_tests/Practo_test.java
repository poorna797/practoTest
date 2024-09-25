package practo_tests;



import Practo_pages.CitySelectionPage;
import Practo_pages.DoctorSlotSelectionPage;
import Practo_pages.HomePage;
import Practo_pages.Home_page;
import Practo_pages.SpecialitySelectionPage;
import base.base;
import org.testng.annotations.*;
public class Practo_test extends base{

		public static HomePage home_object=new HomePage();
		public static Home_page home=new Home_page();
		public static CitySelectionPage city=new CitySelectionPage();
		public static SpecialitySelectionPage speciality=new SpecialitySelectionPage();
		public static DoctorSlotSelectionPage selection=new DoctorSlotSelectionPage();
		
		@BeforeClass
		public void report_set() {
			report_setup();
		}
		
		
		@Test(priority =1)
		public void Test1() throws Exception {
			Browser_Setup();
			openurl();
			city.selectCity();
			speciality.selectSpeciality();
			home.doctorsList();
			close_browser();
			
		}
		
		@Test(priority = 2)
		public void Test2() throws Exception{
			Browser_Setup();
			openurl();
			city.selectCity();
			speciality.selectSpeciality();
			selection.selectSlot();
			close_browser();		
		}
		
		@Test(priority = 3)
		public void Test3() throws Exception {
			Browser_Setup();
			openurl();
			city.selectCity();
			speciality.selectSpeciality();
			home.ToVerifyFilteredValues();
			close_browser();
//			report_flush();
		}
		
		@AfterClass
		public void reports() {
			report_flush();
		}
		
		
		
		
		
		
}
