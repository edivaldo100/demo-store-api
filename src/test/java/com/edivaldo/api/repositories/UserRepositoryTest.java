package com.edivaldo.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.api.entities.Store;
import com.edivaldo.api.entities.User;
import com.edivaldo.api.enums.ProfileEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	private static final String STORE_NAME = "ONE STORE BR";
	
	private static final String NAME = "Hulk";
	private static final String NAME2 = "Homem de ferro";

	@Before
	public void setUp() throws Exception {
		Store store = new Store();
		store.setName(STORE_NAME);
		this.storeRepository.save(store);
		//Store storeReturn = this.storeRepository.findByName(NAME);
		User user = new User(NAME, "hulk@onestore.com", "omaisforte", ProfileEnum.ROLE_USUARIO, store);
		this.userRepository.save(user);
	}
	
	@After
    public final void tearDown() { 
		this.userRepository.deleteAll();
		this.storeRepository.deleteAll();
	}

	@Test
	public void testFindByName() {
		//User findByName = this.userRepository.findByName(NAME);
		Optional<User> findByName = this.userRepository.findByName(NAME);
		assertEquals(NAME, findByName.get().getName());
	}
	
	@Test
	public void testFindByNameNull() {
		//User findByName = this.userRepository.findByName(NAME);
		Optional<User> findByName = this.userRepository.findByName(NAME2);
		assertFalse(findByName.isPresent());
	}

}
