package idv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
@RequestMapping("/")
public class IndexController {
 
	@RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		
		System.out.println("welcome!");
		
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Welcome!");
		model.addObject("message", "This is welcome page!");
		model.setViewName("welcome");
		return model;
 
	}
 
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {
 
		ModelAndView model = new ModelAndView();
		model.addObject("title", "Admin Page");
		model.addObject("message", "This is admin page!");
		model.setViewName("admin");

		return model;
 
	}
 	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
 
		System.out.println("login success!");
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}
 
		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");
 
		return model;
 
	}
 
}