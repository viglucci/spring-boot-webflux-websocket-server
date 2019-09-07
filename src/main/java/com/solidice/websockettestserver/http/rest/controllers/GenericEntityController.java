package com.solidice.websockettestserver.http.rest.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenericEntityController {
	private List<String> entityList = new ArrayList<String>() {
		{
			add("test");
		}
	};

	@RequestMapping("/entity/all")
	public List<String> findAll() {
		return entityList;
	}
}