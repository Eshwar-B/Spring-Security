package in.ashokit.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

@RestController
public class ConsumerRestController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping("/welcome")
    public String callWelcome() {
        String url = "http://localhost:8080/welcome";

        WebClient client = webClientBuilder.build();

        Mono<String> response = client.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);

        return response.block(); // Blocking only for demo
    }

    @GetMapping("/greet")
    public String callGreetWithAuth() {
        String url = "http://localhost:8080/greet";
        String username = "eshwar";
        String password = "eshwar@123";

        String base64Cred = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        
        WebClient webClient = webClientBuilder.build();
        
        Mono<String> response = webClient.get()
                 						 .uri(url)
                 						 .header(HttpHeaders.AUTHORIZATION, "Basic " + base64Cred)
                 						 .accept(MediaType.TEXT_PLAIN)
                 						 .retrieve()
                 						 .bodyToMono(String.class);
        	
        return response.block();
    }
}
