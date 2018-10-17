package com.ieli.claims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ieli.claims.model.app.ApplicationInfo;
import com.ieli.claims.model.app.Claim;
import com.ieli.claims.model.app.RejectedClaims;
import com.ieli.claims.service.pdf.IPDFParser;

@Controller
public class ApplicationInfoController {

	@Autowired
	private IPDFParser iPDFParser;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String home(@RequestParam(name = "input", required = false) MultipartFile pdfFile, ModelMap model) {

		ApplicationInfo applicationInfo = new ApplicationInfo();
		applicationInfo.setClaim(new Claim(new LinkedHashSet<RejectedClaims>()));
		model.addAttribute("applicationInfo", applicationInfo);

		return "applicationInfo";
	}

	@RequestMapping(value = {
			"/uploadAppInfoPDF" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ApplicationInfo> uploadAppInfoPDF(@RequestParam(name = "input") MultipartFile pdfFile,
			Model model) {

		ApplicationInfo applicationInfo = null;
		try {
			applicationInfo = iPDFParser.getPDFConent(pdfFile.getInputStream());

			List<Integer> claimsNumbers = new ArrayList<Integer>();
			List<String> claimsNumbersAsStr = new ArrayList<String>();

			String claimsLine = "";
			String[] numbersLineArr = null;
			for (String str : applicationInfo.getSubTitles()) {

				claimsLine = str.substring(str.indexOf("Claims"), str.indexOf("are rejected")).replace("and", "")
						.replaceAll("Claims", "");
				numbersLineArr = claimsLine.split(",");

				for (int i = 0; i < numbersLineArr.length; i++) {

					String numberSplit = numbersLineArr[i];
					if (numberSplit.contains("-")) {
						claimsNumbers.add(Integer.valueOf(numberSplit.split("-")[0].trim()));
						claimsNumbers.add(Integer.valueOf(numberSplit.split("-")[1].trim()));
					} else {
						claimsNumbers.add(Integer.valueOf(numberSplit.replaceAll(",", "").trim()));
					}

					claimsNumbersAsStr.add(numberSplit.replaceAll(",", "").trim());
				}
			}

			int totalNumOfClaims = Collections.max(claimsNumbers);
			applicationInfo.setTotalNumOfClaims(totalNumOfClaims);

			List<Integer> allClaims = new ArrayList<Integer>();
			for (int i = 0; i < totalNumOfClaims; i++) {
				allClaims.add(i + 1);
			}

			allClaims.removeAll(claimsNumbers);
			applicationInfo.setAllowedClaimsNumbers(allClaims.size());

			String allowedClaimsStr = "";
			for (int i = 0; i < allClaims.size(); i++) {
				if (i == allClaims.size() - 1) {
					allowedClaimsStr += allClaims.get(i);
				} else {
					allowedClaimsStr += allClaims.get(i) + ",";
				}
			}

			applicationInfo.setAllowableClaims(allowedClaimsStr);

			applicationInfo.setClaimsNumbersAsStr(claimsNumbersAsStr);
			applicationInfo.setGroupsClaimsTotal(claimsNumbersAsStr.size());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<ApplicationInfo>(applicationInfo, HttpStatus.OK);
	}

	@RequestMapping(value = { "/previewOutput" }, method = RequestMethod.POST)
	public ModelAndView previewOutput(@Valid ApplicationInfo applicationInfo, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("applicationInfo");
		} else {
			modelAndView.setViewName("previewOutput");
		}

		return modelAndView;
	}

	@RequestMapping(value = { "/saveAndGenerate" }, method = RequestMethod.POST)
	public String saveAndGenerate() {

		return "/";
	}
}
