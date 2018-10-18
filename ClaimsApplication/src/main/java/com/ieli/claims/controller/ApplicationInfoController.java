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

			List<List<Integer>> claimsNumbers = new ArrayList<List<Integer>>();
			List<String> claimsNumbersAsStr = new ArrayList<String>();
			List<String> claimsStatueAsStr = new ArrayList<String>();

			List<String> claimsReferenceAsStr = new ArrayList<String>();
			List<String> claimsPubNumAsStr = new ArrayList<String>();

			String claimsLine = "";
			String statueLine = "";
			String referenceLine = "";
			String pubNumLine = "";
			String[] numbersLineArr = null;
			for (String str : applicationInfo.getSubTitles()) {

				claimsLine = str.substring(str.indexOf("Claims"), str.indexOf("are rejected")).replace("and", "")
						.replaceAll("Claims", "");
				numbersLineArr = claimsLine.split(",");

				for (int i = 0; i < numbersLineArr.length; i++) {

					String numberSplit = numbersLineArr[i];
					List<Integer> groupd = new ArrayList<Integer>();

					if (numberSplit.contains("-")) {

						int start = Integer.valueOf(numberSplit.split("-")[0].trim());
						int end = Integer.valueOf(numberSplit.split("-")[1].trim());
						for (int m = start; m <= end; m++) {
							groupd.add(m);
						}

					} else {
						groupd.add(Integer.valueOf(numberSplit.replaceAll(",", "").trim()));
					}

					claimsNumbersAsStr.add(numberSplit.replaceAll(",", "").trim());
					statueLine = str.substring(str.indexOf("U.S.C."), str.indexOf("as being")).replace("U.S.C.", "")
							.replaceAll("\\(\\d+\\)", "");
					claimsStatueAsStr.add(statueLine.trim());

					referenceLine = str.substring(str.indexOf("referred to as"), str.lastIndexOf("'"))
							.replace("referred to as", "");
					claimsReferenceAsStr.add(referenceLine.trim());

					pubNumLine = str.substring(str.lastIndexOf("'"), str.lastIndexOf(".")).replace("'", "");
					claimsPubNumAsStr.add(pubNumLine.trim());

					claimsNumbers.add(groupd);
				}

			}

			List<Integer> allRejectedClaims = new ArrayList<Integer>();
			for (List<Integer> innerList : claimsNumbers) {
				for (Integer in : innerList) {
					allRejectedClaims.add(in);
				}
			}
			int totalNumOfClaims = Collections.max(allRejectedClaims);
			applicationInfo.setTotalNumOfClaims(totalNumOfClaims);

			List<Integer> allClaims = new ArrayList<Integer>();
			for (int i = 0; i < totalNumOfClaims; i++) {
				allClaims.add(i + 1);
			}

			List<Integer> allowedClaims = new ArrayList<Integer>();
			for (List<Integer> innerList : claimsNumbers) {
				allClaims.removeAll(innerList);
			}

			applicationInfo.setAllowedClaimsNumbers(allowedClaims.size());

			String allowedClaimsStr = "0";
			if (!allowedClaims.isEmpty()) {
				allowedClaimsStr = groupNumbers(allowedClaims);
			}

			applicationInfo.setAllowableClaims(allowedClaimsStr);

			applicationInfo.setClaimsNumbersAsStr(claimsNumbersAsStr);
			applicationInfo.setGroupsClaimsTotal(claimsNumbersAsStr.size());
			applicationInfo.setClaimsStatueAsStr(claimsStatueAsStr);
			applicationInfo.setClaimsReferenceAsStr(claimsReferenceAsStr);
			applicationInfo.setClaimsPubNumAsStr(claimsPubNumAsStr);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<ApplicationInfo>(applicationInfo, HttpStatus.OK);
	}

	public String groupNumbers(List<Integer> nums) {

		Collections.sort(nums);
		List<List<Integer>> ListMain = new ArrayList<>();
		List<Integer> temp = new ArrayList<>();
		temp.add(nums.get(0));

		for (int i = 0; i < nums.size() - 1; i++) {
			if (nums.get(i + 1) == nums.get(i) + 1) {
				temp.add(nums.get(i + 1));
			} else {
				ListMain.add(temp);
				temp = new ArrayList<>();
				temp.add(nums.get(i + 1));
			}
		}

		ListMain.add(temp);

		String allowedClaimsStr = "";

		for (List<Integer> innerList : ListMain) {
			if (innerList.size() == 1) {
				allowedClaimsStr += innerList.get(0) + ",";
			} else {
				allowedClaimsStr += innerList.get(0) + "-" + innerList.get(innerList.size() - 1) + ", ";
			}
		}

		allowedClaimsStr = allowedClaimsStr.substring(0, allowedClaimsStr.length() - 1);

		return allowedClaimsStr;
	}

	@RequestMapping(value = { "/generate" }, method = RequestMethod.POST)
	public String generate(ApplicationInfo applicationInfo) {

		return "redirect:/";
	}

	@RequestMapping(value = { "/saveAndGenerate" }, method = RequestMethod.POST)
	public String saveAndGenerate() {

		return "/";
	}
}
