package com.task22.Answers;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.locks.Condition;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Q1_Travels {
	static WebDriver driver=null;
	public static void main(String[] args) throws InterruptedException {
		driver =new ChromeDriver();//Opens the Chrome Browser
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(20));//Explicit Wait Until 20 seconds 
		driver.navigate().to("https://phptravels.com/demo/");//Navigate to url  
		driver.manage().window().maximize();//Maximize the browser window
		
		/*Fill the form details*/
		//waits until the presence of the WebLocator is located and enter the firstName
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='first_name']"))).sendKeys("Sathish");
		
		//waits until the presence of the WebLocator is located and enter the LastName
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='last_name']"))).sendKeys("Rakesh");	
		
		//waits until the presence of the WebLocator is located and enter the Business Name
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='business_name']"))).sendKeys("SRK Technologies");
		
		//waits until the presence of the WebLocator is located and enter the EMail Id
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='email']"))).sendKeys("testuser@gmail.com");
		
		/*Adds Logic for sum verification*/
		String strnum1=driver.findElement(By.id("numb1")).getText();//Gets  the text from the webElement and stores in strnum1 as string
		int Intnum1=Integer.parseInt(strnum1);//Converts the String into integer
		String strnum2=driver.findElement(By.xpath("//span[@id='numb2']")).getText();//Gets  the text from the webElement and stores in strnum2 as string
		int Intnum2=Integer.parseInt(strnum2);//Converts the String into integer
		int totalIntNum=Intnum1+Intnum2;//Adds two integer and store in totIntNum
		String totIntNumText=Integer.toString(totalIntNum);//converts Integer to String
		
		//waits until the presence of the WebLocator is located and passes the calculated totIntNum value the input field
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='number']"))).sendKeys(totIntNumText);
		//waits until the presence of the WebLocator is located and click the button
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='demo']"))).click();
		
		
		//waits until the presence of the WebLocator is located 
		WebElement successfull_Login=driver.findElement(By.xpath("//p[@class='text-center cw']"));
		wait.until(ExpectedConditions.visibilityOf(successfull_Login));

		/*Verifies that the form is submitted successfully and displays the message*/
		if(successfull_Login.isDisplayed()) {
			System.out.println("Form Submitted Sucessfully");
			wait.until(ExpectedConditions.visibilityOf(successfull_Login));
		}
		else
			System.out.println("Form Submission is Unsuccessfull");
		
		//Takes the Screenshot after the form submission
		TakesScreenshot screenshotDriver=(TakesScreenshot) driver;
		File screenshotFile=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile, new File(".\\src\\test\\java\\com\\task22\\Answers\\Form_submission_scrnsht.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		
		driver.quit();//Quit the browser
	}

}
