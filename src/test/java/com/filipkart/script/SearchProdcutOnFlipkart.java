package com.filipkart.script;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchProdcutOnFlipkart {
	
	WebDriver driver;
	public  By listOfProduct = By.xpath("(//div[starts-with(@class,'_3O0U0u')]//div[@data-id])");
	public  By amountOfProduct  = By.xpath("//div[starts-with(@class,'_3O0U0u')]//div[@data-id]//div[starts-with(text(),'₹')]");
	
	@BeforeTest
	public void openUrl() {
		
		//open url
        System.setProperty("webdriver.chrome.driver", "D:\\SeleniumDownload\\chromedriverr.exe");
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");	
		driver.manage().window().maximize();
        String title = driver.getTitle();
		System.out.println("Title of Page:"+title);
	}
	
	
	@Test(priority=1)
	public void searchTheProduct() throws InterruptedException {
		
	    if(driver.getTitle().contains("Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!")) {
	    	
			driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();
		}
		
		else {
			
			System.out.println("Failed to login");
			
		}
	    
	    //Search a product
	    driver.findElement(By.name("q")).sendKeys("iphone");
	    
	    Thread.sleep(2000);
	    
	    //Click on search product 
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    	
	}
	   @Test(priority=2)
	   public void fetchTheProduct() {
			int count =1;
			List<WebElement>list = driver.findElements(amountOfProduct);
			
			if(list.size()!=0) {
				for(int i=0; i<list.size()-1;i++) {
					WebElement element = list.get(i);
					String amount = element.getText();
					int amont = Integer.parseInt(amount.replaceAll("[^0-9]", "").toString());
					if(amont<=50000)
					{
						String title = driver.findElement(By.xpath("(//div[starts-with(@class,'_3O0U0u')]//div[@data-id]//a[@title])["+i+"]")).getText();
						System.out.println("Product:"+count+"title is:"+title);
						count++;
					}
				}
			}
				
		}
	   
	   @Test(priority=3)
	   public void fetchLowestAndHighestPrice() throws InterruptedException {
			HashMap<Integer, String>hmap = new HashMap<Integer, String>();
			List<WebElement>list = driver.findElements(listOfProduct);
			if(list.size()!=0) {
				
				for(int i=0; i<list.size();i++) {
					String amount = driver.findElement(By.xpath("(//div[starts-with(@class,'_3O0U0u')]//div[@data-id]//div[starts-with(text(),'₹')])["+i+"]")).getText();
					String title = driver.findElement(By.xpath("(//div[starts-with(@class,'_3O0U0u')]//div[@data-id])["+i+"]")).getText();
					amount = amount.replaceAll("[^0-9]", "");
					int price = Integer.parseInt(amount);
					hmap.put(price, title);
					
				}
				
				Set<Integer>keys= hmap.keySet();
				ArrayList<Integer> listsortProduct = new ArrayList<Integer>(keys);
				Collections.sort(listsortProduct);
				
				int getLowest = listsortProduct.get(0);
				int getHighest = listsortProduct.get(listsortProduct.size()-1);
				
				System.out.println("lowest price:"+getLowest);
				System.out.println("Highest price:"+getHighest);
				
			    driver.findElement(By.xpath("//div[contains(text(),'Price -- High to Low')]")).click();
			    Thread.sleep(2000);
			    
			    driver.findElement(By.xpath("//div[contains(text(),'Apple iPhone 12 Pro (Pacific Blue, 512 GB)')]")).click();
			    Thread.sleep(2000);
			    
			    String get = driver.getPageSource();
			    System.out.println("page:"+get);
			}
			
		}
	   
	
}
