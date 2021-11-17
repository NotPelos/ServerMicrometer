package com.example.ServerMicrometer.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;


@Component
@Endpoint(id = "status")
@RestController
public class StatusEndpoint {

	
	Counter counter;
	
	public StatusEndpoint(MeterRegistry registry)
	{
		this.counter = Counter.builder("Status.call").description("Total cambios status").register(registry);
	}
	
	
List<String> statusList = new ArrayList<>();
	
	
	@ReadOperation
	public List<String> status()
	{
		return statusList;
	}
	
	@WriteOperation()
	public void write(@Selector String statusAdd)
	{
		System.out.println(statusAdd);
		counter.increment();
		statusList.add(statusAdd);
	}
	
	@DeleteOperation
	public void delete(@Selector String statusDelete)
	{
		statusList.remove(statusDelete);
	}
}
