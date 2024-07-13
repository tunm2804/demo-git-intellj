package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Order;
import com.demo.model.OrderDetail;
import com.demo.model.Product;
import com.demo.repo.AccountRepo;
import com.demo.repo.OrderDetailRepo;
import com.demo.repo.OrderRepo;
import com.demo.repo.ProductRepo;
import com.demo.service.CartService;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerController {
	@Autowired
	HttpSession session;

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	OrderDetailRepo orderDetailRepo;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	AccountRepo accountRepo;

	@ModelAttribute("cart")
	CartService getCartService(){
		return cartService;
	}

	@Data @AllArgsConstructor
	public static class PriceRange{
		int id;
		int minValue;
		int maxValue;
		String display;
	}

	List<PriceRange> priceRangeList = Arrays.asList(
		new PriceRange(0,0, Integer.MAX_VALUE, "Tất cả"),
		new PriceRange(1,0, 10000000, "Dưới 10 triệu"),
		new PriceRange(2,10000000, 20000000, "Từ 10-20 triệu"),
		new PriceRange(3,20000000, Integer.MAX_VALUE, "Trên 20 triệu")
	);

	@RequestMapping("/")
	public String index(
			@RequestParam(defaultValue="") String keyword,
			@RequestParam(defaultValue="") String categoryId,
			@RequestParam(defaultValue="0") int priceRangeId,
			@RequestParam(defaultValue="0") int p,
			Model model) {

		model.addAttribute("priceRangeList", priceRangeList);
		model.addAttribute("categoryList", categoryService.getAll());
		model.addAttribute("productList", productRepo.findAll());

		int minPrice = priceRangeList.get(priceRangeId).minValue;
		int maxPrice = priceRangeList.get(priceRangeId).maxValue;

		System.out.println("keyword=" + keyword);
		System.out.println("categoryId=" + categoryId);
		System.out.println("minPrice=" + minPrice);
		System.out.println("maxPrice=" + maxPrice);
		System.out.println("page=" + p);

		// TODO: Search & paginate



		Pageable pageable = PageRequest.of(p,5, Sort.by(Sort.Direction.DESC, "price"));
		Page<Product> page  = productRepo.search("%" + keyword + "%","%"+categoryId+"%",minPrice,maxPrice,pageable);//findAll(pageable);
		model.addAttribute("page", page);

		return "customer/index";
	}

	@GetMapping("/detail/{id}")
	public String viewProduct(@PathVariable int id, Model model) {
		Optional<Product> productOptional = productRepo.findById(id);
		if (productOptional.isPresent()) {
			model.addAttribute("product", productOptional.get());
		} else {
			// Handle the case where the product is not found
			return "redirect:/"; // Redirect to home or an error page
		}
		return "customer/detail";
	}

	@RequestMapping("/add-to-cart/{id}")
	public String addToCart(@PathVariable int id){
		cartService.add(id);
		return "redirect:/cart";
	}

	@RequestMapping("/remove-cart/{id}")
	public String removeCart(@PathVariable int id) {
		cartService.remove(id);
		if(cartService.getTotal() == 0){
			return "redirect:/";
		}
		return "redirect:/cart";
	}

	@RequestMapping("/update-cart/{id}")
	public String updateCart(@PathVariable int id, int quantity) {
		cartService.update(id, quantity);
		return "redirect:/cart";
	}

	@GetMapping("/cart")
	public String cart(){
		return "customer/cart";
	}

	@GetMapping("/checkout")
	public String confirm(){
		if(session.getAttribute("account") == null){
			return  "redirect:/login";
		}
		return "customer/checkout";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		return "customer/about";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model) {
		// Check if user/password exists in database
		Optional<Account> accountOpt = accountRepo.findByUsernameAndPassword(username, password);

		if (accountOpt.isPresent()) {
			Account acc = accountOpt.get();
			session.setAttribute("account", acc);
			return "redirect:/";
		}

		model.addAttribute("message", "Tên đăng nhập/mật khẩu không đúng");
		return "login";
	}

	@PostMapping("/purchase")
	public String purchase(@RequestParam String address){
		System.out.println("address=" + address);
		System.out.println("items=" + cartService.getItems());
		Account acc = (Account) session.getAttribute("account");
		if(acc != null) {
			Order order = new Order();
			order.setAccount(acc);
			order.setAddress(address);
			orderRepo.save(order);

			for(OrderDetail item : cartService.getItems()){
				item.setOrder(order);
				orderDetailRepo.save(item);
			}
			// TODO :clear cart
		}
		cartService.clear();
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(){
		session.removeAttribute("username");
		return "redirect:/login";
	}
}
