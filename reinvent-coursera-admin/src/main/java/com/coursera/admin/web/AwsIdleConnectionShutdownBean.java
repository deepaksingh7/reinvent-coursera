package com.coursera.admin.web;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AwsIdleConnectionShutdownBean {

  private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AwsIdleConnectionShutdownBean.class.getName());
  
  @PreDestroy
  private void cleanUp() {
    
      try {
          // Shutting down AWS IdleConnectionReaper thread...
          LOGGER.info("Shutting down AWS IdleConnectionReaper thread ");
          com.amazonaws.http.IdleConnectionReaper.shutdown();
      } catch (Throwable t) {
          // log error
          LOGGER.error("Shutting down AWS IdleConnectionReaper thread failed.");
      }
  }

}