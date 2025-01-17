package com.toyotaproje.arizatakipsistemi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toyotaproje.arizatakipsistemi.dao.ArizaRepository;
import com.toyotaproje.arizatakipsistemi.dto.ArizaDto;
import com.toyotaproje.arizatakipsistemi.model.Ariza;

@Service
public class ArizaTakipService {

	private ArizaRepository arizaRepository;
	
	@Autowired
	public void setArizaRepository(ArizaRepository arizaRepository) {
		this.arizaRepository = arizaRepository;
	}
	
	public List<ArizaDto> findAll() {
		List<Ariza> list = arizaRepository.findAll();
		List<ArizaDto> listDto = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			ArizaDto arizaDto = new ArizaDto();
			arizaDto.setAcil(list.get(i).getAcil());
			arizaDto.setAriza(list.get(i).getAriza());
			arizaDto.setId(list.get(i).getId());
			arizaDto.setKayitTarihi(list.get(i).getKayitTarihi());
			arizaDto.setKayitYapan(list.get(i).getKayitYapan());
			arizaDto.setBildiren(list.get(i).getBildiren());
			arizaDto.setBildirenTel(list.get(i).getBildirenTel());
			arizaDto.setYeri(list.get(i).getYeri());
			arizaDto.setShop(list.get(i).getShop());
			arizaDto.setAtanan(list.get(i).getAtanan());
			arizaDto.setIsBitimiAciklama(list.get(i).getIsBitimiAciklama());
			arizaDto.setTamamlanmaTarihi(list.get(i).getTamamlanmaTarihi());
			arizaDto.setKayitTuru(list.get(i).getKayitTuru());
			listDto.add(arizaDto);
		}
		return listDto;
	}
	
	public List<ArizaDto> findKayitTuru(String kayitTuru) {
		List<Ariza> list = arizaRepository.findByKayitTuru(kayitTuru);
		List<ArizaDto> listDto = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			ArizaDto arizaDto = new ArizaDto();
			arizaDto.setAcil(list.get(i).getAcil());
			arizaDto.setAriza(list.get(i).getAriza());
			arizaDto.setId(list.get(i).getId());
			arizaDto.setKayitTarihi(list.get(i).getKayitTarihi());
			arizaDto.setKayitYapan(list.get(i).getKayitYapan());
			arizaDto.setBildiren(list.get(i).getBildiren());
			arizaDto.setBildirenTel(list.get(i).getBildirenTel());
			arizaDto.setYeri(list.get(i).getYeri());
			arizaDto.setShop(list.get(i).getShop());
			arizaDto.setAtanan(list.get(i).getAtanan());
			arizaDto.setIsBitimiAciklama(list.get(i).getIsBitimiAciklama());
			arizaDto.setTamamlanmaTarihi(list.get(i).getTamamlanmaTarihi());
			arizaDto.setKayitTuru(list.get(i).getKayitTuru());
			listDto.add(arizaDto);
		}
		return listDto;
	}

	public ArizaDto findById(Long id) {
		Optional<Ariza> ariza = arizaRepository.findById(id);
		ArizaDto arizaDto = new ArizaDto(
				ariza.get().getId(),ariza.get().getKayitTarihi(),ariza.get().getKayitYapan(),ariza.get().getBildiren(),ariza.get().getBildirenTel(),
				ariza.get().getAriza(),ariza.get().getYeri(),ariza.get().getShop(),ariza.get().getAtanan(),ariza.get().getAcil(),
				ariza.get().getIsBitimiAciklama(),ariza.get().getTamamlanmaTarihi(),ariza.get().getKayitTuru());
		return arizaDto;
	}
	
	public Ariza findAriza(Long id) {
		Optional<Ariza> ariza = arizaRepository.findById(id);
		return ariza.get();
	}

	public void createAriza(Ariza ariza) {
		arizaRepository.save(ariza);
	}

	public void update(Ariza ariza) {
		arizaRepository.save(ariza);
	}

	public void deleteAriza(Long id) {
		arizaRepository.deleteById(id);
	}

}
