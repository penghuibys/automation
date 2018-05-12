package com.ibm.pp.pages;

import java.util.ArrayList;
import frame.com.pp.auto.action.FrameAction;
import frame.com.pp.auto.action.FrameAssertion;
import frame.com.pp.auto.base.TestBase;


public class VideoContributionPage extends TestBase{

	
	public VideoContributionPage(FrameAction action){
		this.action = action;
	}
	
	
	
	public void verifyFields(ArrayList<String> list, String field){
	
		for (int i = 0 ; i <= list.size()-1; i++){
			if (list.get(i).equalsIgnoreCase(field)){
				FrameAssertion.contains(list.get(i), field, field+" should be exist.");
				break;
			}	
		}
	}
	

}
