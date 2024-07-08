package com.samanecorp.secureapp.secureappEJB.mapper;

import com.samanecorp.secureapp.secureappEJB.dto.AccountUserDto;
import com.samanecorp.secureapp.secureappEJB.entities.AccountUserEntity;

public class AccountUserMapper {
	public static AccountUserEntity accountUserDtoToAccountUserEntity(AccountUserDto accountUserDto) {
		 AccountUserEntity accountUserEntity = new AccountUserEntity();
		 accountUserEntity.setId(accountUserDto.getId());
		 accountUserEntity.setEmail(accountUserDto.getEmail());
		 accountUserEntity.setPassword(accountUserDto.getPassword());
		return accountUserEntity;
		
	}
	
	public static AccountUserDto accountUserEntityToAccountUserDto(AccountUserEntity accountUserEntity) {
		AccountUserDto accountUserDto = new AccountUserDto();
		accountUserDto.setId(accountUserEntity.getId());
		accountUserDto.setEmail(accountUserEntity.getEmail());
		accountUserDto.setPassword(accountUserEntity.getPassword());
		return accountUserDto;
		
	}

}
