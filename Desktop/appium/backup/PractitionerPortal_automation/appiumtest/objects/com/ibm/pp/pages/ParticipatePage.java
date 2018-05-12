package com.ibm.pp.pages;

import org.openqa.selenium.By;

import frame.com.pp.auto.action.FrameAction;
import frame.com.pp.auto.base.TestBase;


public class ParticipatePage extends TestBase{

	
	public ParticipatePage(FrameAction action){
		this.action = action;
	}
	
	
	public void showDetails(String URL){
		action.open(URL+"participate/points","reload application");
		action.click(By.xpath(".//*[@id='points']//a[text()='Show Details']"), "Show Details");
	}
	
	public void selectSection(String sectionName){
		action.click(By.xpath(".//li[descendant::*/text()='"+sectionName+"']"), "select section");
		action.sleep(2, "sleep");
	}
	
	public int convertPoints(String points){
		int point = 0;
		points = points.split("pts")[0];
		if (points.contains(",")){
			points = points.replaceAll(",", "").trim();
		}
		point = Integer.parseInt(points);
		return point;
	}

	public void tabNavigator(String navMenu){
		action.click(By.xpath(".//ul[@class='tab-nav']/li/a[text()='"+navMenu+"']"), "Go to "+navMenu);
	}
}
