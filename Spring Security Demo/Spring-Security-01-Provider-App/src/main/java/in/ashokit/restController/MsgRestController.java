package in.ashokit.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring Security Module !";
    }

    @GetMapping("/greet")
    public String greet() {
        return "Good Afternoon";
    }
}
