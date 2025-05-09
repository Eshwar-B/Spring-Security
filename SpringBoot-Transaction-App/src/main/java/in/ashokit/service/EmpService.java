package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Address;
import in.ashokit.entity.Emp;
import in.ashokit.repo.AddressRepo;
import in.ashokit.repo.EmpRepo;
import javax.transaction.Transactional;

@Service	
public class EmpService {

	@Autowired
	private EmpRepo empRepo;
	
	@Autowired
	private AddressRepo addrRepo;
	
	@Transactional(rollbackOn = Exception.class)
	public void saveEmp() {
		Emp e = new Emp();
		e.setName("Eshwar");
		e.setSalary(65000.75);
		
		Emp savedEmp = empRepo.save(e);
		
		int i = 10 / 0;
		
		Address a = new Address();
		a.setAid(savedEmp.getEid());
		a.setCity("Hyderabad");
		a.setState("TG");
		a.setCountry("India");
		
		addrRepo.save(a);
	}
}
