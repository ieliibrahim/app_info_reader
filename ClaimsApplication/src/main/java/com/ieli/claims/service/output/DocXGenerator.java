package com.ieli.claims.service.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STBorder;

import com.ieli.claims.model.app.ApplicationInfo;
import com.ieli.claims.model.app.Claim;
import com.ieli.claims.model.app.RejectedClaims;
import com.ieli.claims.util.AppUtils;
import com.ieli.claims.util.DocXStaticData;

public class DocXGenerator {

	private XWPFDocument docx;

	// public static void main(String[] args) throws InvalidFormatException,
	// IOException {
	//
	// ApplicationInfo dbApplicationInfo = new ApplicationInfo();
	// dbApplicationInfo.setApplicant("Lalan Jee Mishra");
	// dbApplicationInfo.setApplicationFor("App title 1");
	// dbApplicationInfo.setAttorneyDocketNumber("49606.268US02 (151895)");
	// dbApplicationInfo.setConfirmationNumber("4926");
	// dbApplicationInfo.setExaminer("SUN, MICHAEL");
	// dbApplicationInfo.setFillingDate("02/03/2016");
	// dbApplicationInfo.setGroupArtUnit("2184");
	// dbApplicationInfo.setNotificationDate("11/16/2017");
	// dbApplicationInfo.setNumber("15/014,385");
	// dbApplicationInfo.setTitles(
	// "Claim Rejections - 35 U.S.C. § 102app_new_lineClaim Rejections - 35
	// U.S.C. § 103app_new_line");
	// dbApplicationInfo.setSubTitles(
	// "Claims 1, 6-11, 13-16, 19-26, and 30 are rejected under 35 U.S.C.
	// 102(a)(1) as being anticipated by Wilson et al. (US 2006/0143348), herein
	// referred to as Wilson '348. app_new_lineClaims 2-5, 12, 17, 18, and 27-29
	// are rejected under 35 U.S.C. 103 as being unpatentable over Wilson '348
	// in view of Bjeljac et al. (US 2009/0326730), herein referred to as
	// Bjeljac '730.app_new_line");
	//
	// Claim dbClaim = new Claim();
	// dbClaim.setAllowableClaims("23");
	// dbClaim.setAllowedNumber(5);
	// dbClaim.setObjectedNumber("23");
	// dbClaim.setObjectedStatue("statue");
	// dbClaim.setTotalNumber(30);
	// dbClaim.setApplicationInfo(dbApplicationInfo);
	//
	// RejectedClaims dbRejectedClaims1 = new RejectedClaims();
	// dbRejectedClaims1.setClaimNumbers("1");
	// dbRejectedClaims1.setClaimStatue("statue 1");
	// dbRejectedClaims1.setName("name 1");
	// dbRejectedClaims1.setPublicationNumber("pub num 1");
	//
	// RejectedClaims dbRejectedClaims2 = new RejectedClaims();
	// dbRejectedClaims2.setClaimNumbers("2");
	// dbRejectedClaims2.setClaimStatue("statue 2");
	// dbRejectedClaims2.setName("name 2");
	// dbRejectedClaims2.setPublicationNumber("pub num 2");
	//
	// List<RejectedClaims> rejcl = new ArrayList<RejectedClaims>();
	// rejcl.add(dbRejectedClaims1);
	// rejcl.add(dbRejectedClaims2);
	//
	// dbClaim.setRejectedClaims(rejcl);
	//
	// new DocXGenerator().generateDocX(dbClaim,
	// "F:/Imad/work/upwork/Phil Antonio/RCs/Final Samples/mytoutput/res.docx");
	//
	// System.out.println("Done");
	// }

	public void generateDocX(Claim claim, String outputFile) throws IOException, InvalidFormatException {

		docx = new XWPFDocument();

		createHeader(claim);
		createPageTitle("IN THE UNITED STATES PATENT AND TRADEMARK OFFICE", false);
		addAppInfoTable(claim);
		addAddressInfo();
		createBreak(1);
		createPageTitle("AMENDMENT & RESPONSE TO OFFICE ACTION", true);

		createExaminerTitle(claim);
		createExaminerSubTitle();

		createNewPage();

		createPageTitle("AMENDMENTS TO THE CLAIMS:", true);

		createParagraph("This listing of claims replaces all previously submitted claims as follows:", false);

		createNewPage();

		createPageTitle("REMARKS", true);
		createParagraph("In the outstanding Office Action (“OA”), the Examiner has:", false);

		String[] titlesArr = claim.getApplicationInfo().getSubTitles().split("app_new_line");

		for (String str : titlesArr) {
			createParagraph("- " + str, false);
		}

		String[] subTitlesArr = claim.getApplicationInfo().getTitles().split("app_new_line");

		int index = 1;
		for (String str : subTitlesArr) {
			createParagraph(index + "- " + str, true);
			index++;
		}

		createBreak(24);
		createBottomParagraph(
				"As Applicant’s remarks with respect to the Examiner’s rejections are sufficient to overcome the rejections set forth");
		createBottomParagraph(
				"in the OA, Applicant’s silence as to certain assertions or requirements applicable to such rejections (e.g., whether a ");
		createBottomParagraph(
				"reference constitutes prior art, motivation to combine references, etc.) does not constitute an acquiescence by ");
		createBottomParagraph(
				"Applicant that such assertions are accurate or that such requirements have been met, and Applicant reserves the right ");
		createBottomParagraph("to further analyze and dispute such assertions in the future.");

		createNewPage();

		createPageTitle("CONCLUSION", true);

		FileOutputStream out = new FileOutputStream(new File(outputFile));
		docx.write(out);
		out.close();
	}

