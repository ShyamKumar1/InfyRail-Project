package com.infyrail.controller;

import java.util.List;

import javax.ws.rs.DELETE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infyrail.DTO.TrainDTO;
import com.infyrail.service.TrainService;


@RestController
@CrossOrigin
public class TrainController {
	@Autowired
	TrainService trainService;
	@PostMapping(value = "/route/{routeId}",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createTrain(@PathVariable("routeId") Integer id,@RequestBody TrainDTO trainDTO) {
		trainService.addTrain(id,trainDTO);
	}	
	
	
	@GetMapping(value = "/route/{routeId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TrainDTO> searchTrains(@PathVariable("routeId") Integer id){
		return trainService.fetchTrains(id);	
	}
	
	@DeleteMapping(value="/{routeId}/{trainId}")
	public void deleteTrain(@PathVariable("trainId") Integer id,@PathVariable("routeId") Integer rid) {
		trainService.delete(id,rid);
	}
	@PutMapping(value = "/trains/{trainId}")
	public String updateTrain(@PathVariable("trainid") Integer id,@RequestParam String fare)  								  
	{
		System.out.println("fare "+fare);
		return trainService.updateTrain(id,fare);
	}
	
}
