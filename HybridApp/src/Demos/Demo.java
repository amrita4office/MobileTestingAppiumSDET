package Demos;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class Demo extends Capability{
	
	AndroidDriver<AndroidElement> dr;
	@BeforeTest
	public void setup() throws MalformedURLException{
		dr = capabilities();
		dr.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}
	
	@Test(enabled=false)
	public void testcase1() throws InterruptedException
	{
		dr.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Amrita");
		dr.findElementById("com.androidsample.generalstore:id/radioFemale").click();		
		dr.findElementById("android:id/text1").click();
		dr.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		dr.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		
	}
	
	@Test(enabled=false)
	public void testcase2() throws InterruptedException
	{
		//dr.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Amrita");
		//dr.findElementById("com.androidsample.generalstore:id/radioFemale").click();		
		dr.findElementById("android:id/text1").click();
		dr.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		dr.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		String Message  = dr.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		System.out.println(Message);
		Assert.assertEquals(Message, "Please enter your name");
		
	}	
	
	@Test(enabled=false)
	public void testcase3() throws InterruptedException
	{
		dr.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Amrita");
		dr.findElementById("com.androidsample.generalstore:id/radioFemale").click();		
		dr.findElementById("android:id/text1").click();
		dr.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
		dr.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
		 //if i go with index
        dr.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        dr.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        dr.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
        String amount1 = dr.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
        System.out.println(amount1);
        amount1 = amount1.substring(1);
        Double amount1value = Double.parseDouble(amount1);
        System.out.println(amount1value);
        
        String amount2 = dr.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
        System.out.println(amount2);
        amount2 = amount2.substring(1);
        Double amount2value = Double.parseDouble(amount2);
        System.out.println(amount2value);
        
        String totalamount = dr.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        System.out.println(totalamount);
        totalamount = totalamount.substring(1);
        Double amountTotalvalue = Double.parseDouble(totalamount);
        System.out.println(amountTotalvalue);
        
        Double TotalsumofValue = amount1value+amount2value;
        System.out.println(TotalsumofValue);
        
        Assert.assertEquals(amountTotalvalue, TotalsumofValue);
        
        //int len1 = amount1.length();
        
        //s = amount1.substring(1,len1);
        
	}
	//selecting product  with product name and by scolling in product list
	@Test(enabled=true)
	public void testcase4() throws InterruptedException
	{
	
	dr.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Niharika");
    //driver.findElement(By.xpath("//*[@id='com.androidsample.generalstore:id/nameField']")).sendKeys("Niharika");
	dr.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
    //i have to work with drop down
	dr.findElement(By.id("android:id/text1")).click();
    dr.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"))").click();
    dr.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    Thread.sleep(8000);
    
    dr.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector().textMatches(\"Converse All Star\"))");
    
    int count = dr.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
    
    for(int i=0;i<count;i++)
    {
        String name = dr.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
        if(name.equals("Converse All Star"))
        {
            dr.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            break;
        }
    }
    dr.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();  
    
    
//  Tap on the checkbox
	  WebElement cbox = dr.findElement(By.className("android.widget.CheckBox"));
	  TouchAction t = new TouchAction(dr);
	  t.tap(tapOptions().withElement(element(cbox))).perform();
  
//  Long press on terms
  WebElement terms = dr.findElementById("com.androidsample.generalstore:id/termsButton");
  t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(3))).release().perform();
  
//  Close the terms
  dr.findElement(By.id("android:id/button1")).click();
  
//  Click on visit website
  dr.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
  
  Thread.sleep(9000);
  
  //I have to work with the browser and will have to work on it
  
  Set<String> contextNames = dr.getContextHandles();
  for (String contextName : contextNames) {
      System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
  }
  dr.context("WEBVIEW_com.androidsample.generalstore");
  dr.findElement(By.xpath("//*[@name='q']")).sendKeys("IBM");
  //i want to click on enter
  dr.findElement(By.xpath("//*[@name='q']")).sendKeys(Keys.ENTER);
  dr.pressKey(new KeyEvent(AndroidKey.BACK)); //take you back to home page
  dr.context("NATIVE_APP");

}
	

}
