package idv.testing.webapp;

import idv.util.RestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class AuthenticatedContextTests extends AbstractTestNGSpringContextTests {	

	private RestClient restClient;
	private RestTemplate statefulRest;

	@Value("${login.username}")
	private String username;
	@Value("${login.password}")
	private String password;
	@Value("${server.login.url}")
	private String SERVER_LOGIN_URL;	
	@Value("${server.welcome.url}")
	private String SERVER_WELCOME_URL;	
	@Value("${server.application.path}")
	private String APPLICATION_PATH;
		
	protected void setup() {
		
		restClient = new RestClient();	
		restClient.setApplicationPath(APPLICATION_PATH);
		String returnedUrl = restClient.login(username, password);
		
		Assert.assertTrue(returnedUrl.equals(SERVER_WELCOME_URL));
		
		statefulRest = restClient.template();
		// Add the Jackson and String message converters
		statefulRest.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		statefulRest.getMessageConverters().add(new StringHttpMessageConverter());
		
	}
	
	public RestTemplate getStatefulRest() {
		return statefulRest;
	}

	public void setStatefulRest(RestTemplate statefulRest) {
		this.statefulRest = statefulRest;
	}
	
	public String getApplicationUrl(String relativePath)
	{		
		return restClient.applicationUrl(relativePath);
	}
}
