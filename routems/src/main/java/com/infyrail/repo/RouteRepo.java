package com.infyrail.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infyrail.entity.RouteEntity;

@Repository
public interface RouteRepo extends JpaRepository<RouteEntity,Integer>{

	
	Optional<RouteEntity> findBySourceAndDestination(String source,String destination);
	
	@Query(value = "SELECT count(*) FROM route WHERE source = ?1 and destination=?2", nativeQuery = true)
	Long findRows(String source,String destination);
	
}
