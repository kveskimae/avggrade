package com.foo.webapp.controller;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
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
import com.foo.logic.StudentWithAvgGrade;
import com.foo.logic.gen.Students;
import com.foo.webapp.dto.ExtractionFailureResult;
import com.foo.webapp.dto.ExtractionSuccessResult;
import com.foo.webapp.xml.StudentsMarshaler;

@RestController
@RequestMapping(value = "/upload")
public class FileUploadController {

	private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	private AvgCalculator avgCalculator;

	@Autowired
	private StudentsMarshaler marshaler;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExtractionFailureResult> statusInfo() {
		return new ResponseEntity<ExtractionFailureResult>(new ExtractionFailureResult("Upload controller is up and running", null), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> handleFileUpload(final @RequestParam("file") MultipartFile file) {
		try {
			Students students = marshaler.unmarshal(file.getInputStream());
			List<StudentWithAvgGrade> studentsWithAvgGrade = avgCalculator.calculateAvgGradeForStudents(students);
			ExtractionSuccessResult result = new ExtractionSuccessResult(studentsWithAvgGrade);
			return new ResponseEntity<Object>(result, HttpStatus.OK);
		} catch (JAXBException e) {
			log.error("Parsing XML failed", e);
			return new ResponseEntity<Object>(new ExtractionFailureResult(null, "Malformed XML file was submitted"), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.error("Failure in server", e);
			return new ResponseEntity<Object>(new ExtractionFailureResult(null, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
