package store.service;

import store.converter.OrderProductConverter;
import store.converter.OrderConverter;
import store.converter.ProductConverter;
import store.dao.OrderProductDao;
import store.dto.OrderProductDto;
import store.dto.OrderDto;
import store.dto.ProductDto;
import store.entity.Order;
import store.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderProductServiceImpl implements OrderProductService {


    private OrderProductDao orderProductDao;
    private OrderProductConverter orderProductConverter;
    private ProductConverter productConverter;
    private OrderConverter orderConverter;

    @Override
    @Transactional
    public OrderProductDto getById(long id){
        return orderProductConverter.convertToDto(orderProductDao.getById(id));
    }

    @Override
    @Transactional
    public List<ProductDto> getProductsInOrder(long id_order){
        List<Product> products = orderProductDao.getProductsInOrder(id_order);
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product:  products){
            productsDto.add(productConverter.convertToDto(product));
        }
        return productsDto;
    }


    @Override
    @Transactional
    public List<OrderDto> getOrdersForProduct(long id_product){
        List<Order> orders = orderProductDao.getOrdersForProduct(id_product);
        List<OrderDto> ordersDto = new ArrayList<>();
        for (Order order: orders){
            ordersDto.add(orderConverter.convertToDto(order));
        }
        return ordersDto;
    }

    @Override
    @Transactional
    public void add(OrderProductDto orderProductDto){
        orderProductDao.add(orderProductConverter.convertToEntity(orderProductDto));
    }

    @Override
    @Transactional
    public void deleteById(long id){
        orderProductDao.deleteById(id);
    }

    @Override
    @Transactional
    public void update(OrderProductDto orderProductDto){
        orderProductDao.update(orderProductConverter.convertToEntity(orderProductDto));
    }


    @Autowired
    public void setOrderProductDao(OrderProductDao orderProductDao) {
        this.orderProductDao = orderProductDao;
    }

    @Autowired
    public void setOrderProductConverter(OrderProductConverter orderProductConverter) {
        this.orderProductConverter = orderProductConverter;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Autowired
    public void setOrderConverter(OrderConverter orderConverter) {
        this.orderConverter = orderConverter;
    }
}
