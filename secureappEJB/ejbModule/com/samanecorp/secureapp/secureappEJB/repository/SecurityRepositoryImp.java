package com.samanecorp.secureapp.secureappEJB.repository;

import java.util.Optional;

import com.samanecorp.secureapp.secureappEJB.entities.AccountUserEntity;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SecurityRepositoryImp extends RepositoryImpl<AccountUserEntity> implements ISecurityRepository {

	@Override
	public Optional<AccountUserEntity> login(String email, String pwd) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<AccountUserEntity> cr = cb.createQuery(AccountUserEntity.class);
		Root<AccountUserEntity> user= cr.from(AccountUserEntity.class);
		
		// predicate pour la clause WHERE
		
		Predicate predicateEmail = cb.equal(user.get("email"),email);
		Predicate predicatePwd = cb.equal(user.get("password"),pwd);
		Predicate predicateFinal = cb.and(predicateEmail, predicatePwd);
		cr.select(user);
		cr.where(predicateFinal);
		return Optional.ofNullable(this.em.createQuery(cr).getSingleResult());
	}

}
