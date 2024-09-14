package com.dailycodebuffer.Springboot.tutorial.customValidation;

import org.springframework.stereotype.Component;

@Component
public class CustomValidator {
	
	public boolean notEmptyValidator(String value) {
		if(value=="") {
			return true;
		}
		return  false;
	}

}
