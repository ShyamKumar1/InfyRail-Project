package com.infyrail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infyrail.DTO.TrainDTO;
import com.infyrail.entity.TrainEntity;
import com.infyrail.repo.TrainRepo;

@Service
@Transactional
public class TrainService {

	
	@Autowired
	TrainRepo trainRepo;
	
	public void addTrain(Integer id,TrainDTO trainDTO) {
		trainDTO.setRouteId(id);
		trainRepo.saveAndFlush(TrainDTO.changesTo(trainDTO));
	}
	
	public List<TrainDTO> fetchTrains(Integer routeId){
		
		List<TrainEntity> ents=trainRepo.findByRouteId(routeId);
		List<TrainDTO> dto=new ArrayList<>();
		for(TrainEntity ent:ents) {
			dto.add(TrainDTO.changes(ent));
		}
		return dto;
	}
	
	public void delete(Integer id,Integer rid) {
		trainRepo.deleteByIdAndRouteId(id,rid);		
	}
	public String updateTrain(Integer id, String fare) {
		
		Optional<TrainEntity> en=trainRepo.findById(id);
		try {
			TrainEntity tent=en.get();
			tent.setFare(Double.parseDouble(fare));
			trainRepo.saveAndFlush(tent);
			return "Success: Fare gets Updated";
		} catch (Exception e) {
			return "Failed: Could not update the fare";
			// TODO: handle exception
		}
	}
	
}
