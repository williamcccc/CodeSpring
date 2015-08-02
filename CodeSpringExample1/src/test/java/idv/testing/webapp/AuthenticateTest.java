package idv.testing.webapp;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import idv.util.RestClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;

@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class AuthenticateTest extends AbstractTestNGSpringContextTests {		
	
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
	
	/**
	 * Login Test
	 */
	@Test
	public void testRestClientLogin()
	{	
		RestClient restClient = new RestClient();
		restClient.setApplicationPath(APPLICATION_PATH);
		String returnedUrl = restClient.login(username, password);
		
		AssertJUnit.assertTrue(returnedUrl.equals(SERVER_WELCOME_URL));
	}	
	
    /**
     * Login Test 
     * */
	@SuppressWarnings("deprecation")
	@Test
    public void testPlainLogin()
    {   
    	@SuppressWarnings("rawtypes")
		ResponseEntity result;
    	RestTemplate restTemplate = new RestTemplate();
    	
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("username", username);
        body.add("password", password);
        		
        HttpHeaders headers = new HttpHeaders();        
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);        
        
        HttpEntity<?> requestEntity = new HttpEntity<Object>(body, headers);
        										    
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		//exchange for Login, expect 302 and login success
        result = restTemplate.exchange(SERVER_LOGIN_URL, HttpMethod.POST, requestEntity, String.class);
        
        String returnedUrl = result.getHeaders().getLocation().toString();
        String returnedStatusCode = result.getStatusCode().toString();
        
        Assert.assertTrue(returnedStatusCode.equals(HttpStatus.MOVED_TEMPORARILY.toString()));
        Assert.assertTrue(returnedUrl.equals(SERVER_WELCOME_URL));	
				

    }
	
}
