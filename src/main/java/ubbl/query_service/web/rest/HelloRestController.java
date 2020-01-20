package ubbl.query_service.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.ServiceInstance;

@RestController
public class HelloRestController {
    @Autowired @LoadBalanced
    private RestTemplate client;

    @Autowired
    private DiscoveryClient dClient;
    
    @GetMapping(path="/services")
    public String getServices() {      
        List<String> services = dClient.getServices();
        List<ServiceInstance> serviceInstances = new ArrayList<>();
        List<String> serviceIds = new ArrayList<>();       
        
        for(String service : services) {
            serviceInstances.addAll(dClient.getInstances(service));
        }
        
        for(ServiceInstance service : serviceInstances) {
           serviceIds.add(service.getHost());
        }        

        try {
            System.out.println(this.client.getForObject("http://UBBL-DATA-SERVICE/rest/hello", String.class, new HashMap<String, String>()));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return serviceIds.toString();
    }
}