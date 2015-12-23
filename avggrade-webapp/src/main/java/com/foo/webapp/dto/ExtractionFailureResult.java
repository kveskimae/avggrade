package com.foo.webapp.dto;

public class ExtractionFailureResult implements IExtractionResult {
	
	/**
	 * Extracted text contents
	 */
	public final String text;
	
	/**
	 * Explanation of erroneous situation
	 */
	public final String errorMsg;
	
	public ExtractionFailureResult(final String text, final String error) {
		this.text = text;
		this.errorMsg = error;
	}

}
