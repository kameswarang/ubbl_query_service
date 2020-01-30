package ubbl.query_service.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ubbl-data-service")
public interface DataServiceClient {
    @PostMapping("/query")
    public String search(@RequestBody String query);
}