package com.rmall.pay.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rmall.pay.domain.dto.PayApplyDTO;
import com.rmall.pay.domain.dto.PayOrderFormDTO;
import com.rmall.pay.domain.po.PayOrder;


/**
 * <p>
 * 支付订单 服务类
 * </p>
 *
 * @author rose
 * @since 2024-05-16
 */
public interface IPayOrderService extends IService<PayOrder> {

    String applyPayOrder(PayApplyDTO applyDTO);

    void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO);
}
