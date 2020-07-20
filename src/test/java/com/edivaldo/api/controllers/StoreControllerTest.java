package com.edivaldo.api.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.edivaldo.api.entities.Store;
import com.edivaldo.api.services.StoreService;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
public class StoreControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private StoreService storeService;

	private static final String URI_STORE = "/api/stores/";
	private static final Long BAD_ID = Long.valueOf(300);
	private static final Long ID = Long.valueOf(1);
	private static final String NAME_STORE = "ONE STORE BR";

	//@Test
	//@WithMockUser
	public void testBuscaLojaInvalida() throws Exception {
		
		Optional<Store> findById = this.storeService.findById(BAD_ID);
		BDDMockito.given(findById).willReturn(Optional.empty());
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get(URI_STORE + BAD_ID);
		
		ResultActions perform = mvc.perform(mockHttpServletRequestBuilder.accept(MediaType.APPLICATION_JSON));
		
		ResultActions andExpect = perform.andExpect(status().isBadRequest());
		
		ResultActions andExpect2 = andExpect.andExpect(jsonPath("$.errors").value("Loja não encontrada para o ID " + BAD_ID));
		
		//mvc.perform(mockHttpServletRequestBuilder.accept(MediaType.APPLICATION_JSON))
		//		.andExpect(status().isBadRequest())
		//		.andExpect(jsonPath("$.errors").value("Loja não encontrada para o ID " + BAD_ID));
	}

	//@Test
	//@WithMockUser
	public void testBuscaLojavalida() throws Exception {
		BDDMockito.given(this.storeService.findByName(Mockito.anyString()))
				.willReturn(Optional.of(this.obterDados()));

		mvc.perform(MockMvcRequestBuilders.get(URI_STORE + ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.id").value(ID))
				.andExpect(jsonPath("$.data.name", equalTo(NAME_STORE)));
	}

	private Store obterDados() {
		
		return new Store(NAME_STORE);
	}

}
