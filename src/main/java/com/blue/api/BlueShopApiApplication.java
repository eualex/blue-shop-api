package com.blue.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlueShopApiApplication {

	/*private final PasswordEncoder passwordEncoder;
	private final IUserDetailsRepository userDetailsRepository;

	@Autowired
	public BlueMotorsApiApplication(PasswordEncoder passwordEncoder, IUserDetailsRepository userDetailsRepository) {
		this.userDetailsRepository = userDetailsRepository;
		this.passwordEncoder = passwordEncoder;
	}*/

	public static void main(String[] args) {
		SpringApplication.run(BlueShopApiApplication.class, args);
	}

	/*@PostConstruct
	protected void init() {
		List<Role> roles = new ArrayList<>();

		roles.add(createRole("USER", "User role"));
		roles.add(createRole("ADMIN", "Admin role"));

		User user = new User();

		user.setUsername("alex");
		user.setEmail("alex@test.com");
		user.setPassword(passwordEncoder.encode("alex123"));
		user.setEnabled(true);
		user.setCreatedAt(new Date());
		user.setRoles(roles);

		userDetailsRepository.save(user);
	}

	private Role createRole(String code, String description) {
		Role role = new Role();

		role.setCode(code);
		role.setDescription(description);

		return role;
	}*/

}
