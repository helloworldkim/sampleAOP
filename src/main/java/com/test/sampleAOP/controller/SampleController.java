package com.test.sampleAOP.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.sampleAOP.customAnnotation.LogExecuteTime;
import com.test.sampleAOP.customAnnotation.MyCustom;


@RestController
public class SampleController {

	@GetMapping(value = "/test1")
	public String test1() {
		
		return "test1";
	}
	@MyCustom
	@RequestMapping(value = "/test2")
	public String test2() {
		
		return "test2";
	}
	
	@LogExecuteTime
	@RequestMapping(value = "/test3")
	public String test3() {
		return "test3";
	}
}
