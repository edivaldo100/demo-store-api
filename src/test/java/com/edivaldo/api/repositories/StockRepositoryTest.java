package com.edivaldo.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.edivaldo.api.entities.Product;
import com.edivaldo.api.entities.Stock;
import com.edivaldo.api.entities.Store;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
public class StockRepositoryTest {
	
	@Autowired
	private StockRepository stockRepository;
	
	private static final String NAME_PRODUCT = "Celular";
	private static final Long PRICE_PRODUCT = (long) 1000.00;
	
	//@Before
	public void setUp() throws Exception {
		//Product product = new Product(NAME_PRODUCT, PRICE_PRODUCT);
		//Stock stock = new Stock(
		//this.stockRepository.save(stock);
	}
	
	//@After
    public final void tearDown() { 
		this.stockRepository.deleteAll();
	}

	//@Test
	public void testFindAll() {
		List<Stock> stocks = this.stockRepository.findAll();
		
		stocks.forEach(action ->{
			//System.out.println(action.getProduct().getId()+" - "+action.getProduct().getName()+" no preço: "+action.getProduct().getPrice());
			System.out.println("= Total no estoque : "+action.getQuantity());
		});
		
		System.out.println("-------------- Temos "+stocks.size()+" Produto(s) cadastrado(s)");
		assertNotNull(stocks);
	}

}
