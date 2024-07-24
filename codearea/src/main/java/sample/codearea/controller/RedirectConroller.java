package sample.codearea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectConroller {
	@GetMapping({"/", "/{path:[^\\.]*}"})
	public String redirect() {
		return "forward:/index.html";
	}
}
