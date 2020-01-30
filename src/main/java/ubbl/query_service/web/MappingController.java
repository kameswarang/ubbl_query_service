package ubbl.query_service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ubbl.query_service.web.client.DataServiceClient;

@Controller
public class MappingController {
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
    
    @PostMapping(path="/home", produces="application/json")
    @ResponseBody
    public String postHome(@RequestBody String query) {
        return this.dataService.search(query);
    }
    
     @Autowired
     DataServiceClient dataService;
}
