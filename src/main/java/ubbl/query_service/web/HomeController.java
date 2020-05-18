package ubbl.query_service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ubbl.query_service.model.User;
import ubbl.query_service.web.client.DataServiceClient;

@Controller
@RequestMapping(path={"/home"})
public class HomeController {
    @Autowired
    DataServiceClient dataService;
    
    @GetMapping
    public String getHome(Model model, @AuthenticationPrincipal(errorOnInvalidType=true) UserDetails user) {
        model.addAttribute("user", user);
        return "home";
    }
    
    @PostMapping(produces="application/json")
    @ResponseBody
    public String postHome(@RequestBody String query) {
        try {
            String result = dataService.search(query);
            return result.toString();
        }
        catch(Error e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Try again later.").toString();
        }
    }
}