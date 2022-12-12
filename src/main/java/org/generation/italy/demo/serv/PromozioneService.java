package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.repo.PromozioneRepo;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class PromozioneService {
	
	@Autowired
	private PromozioneRepo promozioneRepo;
	
	public void save(Promozione promozione) {
		
		promozioneRepo.save(promozione);
	}
	public void delete(Promozione promozione) {
		
		promozioneRepo.delete(promozione);
	}
    public Optional<Promozione> findPromozioneById(int id) {
		
		return promozioneRepo.findById(id);
	}
	public List<Promozione> findAll() {
		
		return promozioneRepo.findAll();
	}
	
	@Transactional
	public List<Promozione> findAllWPizza() {
		
		List<Promozione> promozioni = promozioneRepo.findAll();
		
		for (Promozione promozione : promozioni) {
			
			Hibernate.initialize(promozione.getPizzas());
		}
		
		return promozioni;
	}

}
