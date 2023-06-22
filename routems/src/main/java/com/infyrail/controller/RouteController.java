package com.infyrail.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infyrail.DTO.RouteDTO;
import com.infyrail.DTO.TrainDTO;
import com.infyrail.service.RouteHystrixService;
import com.infyrail.service.RouteService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin
public class RouteController {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	RouteHystrixService hystrixService;
	@Autowired
	RouteService routeService;
	
//	@HystrixCommand(fallbackMethod = "createRouteFallBack")
	@PostMapping(value = "/route",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createRoute(@RequestBody RouteDTO routeDTO) {
		
		
		List<TrainDTO> list=routeDTO.getTrainList();
		Integer id= routeService.addRoute(routeDTO);
		if(id==null) {
			RouteDTO routeDTO1=routeService.fetchTrain(routeDTO.getSource(), routeDTO.getDestination());
			id=routeDTO1.getId();
		}
		for(TrainDTO train:list) {
			hystrixService.postTrains(id,train);
		}
	}
	public void createRouteFallBack(@RequestBody RouteDTO routeDTO) {
		logger.info("something went wrong while adding route in database {}", routeDTO);
	}
	@GetMapping(value = "/route/{routeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public RouteDTO fetchRoute(@PathVariable("routeId") Integer id) throws InterruptedException, ExecutionException {
		RouteDTO routeDTO=routeService.searchRoute(id);
		Future<List<TrainDTO>> ftdto= hystrixService.getTrains(id);
		routeDTO.setTrainList(ftdto.get());
		return routeDTO;
	}
	@GetMapping(value = "/route/trains",produces = MediaType.APPLICATION_JSON_VALUE)
	public RouteDTO fetchTrain(@RequestParam String source,@RequestParam String destination) throws InterruptedException, ExecutionException{
		RouteDTO routeDTO=routeService.fetchTrain(source, destination);
		Integer id=routeDTO.getId();
		Future<List<TrainDTO>> ftdto= hystrixService.getTrains(id);
		routeDTO.setTrainList(ftdto.get());
		return routeDTO;
	}
	@PutMapping(value = "/route/{routeId}")
	public void updateRoute(@PathVariable("routeId") String id,@MatrixVariable Map<String, String> map) 						  
	{
		routeService.updateRoute(id,map.get("source"),map.get("destination"));
	}
	
	@DeleteMapping(value = "/route/{routeId}/{trainId}")
	public void deleteTrain(@PathVariable("routeId") Integer rid,@PathVariable("trainId") Integer id) {
		hystrixService.deleteTrains(rid,id);
	}
	@PutMapping(value = "/route/{routeId}/trains",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addTrain(@PathVariable("routeId") Integer id,@RequestBody TrainDTO trainDTO) {
			hystrixService.postTrains(id,trainDTO);
	}
	
	
}
