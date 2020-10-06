package dmitr2ish.com.github.KafkaTesting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @GetMapping
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }
}
