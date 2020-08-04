package com.mary.blog.securityex01.text;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class GlobalExceptionController {
	
	@ExceptionHandler(value= {NullPointerException.class, IllegalArgumentException.class})
	public String null_illigalArgument_Exception(Exception e) {
		StringBuilder sb=new StringBuilder();
		sb.append("<h1>해당 아규먼트로 찾을 수 있는 정보가 없어요.</h1>");
		sb.append("<script>alert('"+e.getMessage()+"');");
		sb.append("location.href='/';</script>");
		return sb.toString();
	}
}
