package com.toyotaproje.arizatakipsistemi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.toyotaproje.arizatakipsistemi.model.Personel;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Long> {
	Personel findByUsername(String username);
	
	boolean existsByUsername(String username);
}
//JpaRepository miraz alıyor.Bu sayede save() delete() gibi methodlar predefined(önceden tanımlanmış) geliyor.
//Ek olarak findByUsername() ile sql yazmadan ilgili kaydı döndürebiliyoruz.