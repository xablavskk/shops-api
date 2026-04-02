package com.xvlkk.sp.shops.service;

import com.xvlkk.sp.shops.dto.PaymentDTO;
import com.xvlkk.sp.shops.enums.OrderStatusEnum;
import com.xvlkk.sp.shops.enums.PaymentStatusEnum;
import com.xvlkk.sp.shops.exceptions.ResourceNotFoundException;
import com.xvlkk.sp.shops.mapper.PaymentMapper;
import com.xvlkk.sp.shops.model.Order;
import com.xvlkk.sp.shops.model.Payment;
import com.xvlkk.sp.shops.rabbit.service.PaymentProducerService;
import com.xvlkk.sp.shops.repository.OrderRepository;
import com.xvlkk.sp.shops.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentProducerService paymentProducerService;

    public List<PaymentDTO> findByOrder(Long cdOrder) {
        return paymentRepository.findByOrderCdOrder(cdOrder).stream().map(paymentMapper::toDto).toList();
    }

    public PaymentDTO findById(Long id) {
        return paymentMapper.toDto(findOrThrow(id));
    }

    @Transactional
    public PaymentDTO create(Long cdOrder, PaymentDTO dto) {
        Order order = orderRepository.findById(cdOrder)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + cdOrder));

        Payment payment = paymentMapper.toEntity(dto);
        payment.setOrder(order);
        payment.setStatus(PaymentStatusEnum.PENDING.getStPayment());

        Payment saved = paymentRepository.save(payment);
        paymentProducerService.sendPaymentCreatedEvent(paymentMapper.toDto(saved));
        return paymentMapper.toDto(saved);
    }

    @Transactional
    public PaymentDTO approve(Long id) {
        Payment payment = findOrThrow(id);
        payment.setStatus(PaymentStatusEnum.APPROVED.getStPayment());

        Order order = payment.getOrder();
        order.setStatus(OrderStatusEnum.PAID.getStOrder());
        orderRepository.save(order);

        Payment saved = paymentRepository.save(payment);
        paymentProducerService.sendPaymentCreatedEvent(paymentMapper.toDto(saved));
        return paymentMapper.toDto(saved);
    }

    @Transactional
    public PaymentDTO decline(Long id) {
        Payment payment = findOrThrow(id);
        payment.setStatus(PaymentStatusEnum.DECLINED.getStPayment());
        return paymentMapper.toDto(paymentRepository.save(payment));
    }

    @Transactional
    public PaymentDTO refund(Long id) {
        Payment payment = findOrThrow(id);
        payment.setStatus(PaymentStatusEnum.REFUNDED.getStPayment());

        Order order = payment.getOrder();
        order.setStatus(OrderStatusEnum.CANCELLED.getStOrder());
        orderRepository.save(order);

        return paymentMapper.toDto(paymentRepository.save(payment));
    }

    private Payment findOrThrow(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found: " + id));
    }
}
