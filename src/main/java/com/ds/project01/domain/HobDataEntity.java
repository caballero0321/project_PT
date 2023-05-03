package com.ds.project01.domain;





import com.ds.project01.dto.HobDataDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@IdClass(HobDataPK.class)
@Table(name="hobbydata_tb")
public class HobDataEntity {
	

	@Id
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private UserEntity userEntity; 
	
	
	@Id
	@ManyToOne
	@JoinColumn(name = "hobby_cd")
	private HobEntity hobbyEntity;
	
	
	public static HobDataEntity toHobbyDataEntity(HobDataDto dto) {
		HobDataEntity hdEntity = new HobDataEntity();
		UserEntity userEntity = new UserEntity();
		HobEntity hobEntity = new HobEntity();
		
		userEntity.setUserId(dto.getUserId());
		hobEntity.setHobbyCd(dto.getHobbyCd());
		hdEntity.setUserEntity(userEntity);
		hdEntity.setHobbyEntity(hobEntity);
		
		System.out.println(hdEntity);
		
		return hdEntity;
	}
}