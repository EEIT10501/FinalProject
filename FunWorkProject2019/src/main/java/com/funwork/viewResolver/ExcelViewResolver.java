package com.funwork.viewResolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.funwork.view.MultipleResumeExcelView;
import com.funwork.view.MultipleScheduleExcelView;
import com.funwork.view.SingleResumeExcelView;
import com.funwork.view.SingleScheduleExcelView;

public class ExcelViewResolver implements ViewResolver{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		
		View view = null;
		if (viewName.startsWith("fileDownload/showMembers")) {
			System.out.println("resolveViewName");
			view = new MultipleResumeExcelView();
		} else if (viewName.startsWith("fileDownload/showMember"))  {
			view = new SingleResumeExcelView();
		} else if (viewName.startsWith("fileDownload/showSchedule"))  {
			view = new SingleScheduleExcelView();
		} else if (viewName.startsWith("fileDownload/showOtherSchedule")) {
				view = new MultipleScheduleExcelView();
				}
//		System.out.println("ExcelViewResolver, viewName=" + viewName + ", return value=" + view);
		return view;
      }
	
}