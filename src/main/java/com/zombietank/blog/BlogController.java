package com.zombietank.blog;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "blog";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public Blog create() {
		return new Blog();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public void submitChanges(@Valid Blog blog, BindingResult bindingResult) {
	}
}
