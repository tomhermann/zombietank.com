package com.zombietank.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(mode = AdviceMode.ASPECTJ)
public class TaskConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		return taskExecutor();
	}

	@Bean
	public Executor taskExecutor() {
		return new ThreadPoolTaskExecutor();
	}
}
