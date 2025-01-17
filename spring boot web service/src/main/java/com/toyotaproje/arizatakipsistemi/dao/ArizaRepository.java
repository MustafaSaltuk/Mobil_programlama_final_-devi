package com.toyotaproje.arizatakipsistemi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toyotaproje.arizatakipsistemi.model.Ariza;

public interface ArizaRepository extends JpaRepository<Ariza,Long>{
	
	List<Ariza> findByKayitTuru(String kayitTuru);
	
}
