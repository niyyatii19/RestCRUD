package com.restApi.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.Data;
@Data
public class CustomerDTO {
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
	private String email;
}