	private void createBottomParagraph(String text) {
		XWPFParagraph paragraph = docx.createParagraph();
		paragraph.setSpacingBetween(0.7);
		XWPFRun run = paragraph.createRun();
		run.setText(text);
		run.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		run.setFontSize(10);

	}

	private void createParagraph(String text, boolean isBold) {
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addTab();
		run.setText(text);
		run.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		run.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		run.setBold(isBold);

	}

	private void createExaminerTitle(Claim claim) {
		XWPFParagraph dearParagraph = docx.createParagraph();
		XWPFRun dearRun = dearParagraph.createRun();
		dearRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		dearRun.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		String exFName = claim.getApplicationInfo().getExaminer();
		if (exFName.contains(", ")) {
			exFName = exFName.split(",")[0];
		}
		dearRun.setText("Dear Examiner " + exFName + ": ");

		XWPFParagraph officeParagraph = docx.createParagraph();
		XWPFRun officeRun = officeParagraph.createRun();
		officeRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		officeRun.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		officeRun.addTab();
		officeRun.setText("In response to the Office Action mailed " + claim.getApplicationInfo().getFillingDate()
				+ ", please amend the above-identified patent application as set forth below.");
		createBreak(1);
	}

	private void createExaminerSubTitle() {

		XWPFParagraph amendParagraph = docx.createParagraph();
		XWPFRun amendRun = amendParagraph.createRun();
		amendRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		amendRun.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		amendRun.setBold(true);
		amendRun.addTab();
		amendRun.setText("Amendments to the Claims");

		XWPFRun amendRun2 = amendParagraph.createRun();
		amendRun2.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		amendRun2.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		amendRun2.setText(" begin on page 2 of this paper.");

		XWPFParagraph remParagraph = docx.createParagraph();
		XWPFRun remRun = remParagraph.createRun();
		remRun.addTab();
		remRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		remRun.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		remRun.setBold(true);
		remRun.setText("Remarks");

		XWPFRun remRun2 = remParagraph.createRun();
		remRun2.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		remRun2.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		remRun2.setText(" begin on page 6 of this paper.");

	}

	private void createBreak(int brCount) {

		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		for (int i = 0; i < brCount; i++) {
			run.addBreak();
		}

	}

	private void addAddressInfo() {

		XWPFTable addressTable = docx.createTable();

		XWPFTableRow tableRowOne = addressTable.createRow();
		createAddressCell(tableRowOne, "VIA EFS-WEB", ParagraphAlignment.LEFT, true);
		createAddressCell(tableRowOne, "Palo Alto, California", ParagraphAlignment.RIGHT, false);

		XWPFTableRow tableRowTwo = addressTable.createRow();
		createAddressCell(tableRowTwo, "Commissioner for Patents", ParagraphAlignment.LEFT, false);
		createAddressCell(tableRowTwo, AppUtils.getToday("MM/dd,yyyy"), ParagraphAlignment.RIGHT, false);

		XWPFTableRow tableRowThree = addressTable.createRow();
		createAddressCell(tableRowThree, "P. O. Box 1450", ParagraphAlignment.LEFT, false);

		XWPFTableRow tableRowFour = addressTable.createRow();
		createAddressCell(tableRowFour, "Alexandria, VA 22313-1450", ParagraphAlignment.LEFT, false);

		CTTblPr tblpro = addressTable.getCTTbl().getTblPr();

		CTTblBorders borders = tblpro.addNewTblBorders();
		borders.addNewBottom().setVal(STBorder.NONE);
		borders.addNewLeft().setVal(STBorder.NONE);
		borders.addNewRight().setVal(STBorder.NONE);
		borders.addNewTop().setVal(STBorder.NONE);

		borders.addNewInsideH().setVal(STBorder.NONE);
		borders.addNewInsideV().setVal(STBorder.NONE);

	}

