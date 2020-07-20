package com.edivaldo.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.api.entities.User;
import com.edivaldo.api.repositories.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;


	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.userRepository.save(Mockito.any(User.class))).willReturn(new User());
		BDDMockito.given(this.userRepository.findOne(Mockito.anyLong())).willReturn(new User());
		BDDMockito.given(this.userRepository.findByEmail(Mockito.anyString())).willReturn(new User());
		//BDDMockito.given(this.userRepository.findByName(Mockito.anyString())).willReturn(new Optional<User>().get());
	}

	@Test
	public void testPersistir() {
		User user = this.userService.persistir(new User());

		assertNotNull(user);
	}


	@Test
	public void testFindByEmail() {
		Optional<User> user = this.userService.findByEmail("email@email.com");

		assertTrue(user.isPresent());
	}
}
