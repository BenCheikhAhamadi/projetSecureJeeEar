package com.samanecorp.secureapp.secureappEJB.repository;

import java.util.Optional;

import com.samanecorp.secureapp.secureappEJB.entities.AccountUserEntity;

public interface ISecurityRepository extends Repository<AccountUserEntity>{
 public Optional<AccountUserEntity> login(String email, String pwd);
}
