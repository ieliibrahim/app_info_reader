package com.ieli.claims.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ieli.claims.model.app.ApplicationInfo;
import com.ieli.claims.model.app.Claim;
import com.ieli.claims.model.app.RejectedClaims;
import com.ieli.claims.repository.ApplicationInfoRepository;
import com.ieli.claims.repository.ClaimsRepository;
import com.ieli.claims.repository.RejectedClaimsRepository;
import com.ieli.claims.service.pdf.IPDFParser;

@Controller
public class ApplicationInfoController {

	@Autowired
	private IPDFParser iPDFParser;

	@Autowired
	private ApplicationInfoRepository applicationInfoRepository;

	@Autowired
	private ClaimsRepository claimsRepository;

	@Autowired
	private RejectedClaimsRepository rejectedClaimsRepository;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		List<ApplicationInfo> applicationInfos = applicationInfoRepository
				.findAll(new Sort(Sort.Direction.DESC, "applicationId"));
		model.addAttribute("applicationInfos", applicationInfos);

		return "index";
	}

	@RequestMapping(value = {
			"/getAppInfo/{appInfoId}" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Claim> getAppInfo(@PathVariable(name = "appInfoId") Integer appInfoId, Model model) {

		Claim claim = claimsRepository.findByApplicationInfoApplicationId(appInfoId);
		List<RejectedClaims> rejectedClaims = rejectedClaimsRepository.findByClaimId(claim.getClaimId());
		claim.setRejectedClaims(rejectedClaims);

		return new ResponseEntity<Claim>(claim, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/generateDoc/{appInfoId}" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Claim> generateDoc(@PathVariable(name = "appInfoId") Integer appInfoId, Model model) {

		Claim claim = claimsRepository.findByApplicationInfoApplicationId(appInfoId);
		List<RejectedClaims> rejectedClaims = rejectedClaimsRepository.findByClaimId(claim.getClaimId());
		claim.setRejectedClaims(rejectedClaims);

		return new ResponseEntity<Claim>(claim, HttpStatus.OK);
	}

	@RequestMapping(value = {
			"/uploadAppInfoPDF" }, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String uploadAppInfoPDF(@RequestParam(name = "input") MultipartFile[] pdfFiles, Model model) {

		for (MultipartFile pdfFile : pdfFiles) {
			try {

				ApplicationInfo applicationInfo = iPDFParser.getPDFConent(pdfFile.getInputStream());
				applicationInfo.setApplicationFor("");

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

				Claim claim = new Claim();
				claim.setAllowableClaims(allowedClaimsStr);
				claim.setAllowedNumber(allowedClaims.size());
				claim.setTotalNumber(totalNumOfClaims);
				claim.setObjectedNumber("0");
				claim.setObjectedStatue("N/A");
				claim.setAllowedNumber(0);

				claim.setApplicationInfo(applicationInfo);

				applicationInfoRepository.save(applicationInfo);
				Claim dbClaim = claimsRepository.saveAndFlush(claim);

				for (int i = 0; i < claimsNumbersAsStr.size(); i++) {

					RejectedClaims rejectedClaim = new RejectedClaims();
					rejectedClaim.setClaimId(dbClaim.getClaimId());
					rejectedClaim.setClaimNumbers(claimsNumbersAsStr.get(i));
					rejectedClaim.setClaimStatue(claimsStatueAsStr.get(i));
					rejectedClaim.setName(claimsReferenceAsStr.get(i));
					rejectedClaim.setPublicationNumber(claimsPubNumAsStr.get(i));

					rejectedClaimsRepository.save(rejectedClaim);

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return "redirect:/index";
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

		applicationInfoRepository.saveAndFlush(applicationInfo);
		return "redirect:/";
	}

	@RequestMapping(value = { "/saveAndGenerate" }, method = RequestMethod.POST)
	public String saveAndGenerate() {

		return "/";
	}
}
