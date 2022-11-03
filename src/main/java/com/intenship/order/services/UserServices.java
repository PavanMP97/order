package com.intenship.order.services;

import com.intenship.order.entity.OrderDomain;
import com.intenship.order.exception.InvokeException;
import com.intenship.order.repository.OrderRepository;
import com.intenship.order.repository.UserRepository;
import com.intenship.order.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServices implements UserServiceInterface {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public Object placeOrder(Long userId, OrderDomain orderDomain) {
        if (userRepository.existsById(userId)) {
            String obj = webClientBuilder.build().get().uri("http://localhost:8080/product/" + orderDomain.getProductId()).retrieve().bodyToMono(String.class).block();
            String message = "Product doesn't exist with the given product id: " + orderDomain.getProductId();
            if (obj.equals(message)) {
                return obj;
            } else {
                ProductResponse productResponse = (ProductResponse) webClientBuilder.build().get().uri("http://localhost:8080/product/" + orderDomain.getProductId()).retrieve().bodyToMono(ProductResponse.class).block();
                if (productResponse.getStock() != 0) {
                    if (userRepository.findById(userId).get().getMoney() >= productResponse.getPrice()) {
                        if (orderDomain.getQuantity() <= 5) {
                            int order = userRepository.findById(userId).get().getTotalOrders();
                            if (order >= 50) {
                                orderDomain.setUserDomain(userRepository.findById(userId).get());
                                orderDomain.setStatus(OrderDomain.Status.ORDER_FAILED);
                                orderRepository.save(orderDomain);
                                return InvokeException.insufficientBalanceException();
                            } else {
                                int totalOrder = order + orderDomain.getQuantity();
                                userRepository.updateTotalOrdersById(totalOrder, userId);
                                int totalAmount = userRepository.findById(userId).get().getMoney();
                                int balance = totalAmount - productResponse.getPrice();
                                userRepository.updateMoneyById(balance, userId);
                                orderDomain.setUserDomain(userRepository.findById(userId).get());
                                orderDomain.setStatus(OrderDomain.Status.ORDER_SUCCESSFULL);
                                orderRepository.save(orderDomain);
                                webClientBuilder.build().put().uri("http://localhost:8080/product/" + orderDomain.getProductId() + "/product-1").retrieve().bodyToMono(String.class).block();
                                return "Order Placed Successfully.... ";
                            }
                        } else {
                            return InvokeException.limitExceededException();
                        }
                    } else {
                        orderDomain.setUserDomain(userRepository.findById(userId).get());
                        orderDomain.setStatus(OrderDomain.Status.ORDER_FAILED);
                        orderRepository.save(orderDomain);
                        return InvokeException.insufficientBalanceException();
                    }
                } else {
                    orderDomain.setUserDomain(userRepository.findById(userId).get());
                    orderDomain.setStatus(OrderDomain.Status.ORDER_FAILED);
                    orderRepository.save(orderDomain);
                    Object insufficientStock = webClientBuilder.build().get().uri("http://localhost:8080/product/insufficientStock").retrieve().bodyToMono(String.class).block();
                    return insufficientStock;
                }

            }
        } else {

            return InvokeException.userNotFoundException() + userId;
        }
    }

    @Override
    public Object debit(Long userId, OrderDomain orderDomain) {
        ProductResponse productResponse = (ProductResponse) webClientBuilder.build().get().uri("http://localhost:8080/product/" + orderDomain.getProductId()).retrieve().bodyToMono(ProductResponse.class).block();
        int totalAmount = userRepository.findById(userId).get().getMoney();
        int balance = totalAmount - productResponse.getPrice();
        userRepository.updateMoneyById(balance, userId);
        return "Available balance is" + balance;
    }

    @Override
    public Object showOrderDetails(Long userId) {
        if (userRepository.findById(userId).get().getOrderDomains().isEmpty()) {
            return InvokeException.noOrdersFoundException();
        } else {
            return userRepository.findById(userId).get();
        }
    }

    @Override
    public Object showOrderDetailsByProductId(Long productId) {
        List<OrderDomain> list = new ArrayList<>();
        orderRepository.findAll().forEach(orderDomain -> {
            if (orderDomain.getProductId() == productId) {
                list.add(orderDomain);
            }
        });
        if (list.isEmpty()) {
            return InvokeException.noOrdersFoundException();
        } else {
            return list;
        }
    }
}
