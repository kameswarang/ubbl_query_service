package ubbl.query_service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ubbl.query_service.web.client.DataServiceClient;

@Controller
public class MappingController {
    @Autowired
    DataServiceClient dataService;
    
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @PostMapping(path="/home", produces="application/json")
    @ResponseBody
    public String postHome(@RequestBody String query) {
        try {
            String result = dataService.search(query);
            return ResponseEntity.ok(result).toString();
        }
        catch(Error e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error. Try again later.").toString();
        }
    }
}