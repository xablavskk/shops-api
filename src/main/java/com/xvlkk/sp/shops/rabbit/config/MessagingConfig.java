package com.xvlkk.sp.shops.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    // ── Order ──────────────────────────────────────────────────────────────
    public static final String ORDER_EXCHANGE             = "order.exchange";
    public static final String ORDER_CREATED_QUEUE        = "order.created.queue";
    public static final String ORDER_CREATED_ROUTING_KEY  = "order.created";
    public static final String ORDER_DLQ                  = "order.created.dlq";

    // ── Payment ────────────────────────────────────────────────────────────
    public static final String PAYMENT_EXCHANGE            = "payment.exchange";
    public static final String PAYMENT_CREATED_QUEUE       = "payment.created.queue";
    public static final String PAYMENT_CREATED_ROUTING_KEY = "payment.created";
    public static final String PAYMENT_DLQ                 = "payment.created.dlq";

    // ── Shipment ───────────────────────────────────────────────────────────
    public static final String SHIPMENT_EXCHANGE            = "shipment.exchange";
    public static final String SHIPMENT_CREATED_QUEUE       = "shipment.created.queue";
    public static final String SHIPMENT_CREATED_ROUTING_KEY = "shipment.created";
    public static final String SHIPMENT_DLQ                 = "shipment.created.dlq";

    // ── Dead-Letter Exchange (shared) ──────────────────────────────────────
    public static final String DLX = "dlx.exchange";

    // ── Shared infrastructure ──────────────────────────────────────────────
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    // ── Order beans ───────────────────────────────────────────────────────
    @Bean public TopicExchange orderExchange() { return new TopicExchange(ORDER_EXCHANGE); }

    @Bean
    public Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_CREATED_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX)
                .withArgument("x-dead-letter-routing-key", ORDER_DLQ)
                .build();
    }

    @Bean public Queue orderDlq() { return QueueBuilder.durable(ORDER_DLQ).build(); }

    @Bean
    public Binding orderCreatedBinding() {
        return BindingBuilder.bind(orderCreatedQueue()).to(orderExchange()).with(ORDER_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding orderDlqBinding() {
        return BindingBuilder.bind(orderDlq()).to(deadLetterExchange()).with(ORDER_DLQ);
    }

    // ── Payment beans ─────────────────────────────────────────────────────
    @Bean public TopicExchange paymentExchange() { return new TopicExchange(PAYMENT_EXCHANGE); }

    @Bean
    public Queue paymentCreatedQueue() {
        return QueueBuilder.durable(PAYMENT_CREATED_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX)
                .withArgument("x-dead-letter-routing-key", PAYMENT_DLQ)
                .build();
    }

    @Bean public Queue paymentDlq() { return QueueBuilder.durable(PAYMENT_DLQ).build(); }

    @Bean
    public Binding paymentCreatedBinding() {
        return BindingBuilder.bind(paymentCreatedQueue()).to(paymentExchange()).with(PAYMENT_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding paymentDlqBinding() {
        return BindingBuilder.bind(paymentDlq()).to(deadLetterExchange()).with(PAYMENT_DLQ);
    }

    // ── Shipment beans ────────────────────────────────────────────────────
    @Bean public TopicExchange shipmentExchange() { return new TopicExchange(SHIPMENT_EXCHANGE); }

    @Bean
    public Queue shipmentCreatedQueue() {
        return QueueBuilder.durable(SHIPMENT_CREATED_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX)
                .withArgument("x-dead-letter-routing-key", SHIPMENT_DLQ)
                .build();
    }

    @Bean public Queue shipmentDlq() { return QueueBuilder.durable(SHIPMENT_DLQ).build(); }

    @Bean
    public Binding shipmentCreatedBinding() {
        return BindingBuilder.bind(shipmentCreatedQueue()).to(shipmentExchange()).with(SHIPMENT_CREATED_ROUTING_KEY);
    }

    @Bean
    public Binding shipmentDlqBinding() {
        return BindingBuilder.bind(shipmentDlq()).to(deadLetterExchange()).with(SHIPMENT_DLQ);
    }
}
