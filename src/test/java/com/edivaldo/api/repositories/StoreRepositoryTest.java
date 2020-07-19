package com.edivaldo.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.api.entities.Store;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StoreRepositoryTest {
	
	@Autowired
	private StoreRepository storeRepository;
	
	private static final String NAME = "ONE STORE BR";

	@Before
	public void setUp() throws Exception {
		
		Store store = new Store();
		store.setName(NAME);
		this.storeRepository.save(store);
	}
	
	@After
    public final void tearDown() { 
		this.storeRepository.deleteAll();
	}

	@Test
	public void testFindByName() {
		Store store = this.storeRepository.findByName(NAME);
		
		assertEquals(NAME, store.getName());
	}

}
