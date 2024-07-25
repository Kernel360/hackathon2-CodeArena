package sample.codearea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {
	@GetMapping({"/", "/{path:[^\\.]*}", "/{parentPath:[^\\.]*}/{childPath:[^\\.]*}"})
	public String redirect() {
		System.out.println("redirect");
		return "forward:/index.html";
	}
}
