package com.edivaldo.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.api.entities.Ordered;
import com.edivaldo.api.entities.Product;
import com.edivaldo.api.entities.ProductItem;
import com.edivaldo.api.entities.Store;
import com.edivaldo.api.entities.User;
import com.edivaldo.api.enums.ProfileEnum;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderedRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private OrderedRepository orderedRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductItemRepository productItemRepository;
	
	private static final String STORE_NAME = "ONE STORE BR";
	
	private static final String NAME = "Hulk";
	private static final String NAME2 = "Homem de ferro";

	@Before
	public void setUp() throws Exception {
		
		Store store = new Store();
		store.setName(STORE_NAME);
		this.storeRepository.save(store);
		User user = new User(NAME, "hulk@onestore.com", NAME);
		User save2 = this.userRepository.save(user);
		
		
		Product product = new Product("super carro", 10000L);
		Product product2 = new Product("Banana", 10000L);
		Product saveproduct = productRepository.save(product);
		
		/*ProductItem productItem = new ProductItem();
		productItem.setProduct(saveproduct);
		productItem.setId(1L);
		
		ProductItem productItem2 = new ProductItem();
		productItem2.setProduct(saveproduct2);
		productItem2.setId(2L);
		
		ProductItem save2productItem = productItemRepository.save(productItem);
		ProductItem save2productItem2 = productItemRepository.save(productItem2);*/
		
		Ordered ordered = new Ordered();
		ordered.setId(1L);
		ordered.setUser(save2);
		
		
		//new ProductItem(1L, orderedSave, save2productItem);
		
		Long id = 1L;
		Ordered orderedSav = this.orderedRepository.save(ordered);
		Product saveproduc = productRepository.save(product2);
		ProductItem productItem3 = new ProductItem(id, orderedSav, saveproduc);
		productItemRepository.save(productItem3);
		
		//Set<ProductItem> productIte = new HashSet<>();
		//productIte.add(save2productItem);
		//productIte.add(save2productItem2);
		
		//ordered.setProductItem(productIte);
		
		
		//Set<ProductItem> productItems = new LinkedHashSet<ProductItem>();
		//productItems.add(save2productItem);
		//ordered.setProductItem(productItems);
		//ordered.setUser(userRepository.findById(1L));
		
		
		System.out.println("------------------------------------");
	}
	
	@After
    public final void tearDown() { 
		//this.userRepository.deleteAll();
	}

	@Test
	public void testFindById() {
		Ordered findById = this.orderedRepository.findById(1L);
		assertNotNull(findById);
	}
	
	@Test
	public void testFindByNameNull() {
		List<Ordered> findAll = this.orderedRepository.findAll();
		
		findAll.forEach(action->{
			System.out.println(action);
		});
		assertNotNull(findAll);
	}

}
