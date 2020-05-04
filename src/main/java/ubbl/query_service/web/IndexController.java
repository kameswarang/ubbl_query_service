package ubbl.query_service.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public String get(Model model) {
        String eureka = System.getenv("EUREKA_SERVER_URI");
        model.addAttribute("eureka_server", eureka.substring(0, eureka.lastIndexOf("/")) + "/start");
        model.addAttribute("data_service", System.getenv("UBBL_DATA_SERVICE"));
        return "index";
    }
}