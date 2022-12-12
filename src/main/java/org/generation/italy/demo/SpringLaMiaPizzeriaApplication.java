package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaApplication implements CommandLineRunner {
	
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
		Promozione prom3 = new Promozione(LocalDate.of(2022, 7, 10), LocalDate.of(2022, 9, 10), "promozione1");
		
		promozioneService.save(prom1);
		promozioneService.save(prom2);
		promozioneService.save(prom3);
		
		Pizza p1 = new Pizza("margherita", "mozzarella e pomodoro", 5, prom1);
		Pizza p2 = new Pizza("rossini", "mozzarella, pomodoro, uovo e majonese", 8, prom3);
		Pizza p3 = new Pizza("salsiccia", "mozzarella, pomodoro e salsiccia", 6, prom2);
		Pizza p4 = new Pizza("ciglista", "base bianca", 5, prom3);

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
		
		
		
		
		// LETTURA
		
		List<Pizza> pizzas = pizzaService.findAll();
		System.out.println(pizzas);
		
		List<Drink> drinks = drinkService.findAll();
		System.out.println(drinks);
		
		List<Promozione> promozioni = promozioneService.findAll();
		System.out.println(promozioni);
		

	}
}
