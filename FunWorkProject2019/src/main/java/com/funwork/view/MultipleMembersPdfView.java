package com.funwork.view;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.funwork.model.Job;
import com.funwork.model.Resume;
import com.funwork.viewResolver.AbstractITextPdfView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class MultipleMembersPdfView extends AbstractITextPdfView {
	final String fontPath = "c:\\windows\\fonts\\kaiu.ttf";
	BaseFont bfChinese;
	Font titleFont;

	Font normalFont;
	Font italicFont;
	Font underlineFont;
	
	ServletContext context;
	public MultipleMembersPdfView(ServletContext context) {
		this.context = context;
	}

	// 設定字型物件
	private void init() throws Exception {
		bfChinese = BaseFont.createFont(fontPath, "Identity-H", BaseFont.NOT_EMBEDDED);
		titleFont = new Font(bfChinese, 18, Font.BOLD);
		normalFont = new Font(bfChinese, 14, Font.NORMAL);
		italicFont = new Font(bfChinese, 12, Font.ITALIC);
		underlineFont = new Font(bfChinese, 40, Font.UNDERLINE);
	}

	

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		init();
		// 處理文章式資料
//		processArticle(document);
		// 由新頁開始列印表格
		document.newPage();
		// 處理表格式資料		
		processTable(model, document, 0);

		
	}
	
	// 處理表格化資料
	private void processTable(Map<String, Object> model, Document document, int newLines) throws Exception {
		// 表格的前置資料
		for(int n=0; n < newLines; n++) {
			document.add( Chunk.NEWLINE );	
		}
		Job job =  (Job) model.get("job");
		
		addTitle(document, job.getJobCompany().getName() + job.getTitle() +"的應徵紀錄", titleFont);
		document.add( Chunk.NEWLINE );	
		String[] tableTitle = {"生日","最高學歷" ,"姓名", "電話", "自我介紹"};
		PdfPTable table = createTable(model, tableTitle, 5);
		document.add(table);
		
	}

	private PdfPTable createTable(Map<String, Object> model, String[] title, int columnCount ) {
		PdfPTable table = new PdfPTable(columnCount); // PDF文件的直欄數量
		setTableHeading(table, title); 
		setTableData(table, model);
		return table;
		// 處理表格的標頭 // .setVerticalAlignment(Element.ALIGN_MIDDLE);
	}

	private void setTableData(PdfPTable table, Map<String, Object> model) {
		System.out.println("model=" + model);
		@SuppressWarnings("unchecked")
		List<Resume> list =  (List<Resume>) model.get("allMembers");
		for(Resume m : list) {
			MyTextPdfPCell cell1 = new MyTextPdfPCell();
			cell1.setPhrase(new Phrase(String.valueOf(m.getBirth()), normalFont));
			System.out.println(String.valueOf(m.getBirth()));
			table.addCell(cell1);
			//
			MyTextPdfPCell cell2 = new MyTextPdfPCell();
			cell2.setPhrase(new Phrase(m.getEducationLevel(), normalFont));
			System.out.println(m.getEducationLevel());
			table.addCell(cell2);
			//
			MyTextPdfPCell cell3 = new MyTextPdfPCell();
			cell3.setPhrase(new Phrase(m.getName(), normalFont));
			System.out.println(m.getName());
			table.addCell(cell3);
			
			MyTextPdfPCell cell4 = new MyTextPdfPCell();
			cell4.setPhrase(new Phrase(m.getPhoneNum(), normalFont));
			System.out.println(m.getPhoneNum());
			table.addCell(cell4);
			
			MyTextPdfPCell cell5 = new MyTextPdfPCell();
			cell5.setPhrase(new Phrase(m.getSelfIntro(), normalFont));
			System.out.println(m.getSelfIntro());
			table.addCell(cell5);
		}
	}

	private void setTableHeading(PdfPTable table, String[] title) {
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
		BaseColor color = new BaseColor(224, 224, 224);
		table.getDefaultCell().setBackgroundColor(color);
		for(int n = 0; n < title.length; n++) {
			PdfPCell cell1 = new PdfPCell();
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setPaddingBottom(8F);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell1.setPhrase(new Phrase(title[n], titleFont));
			table.addCell(cell1);
		}
		

		
	}
	
	private void addTitle(Document document, String title, Font font) throws Exception {
		Paragraph pg1 = new Paragraph();
		pg1.setFont(font);
		pg1.setAlignment(Paragraph.ALIGN_CENTER);
		pg1.add(title);
		document.add(pg1);
	}

	class MyTextPdfPCell extends PdfPCell{
		MyTextPdfPCell(){
	    	setBackgroundColor(BaseColor.LIGHT_GRAY);
	    	setPaddingBottom(8F);
	    	setHorizontalAlignment(Element.ALIGN_CENTER);
	    	setVerticalAlignment(Element.ALIGN_MIDDLE);
		}
	}
}
