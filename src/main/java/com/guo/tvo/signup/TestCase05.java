package com.guo.tvo.signup;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Test Case 05 : Last Name  - Negative testing for the SignUp functionality. 
 *
 * @author Leping Guo
 *
 */

public class TestCase05
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	 // System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");     
        System.setProperty("webdriver.chrome.driver", "./webDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize(); 
        
        String csvFile ="./dataSource/lastNameNegativeData.csv";
        BufferedReader br = null;
        String line ="";
        String csvSplitBy = ",";
        
       try{
	        br = new BufferedReader(new FileReader(csvFile));
	        String firstLine = br.readLine(); //the data from firstLine should be skipped, not used for testing.
	        while ((line = br.readLine()) != null) {
		      	
		        String[] signupInfo = line.split(csvSplitBy);
		        String lastName = signupInfo[1];
		        String email = signupInfo[2];
		      
		        driver.get("https://mpower.tvo.org/educators/#/signup/");
		        driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		      
		        driver.findElement(By.id("firstName")).sendKeys("Liping");
		      
		        driver.findElement(By.id("lastName")).sendKeys(lastName);  //using parameter of Last Name
		        Select selectRole = new Select(driver.findElement(By.id("educatorRoleId")));
		        selectRole.selectByVisibleText("Administrator");

		        Select selectBoard = new Select(driver.findElement(By.id("boardId")));
		        selectBoard.selectByVisibleText("Algoma DSB");
		        
		        Select selectSchool = new Select(driver.findElement(By.id("schoolId")));
		        selectSchool.selectByVisibleText("Anna McCrea Public School");
		        
		        driver.findElement(By.id("email")).sendKeys(email);  // using parameter of Email
		        driver.findElement(By.id("userEmailConfirm")).sendKeys(email); // using parameter of Email
		        
		        driver.findElement(By.id("userPassword")).sendKeys("guo123456");
		        driver.findElement(By.id("userPasswordConfirm")).sendKeys("guo123456");
		                        
		        
		        Select selectWhere = new Select(driver.findElement(By.id("source")));
		        selectWhere.selectByVisibleText("Another Educator");
		        
		        driver.findElement(By.id("agreement")).click();
		        driver.findElement(By.id("emailPromos")).click(); 
		        driver.findElement(By.id("registerSubmit")).click();
		        Thread.sleep(3000);
		        
		        //Here verify that registering is failed
		        String expectedUrl = "https://mpower.tvo.org/educators/#/signup/";
				String actualUrl = driver.getCurrentUrl();
				System.out.println("Current actualUrl is " + actualUrl);
				driver.manage().timeouts().implicitlyWait(5000,TimeUnit.SECONDS);
				Assert.assertTrue(expectedUrl.equals(actualUrl));
		        		        
		        	
		     }
       }
       catch(IOException e){ 
    	   e.printStackTrace(); 
    	}  
       driver.close();
       System.out.println("TestCase05 compeleted successfully!"); 
    }
}
