package com.infyrail.DTO;


public class TrainDTO {

	Integer id;
	String trainName;
	String arrivalTime;
	String departureTime;
	Double fare;
	Integer routeId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	@Override
	public String toString() {
		return "TrainDTO [id=" + id + ", trainName=" + trainName + ", arrivalTime=" + arrivalTime + ", departureTime="
				+ departureTime + ", fare=" + fare + ", routeId=" + routeId + "]";
	}
	
}
