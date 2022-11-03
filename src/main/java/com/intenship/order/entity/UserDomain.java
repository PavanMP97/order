package com.intenship.order.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_table")
public class UserDomain {
    @Id
    private Long user_id;
    @JsonBackReference
    private int money;
    private int totalOrders;
    @OneToMany(mappedBy = "userDomain")
    @JsonManagedReference
    private List<OrderDomain> orderDomains;


}
