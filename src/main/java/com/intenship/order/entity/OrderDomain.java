package com.intenship.order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "order_table")
public class OrderDomain {
    public enum Status {
        ORDER_SUCCESSFULL, ORDER_FAILED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "dd-mm-yyy  h:mm a")
    private LocalDateTime dateTime = LocalDateTime.now();
    private Long productId;
    private int quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserDomain userDomain;
    private Status status;
}