	private void addAppInfoTable(Claim claim) {
		XWPFTable appInfoTable = docx.createTable();
		appInfoTable.setCellMargins(150, 150, 150, 150);

		XWPFTableRow tableRowOne = appInfoTable.createRow();
		createCell(tableRowOne, "In re application of: " + claim.getApplicationInfo().getApplicant(), true);
		createCell(tableRowOne, "Group Art Unit: " + claim.getApplicationInfo().getGroupArtUnit(), false);

		XWPFTableRow tableRowTwo = appInfoTable.createRow();
		createCell(tableRowTwo, "Application No.: " + claim.getApplicationInfo().getNumber(), true);
		createCell(tableRowTwo, "Examiner: " + claim.getApplicationInfo().getExaminer(), false);

		XWPFTableRow tableRowThree = appInfoTable.createRow();
		createCell(tableRowThree, "Filed: " + claim.getApplicationInfo().getFillingDate(), true);
		createCell(tableRowThree, "Confirmation No.: " + claim.getApplicationInfo().getConfirmationNumber(), false);

		XWPFTableRow tableRowFour = appInfoTable.createRow();
		createCell(tableRowFour, "For: " + claim.getApplicationInfo().getApplicationFor(), true);
		createCell(tableRowFour, "Attorney Docket No.: " + claim.getApplicationInfo().getAttorneyDocketNumber(), false);

		CTTblPr tblpro = appInfoTable.getCTTbl().getTblPr();

		CTTblBorders borders = tblpro.addNewTblBorders();
		borders.addNewBottom().setVal(STBorder.SINGLE);
		borders.addNewLeft().setVal(STBorder.NONE);
		borders.addNewRight().setVal(STBorder.NONE);
		borders.addNewTop().setVal(STBorder.NONE);

		borders.addNewInsideH().setVal(STBorder.NONE);
		borders.addNewInsideV().setVal(STBorder.NONE);
	}

	private void createAddressCell(XWPFTableRow tableRow, String text, ParagraphAlignment align, boolean isBoald) {

		XWPFTableCell cell = tableRow.addNewTableCell();
		XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
		cellParagraph.setAlignment(align);
		XWPFRun cellRun = cellParagraph.createRun();
		cellRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		cellRun.setBold(isBoald);
		cellRun.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		cellRun.setText(text);

	}

	private void createCell(XWPFTableRow tableRow, String text, boolean addBorder) {

		XWPFTableCell cell = tableRow.addNewTableCell();
		XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
		cellParagraph.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun cellRun = cellParagraph.createRun();
		cellRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		cellRun.setFontSize(DocXStaticData.TITLE_FONT_SIZE);
		cellRun.setText(text);

		if (addBorder) {
			CTTc ctTc = cell.getCTTc();
			CTTcPr tcPr = ctTc.addNewTcPr();
			CTTcBorders border = tcPr.addNewTcBorders();
			border.addNewRight().setVal(STBorder.SINGLE);
		}

	}

	private void createHeader(Claim claim) {
		CTSectPr sectPr = docx.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(docx, sectPr);
		CTP ctpHeader = CTP.Factory.newInstance();

		String attDocketText = "Attorney Docket No. " + claim.getApplicationInfo().getAttorneyDocketNumber();

		String appNum = "Application No. " + claim.getApplicationInfo().getNumber();

		XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, docx);
		headerParagraph.setAlignment(ParagraphAlignment.RIGHT);

		XWPFRun attDocketRun = headerParagraph.createRun();
		attDocketRun.setFontSize(DocXStaticData.HEADER_FONT_SIZE);
		attDocketRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		attDocketRun.setText(attDocketText);

		attDocketRun.addBreak();

		XWPFRun appNumRun = headerParagraph.createRun();
		appNumRun.setFontSize(DocXStaticData.HEADER_FONT_SIZE);
		appNumRun.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		appNumRun.setText(appNum);

		XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		parsHeader[0] = headerParagraph;
		policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
	}

	private void createPageTitle(String title, boolean isUnderLined) {
		XWPFParagraph paragraph = docx.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText(title);
		run.setBold(true);
		if (isUnderLined) {
			run.setUnderline(UnderlinePatterns.SINGLE);
		}
		run.setFontFamily(DocXStaticData.MAIN_FONT_FAMILY);
		run.setFontSize(DocXStaticData.TITLE_FONT_SIZE);

		paragraph.setAlignment(ParagraphAlignment.CENTER);

	}

	private void createNewPage() {
		XWPFParagraph paragraph = docx.createParagraph();
		paragraph.setPageBreak(true);

	}
}
