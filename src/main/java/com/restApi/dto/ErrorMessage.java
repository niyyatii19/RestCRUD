package com.restApi.dto;

import lombok.Data;

@Data
public class ErrorMessage {

	private int errorCode;
	private String errorMessage;
}
