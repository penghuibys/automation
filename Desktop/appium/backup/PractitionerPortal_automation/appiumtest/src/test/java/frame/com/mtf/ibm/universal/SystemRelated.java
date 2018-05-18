package frame.com.mtf.ibm.universal;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.io.BufferedOutputStream;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipOutputStream;  

/**
 * @Created :  java
 * @Date    :  10/24/2015
 * @author  :  zxyzhan@cn.ibm.com(JamesZhan)
 */

public class SystemRelated {
	
	public  String osType(){
		
		String os = System.getProperties().getProperty("os.name");	
		String osName =""; 		
		if (os.contains("Windows")){
			osName = "Windows";
		} else {
			osName = "Mac";
		} 
		return osName;				
	}
	
	
	/**
	 * @return
	 * @notice to identify mobile device type according the xml tag and this tag related with xlsx sheet name
	 * 		   true is Android, false is iOS
	 */
//	public static boolean mobileType(){
//		
//		ReadFile rf = new ReadFile();
//		String type = rf.xmlValue("objectSheet");
//		Boolean osType = null;
//		if (type.equals("AndroidObject")){
//			osType = true;
//		}else if (type.equals("iOSObject")){
//			osType = false;
//		} else {
//			System.out.println("[Caution]: Your object.xlsx or ConfigCenter.xml file has wrong name, neither “AndroidObject” nor “iOSObject”");
//		}
//		return osType;
//	}
	
//	public static String driverType(){
//		
//		ReadFile rf = new ReadFile();
//		String type = rf.xmlValue("objectSheet");
//		String driverType = null;
//		if (type.equals("AndroidObject") || type.equals("iOSObject")){
//			driverType = "mobile";
//		}else if (type.equals("webObject")){
//			driverType = "web";
//		} else {
//			System.out.println("[Caution]: Your object.xlsx or ConfigCenter.xml file has wrong name, neither 'appiumDriver' nor 'webDriver'.");
//		}
//		return driverType;
//	}
//	

	public static String returnNowTime(String dateFormat){
		
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);//time format
		String getTime = df.format(new Date());// new Date() to get now time
		return getTime;
	}
	
	public static String returnNowTime(){
		
		return returnNowTime("yyyy-MM-dd HH-mm-ss");
	}

