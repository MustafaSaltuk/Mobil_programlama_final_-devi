package com.toyotaproje.arizatakipsistemi.model;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ariza")
public class Ariza {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="kayit_tarihi")
	private LocalDateTime kayitTarihi;
	
	@Column(name="kayit_yapan")
	private String kayitYapan;
	
	@Column(name="bildiren")
	private String bildiren;
	
	@Column(name="bildiren_tel")
	private Long bildirenTel;
	
	@Column(name="ariza")
	private String ariza;
	
	@Column(name="yeri")
	private String yeri;
	
	@Column(name="shop")
	private String shop;
	
	@Column(name="atanan")
	private String atanan;
	
	@Column(name="acil")
	private String acil;
	
	@Column(name="is_bitimi_aciklama")
	private String isBitimiAciklama;
	
	@Column(name="tamamlanma_tarihi")
	private Date tamamlanmaTarihi;
	
	@Column(name="kayit_turu")
	private String kayitTuru;

	public Ariza(Long id, LocalDateTime kayitTarihi, String kayitYapan, String bildiren, Long bildirenTel, String ariza,
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

	public Ariza() {
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
