package com.salesianostriana.dam.cemapp.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockBodyContent;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesianostriana.dam.cemapp.controller.PsmController;
import com.salesianostriana.dam.cemapp.service.PSMService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PsmController.class)
public class PsmControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PSMService psmService;

	@Test
	@WithMockUser(username = "admin@admin.com", password = "admin", roles = "ADMIN")
	public void subidaExistosa() throws Exception {

		ResultMatcher ok = MockMvcResultMatchers.status().isCreated();

		String fileName = "test.txt";
		File file = new File(fileName);
		// delete if exits
		file.delete();

		MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file", fileName, "text/plain",
				"test data".getBytes());
		
		
		
		
/*
		mockMvc.perform(post("/uploadPsm") //
                .param("file", mockMultipartFile) //
                .param("idAnyoEscolar", "7")
                .param("idColegio", "2")
                .param("evaluacion")
                .param("idPuntoControl", null)//                
                .contentType(MediaType.APPLICATION_FORM_URLENCODED) //
                .accept(MediaType.APPLICATION_JSON)) //
        .andExpect(status().isOk()) //
        .andReturn();*/
	}
}
