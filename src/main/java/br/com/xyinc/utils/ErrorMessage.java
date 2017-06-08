package br.com.xyinc.utils;

public class ErrorMessage {
	
	private String code;
	private String message;
	
	public ErrorMessage() {

	}

	public ErrorMessage(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorMessage [code=").append(code).append(", message=").append(message).append("]");
		return builder.toString();
	}
	
}
