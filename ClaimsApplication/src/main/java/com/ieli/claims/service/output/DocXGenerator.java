package com.ieli.claims.service.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.Text;

import com.ieli.claims.model.app.Claim;

public class DocXGenerator {

	private void generateDocX(Claim claim) throws Docx4JException, IOException {

		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(new File(
				"C:/Users/ielii/git/app_info_reader/ClaimsApplication/src/main/resources/claim_template.docx")));

		replacePlaceholder(template, "a test", "trademarkOfficeTitle");
		
		writeDocxToStream(template, "F:/Imad/work/upwork/Phil Antonio/RCs/Final Samples/mytoutput/res.docx");

	}

	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement)
			obj = ((JAXBElement<?>) obj).getValue();

		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}

		}
		return result;
	}

	private void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder) {
		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);

		for (Object text : texts) {
			Text textElement = (Text) text;
			if (textElement.getValue().equals(placeholder)) {
				textElement.setValue(name);
			}
		}
	}

	private void writeDocxToStream(WordprocessingMLPackage template, String target)
			throws IOException, Docx4JException {
		File f = new File(target);
		template.save(f);
	}
}
