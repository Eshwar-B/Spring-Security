package in.ashokit.restController;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerRestController {
	
	@Autowired
	private RestTemplate rt;

	@GetMapping("/welcome")
	public String getWelcomeMsg()
	{
		String url = "http://localhost:8080/welcome";
		
		String username = "eshwar";
		String password = "eshwar@123";
		String cred = username + ":" + password;
		String base64cred = Base64.getEncoder().encodeToString(cred.getBytes());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic "+ base64cred);
		
		HttpEntity httpEntity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = rt.exchange(url, 
													  HttpMethod.GET, 
													  httpEntity, 
													  String.class);
		
		return response.getBody();	
	}
	
	@GetMapping("/greet")
	public String getGreetMsg()
	{
		String url = "http://localhost:8080/greet";
		
		String username = "eshwar";
		String password = "eshwar@123";
		String cred = username + ":" + password;
		String base64cred = Base64.getEncoder().encodeToString(cred.getBytes());
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic "+ base64cred);
		
		HttpEntity httpEntity = new HttpEntity<>(headers);
		
		ResponseEntity<String> response = rt.exchange(url, 
													  HttpMethod.GET, 
													  httpEntity, 
													  String.class);
		
		return response.getBody();	
	}
	
}
