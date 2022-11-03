package com.intenship.order.controller;

import com.intenship.order.entity.OrderDomain;
import com.intenship.order.services.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping("/{userId}/order")
    public ResponseEntity<Object> placeOrder(@PathVariable("userId") Long userId, @RequestBody OrderDomain orderDomain) {
        return new ResponseEntity<Object>(userServiceInterface.placeOrder(userId, orderDomain), HttpStatus.OK);
    }

    @PutMapping("/{userId}/account")
    public ResponseEntity<Object> deductAmount(@PathVariable("userId") Long userId, @RequestBody OrderDomain orderDomain) {
        return new ResponseEntity<Object>(userServiceInterface.debit(userId, orderDomain), HttpStatus.OK);
    }

    @GetMapping("/{userId}/order/all")
    public ResponseEntity<Object> orderDetails(@PathVariable("userId") Long userId) {
        return new ResponseEntity<Object>(userServiceInterface.showOrderDetails(userId), HttpStatus.OK);
    }

    @GetMapping("/details-by-productId/{productId}")
    public ResponseEntity<Object> orderDetailsByProductId(@PathVariable Long productId) {
        return new ResponseEntity<Object>(userServiceInterface.showOrderDetailsByProductId(productId), HttpStatus.OK);
    }

}