//	public static String createUniqueFolder(String folderName){
//		String osReportLocation;
//		if (mobileType() == true){
//			osReportLocation = "/Implement/TestReport/Android/";
//		}else{
//			osReportLocation = "/Implement/TestReport/iOS/";
//		}
//		String curLocation = System.getProperty("user.dir");
//        File dir=new File(curLocation + osReportLocation + folderName +"_"+ returnNowTime("yyyyMMddHHmmss"));
//        String folderPath = dir.toString();//convert type from file to string
//        if(!dir.exists()){
//        	dir.mkdir();//create root folder only 
//        	return folderPath;    
//	}
//		return folderPath;
//	}
	
	
	/**
	 * 
	 * @param c
	 * @return
	 * @notice to tell each String is Chinese or English however unable to tell mix words such as "你好,Hello"
	 * 		   the first word is Chinese then it is then English
	 */
    public static boolean isChinese(char c) {  
  	  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }
    
	public static boolean isChinese(String strName) {
    	
    	ArrayList<String> listBox = new ArrayList<String>();
        char[] ch = strName.toCharArray();  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];
            if (isChinese(c) == true) { 
            	//chinese is 0
                 listBox.add("0");
            } else {
            	//english is 1
            	listBox.add("1");
            }  
        }
        
        // length 2 = chinese, length 1 = english
        int chineseOrEnglish = removeDuplicated(listBox).size();
        System.out.println(chineseOrEnglish);
        //if list == 1 that means either pure chinese or pure english then give a condition "0" to tell whether is chinese
        if (chineseOrEnglish == 1 & removeDuplicated(listBox).contains("0")){
        	//pure chinese
        	return true;
        }else if (chineseOrEnglish == 2){
        	//mix chinese and english
        	return true;
        }else{
        	//prue english
        	return false;
        } 
    }
    
	public static ArrayList<String> removeDuplicated(ArrayList<String> list){
		ArrayList<String> newAl = new ArrayList<String>(); 
        for(Iterator<String> it = list.iterator(); it.hasNext();) {  
            String obj = it.next();  
            if(!newAl.contains(obj)){  
                newAl.add(obj);  
           }
        }
		return newAl;
        }
	
	/**
	 * @notice below part is from internet,thanks for the author. 
     * @desc 将源文件/文件夹生成指定格式的压缩文件,格式zip 
 	 * @param resourePath 源文件/文件夹 
	 * @param targetPath  目的压缩文件保存路径 
	 * @return void 
	 * @throws Exception  
	 */  
	  public void compressedFile(String resourcesPath,String targetPath) throws Exception{
		  
		  //String targetPath = "Implement/TestReport/ZipReport";
	      File resourcesFile = new File(resourcesPath);     //源文件  
	      File targetFile = new File(targetPath);//目标目录存放路径
	      //如果目的路径不存在，则新建  
	      if(!targetFile.exists()){       
	            targetFile.mkdirs();    
	        } 	          
	      //String targetName = resourcesFile.getName()+".zip";   //目的压缩文件名
	      String targetName = "TestReport_" + returnNowTime("yyyyMMddHHmmss") +".zip";   //目的压缩文件名
	      FileOutputStream outputStream = new FileOutputStream(targetPath+"/"+targetName);  
	      ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(outputStream));  
	      createCompressedFile(out, resourcesFile, "");  
	        out.close();    
	    }  
	      
	    /** 
	     * @desc 生成压缩文件。 
	     *       如果是文件夹，则使用递归，进行文件遍历、压缩 
	     *       如果是文件，直接压缩 
	     * @param out  输出流 
	     * @param file  目标文件 
	     * @return void 
	     * @throws Exception  
	     */  
	  public void createCompressedFile(ZipOutputStream out,File file,String dir) throws Exception{  
	      //如果当前的是文件夹，则进行进一步处理  
		  if(file.isDirectory()){  
	            //得到文件列表信息  
	            File[] files = file.listFiles();  
	            //将文件夹添加到下一级打包目录  
	            out.putNextEntry(new ZipEntry(dir+"/"));  
	              
	            dir = dir.length() == 0 ? "" : dir +"/";  
	              
	            //循环将文件夹中的文件打包  
	            for(int i = 0 ; i < files.length ; i++){  
	                createCompressedFile(out, files[i], dir + files[i].getName()); //递归处理  
	            }  
	        }  
	        else{   //当前的是文件，打包处理  
	            //文件输入流  
	            FileInputStream fis = new FileInputStream(file);  
	              
	            out.putNextEntry(new ZipEntry(dir));  
	            //进行写操作  
	            int j =  0;  
	            byte[] buffer = new byte[1024];  
	            while((j = fis.read(buffer)) > 0){  
	                out.write(buffer,0,j);  
	            }  
	            //关闭输入流  
	            fis.close();  
	        }  
	    }
	  
	  public static void exeBuildFile(){      
	        File buildFile = new File("build.xml");  
	        Project p = new Project();  
	        //添加日志输出          
	        DefaultLogger consoleLogger = new DefaultLogger();  
	        consoleLogger.setErrorPrintStream(System.err);  
	        consoleLogger.setOutputPrintStream(System.out);  
	        p.addBuildListener(consoleLogger);  
	        try {  
	            p.fireBuildStarted();  
	            p.init();  
	            ProjectHelper helper = ProjectHelper.getProjectHelper();  
	            helper.parse(p, buildFile); 
	            //输出执行结果
	            //p.executeTarget(p.getDefaultTarget());
	            p.executeTarget("transform");
	            //p.fireBuildFinished(null);  
	        } catch (BuildException e) {  
	            p.fireBuildFinished(e);  
	        }  
	    }  
	  }