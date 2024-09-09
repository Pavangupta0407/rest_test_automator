package com.rta.junit;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rta.junit.dto.JunitInputDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestTestAutomator {
	
	private static final Logger logger = LoggerFactory.getLogger(RestTestAutomator.class);
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Parameter(value = 1)
	public JunitInputDto junitInputDto;
	
	@Before
	public void setup() throws Exception {
		if(junitInputDto.getMockMethods() != null && junitInputDto.getMockMethods().size()>0) {
			for(Object methodname : junitInputDto.getMockMethods()) {
				Method method;
				try {
					method = MockUtility.class.getMethod(methodname.toString());
					method.invoke(new MockUtility());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	@Parameterized.Parameters(name = "{index}:{0}")
	public static Collection<Object[]> data() throws IOException {
		Collection<Object[]> params = new ArrayList<>();
		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		Resource[] resource = patternResolver.getResources("classpath*:junits/*.json");
		ArrayList<JunitInputDto> list = new ArrayList<>();
		for(int i=0; i < resource.length; i++) {
			
		}
		return params;
		
		
	}
	
	@Test
	public void test() throws Exception {
		JunitInputDto testcase = junitInputDto;
		try {
			if(testcase.getEnabled() && testcase.getType().equals("REST_API_TESTS")) {
				HttpMethod requestMethod = HttpMethod.valueOf(testcase.getRequestMethod());
				String endPointUrl = createURLWithPort(testcase.getRequestEndpoint());
				MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.request(requestMethod, endPointUrl);
				requestBuilder.accept(testcase.getContentType());
				if(testcase.getBody() != null) {
					requestBuilder.contentType(testcase.getContentType());
					requestBuilder.content(MAPPER.writeValueAsString(testcase.getBody()));
				}
				if(testcase.getAuthToken() != null) {
					requestBuilder.header("authorization", testcase.getAuthToken());
				}
				logger.info("RUNNING TESTCASE : "+ testcase.getComment());
				ResultActions resultActions = this.mockMvc.perform(requestBuilder);
				resultActions.andDo(print()).andExpect(MockMvcResultMatchers.status().is(testcase.getExpectedStatus()));
				if (testcase.getExpectedResponseBody() != null) {
					resultActions.andExpect(MockMvcResultMatchers.content().json(MAPPER.writeValueAsString(testcase.getExpectedResponseBody()),false));
				}
			}
		} catch (Exception e) {
			logger.info("Error in junit test case {}",Arrays.toString(e.getStackTrace()));
		}
		
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:"+ port +uri;
	}

}
