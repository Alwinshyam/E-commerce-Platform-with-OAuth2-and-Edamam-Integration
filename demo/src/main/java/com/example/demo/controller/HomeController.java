package com.example.demo.controller;

import com.example.demo.global.GlobalData;
import com.example.demo.service.CategorieService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategorieService categorieService;
    @Autowired
    ProductService productService;

    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "index";
    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories",categorieService.getAllCategories());
        model.addAttribute("products",productService.getAllProduct());
        model.addAttribute("cartCount",GlobalData.cart.size());

        return "shop";
    }
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categorieService.getAllCategories());
        model.addAttribute("cartCount",GlobalData.cart.size());

        model.addAttribute("products",productService.getAllProductByCategoryId(id));
        return "shop";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewproduct(Model model, @PathVariable int id){

        model.addAttribute("product",productService.getProductById(id).get());
        model.addAttribute("cartCount",GlobalData.cart.size());

        return "viewProduct";
    }
}
