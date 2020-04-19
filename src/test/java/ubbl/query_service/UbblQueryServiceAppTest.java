package ubbl.query_service;

import com.mongodb.Block;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import ubbl.query_service.config.MongoConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UbblQueryServiceAppTest {
    private MongoConfig mc;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void print_databaseName_to_console() {
		mc = new MongoConfig();
//reference to forEach is ambiguous
//[ERROR]   both method forEach(java.util.function.Consumer<? super T>) in java.lang.Iterable and 
//method forEach(com.mongodb.Block<? super TResult>) in com.mongodb.client.MongoIterable match
		mc.mongoClient().listDatabaseNames().forEach((Block<String>)System.out::println);
	}
}
