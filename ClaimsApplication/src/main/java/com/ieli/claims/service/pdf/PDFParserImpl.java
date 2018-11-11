package com.ieli.claims.service.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ieli.claims.model.app.ApplicationInfo;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;

@Service("iPDFParser")
public class PDFParserImpl implements IPDFParser {

	@Override
	public ApplicationInfo getPDFConent(InputStream inputStream) throws IOException {

		ApplicationInfo applicationInfo = new ApplicationInfo();

		try {
			Document pdf = PDF.open(inputStream, "");

			for (int i = 0; i <= 2; i++) {

				StringBuilder text = new StringBuilder();
				pdf.getPage(i).pipe(new OutputTarget(text));

				if (i == 0 || i == 1) {
					if (text != null) {
						if (!text.equals("")) {

							String[] pageLines = text.toString().split("\n");
							String line = "";
							for (int s = 0; s < pageLines.length; s++) {

								line = pageLines[s];

								if (line.trim().startsWith("APPLICATION NO.")) {
									String appInfoLine = pageLines[s + 3];

									String[] appInfoLineArr = appInfoLine
											.split("                                        ");
									applicationInfo.setNumber(appInfoLineArr[0].replaceAll("\r", "").trim());
									applicationInfo.setFillingDate(appInfoLineArr[1].replaceAll("\r", "").trim());
									applicationInfo.setApplicant(appInfoLineArr[2].replaceAll("\r", "").trim());

								} else if (line.trim().startsWith("NOTIFICATION DATE")) {

									String fieldDateLine = pageLines[s + 4];
									String[] fieldDateLineArr = fieldDateLine
											.split("                                   ");
									applicationInfo.setNotificationDate(fieldDateLineArr[0].replaceAll("\r", "").trim());

								} else if (line.trim().startsWith("ATTORNEY DOCKET NO")) {

									String attorneyLine = pageLines[s + 3];
									String[] attorneyLineArr = attorneyLine.split("                              ");

									applicationInfo
											.setAttorneyDocketNumber(attorneyLineArr[0].replaceAll("\r", "").trim());
									applicationInfo
											.setConfirmationNumber(attorneyLineArr[1].replaceAll("\r", "").trim());
								} else if (line.trim().startsWith("EXAMINER")) {

									String examinerLine = pageLines[s + 3];
									applicationInfo.setExaminer(examinerLine.replaceAll("\r", "").trim());

								} else if (line.trim().startsWith("ART UNIT")) {

									String artUnitLine = pageLines[s + 3];
									applicationInfo.setGroupArtUnit(artUnitLine);
								} else if (line.trim().startsWith("2a)")) {
									String[] finalOfficeLineArr = line.split("                             ");
									String finalOffice = finalOfficeLineArr[0].trim().replaceAll("\r", "");
									String nonFinalOffice = finalOfficeLineArr[1].replaceAll("\r", "").trim();

									if (finalOffice.startsWith("2a)K")) {
										applicationInfo.setFinalOfficeAction(1);
									} else if (nonFinalOffice.startsWith("2b)K")) {
										applicationInfo.setFinalOfficeAction(0);
									}
								}
							}

						}
					}
				}

			}

			getTitlesAndSubtitles(pdf, applicationInfo);

			pdf.close();
		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return applicationInfo;
	}

	private void getTitlesAndSubtitles(Document pdf, ApplicationInfo applicationInfo) {

		List<String> titles = new ArrayList<String>();

		List<String> subTitles = new ArrayList<String>();

		List<Integer> rejectionPages = new ArrayList<Integer>();

		for (int i = 2; i < pdf.getPageCnt(); i++) {

			StringBuilder text = new StringBuilder();
			pdf.getPage(i).pipe(new OutputTarget(text));

			if (text != null) {
				if (!text.equals("")) {

					String[] pageLines = text.toString().split("\n");
					String line = "";
					for (int s = 0; s < pageLines.length; s++) {

						line = pageLines[s];

						if (line.trim().startsWith("Claim Rejections - ")) {

							rejectionPages.add(i);

						}
					}

				}
			}
		}

		for (int i = 0; i < rejectionPages.size(); i++) {

			StringBuilder text = new StringBuilder();
			pdf.getPage(rejectionPages.get(i)).pipe(new OutputTarget(text));

			if (text != null) {
				if (!text.equals("")) {

					String[] pageLines = text.toString().split("\n");
					String line = "";
					for (int s = 0; s < pageLines.length; s++) {

						line = pageLines[s];

						if (line.trim().startsWith("Claim Rejections - ")) {

							titles.add(line.replaceAll("\r", "").trim());

							for (int n = s; n < pageLines.length; n++) {

								line = pageLines[n];

								String subTitle = "";
								if (line.trim().startsWith("Claims")) {

									subTitle += line.replaceAll("\r", "").trim() + "\n";

									for (int f = n + 1; f < pageLines.length; f++) {

										String lastLine = pageLines[f];

										if (lastLine.trim().contains("'") & lastLine.trim().contains(".")) {
											subTitle += lastLine.replaceAll("\r", "").trim() + "\n";
											subTitles.add(subTitle);
											break;

										}
									}

								}
							}

							break;
						}
					}

				}
			}
		}

		applicationInfo.setTitlesTrans(titles);
		String titlesStr = "";
		for(String title : titles) {
			titlesStr += title + "app_new_line";
		}
		applicationInfo.setTitles(titlesStr);
		
		applicationInfo.setSubTitlesTrans(subTitles);
		String subTitlesStr = "";
		for(String subTitle : subTitles) {
			subTitlesStr += subTitle + "app_new_line";
		}
		applicationInfo.setSubTitles(subTitlesStr);
		
	}

}
