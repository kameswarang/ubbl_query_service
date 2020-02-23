package kganesh1795.security_tutorial.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${spring.profiles.active:}")
    private String profile;
    
    @Value("${MONGODB_URI:}")
    //MONGODB_URI is an env var set only in prod envs with spring profile set to active
    private String uri;
    
    @Value("${spring.application.databaseName}")
    private String db;
    
	@Override
	protected String getDatabaseName() {
	    //in prod env parse the database name from MONGODB_URI
	    if(profile=="prod") {
	        if(this.db==null) {
    	        this.db = this.uri.substring(uri.lastIndexOf("/")+1);
	        }
	    }
	    //in other envs return the same set in application.yml
		return this.db;
	}

	@Override
	public MongoClient mongoClient() {
	    MongoClient mc;
	    
		if(this.profile=="prod") {
		    mc = MongoClients.create(this.uri);
		}
		//For non-prod envs drop "user" collection
		else {
		    mc = MongoClients.create();
		    mc.getDatabase(this.getDatabaseName()).getCollection("user").drop();
		}
		
		return mc;
	}
}