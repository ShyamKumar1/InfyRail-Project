package com.infyrail.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infyrail.DTO.RouteDTO;
import com.infyrail.DTO.TrainDTO;
import com.infyrail.entity.RouteEntity;
import com.infyrail.repo.RouteRepo;

@Service
public class RouteService {
	
	@Autowired
	RouteRepo routeRepo;
	public Integer addRoute(RouteDTO routeDTO) {
		Long count=routeRepo.findRows(routeDTO.getSource(), routeDTO.getDestination());
		if(count==0) {
			RouteEntity en=RouteDTO.change(routeDTO);
			routeRepo.saveAndFlush(en);
			return en.getId();
		}
		return null;
	
	}
	
	public RouteDTO fetchTrain(String source,String destination) {
		Optional<RouteEntity> ent=routeRepo.findBySourceAndDestination(source, destination);
		if(ent.isPresent()) {
			return  RouteDTO.changeTo(ent.get());
		}
		return null;
	}
	public RouteDTO searchRoute(Integer id) {
		Optional<RouteEntity> ent= routeRepo.findById(id);
		if(ent.isPresent()) {
			RouteDTO dto= RouteDTO.changeTo(ent.get());
			return dto;
		}
		return null;
	}
	public RouteDTO updateRoute(String id,String source,String destination) {
		Optional<RouteEntity> ent=routeRepo.findById(Integer.parseInt(id));
		if(ent.isPresent()) {
			RouteEntity entity=ent.get();
			entity.setSource(source);
			entity.setDestination(destination);
			routeRepo.saveAndFlush(entity);
			RouteDTO routeDTO=RouteDTO.changeTo(entity);
			return routeDTO;
		}
		return null;
	}
	
	
}
