package aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
@EnableHazelcastHttpSession
@EnableCaching
@SpringBootApplication
public class ProyectoBaseSpringApplication 
{
	private static final Log LOG = LogFactory.getLog(ProyectoBaseSpringApplication.class);
	
	public static void main(String[] args)
	{
		SpringApplication.run(ProyectoBaseSpringApplication.class, args);
	}
	
	@Bean
	public Config config() {
		Config config = new Config();
		
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		
		joinConfig.getMulticastConfig().setEnabled(false);
	
		List<String> servicios=new ArrayList<String>();
		servicios.add("sportcenter1");
		servicios.add("sportcenter2");
		
		joinConfig.getTcpIpConfig().setEnabled(true).setMembers(servicios);
		return config;
	}
	
	@Bean
	public CacheManager cacheManager() 
	{
	   LOG.info("Activating cache...");
	   return new ConcurrentMapCacheManager("cacheURJC");
	}
}
