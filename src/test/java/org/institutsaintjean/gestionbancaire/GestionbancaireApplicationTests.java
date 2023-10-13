package org.institutsaintjean.gestionbancaire;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class GestionbancaireApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test
	public void encodeEncryptUserPassword(){
		String password = "qsdfgh";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String testPasswordEncoded = passwordEncoder.encode(password);
		System.out.println("encoded password = "+testPasswordEncoded);
	}
}
