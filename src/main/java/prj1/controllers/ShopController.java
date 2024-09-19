package prj1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import prj1.domains.Shop;
import prj1.services.ShopService;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @PostMapping("/creat")
    public Shop creatShop(@RequestBody Shop shop){
        return shopService.creatShop(shop);
    }
    @PutMapping("/update")
    public Shop update(@RequestParam String id , @RequestBody Shop shop){
        return shopService.updateShop(id ,shop);
    }

    @GetMapping("/list")
    public List<Shop> getAllShop(){
        return shopService.getAllShop();
    }

    @GetMapping("/seach")
    public List<Shop>seachShop(@RequestParam String keyword){
        return shopService.searchShop(keyword);
    }

}
