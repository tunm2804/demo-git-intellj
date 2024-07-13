package com.demo.controller;

import com.demo.model.Account;
import com.demo.model.Category;
import com.demo.model.Product;
import com.demo.repo.AccountRepo;
import com.demo.repo.CategoryRepo;
import com.demo.repo.ProductRepo;
import com.demo.service.CategoryService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class AdminController {

    private final ServletContext servletContext;
    public AdminController(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Autowired
    HttpSession session;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categoryList")
    public List<Category> getlistCate(){return categoryRepo.findAll();}

    @Autowired
    ProductRepo productRepo;

    @Autowired
    AccountRepo accountRepo;

    //  Category
    @GetMapping("/admin")
    public String listCategory(Model model){
        model.addAttribute("listC",categoryRepo.findAll());
        return "admin/category/list";
    }

    @GetMapping("/admin/category/create")
    public String createViewCategory(@ModelAttribute("cate")Category category){
        return "admin/category/form";
    }

    @PostMapping("/admin/category/create")
    public String createCategory(@Valid @ModelAttribute("cate")Category category,BindingResult rs){
        if(!rs.hasErrors()){
            categoryRepo.save(category);
            return "redirect:/admin";
        }
        return "admin/category/form";
    }

    @GetMapping("/admin/category/update/{id}")
    public String editCategory(@PathVariable String id, Model model){
        Category category =categoryRepo.findById(id).orElse(null);
        model.addAttribute("cate",category);
        model.addAttribute("isUpdate",true);
        return "admin/category/form";
    }

    @PostMapping("/admin/category/update/{id}")
    public String update(@PathVariable String id,@ModelAttribute("cate")Category category){
        category.setId(id);
        categoryRepo.save(category);
        return "redirect:/admin";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String delete(@PathVariable String id){
        categoryRepo.deleteById(id);
        return "redirect:/admin";
    }

    //  Product
//    @GetMapping("/admin/product")
//    public String listProduct(Model model){
//        model.addAttribute("listP",productRepo.findAll());
//        return "admin/product/list";
//    }

    @GetMapping("/admin/product/create")
    public String createViewProduct(@ModelAttribute("p")Product product,Model model){
        return "admin/product/form";
    }

//    @PostMapping("/admin/product/create")
//    public String createProduct(@Valid @ModelAttribute("p")Product product,
//                                BindingResult rs, Model model){
//        if(!rs.hasErrors()){
//            productRepo.save(product);
//            return "redirect:/admin/product";
//        }
//        return "admin/product/form";
//    }
@PostMapping("/admin/product/create")
public String saveProduct(@Valid @ModelAttribute("p") Product product,
                          @RequestParam("imageFile") MultipartFile imageFile,
                          BindingResult result) throws IOException {
    if (result.hasErrors()) {
        return "admin/product/form";
    }

    if (!imageFile.isEmpty()) {
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Paths.get("E:\\Java5\\assignment2\\src\\main\\resources\\static\\images", fileName);

        Files.createDirectories(filePath.getParent());

        if (!Files.exists(filePath)) {
            try (InputStream inputStream = imageFile.getInputStream()) {
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        product.setImage(fileName);
    }
    productRepo.save(product);
    return "redirect:/admin/product";
}
    //    @PostMapping("/admin/product/create")
//    public String createProduct(@Valid @ModelAttribute("p")Product product, BindingResult rs, Model model,
//                                @RequestParam(value="image",required = false) MultipartFile fileImage){
//        if(!fileImage.isEmpty()){
//            String uploadDir = servletContext.getRealPath("/image");
//            String filePath = uploadDir + File.separator + fileImage.getOriginalFilename();
//            try{fileImage.transferTo(new File(filePath));}
//            catch (IOException e){
//                throw new RuntimeException(e);
//            }
//            System.out.println(filePath);
//            product.setImage("/image"+fileImage.getOriginalFilename());
//        }
//        if(!rs.hasErrors()){
//            productRepo.save(product);
//            return "redirect:/admin/product";
//        }
//        return "admin/product/form";
//    }
//    @PostMapping("/admin/product/create")
//    public String createProduct(@Valid @ModelAttribute("p") Product product, BindingResult rs, Model model,
//                            @RequestParam(value="image",required = false) MultipartFile fileImage) {
//    if (!rs.hasErrors()) {
//        String uploadDir = "/path/to/your/upload/directory"; // Replace with your configured path
//        String fileName = fileImage.getOriginalFilename(); // Use original filename
//        String filePath = uploadDir + File.separator + fileName;
//        try {
//            fileImage.transferTo(new File(filePath)); // Save the uploaded file
//            product.setImage(fileName); // Set image property to the filename
//        } catch (IOException e) {
//            // Handle IO exceptions more gracefully (e.g., log the error)
//            throw new RuntimeException(e); // Or consider a more specific exception
//        }
//        productRepo.save(product);
//        return "redirect:/admin/product";
//    }
//    return "admin/product/form";
//}


    @GetMapping("/admin/product/update/{id}")
    public String editProduct(@PathVariable int id,Model model){
        Product product = productRepo.findById(id).orElse(null);
        model.addAttribute("p",product);
        model.addAttribute("isUpdate",true);
        return "admin/product/form";
    }

    @PostMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id,@ModelAttribute("p")Product product){
        product.setId(id);
        productRepo.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable int id){
        productRepo.deleteById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("admin/product")
    public String allProduct(Model model,@RequestParam(defaultValue = "")String keyword,
                             @RequestParam(defaultValue = "0")int p){
    session.setAttribute("keyword",keyword);
        Pageable pageable = PageRequest.of(p,5);
        Page<Product> page =productRepo.findByKeyword("%"+keyword+"%","%"+keyword+"%",pageable);
        model.addAttribute("page",page);
    return"admin/product/list";
    }


    //  Account
    @GetMapping("/admin/account")
    public String listAccount(Model model){
        model.addAttribute("listA",accountRepo.findAll());
        return "admin/account/list";
    }

    @GetMapping("/admin/account/create")
    public String createViewAccount(@ModelAttribute("a")Account account){
        return "admin/account/form";
    }

    @PostMapping("/admin/account/create")
    public String createAccount(@Valid @ModelAttribute("a")Account account,BindingResult rs,Model model){
        if(!rs.hasErrors()){
            accountRepo.save(account);
            return "redirect:/admin/account";
        }
        return "admin/account/form";
    }

    @GetMapping("/admin/account/update/{username}")
    public String editViewAccount(@PathVariable String username,Model model){
        Account account = accountRepo.findByUsername("%"+username+"%");
        model.addAttribute("a",account);
        model.addAttribute("isUpdate",true);
        return "admin/account/form";
    }

    @PostMapping("/admin/account/update/{username}")
    public String editAccount(@PathVariable String username, @ModelAttribute("a")Account account){
        account.setUsername(username);
        accountRepo.save(account);
        return "redirect:/admin/account";
    }

    @GetMapping("/admin/account/delete/{username}")
    public String deleteAccount(@PathVariable String username){
        Account account = accountRepo.findByUsername("%"+username+"%");
        accountRepo.delete(account);
        return "redirect:/admin/account";
    }
}
