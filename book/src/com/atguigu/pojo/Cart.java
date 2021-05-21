package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

//购物车对象
public class Cart {
//    private Integer totalCount;
//    private BigDecimal totalPrice;

    //key 商品编号，  value 商品信息
    private Map<Integer,CartItem> items = new LinkedHashMap<>();


    public void addItem(CartItem cartItem){
        //先查看购物车中是否已经添加过此商品，如果已经添加，则数量增加，总金额增加
        //如果没有添加，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            //没有添加过
            items.put(cartItem.getId(),cartItem);
        }else {
            //已经添加过
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deletedItem(Integer id){
        items.remove(id);
    }

    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id, Integer count){
        //先查看购物车中是否有此商品，如果有则更新数量和总金额
        CartItem item = items.get(id);
        if(item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }



    public Integer getTotalCount() {
        Integer totalCount = 0;

        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {

        BigDecimal totalPrice = new BigDecimal(0);

        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }



        return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
