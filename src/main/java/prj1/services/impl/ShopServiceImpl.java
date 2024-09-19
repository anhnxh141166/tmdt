package prj1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prj1.domains.Shop;
import prj1.repositories.ShopRepository;
import prj1.services.ShopService;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop creatShop(Shop shop) {

        if(shop !=null){
            return shopRepository.save(shop);
        }
        return null;
    }

    @Override
    public Shop updateShop(String id ,Shop shop){
        Optional<Shop> shop1 = shopRepository.findById(id);
        Shop shop2 ;
        if(shop1.isPresent()){
            shop2 = shop1.get();
            shop2.setShopName(shop.getShopName());
            shop2.setShopAddress(shop.getShopAddress());
            shop2.setShopDescription(shop.getShopDescription());
            return shopRepository.save(shop2);
        }
        return null;
    }

    @Override
    public Shop deleteShop(String id) {
        Shop shop = shopRepository.findById(id).orElse(null);
            if(shop != null ){
                shopRepository.delete(shop);
            }
        return null;
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public List<Shop> searchShop(String keyword){
        return null;
//        return shopRepository.findByNameContainingIgnoreCase(keyword);
    }
}


