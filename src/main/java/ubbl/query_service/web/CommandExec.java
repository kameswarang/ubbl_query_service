package ubbl.query_service.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommandExec {
    @PostMapping("/exec")
    public String exec(@RequestParam(name="command", defaultValue="") String command) {
        return "";
    }
}
