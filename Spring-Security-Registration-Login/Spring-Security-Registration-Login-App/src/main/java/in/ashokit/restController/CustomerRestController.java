package in.ashokit.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Customer;
import in.ashokit.repo.CustomerRepository;
import in.ashokit.service.JWTService;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JWTService jwtService;
	
	@PostMapping("/register")
	public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
		String password = pwdEncoder.encode(customer.getPwd());

		customer.setPwd(password);

		customerRepo.save(customer);

		return new ResponseEntity<>("Customer Registered", HttpStatus.CREATED);
	}

	@GetMapping("/welcome")
	public String welcome()
	{
		return "Welcome to Spring Security Login Registration";
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginCustomer(@RequestBody Customer customer) {

		UsernamePasswordAuthenticationToken emailPwdAuthToken = 
								new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPwd());
		
		try {
			
			Authentication authenticate = authManager.authenticate(emailPwdAuthToken);
			
			if(authenticate.isAuthenticated()) {
				
				String jwtToken = jwtService.generateToken(customer.getEmail());
				return new ResponseEntity<>(jwtToken, HttpStatus.OK);
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}

}
