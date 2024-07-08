package com.samanecorp.secureapp.secureappEJB.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.secureappEJB.dto.AccountUserDto;
import com.samanecorp.secureapp.secureappEJB.mapper.AccountUserMapper;
import com.samanecorp.secureapp.secureappEJB.repository.ISecurityRepository;

import jakarta.ejb.EJB;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityNotFoundException;
@LocalBean
@Stateless
public class SecurityServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
	@EJB
	private ISecurityRepository loginRepository;
	
	public Optional<AccountUserDto> login(String email,String password){
		logger.info("Tentative email :{} pwd : {}", email,password);
		
		return loginRepository.login(email,password)
				.map(user->{
					AccountUserDto accountUserDto = AccountUserMapper.accountUserEntityToAccountUserDto(user);
					logger.info("infos incorrect");
					return Optional.of(accountUserDto);
				}).orElseThrow( () -> new EntityNotFoundException("infos incorrect."));
	}
	public int save(AccountUserDto accountUserDto) {
		return loginRepository.add(AccountUserMapper.accountUserDtoToAccountUserEntity(accountUserDto));
	}
}
