package ru.baster.spring.sample.shop.study.market.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.baster.spring.sample.shop.study.market.model.OrderItem;
import ru.baster.spring.sample.shop.study.market.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;
    private int amount;

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
    }

    public void addOrIncrement(Product product){
        for (OrderItem o: items) {
            if(o.getProduct().getId().equals(product.getId())){
                o.incrementQuantity();
                totalAmount();
                return;
            }
        }

        items.add(new OrderItem(product));
        totalAmount();
    }

    public void increment(Long productId){
        for (OrderItem o: items) {
            if(o.getProduct().getId().equals(productId)){
                o.incrementQuantity();
                totalAmount();
                return;
            }
        }
    }

    public void decrementOrRemove(Long productId){
        Iterator<OrderItem> itemIterator = items.iterator();
        while (itemIterator.hasNext()){
            OrderItem o = itemIterator.next();
            if(o.getProduct().getId().equals(productId)){
                o.decrementQuantity();
                if(o.getQuantity() <=0){
                    itemIterator.remove();
                }
                totalAmount();
                return;
            }
        }
    }

    public void remove(Long productId){
        Iterator<OrderItem> itemIterator = items.iterator();
        while (itemIterator.hasNext()){
            OrderItem o = itemIterator.next();
            if(o.getProduct().getId().equals(productId)){
                itemIterator.remove();
                totalAmount();
                return;
            }
        }
    }

    public void totalAmount(){
        amount = 0;
        for (OrderItem o: items) {
            amount += o.getPrice() * o.getQuantity();
        }
    }
}