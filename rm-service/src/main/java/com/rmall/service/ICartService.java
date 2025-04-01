package com.rmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rmall.domain.dto.CartFormDTO;
import com.rmall.domain.po.Cart;
import com.rmall.domain.vo.CartVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2023-05-05
 */
public interface ICartService extends IService<Cart> {

    void addItem2Cart(CartFormDTO cartFormDTO);

    List<CartVO> queryMyCarts();

    void removeByItemIds(Collection<Long> itemIds);
}
