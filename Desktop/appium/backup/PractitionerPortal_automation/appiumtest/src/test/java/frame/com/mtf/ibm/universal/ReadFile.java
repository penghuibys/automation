package frame.com.mtf.ibm.universal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import frame.com.mtf.ibm.operation.Locate;
import io.appium.java_client.AppiumDriver;

public class ReadFile {
	public static AppiumDriver<WebElement> androidDriver;
	public static AppiumDriver<WebElement> iOSDriver;
	
	public ReadFile(AppiumDriver<WebElement> iOSDriver, AppiumDriver<WebElement> androidDriver) {
		ReadFile.iOSDriver = iOSDriver;
		ReadFile.androidDriver = androidDriver;
	}
	

	
	/**
	 * @purpose to get value from xml file according the tag name and index
	 * @param tagName
	 * @return according to the index return the value if there is several same tag name
	 */
	
	public String xmlValue(String tagName, int i) {
		
		File f = new File("config/ConfigCenter.xml");
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
	    DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}   
	    Document doc = null;
		try {
			doc = builder.parse(f);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    //node name should be changed according to xml file, item is index which return first value of xml once found
	    String getXML = doc.getElementsByTagName(tagName).item(i).getFirstChild().getNodeValue();
		return getXML;		   	
		}

	//overloading the returnValue if only no same tag name
	public String xmlValue(String tagName){	
		
		return xmlValue(tagName,0);
}

	/**
	 * @purpose  search data from xlsx file according to objName then return result as list
	 * @param objName
	 * @return funObject[0] = By.Function, funObject[1] = page object 
	 * @note  these xlsx data will use in selenium to operate the UI
	 */

	public List<String> getObject(String objName){
		
		List<String> objList = new ArrayList<String>();		

		//declare the xlsx location and tell the sheet name
		XSSFWorkbook workbook = null;
		
		try {
			workbook = new XSSFWorkbook(new FileInputStream("resource/pageobject/Object.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// to tell Android or iOS object sheet in xlsx
		
		String objectSheet = null;
		if (androidDriver != null) {
			objectSheet = "AndroidObject";
		} else {
			objectSheet = "iOSObject";
		}
		XSSFSheet sheet = workbook.getSheet(objectSheet);	

		/**
		 * @purpose if the column name is unique in the sheet that easy to locate element and improve performance and avoid null pointer exception
		 * @notice to get the column name index, therefore the column name should be unique and hard code in xlsx sheet
		 */
		int columnIndex = 0;
		for(int i = sheet.getFirstRowNum();i < sheet.getPhysicalNumberOfRows(); i++){
			XSSFRow row = sheet.getRow(i);
			for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++){
				String cell = row.getCell(j).toString();
				//get column index
				if (cell.equals("Symbol")){
					columnIndex  = row.getCell(j).getColumnIndex();
					}
				}
			//to get the next two elements according the column index, getAttribute = 1, getObject = 2 
			String cell = row.getCell(columnIndex).toString();
			if (cell.equals(objName)){
				String getObject = row.getCell(columnIndex+1).toString();
				String getAttribute= row.getCell(columnIndex+2).toString();
				objList.add(getObject);
				objList.add(getAttribute);
				
			}
		}
		
		//in case unable to find name in the sheet return the information to notice
		if (objList.size() == 0)objList.add("[Info:]Unable to find name in the xlsx sheet,"
									+ "please check your Name,Attribute and Object colunm,no allow to be empty");
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objList;
	}
	
	public static String downloadCaptcha(String appURL){

		File f = new File("Applications/Download/","Captcha"); 
		try {
			System.out.println("[Info:]tring to download from url,be patient");
			URL url = new URL(appURL);
			FileUtils.copyURLToFile(url,f);
			System.out.println("[Info:]the application has down successfully");
		} catch (Exception e) {
			System.out.println("[Info:]unable to download from this url: " + appURL);
			System.out.println("[Info:]please do it manually or check your URL or network");
		}
		
		File classpathRoot = new File(System.getProperty("user.dir"));
		// the appium install application to device must be absolute path
		return classpathRoot + "/" +f.toString();
	}
	
	
	
}
