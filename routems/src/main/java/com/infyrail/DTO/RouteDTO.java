package com.infyrail.DTO;

import java.util.ArrayList;
import java.util.List;


import com.infyrail.entity.RouteEntity;




public class RouteDTO {
	Integer id;
	String source;
	String destination;
	List<TrainDTO> trainList=new ArrayList<>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public List<TrainDTO> getTrainList() {
		return trainList;
	}
	public void setTrainList(List<TrainDTO> trainList) {
		this.trainList = trainList;
	}
	@Override
	public String toString() {
		return "RouteDTO [id=" + id + ", source=" + source + ", destination=" + destination + ", trainList=" + trainList
				+ "]";
	}
	public static RouteDTO changeTo(RouteEntity entity) {
		RouteDTO dto = new RouteDTO();
		dto.setDestination(entity.getDestination());
		dto.setId(entity.getId());
		dto.setSource(entity.getSource());
		return dto;
	}
	public static RouteEntity change(RouteDTO dto) {
		RouteEntity entity = new RouteEntity();
		entity.setDestination(dto.getDestination());
		entity.setId(dto.getId());
		entity.setSource(dto.getSource());
		return entity;
	}
	
}
