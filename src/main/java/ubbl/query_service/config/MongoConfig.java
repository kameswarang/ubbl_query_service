package ubbl.query_service.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {
    private String uri;
    private String db;
    private String profile;
    
	@Override
	// also act as a init method
	protected String getDatabaseName() {
	    if(this.uri == null) {
        	this.uri = System.getenv("MONGODB_URI");
        	if(this.uri == null) {
        		this.uri = "mongodb://localhost/ubbdb";
        	}
	        this.db = this.uri.substring(uri.lastIndexOf("/")+1);
	    }
		return this.db;
	}

	@Override
	public MongoClient mongoClient() {
		this.getDatabaseName();
		profile = System.getenv("spring_profiles_active");
		System.out.println(profile);
		
	    MongoClient mc = MongoClients.create(this.uri);
		
		//For non-prod envs drop "user" collection
		if(!profile.equals("prod")) {
		    MongoCollection<Document> user = mc.getDatabase(this.getDatabaseName()).getCollection("user");
		    if(user != null) {
		    	//user.drop();
		    	//System.out.println("user collection dropped");
		    }
		}
		
		return mc;
	}
}