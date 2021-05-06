package aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//@EnableHazelcastHttpSession
@EnableCaching
@SpringBootApplication
public class ProyectoBaseSpringApplication 
{
	private static final Log LOG = LogFactory.getLog(ProyectoBaseSpringApplication.class);
	
	public static void main(String[] args)
	{
		SpringApplication.run(ProyectoBaseSpringApplication.class, args);
	}
	/*
	@Bean
	public Config config() {
		Config config = new Config();
		
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		
		joinConfig.getMulticastConfig().setEnabled(false);
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("127.0.0.1"));
	
		return config;
	}
	*/
	@Bean
	public CacheManager cacheManager() 
	{
	   LOG.info("Activating cache...");
	   return new ConcurrentMapCacheManager("cacheURJC");
	}
	
	/*
	  
	 <dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-core</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-hazelcast</artifactId>
		</dependency>
		<dependency>
			<groupId>com.hazelcast</groupId>
			<artifactId>hazelcast</artifactId>
		</dependency>
	 
	 */
}
