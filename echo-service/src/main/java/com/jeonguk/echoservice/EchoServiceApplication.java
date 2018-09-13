package com.jeonguk.echoservice;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootApplication
public class EchoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EchoServiceApplication.class, args);
	}

	@Bean
	public GsonHttpMessageConverter gsonHttpMessageConverter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
		converter.setGson(gsonBuilder.create());
		return converter;
	}

	@RestController
	@RequestMapping("/api/echo")
	class EchoController {

		@GetMapping("/one")
		public List<Post> getPosts(@RequestHeader("TEST-HEADER") String header) {
			log.info("TEST-HEADER {}", header);
			return getPostList();
			//throw new RuntimeException(header);
		}

		private List<Post> getPostList() {
			Post post1 = new Post();
			post1.setId(1L);
			post1.setTitle("post title1");
			post1.setContent("post content1");
			post1.setUserName("user1");
			post1.setCreatedAt("2018-09-11 11:11:11");
			Post post2 = new Post();
			post2.setId(2L);
			post2.setTitle("post title2");
			post2.setContent("post content2");
			post2.setUserName("user2");
			post2.setCreatedAt("2018-09-11 12:12:12");
			return Arrays.asList(post1, post2);
		}
	}

	@Data
	class Post {
		private Long id;
		private String title;
		private String content;
		private String userName;
		private String createdAt;
	}
}
