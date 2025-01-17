package com.toyotaproje.arizatakipsistemi.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class ArizaDto {
	private Long id;
	private LocalDateTime kayitTarihi;
	private String kayitYapan;
	private String bildiren;
	private Long bildirenTel;
	private String ariza;
	private String yeri;
	private String shop;
	private String atanan;
	private String acil;
	private String isBitimiAciklama;
	private Date tamamlanmaTarihi;
	private String kayitTuru;
	
	public ArizaDto(Long id, LocalDateTime kayitTarihi, String kayitYapan, String bildiren, Long bildirenTel, String ariza,
			String yeri, String shop, String atanan, String acil, String isBitimiAciklama, Date tamamlanmaTarihi,
			String kayitTuru) {
		super();
		this.id = id;
		this.kayitTarihi = kayitTarihi;
		this.kayitYapan = kayitYapan;
		this.bildiren = bildiren;
		this.bildirenTel = bildirenTel;
		this.ariza = ariza;
		this.yeri = yeri;
		this.shop = shop;
		this.atanan = atanan;
		this.acil = acil;
		this.isBitimiAciklama = isBitimiAciklama;
		this.tamamlanmaTarihi = tamamlanmaTarihi;
		this.kayitTuru = kayitTuru;
	}

	public ArizaDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getKayitTarihi() {
		return kayitTarihi;
	}

	public void setKayitTarihi(LocalDateTime kayitTarihi) {
		this.kayitTarihi = kayitTarihi;
	}

	public String getKayitYapan() {
		return kayitYapan;
	}

	public void setKayitYapan(String kayitYapan) {
		this.kayitYapan = kayitYapan;
	}

	public String getBildiren() {
		return bildiren;
	}

	public void setBildiren(String bildiren) {
		this.bildiren = bildiren;
	}

	public Long getBildirenTel() {
		return bildirenTel;
	}

	public void setBildirenTel(Long bildirenTel) {
		this.bildirenTel = bildirenTel;
	}

	public String getAriza() {
		return ariza;
	}

	public void setAriza(String ariza) {
		this.ariza = ariza;
	}

	public String getYeri() {
		return yeri;
	}

	public void setYeri(String yeri) {
		this.yeri = yeri;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getAtanan() {
		return atanan;
	}

	public void setAtanan(String atanan) {
		this.atanan = atanan;
	}

	public String getAcil() {
		return acil;
	}

	public void setAcil(String acil) {
		this.acil = acil;
	}

	public String getIsBitimiAciklama() {
		return isBitimiAciklama;
	}

	public void setIsBitimiAciklama(String isBitimiAciklama) {
		this.isBitimiAciklama = isBitimiAciklama;
	}

	public Date getTamamlanmaTarihi() {
		return tamamlanmaTarihi;
	}

	public void setTamamlanmaTarihi(Date tamamlanmaTarihi) {
		this.tamamlanmaTarihi = tamamlanmaTarihi;
	}

	public String getKayitTuru() {
		return kayitTuru;
	}

	public void setKayitTuru(String kayitTuru) {
		this.kayitTuru = kayitTuru;
	}
	
}
