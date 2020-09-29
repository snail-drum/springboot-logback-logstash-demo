package com.snaildrum.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void bar() {
		String url = "http://localhost:" + port + "/foo/bar";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>("{\"name\":\"test\",\"age\":18}", headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		Assert.assertEquals("a", responseEntity.getBody());
	}

	@Test
	void person() {
		String url = "http://localhost:" + port + "/foo/person";
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("name", "testPerson");
		param.add("age", 20);
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param, null);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		Assert.assertEquals("b", responseEntity.getBody());
	}

	@Test
	void personNameQuery() {
		String url = "http://localhost:" + port + "/foo/person/zhangsan";
		ResponseEntity<Person> entity = restTemplate.getForEntity(url, Person.class);
		Person person = entity.getBody();
		Assert.assertEquals("zhangsan", person.getName());
	}

	@Test
	void upload() {
		String url = "http://localhost:" + port + "/foo/upload";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String img = "9cb45be942a0d0323bd28855f3c09ee7.png";

		FileSystemResource file = new FileSystemResource(new File(classLoader.getResource(img).getFile()));
		MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
		param.add("file", file);
		param.add("name", "zhangsan");
		HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(param);
		ResponseEntity<String> entity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
		Assert.assertEquals("success", entity.getBody());
	}

}
