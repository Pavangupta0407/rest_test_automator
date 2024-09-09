package com.rta.junit.dto;

import java.util.List;

import lombok.Data;

@Data
public class JunitInputDto {

	private String comment;
	private String restClass;
	private String testClass;
	private String requestMethod;
	private Integer expectedStatus;
	private Object expectedResponseBody;
	private String expectedContentType;
	private String requestEndpoint;
	private String contentType;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRestClass() {
		return restClass;
	}

	public void setRestClass(String restClass) {
		this.restClass = restClass;
	}

	public String getTestClass() {
		return testClass;
	}

	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Integer getExpectedStatus() {
		return expectedStatus;
	}

	public void setExpectedStatus(Integer expectedStatus) {
		this.expectedStatus = expectedStatus;
	}

	public Object getExpectedResponseBody() {
		return expectedResponseBody;
	}

	public void setExpectedResponseBody(Object expectedResponseBody) {
		this.expectedResponseBody = expectedResponseBody;
	}

	public String getExpectedContentType() {
		return expectedContentType;
	}

	public void setExpectedContentType(String expectedContentType) {
		this.expectedContentType = expectedContentType;
	}

	public String getRequestEndpoint() {
		return requestEndpoint;
	}

	public void setRequestEndpoint(String requestEndpoint) {
		this.requestEndpoint = requestEndpoint;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Object getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Object queryParams) {
		this.queryParams = queryParams;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String getEntityData() {
		return entityData;
	}

	public void setEntityData(String entityData) {
		this.entityData = entityData;
	}

	public String getEntityUnit() {
		return entityUnit;
	}

	public void setEntityUnit(String entityUnit) {
		this.entityUnit = entityUnit;
	}

	public String getAcceptInput() {
		return acceptInput;
	}

	public void setAcceptInput(String acceptInput) {
		this.acceptInput = acceptInput;
	}

	public List<Object> getMockMethods() {
		return mockMethods;
	}

	public void setMockMethods(List<Object> mockMethods) {
		this.mockMethods = mockMethods;
	}

	public List<MockRestDto> getMockRestList() {
		return mockRestList;
	}

	public void setMockRestList(List<MockRestDto> mockRestList) {
		this.mockRestList = mockRestList;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String authToken;
	private Object queryParams;
	private Object body;
	private String acceptLanguage;
	private String entityClass;
	private String entityData;
	private String entityUnit;
	private String acceptInput;

	private List<Object> mockMethods = null;
	private List<MockRestDto> mockRestList = null;
	private Boolean enabled;
	private String type = "REST_API_TEST";

}
