package com.snaildrum.demo;

import com.snaildrum.demo.filter.MyFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@SpringBootApplication
@RestController
@RequestMapping("/foo")
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostMapping("/person")
	public ResponseEntity<String> person(Person person) {
		LOGGER.info("add person {}", person);
		return new ResponseEntity<>("b", HttpStatus.OK);
	}

	@PostMapping("/bar")
	public ResponseEntity<String> bar(@RequestBody Person person) {
		LOGGER.info("add person {}", person);
		return new ResponseEntity<>("a", HttpStatus.OK);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> upload(MultipartFile file, String name) {
		LOGGER.info("{} upload file {} ", name, file.getOriginalFilename());
		return new ResponseEntity<>("success", HttpStatus.OK);
	}

	@GetMapping("/person/{name}")
	public ResponseEntity<Person> person(@PathVariable String name) {
		LOGGER.info("query person {}", name);
		Person person = new Person();
		person.setName(name);
		person.setAge(19);
		return new ResponseEntity<>(person, HttpStatus.OK);
	}

	@Bean
	public MyFilter createFilter() {
		return new MyFilter();
	}

/*	@Bean
	public TeeFilter createTeeFilter() {
		return new TeeFilter();
	}*/
}








