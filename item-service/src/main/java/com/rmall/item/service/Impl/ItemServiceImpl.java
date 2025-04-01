package com.rmall.item.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rmall.api.dto.ItemDTO;
import com.rmall.api.dto.OrderDetailDTO;
import com.rmall.common.exception.BizIllegalException;
import com.rmall.common.utils.BeanUtils;

import com.rmall.item.domain.po.Item;
import com.rmall.item.mapper.ItemMapper;
import com.rmall.item.service.IItemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author rose
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {

    @Override
    public void deductStock(List<OrderDetailDTO> items) {
        String sqlStatement = "com.rmall.item.mapper.ItemMapper.updateStock";
        boolean r = false;
        try {
            r = executeBatch(items, (sqlSession, entity) -> sqlSession.update(sqlStatement, entity));
        } catch (Exception e) {
            throw new BizIllegalException("更新库存异常，可能是库存不足!", e);
        }
        if (!r) {
            throw new BizIllegalException("库存不足！");
        }
    }

    @Override
    public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
        return BeanUtils.copyList(listByIds(ids), ItemDTO.class);
    }
}
