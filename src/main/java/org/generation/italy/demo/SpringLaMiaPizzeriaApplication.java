package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaApplication implements CommandLineRunner {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PromozioneService promozioneService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		// INSERIMENTO
		
		Promozione prom1 = new Promozione(LocalDate.of(2022, 1, 8), LocalDate.of(2022, 3, 8), "promozione1");
		Promozione prom2 = new Promozione(LocalDate.of(2022, 4, 10), LocalDate.of(2022, 6, 10), "promozione2");
		Promozione prom3 = new Promozione(LocalDate.of(2022, 7, 10), LocalDate.of(2022, 9, 10), "promozione3");
		
		promozioneService.save(prom1);
		promozioneService.save(prom2);
		promozioneService.save(prom3);
		
		Ingrediente i1 = new Ingrediente("pomodoro");
		Ingrediente i2 = new Ingrediente("salsiccia");
		Ingrediente i3 = new Ingrediente("uova");
		Ingrediente i4 = new Ingrediente("mozzarella");
		
		ingredienteService.save(i1);
		ingredienteService.save(i2);
		ingredienteService.save(i3);
		ingredienteService.save(i4);
		
		List<Ingrediente> ingP1 = Arrays.asList(new Ingrediente[] {
				i1, i2
		});		
		Pizza p1 = new Pizza("Margherita", "descrizione margherita", 5, prom1, ingP1);
		
		List<Ingrediente> ingP2 = Arrays.asList(new Ingrediente[] {
				i2, i4
		});
		Pizza p2 = new Pizza("Salsiccia", "descrizione Salsiccia", 6, prom2, ingP2);
		
		List<Ingrediente> ingP3 = Arrays.asList(new Ingrediente[] {
				i3, i4
		});
		Pizza p3 = new Pizza("Diavola", "descrizione diavola", 7, ingP3);
		
		Pizza p4 = new Pizza("Rossini", "descrizione rossini", 7, prom3);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		
		Drink d1 = new Drink("coca cola", "zero", 3);
		Drink d2 = new Drink("birra", "bionda", 3);
		Drink d3 = new Drink("vino", "rosso", 5);
		
		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		
	}
}
