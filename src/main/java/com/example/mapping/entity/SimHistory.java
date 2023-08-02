package com.example.mapping.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="sim_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="sim_provider_id")
    private Integer simProviderID;
    @Column(name="imei_number")
    private Long imeiNumber; //IMEI number is 16 digits long
    @Column(name="sim_number")
    private Integer simNumber;
    @Column(name="purchased_from")
    private String purchasedFrom;
    @Column(name="purchased_date")
    private Date purchasedDate;
    @Column(name="purchased_price")
    private Double purchasedPrice;
    @Column(name="is_recharged")
    private Integer isRecharged;
    @Column(name="is_active")
    private Integer isActive;
    @Column(name="created_at")
    private Long createdAt;
    @Column(name="created_by")
    private Integer createdBy;
    @Column(name="modified_at")
    private Long modifiedAt;   //only set the value during PUT operation
    @Column(name="modified_by")
    private Integer modifiedBy; //only set the value during PUT operation


}
