package br.com.casadocodigo.config.validation;

public class FormErrorDto {

	private String inputField;
	
	private String errorType;

	public FormErrorDto(String inputField, String errorType) {
		super();
		this.inputField = inputField;
		this.errorType = errorType;
	}

	public String getInputField() {
		return inputField;
	}

	public String getErrorType() {
		return errorType;
	}
	
}
