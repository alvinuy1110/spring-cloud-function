/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.function.adapter.openwhisk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry;
import org.springframework.cloud.function.context.catalog.FunctionInspector;
import org.springframework.cloud.function.context.catalog.InMemoryFunctionCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import org.springframework.messaging.converter.CompositeMessageConverter;

import java.util.function.Function;

/**
 * @author Mark Fisher
 */
// @checkstyle:off
@SpringBootApplication
@EnableConfigurationProperties(FunctionProperties.class)
public class OpenWhiskActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenWhiskActionApplication.class, args);
	}


	@Bean
	public Function<String, String> uppercase() {
		return value -> new StringBuilder(value).toString().toUpperCase();
	}


	// NOTE had to register this as getting an error
//	No qualifying bean of type 'org.springframework.cloud.function.context.catalog.FunctionInspector'
	// THIS IS A HACK FOR NOW
	@Bean
	@Primary
	FunctionInspector functionInspector(ConversionService conversionService, @Nullable CompositeMessageConverter messageConverter) {
		return new BeanFactoryAwareFunctionRegistry(conversionService, messageConverter);
//		return new InMemoryFunctionCatalog();
	}
}
// @checkstyle:on
