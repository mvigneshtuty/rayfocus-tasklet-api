package com.rayfocus.api.tasklet.reminder.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.rayfocus.api.tasklet.reminder.db.repository")
public class DynamoDBConfig {

	@Autowired
	private ServiceConfig serviceConfig;

	@Bean
	public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials) {
		@SuppressWarnings("deprecation")
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(awsCredentials);
		return amazonDynamoDB;
	}

	@Bean
	public AWSCredentials awsCredentials() {
		return new BasicAWSCredentials(serviceConfig.getAwsAccessKey(), serviceConfig.getAwsSecretKey());
		//return new BasicAWSCredentials(serviceConfig.getAwsAccessKey(), serviceConfig.getAwsSecretKey());
	}

}
