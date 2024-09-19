package prj1.services;

import prj1.domains.Shop;

import java.util.List;

public interface ShopService {

    // Ham tao shop moi
    public Shop creatShop(Shop shop);

    // Ham cap nhat thong tin shop
    public Shop updateShop(String id ,Shop shop);

    //Ham xoa shop
    public Shop deleteShop(String id);

    //Ham xem danh sach shop
    public List<Shop> getAllShop();

    List<Shop> searchShop(String keyword);
}
