package com.ds.project01.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.HobDataEntity;
import com.ds.project01.domain.HobEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.HobDataDto;
import com.ds.project01.dto.UserDto;



@Service
public class PT_Service { 
	
	RestTemplate restTemplate = new RestTemplate();
	String btUrl = "http://localhost:8087";
	
	
	public List<UserEntity> adminList(String searchKeyword){
		String url;
		if(searchKeyword != null)
		url = btUrl + "/bt/list?searchKeyword="+searchKeyword;
		else
		url = btUrl + "/bt/list";
		ResponseEntity<List<UserEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<UserEntity>>() {});
		
		return response.getBody();
	}
	
	public void insert(UserDto dto) {
		String url = btUrl + "/bt/userSave";
        restTemplate.postForObject(url, dto, UserDto.class);
	}
	
	public void hobbyDataInsert(HobDataDto hdDto) {
		String url = btUrl + "/bt/hobbyDataSave";
		restTemplate.postForObject(url, hdDto, HobDataDto.class);
	}
	public void delete(UserDto dto) {
		String url = btUrl + "/bt/delete";
		restTemplate.postForObject(url, dto, UserDto.class);
	}
	
	public UserEntity view(String userId) {
		String url = btUrl + "/bt/view?userId="+userId;
		ResponseEntity<UserEntity> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<UserEntity>() {});
		
		return response.getBody();
	}
	
	public List<DeptEntity> deptList(){
		String url = btUrl + "/bt/deptList";
		ResponseEntity<List<DeptEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<DeptEntity>>() {});
		
		return response.getBody();
	}
//	
	public List<HobEntity> hobbyList(){
		String url = btUrl + "/bt/hobbyList";
		ResponseEntity<List<HobEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<HobEntity>>() {});
		
		return response.getBody();
	}
	
	public List<HobDataEntity> HobbyDataView(String userId) {
		String url = btUrl + "/bt/hobbyDataList?userId="+userId;
		ResponseEntity<List<HobDataEntity>> response= restTemplate.exchange(url, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<HobDataEntity>>() {});
		
		return response.getBody();
	}
}