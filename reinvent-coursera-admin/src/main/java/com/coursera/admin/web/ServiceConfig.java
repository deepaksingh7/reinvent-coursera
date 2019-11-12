package com.coursera.admin.web;

import org.springframework.context.annotation.Bean;

public class ServiceConfig {
	
	// Create a bean to register destroy method where we can shutdown the 
	  // httpconnection objects left open by AWS SDK
	  // @return AmazonDynamoDBClient
	  //
	  @Bean
	  public AwsIdleConnectionShutdownBean awsIdleConnectionShutdownBean() {
	      return new AwsIdleConnectionShutdownBean();
	  }

}
