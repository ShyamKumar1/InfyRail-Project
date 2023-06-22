package com.infyrail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infyrail.DTO.TrainDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

@Service
public class RouteHystrixService {
	
	@Autowired
	RestTemplate template;
	@HystrixCommand
	public Future<Object> deleteTrains(final Integer routeId,final Integer id) {
		return new AsyncResult<Object>() {
			@Override
			public Object invoke() {
				template.delete("http://TRAINMS/"+routeId+"/"+id);
				return null;
			}
		};
	}
	
	
	@HystrixCommand
	public Future<List<TrainDTO>> getTrains(final Integer routeId)
	{
		return new AsyncResult<List<TrainDTO>>() {
		@Override
		public List<TrainDTO> invoke(){
			@SuppressWarnings("unchecked")
			List<TrainDTO> tdto= new ArrayList<>(template.getForObject("http://TRAINMS/route/"+routeId, List.class));
			return tdto;
		}
		};
		
	}
	
	@HystrixCommand
	public Future<Object> postTrains(final Integer routeId,TrainDTO trainDTO) {
		return new AsyncResult<Object>() {
			@Override
			public Object invoke() {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				HttpEntity<TrainDTO> entity = new HttpEntity<>(trainDTO, headers);
				template.postForObject("http://TRAINMS/route/"+routeId,entity,Void.class);
				return null;
			}
		};
	}
	
	
}

