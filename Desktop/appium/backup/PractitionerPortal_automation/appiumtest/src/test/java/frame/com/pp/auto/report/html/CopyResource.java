package frame.com.pp.auto.report.html;


import frame.com.pp.auto.config.FrameConfig;
import frame.com.pp.auto.util.FileUtil;

public class CopyResource {
	
	
	/*************************************************************************************************
	 * @author Henry
	 * @date 2015/11/12
	 * 
	 *    The method is designed to copy the css file of html report, Failed.png and Passed.png from
	 *    the folder 'resource' to the destination path.
	 * 
	************************************************************************************************/
	public void createCss(){

		FileUtil.copyFile(FileUtil.getAbsolutelyPath("resource/html-res/css", "report.css").replace("\\", "/"), getCssPath());
		FileUtil.copyFile(FileUtil.getAbsolutelyPath("resource/html-res/image", "Failed.png").replace("\\", "/"), getFailImagePath());
		FileUtil.copyFile(FileUtil.getAbsolutelyPath("resource/html-res/image", "Passed.png").replace("\\", "/"), getPassImagePath());

	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @return String cssPath
	 * 
	 *    The method is designed to create a path for css file for test report and return the absolutely
	 *    path.
	 * 
	************************************************************************************************/
	private String getCssPath(){
		FileUtil.createPath(FrameConfig.getInstance().getConfig("ReportPath") + "/css");
		return FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ReportPath") + "/css/report.css");
	}
	
	
	/*************************************************************************************************
	 * @author Ink
	 * @date 2015/11/12
	 * @return String failPath
	 * 
	 *    The method is designed to create a path for failed image file for test report and return the absolutely
	 *    path.
	 * 
	************************************************************************************************/
	private String getFailImagePath(){
		FileUtil.createPath(FrameConfig.getInstance().getConfig("ReportPath") + "/image");
		return FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ReportPath") + "/image/Failed.png");
	}
	
	
	/*************************************************************************************************
	 * @author Henry
	 * @date 2015/11/12
	 * @return String passPath
	 * 
	 *    The method is designed to create a path for pass image file for test report and return the absolutely
	 *    path.
	 * 
	************************************************************************************************/
	private String getPassImagePath(){
		FileUtil.createPath(FrameConfig.getInstance().getConfig("ReportPath") + "/image");
		return FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ReportPath") + "/image/Passed.png");
	}
	

}
