package com.ibm.pp.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.openqa.selenium.By;

import frame.com.pp.auto.action.FrameAction;
import frame.com.pp.auto.base.TestBase;


public class CommonPage extends TestBase{
	private HashMap<String, String> prod = new HashMap<String, String>();
	private HashMap<String, String> stage = new HashMap<String, String>();
	private HashMap<String, String> test = new HashMap<String, String>();
	private String url;
	private String username;
	private String password;
	
	public CommonPage(FrameAction action){
		this.action = action;
	}
	
	public void openUrl(String urlParam, String sheet){
		data.loadData(sheet, "demo");
		String url = data.getData(urlParam,"url");
		action.open(url, "Login web application");
	}
	
	public void login(String userName, String psw) {
		//For Stage
		if (System.getProperty("env")!= null && System.getProperty("env").equalsIgnoreCase("stage")){
			action.sendKeys(By.id("username"), userName, "Enter Username:" + userName);
			action.sendKeys(By.id("password"), psw, "Enter password:" + psw);
			action.submit(By.xpath(".//input[@value='Submit']"), "Click on Sign in button");
		}else
		//For TEST and PROD
			action.sendKeys(By.id("Intranet_ID"), userName, "Enter Username:" + userName);
			action.sendKeys(By.id("password"), psw, "Enter password:" + psw);
			action.submit(By.xpath(".//*[@id='loginform']//input[@value='Sign in']"), "Click on Sign in button");
	}
	
	
	public void menuNavigator(String level1, String level2){
		action.mouseMove(By.xpath(".//*[@id='side-nav']//a[contains(text(),'"+level1+"')]"), "Mouse move on "+level1);
		action.sleep(3, "System will stop 3 sec");
		if (level1.equalsIgnoreCase("Search") || 
				level1.equalsIgnoreCase("Participate") ||
					level1.equalsIgnoreCase("Contribute")){
			action.click(By.xpath(".//*[@id='side-nav']//a[text()='"+level1+"']"), "Click on "+level1);
			action.sleep(2, "System will stop 2 sec");
			return;
		}
		action.click(By.xpath(".//*[@id='side-nav']//a[contains(text(),'"+level1+"')]"), "Click on "+level1);
		action.click(By.xpath(".//*[@id='side-nav']//a[contains(text(),'"+level2+"')]"), "Click on "+level2); 
		action.sleep(2, "System will stop 2 sec");
	}
	
	public String getURL(String fileName, String sheetName, String caseNameProd, String caseNameStage,String caseNameTest){
		data.loadData(fileName, sheetName);
		
		prod.put("URL", data.getData(caseNameProd, "url"));
		stage.put("URL", data.getData(caseNameStage, "url"));
		test.put("URL", data.getData(caseNameTest, "url"));
		
		url = getEnvData(prod, stage, test).get("URL");
		return url;
		
	}
	
	public HashMap<String, String> getUserInfo(String fileName, String sheetName, String UserProd,String PasswordProd, 
			String UserStage,String PasswordStage,String UserTest,String PasswordTest){
		data.loadData(fileName, sheetName);
		
		prod.put("username", data.getData(UserProd, "username"));
		prod.put("password", data.getData(PasswordProd, "password"));
		
		stage.put("username", data.getData(UserStage, "username"));
		stage.put("password", data.getData(PasswordStage, "password"));
		
		test.put("username", data.getData(UserTest, "username"));
		test.put("password", data.getData(PasswordTest, "password"));
		
		username = getEnvData(prod, stage, test).get("username");
		password = getEnvData(prod, stage, test).get("password");
		
		HashMap<String,String> userInfo = new HashMap<String,String>();
		userInfo.put("username", username);
		userInfo.put("password", password);
		
		return userInfo;
	}
	
	public void loginPractitionerPortal(String userName, String password){
	
		action.open(url, "Login web application");
		login(userName, password);
	}
	

	public boolean httpDownload(String httpUrl,String saveFile){  
	       int bytesum = 0;  
	       int byteread = 0;  
	  
	       URL url = null;  
		    try {  
		        url = new URL(httpUrl);  
		    } catch (MalformedURLException e1) {  
		        e1.printStackTrace();  
		        return false;  
		    }  
		  
		       try {  
		           URLConnection conn = url.openConnection();  
		           InputStream inStream = conn.getInputStream();  
		           FileOutputStream fs = new FileOutputStream(saveFile);  
		  
		           byte[] buffer = new byte[1204];  
		           while ((byteread = inStream.read(buffer)) != -1) {  
		               bytesum += byteread;  
		               System.out.println(bytesum);  
		               fs.write(buffer, 0, byteread);  
		           }  
		           fs.close();
		           return true;  
		       } catch (FileNotFoundException e) {  
		           e.printStackTrace();  
		           return false;  
		       } catch (IOException e) {  
		           e.printStackTrace();  
		           return false;  
		       }  
		   }
	
    
    public boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
          return flag;
        }
        if (!file.isDirectory()) {
          return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
           if (path.endsWith(File.separator)) {
              temp = new File(path + tempList[i]);
           } else {
               temp = new File(path + File.separator + tempList[i]);
           }
           if (temp.isFile()) {
              temp.delete();
           }
           if (temp.isDirectory()) {
              delAllFile(path + "/" + tempList[i]);
              flag = true;
           }
        }
        return flag;
 	}
}
