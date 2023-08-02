package com.example.mapping.one_to_one_unidirectional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id")
        private Long id;

        @Column(name="order_tracking_number")
        private String orderTrackingNumber;

        @Column(name="total_quantity")
        private int totalQuantity;

        @Column(name="total_price")
        private BigDecimal totalPrice;

        @Column(name="status")
        private String status;

        @Column(name="date_created")
        private Date dateCreated;

        @Column(name="last_updated")
        private Date lastUpdated;

        // Unidirectional-> means, "to know which billing address is associated with this particular order,
        // need a foreign key column named "billing_address_id" or similar that can trace back to its associated
        //billing address
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "billing_address_id", referencedColumnName = "id")
        private Address billingAddress;

}
