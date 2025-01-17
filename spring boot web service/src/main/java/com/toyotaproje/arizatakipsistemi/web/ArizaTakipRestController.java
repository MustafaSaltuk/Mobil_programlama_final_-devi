package com.toyotaproje.arizatakipsistemi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toyotaproje.arizatakipsistemi.dto.ArizaDto;
import com.toyotaproje.arizatakipsistemi.model.Ariza;
import com.toyotaproje.arizatakipsistemi.service.ArizaTakipService;

@RestController
@RequestMapping("/ariza")
public class ArizaTakipRestController {
	@Autowired
	private ArizaTakipService arizaTakipService;
	
	@RequestMapping(method=RequestMethod.GET,value="/findall")
	public ResponseEntity<List<ArizaDto>> getAll(){
		List<ArizaDto> arizalar = arizaTakipService.findAll();
		return ResponseEntity.ok(arizalar);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/findbykayitturu")
	public ResponseEntity<List<ArizaDto>> findByKullanimTuru(@RequestParam("kayitturu") String kayitTuru){
		List<ArizaDto> arizalar = arizaTakipService.findKayitTuru(kayitTuru);
		return ResponseEntity.ok(arizalar);
	}

	@RequestMapping(method=RequestMethod.GET,value="/findbyid/{id}")
	public ResponseEntity<ArizaDto> findAriza(@PathVariable("id") Long id){
		try {
			ArizaDto arizaDto = arizaTakipService.findById(id);
			return ResponseEntity.ok(arizaDto);
		}catch(Exception ex) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method=RequestMethod.POST,value="/create")
	public ResponseEntity<?> createAriza(@RequestBody Ariza ariza){
		try {
			arizaTakipService.createAriza(ariza);
			return ResponseEntity.ok().build();
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/update/{id}")
	public ResponseEntity<?> updateAriza(@PathVariable("id") Long id,@RequestBody Ariza arizaRequest){
		try {
			Ariza ariza = arizaTakipService.findAriza(id);
			ariza.setKayitTarihi(arizaRequest.getKayitTarihi());
			ariza.setKayitYapan(arizaRequest.getKayitYapan());
			ariza.setBildiren(arizaRequest.getBildiren());
			ariza.setBildirenTel(arizaRequest.getBildirenTel());
			ariza.setAriza(arizaRequest.getAriza());
			ariza.setYeri(arizaRequest.getYeri());
			ariza.setShop(arizaRequest.getShop());
			ariza.setAtanan(arizaRequest.getAtanan());
			ariza.setAcil(arizaRequest.getAcil());
			ariza.setIsBitimiAciklama(arizaRequest.getIsBitimiAciklama());
			ariza.setTamamlanmaTarihi(arizaRequest.getTamamlanmaTarihi());
			ariza.setKayitTuru(arizaRequest.getKayitTuru());
			arizaTakipService.update(ariza);
			return ResponseEntity.ok().build();
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/delete/{id}")
	public ResponseEntity<?> deleteAriza(@PathVariable("id") Long id){
		try {
			arizaTakipService.deleteAriza(id);
			return ResponseEntity.ok().build();
		}catch(Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
