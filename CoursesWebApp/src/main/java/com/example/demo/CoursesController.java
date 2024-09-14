package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CoursesController {
	@RequestMapping("/courses")
	public ModelAndView courses(@RequestParam("cname")String coursename)
	{
		ModelAndView mv = new ModelAndView();
		mv.addObject("cname", coursename);
		mv.setViewName("courses");
		return mv;
	} 

}
