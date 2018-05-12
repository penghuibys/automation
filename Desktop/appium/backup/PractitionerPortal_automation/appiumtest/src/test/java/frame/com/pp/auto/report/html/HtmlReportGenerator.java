package frame.com.pp.auto.report.html;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import frame.com.pp.auto.config.FrameConfig;
import frame.com.pp.auto.report.NameUtil;
import frame.com.pp.auto.report.OverviewGenerator;
import frame.com.pp.auto.report.model.CaseModel;
import frame.com.pp.auto.report.model.OverviewModel;
import frame.com.pp.auto.util.FileUtil;

public class HtmlReportGenerator {
	private PrintStream printStream;
	private List<CaseModel> caseList;
	private OverviewModel overview;
	private OverviewGenerator overviewGenerator;
	private StringBuilder sb;
	
	
	public HtmlReportGenerator(List<CaseModel> list){
		this.caseList = list;
		this.overview = new OverviewModel();
		this.overviewGenerator = new OverviewGenerator(list);
		overview = overviewGenerator.getOverview();
	}
	
	public void createHtmlReport(){
		String p = this.getPath();
		p = "/Users/devicepass/Desktop/appium/PractitionerPortal_automation/frame-output/report/index.html";
		createHtml(p);
		this.sb = new HtmlReportUtil(caseList, overview).writeHtml();
		saveHtml();
		new CopyResource().createCss();
	}
	
	
	private String getPath(){
		String path = FrameConfig.getInstance().getConfig("ReportPath");
		FileUtil.createPath(FrameConfig.getInstance().getConfig("ReportPath"));
		
		String path_ = NameUtil.getERepotrName() + ".html";
		return FileUtil.getAbsolutelyPath(FrameConfig.getInstance().getConfig("ReportPath"), NameUtil.getERepotrName() + ".html");
	}
	

	private void createHtml(String path){
		try {
			this.printStream = new PrintStream(new FileOutputStream(path), true, "utf-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private void saveHtml(){
		this.printStream.println(sb.toString());
	}
}
