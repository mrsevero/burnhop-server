package br.com.burnhop;

import java.sql.Date;
import java.sql.Timestamp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.burnhop.model.Users;
import br.com.burnhop.model.Login;
import br.com.burnhop.model.Dto.CreatedLoginDto;
import br.com.burnhop.model.Dto.CreatedUserDto;
import br.com.burnhop.model.Dto.UserDto;
import br.com.burnhop.api.resource.UsersResource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;


import org.springframework.boot.test.mock.mockito.MockBean;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;


import br.com.burnhop.repository.LoginRepository;
import br.com.burnhop.repository.UsersRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BackendApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    
	@Test
	void contextLoads() {

	}

	CreatedUserDto makeUser(){
		String name = "Test";
		String username = "test";
		String data_nasc = "2000-01-01";
		String email = "test1@test.com";
		String password = "12345";
	
		CreatedUserDto createdUserDto = new CreatedUserDto();
		CreatedLoginDto createdLoginDto = new CreatedLoginDto();

		createdLoginDto.setEmail(email);
		createdLoginDto.setPassword(password);

		createdUserDto.setLogin(createdLoginDto);
		createdUserDto.setName(name);
		createdUserDto.setUsername(username);
		createdUserDto.setData_nasc(data_nasc);

		return createdUserDto;
    }

	@Test
	void testCreateUserRequest() throws Exception{

		CreatedUserDto createdUserDto = makeUser();

		mockMvc.perform(post("/users")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(createdUserDto)))
				.andExpect(status().isOk());
			
		mockMvc.perform(post("/users")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(createdUserDto)))
				.andExpect(status().isConflict());
	}
}
