package com.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
		return builder.routes().build();
		/*Function<PredicateSpec, Buildable<Route>> routeFunction=p->p.path("/get")
				.filters(f->f.addRequestHeader("MyHeader", "headerValue")
						.addRequestParameter("paramMy", "myparem"))
				.uri("http://httpbin.org:80");
		return builder.routes().route(routeFunction).build();*/
	}
}
