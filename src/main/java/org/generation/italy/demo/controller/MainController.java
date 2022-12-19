package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingrediente;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promozione;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.IngredienteService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromozioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	@Autowired
	private PromozioneService promozioneService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@GetMapping
	public String getPizzas(Model model) {
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "pizzas";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		
		List<Promozione> promozioni = promozioneService.findAll();
		model.addAttribute("promozioni", promozioni);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		if (optPizza.isEmpty()) {
			
			System.err.println("Pizza non presente con id: " + id);
		}
		
		Pizza pizza = optPizza.get();
		
		model.addAttribute("pizza", pizza);
		
		return "pizza";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza(Model model) {
		
		Pizza pizza = new Pizza();
		model.addAttribute("pizza", pizza);
		
		List<Promozione> promozioni = promozioneService.findAll();
		model.addAttribute("promozioni", promozioni);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza-create";
	}
	@PostMapping("/pizza/create")
	public String storePizza(@Valid @ModelAttribute("pizza") Pizza pizza) {
		
		pizzaService.save(pizza);
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/update/{id}")
	public String editPizza(@PathVariable("id") int id, Model model) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		model.addAttribute("pizza", pizza);
		
		List<Promozione> promozioni = promozioneService.findAll();
		model.addAttribute("promozioni", promozioni);
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "pizza-update";
	}
	@PostMapping("/pizza/store")
	public String updatePizza(@Valid Pizza pizza) {
		
		pizzaService.save(pizza);		
		
		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(@PathVariable("id") int id) {
		
		Optional<Pizza> optPizza = pizzaService.findPizzaById(id);
		Pizza pizza = optPizza.get();
		
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
	
	//--------------------------------------------------------------------------------------
	
	
	@GetMapping("/drink")
	public String getDrinks(Model model) {
		
		List<Drink> drinks = drinkService.findAll();
		model.addAttribute("drinks", drinks);
		
		return "drinks";
	}
	
	@GetMapping("/drink/{id}")
	public String getDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.findDrinkById(id);
		
		if (optDrink.isEmpty()) {
			
			System.err.println("Drink non presente con id: " + id);
		}
		
		Drink drink = optDrink.get();
		
		model.addAttribute("drink", drink);
		
		return "drink";
	}
	
	@GetMapping("/drink/create")
	public String createDrink(Model model) {
		
		Drink drink = new Drink();
		model.addAttribute("drink", drink);
		
		return "drink-create";
	}
	@PostMapping("/drink/create")
	public String storeDrink(@Valid @ModelAttribute("drink") Drink drink) {
		
		drinkService.save(drink);
		
		return "redirect:/drink";
	}
	
	@GetMapping("/drink/update/{id}")
	public String editDrink(@PathVariable("id") int id, Model model) {
		
		Optional<Drink> optDrink = drinkService.findDrinkById(id);
		Drink drink = optDrink.get();
		
		model.addAttribute("drink", drink);
		
		return "drink-update";
	}
	@PostMapping("/drink/store")
	public String updateDrink(@Valid Drink drink) {
		
		drinkService.save(drink);		
		
		return "redirect:/drink";
	}
	
	@GetMapping("/drink/delete/{id}")
	public String deleteDrink(@PathVariable("id") int id) {
		
		Optional<Drink> optDrink = drinkService.findDrinkById(id);
		Drink drink = optDrink.get();
		
		drinkService.delete(drink);
		
		return "redirect:/drink";
	}
	
	//--------------------------------------------------------------------------------------

	
	 
	
    @GetMapping("/promozione")
	public String getPromozioni(Model model) {
			
		List<Promozione> promozioni = promozioneService.findAll();
		model.addAttribute("promozioni", promozioni);
			
		return "promozioni";
	}
    
	@GetMapping("/promozione/create")
	public String createPromozione(Model model) {
			
		Promozione promozione = new Promozione();
		model.addAttribute("promozione", promozione);
			
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
			
		return "promozione-create";
	}
	
	@PostMapping("/promozione/store")
	public String storePromozioni(
				@Valid Promozione promozione
			) {
			
		System.err.println(promozione);
		if (promozione.getPizzas() != null)
			for (Pizza pizza : promozione.getPizzas()) {
					
				System.err.println("\t" + pizza);
			}
	   	else 
			System.err.println("- no pizza -");
			
		List<Pizza> promozionePizza = promozione.getPizzas();
		for(Pizza pizza : promozionePizza) {
				
			pizza.setPromozione(promozione);
		}
			
	    promozioneService.save(promozione);
		
		return "redirect:/promozione";
	}
	
	//--------------------------------------------------------------------------------------
	
	@GetMapping("/ingrediente")
	public String getIngredienti(Model model) {
		
		List<Ingrediente> ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		
		return "ingredienti";
	}
	@GetMapping("/ingrediente/create")
	public String createIngrediente(Model model) {
		
		Ingrediente ingrediente = new Ingrediente();
		model.addAttribute("ingrediente", ingrediente);
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "ingrediente-create";
	}
	@PostMapping("/ingrediente/create")
	public String storeIngrediente(@Valid Ingrediente ingrediente) {
		
		for (Pizza p : ingrediente.getPizze()) {
			
			p.getIngredienti().add(ingrediente);
		}
		
		ingredienteService.save(ingrediente);
		
		return "redirect:/ingrediente";
	}
	
	@GetMapping("/ingrediente/update/{id}")
	public String updateIngrediente(
			@PathVariable("id") int id,
			Model model
		) {
		
		Ingrediente ingrediente = ingredienteService.findIngredienteById(id);
		model.addAttribute("ingrediente", ingrediente);
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "ingrediente-update";
	}
	@PostMapping("/ingrediente/update/{id}")
	public String editIngrediente(
			@PathVariable("id") int id,
			@Valid Ingrediente ingrediente
		) {
		
		Ingrediente oldIng = ingredienteService.findIngredienteById(id);
		
		for (Pizza p : oldIng.getPizze()) {
			
			p.getIngredienti().remove(oldIng);
		}
		
		for (Pizza p : ingrediente.getPizze()) {
			
			p.addIngrediente(ingrediente);
		}
		
		ingredienteService.save(ingrediente);
		
		return "redirect:/ingrediente";
	}
	
	@GetMapping("/ingrediente/delete/{id}")
	public String deleteIngrediente(@PathVariable("id") int id) {
		
		Optional<Ingrediente> optIngrediente = Optional.ofNullable(ingredienteService.findIngredienteById(id));
		Ingrediente ingrediente = optIngrediente.get();
		
		ingredienteService.delete(ingrediente);
		
		return "redirect:/ingrediente";
	}
}
