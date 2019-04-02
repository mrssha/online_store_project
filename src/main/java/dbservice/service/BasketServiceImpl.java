package dbservice.service;

import dbservice.converter.BasketConverter;
import dbservice.converter.OrderConverter;
import dbservice.converter.ProductConverter;
import dbservice.dao.BasketDao;
import dbservice.dto.BasketDto;
import dbservice.dto.OrderDto;
import dbservice.dto.ProductDto;
import dbservice.entity.Order;
import dbservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    BasketDao basketDao;

    @Autowired
    BasketConverter basketConverter;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    OrderConverter orderConverter;

    @Override
    @Transactional
    public BasketDto getById(long id){
        return basketConverter.convertToDto(basketDao.getById(id));
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsInOrder(long id_order){
        List<Product> products = basketDao.getProductsInOrder(id_order);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product:  products){
            productsDto.add(productConverter.convertToDto(product));
        }
        return productsDto;
    }


    @Override
    @Transactional
    public List<OrderDto> getOrdersForProduct(long id_product){
        List<Order> orders = basketDao.getOrdersForProduct(id_product);
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order: orders){
            ordersDto.add(orderConverter.convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    @Transactional
    public void add(BasketDto basketDto){
        basketDao.add(basketConverter.convertToEntity(basketDto));
    }

    @Override
    @Transactional
    public void deleteById(long id){
        basketDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(BasketDto basketDto){
        basketDao.update(basketConverter.convertToEntity(basketDto));
    }


}
