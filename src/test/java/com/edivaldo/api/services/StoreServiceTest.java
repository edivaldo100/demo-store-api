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

import com.edivaldo.api.entities.Store;
import com.edivaldo.api.repositories.StoreRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class StoreServiceTest {

	@MockBean
	private StoreRepository storeRepository;

	@Autowired
	private StoreService storeService;

	private static final String NAME = "ONE STORE BR";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.storeRepository.findByName(Mockito.anyString())).willReturn(new Store());
		BDDMockito.given(this.storeRepository.save(Mockito.any(Store.class))).willReturn(new Store());
	}

	@Test
	public void testFindStoreByName() {
		Optional<Store> store = this.storeService.findByName(NAME);

		assertTrue(store.isPresent());
	}
	
	@Test
	public void testPersistirEmpresa() {
		Store store = this.storeService.persistir(new Store());

		assertNotNull(store);
	}

}
