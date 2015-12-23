package com.foo.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foo.logic.AvgCalculator;
import com.foo.webapp.dto.ExtractionFailureResult;
import com.foo.webapp.dto.ExtractionSuccessResult;

@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {

	private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private AvgCalculator fileSaver;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExtractionFailureResult> statusInfo() {
		return new ResponseEntity<ExtractionFailureResult>(new ExtractionFailureResult("Upload controller is up and running", null), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> handleFileUpload(final @RequestParam("file") MultipartFile file) {
		try {
			ExtractionSuccessResult result = new ExtractionSuccessResult("123");
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} 
		/*
		catch (ParsingFailureException e) {
			log.error("Parsing failed", e);
			return new ResponseEntity<Object>(new ExtractionFailureResult(null, e.getMessage()), HttpStatus.BAD_REQUEST);
		} */
		catch (Exception e) {
			log.error("Failure in server", e);
			return new ResponseEntity<Object>(new ExtractionFailureResult(null, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
