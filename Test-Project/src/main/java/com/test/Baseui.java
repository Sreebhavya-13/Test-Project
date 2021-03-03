package com.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Baseui {

		public static WebDriver driver=null;
		public static Properties prop;
		public static WebElement element;
		
	
		@BeforeClass
		public void Royal() throws IOException{
			
			prop = new Properties();
			FileInputStream ip = null;
			try {
				ip = new FileInputStream("C:\\Users\\User\\Documents\\Mini\\test\\src\\main\\java\\test.properties");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				prop.load(ip);
			}
		//Handling Multiple browsers
		@Test(priority=1)

		public void handleBrowser() 
		{
			System.out.println(prop.getProperty("browser"));
			String browserName=(prop.getProperty("browser"));
			if(browserName.equals("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver","C:\\Users\\User\\Documents\\Mini\\test\\src\\test\\resources\\Drivers\\chromedriver.exe");
				driver=new ChromeDriver();
							
			}
			else if(browserName.equals("Firefox"))
			{
				System.setProperty("webdriver.gecko.driver","C:\\Users\\User\\Documents\\Mini\\test\\src\\test\\resources\\Drivers\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else {
				System.out.println("No browser value is given");
				
			}
			driver.manage().window().maximize();
			
		}
		//Launch the website using baseUrl
		@Test(dependsOnMethods = { "handleBrowser" })
		public void openUrl() {
			
		   driver.get(prop.getProperty("baseUrl"));

		}		
		//Check whether Popup occurs or not
		@Test(priority=2)
		public void checkPopUp()
		{
			driver.manage().timeouts().implicitlyWait(35,TimeUnit.SECONDS);
			if(driver.findElement(By.xpath(prop.getProperty("popup_xpath"))).isDisplayed())
			{
				driver.findElement(By.xpath(prop.getProperty("popup_xpath"))).click();
				System.out.println("Popup occured");
			}
			else 
			{
				System.out.println("No Popup");
			}
			
		}	
		//Search for cabs from homepage
		@Test(priority=3)
		public void cabs() 
		{
			
			driver.manage().timeouts().implicitlyWait(35,TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("cab_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(35,TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("radio"))).click();
		}
		//Select city - From  
		@Test(priority=4)
		public void city()
		{
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("from_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("from1_xpath"))).sendKeys("Delhi");
		
			List<WebElement> list = driver.findElements(By.xpath(prop.getProperty("list_xpath")));
			//System.out.println("Total suggestions are: "+list.size());
			for(int i=0;i<list.size();i++)
			{
				list.get(i).getText();
				
				if(list.get(i).getText().contains("Delhi")) 
				{
					list.get(i).click();
					break;
				}	
			}
		}
		//Select city - To
		@Test(priority=5)
		public void city1() 
		{
			
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("To_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("To1_xpath"))).sendKeys("Manali, Himachal Pradesh, India");
			

			List<WebElement> list1 = driver.findElements(By.xpath(prop.getProperty("list1_xpath")));
			//System.out.println("Total suggestions are: "+list.size());
			for(int i=0;i<list1.size();i++)
			{
				list1.get(i).getText();
				
				if(list1.get(i).getText().contains("Manali, Himachal Pradesh, India")) 
				{
					list1.get(i).click();
					break;
				}	
			}
		}
		//Select departure date and time
		@Test(priority=6)
		public void Date_time() {
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("Dep_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("Date_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("Time_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("Time1_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("Search_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("dropdown"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("dropdown1"))).click();
			
			driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("Car_xpath"))).click();
		}
		//Select gift card options
		@Test(priority=7)
		public void More() {
		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			Actions action = new Actions(driver);
			action.moveToElement(driver.findElement(By.xpath(prop.getProperty("name")))).build().perform();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("gift_xpath"))).click();
		}
		//Select Group gifting option
		@Test(priority=8)
		public void group_gifting() 
		{
			try {
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.findElement(By.xpath(prop.getProperty("link_xpath"))).click(); 
				System.out.println("Pass");
			}catch(Exception e) {
				//e.printStackTrace();
				System.out.println("The group_gifting page is not responding");
			}
			
			ArrayList<String> tabs=new ArrayList<String> (driver.getWindowHandles());
		   
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  
		    driver.switchTo().window(tabs.get(0));
		    
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    driver.findElement(By.xpath(prop.getProperty("hotels"))).click();
		}
		//Select Hotel
		@Test(priority=9)
		public void hotel() {
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("hotels_xpath"))).click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("hotels1_xpath"))).sendKeys("Manali, Himachal Pradesh, India");
			
			
			List<WebElement> list2 = driver.findElements(By.xpath(prop.getProperty("list2_xpath")));
			
			for(int i=0;i<list2.size();i++)
			{
				list2.get(i).getText();
				if(list2.get(i).getText().contains("Manali, Himachal Pradesh, India")) 
				{
					list2.get(i).click();
					break;
				}	
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("checkIn"))).click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("date1"))).click();
			
			driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("checkOut"))).click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("date2"))).click();
			
		}
		//Select Rooms
		@Test(priority=10)
		public void Persons(){
			//driver.findElement(By.id("guest")).click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("guest"))).click();
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement ul= driver.findElement(By.xpath(prop.getProperty("adult")));
		    
			//driver.manage().timeouts().implicitlyWait(,TimeUnit.SECONDS);
		    System.out.println("All the numbers for Adult persons in each room in the hotel are: ");
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    
		    List<WebElement> allOptions= ul.findElements(By.tagName(prop.getProperty("tag")));
		    
		    for(WebElement selectLi: allOptions)
		    {
		    	String c=selectLi.getText();
		        System.out.println(c);
		    }
		}
		//Close browser
		@AfterClass
		public void closeBrowser() {
			driver.quit();
		}
}
