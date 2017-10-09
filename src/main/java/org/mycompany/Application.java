/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mycompany;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@SpringBootApplication
public class Application {

    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipart = new CommonsMultipartResolver();
        multipart.setMaxUploadSize(3 * 1024 * 1024);
        return multipart;
    }

    @Bean
    @Order(0)
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
    
    @Bean
    public ServletRegistrationBean camelServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/camel/*");
        registration.setName("CamelServlet");
        return registration;
    }

}
