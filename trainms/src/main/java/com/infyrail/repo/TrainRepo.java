package com.infyrail.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infyrail.entity.TrainEntity;
@Repository
public interface TrainRepo extends JpaRepository<TrainEntity, Integer>{

	List<TrainEntity> findByRouteId(Integer routeId);
	Long deleteByIdAndRouteId(Integer id,Integer routeId);
	
}
