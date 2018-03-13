package com.fts.pbp.migrator.bond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController("bond")
@Api
public class BondController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BondController.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@GetMapping("/{bond-prefix}/{env}/{rabbitmq-suffix}")
	@ResponseStatus(value = HttpStatus.OK)
	public void migrate(@PathVariable("bond-prefix") String bondPrefix, @PathVariable("env") String environment,
			@PathVariable("rabbitmq-suffix") String rabbitMqSuffix) {
		LOGGER.info("String migration {}:{}:{}", bondPrefix, environment, rabbitMqSuffix);
		rabbitTemplate.convertAndSend(BondQueueConfig.queueName, "Hello from RabbitMQ!");
	}

}
