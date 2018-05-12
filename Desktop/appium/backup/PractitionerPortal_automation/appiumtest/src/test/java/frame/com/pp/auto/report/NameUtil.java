package frame.com.pp.auto.report;

import java.util.Date;

import frame.com.pp.auto.util.TimeUtil;

public class NameUtil {
	
	
	public static String getImageName(String method){
		return method + "_" + String.valueOf(new Date().getTime()) + ".jpg";
	}
	
	public static String getERepotrName(){
		return "index";
	}
	
	public static String getFolderName(){
		return TimeUtil.getDate("yyyyMMdd");
	}

}
