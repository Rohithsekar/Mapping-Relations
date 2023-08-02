package com.example.mapping.entity;

import com.example.mapping.one_to_one_unidirectional.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="sim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    // Unidirectional-> means, "to know which sim_provider is associated with this particular sim,we
    // need a foreign key column named "sim_provider_id" or similar that can trace back to its associated
    //sim_provider
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "sim_provider_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "sim_provider_id"))
    private SimProvider simProvider;
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
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "sim_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "sim_id"))
    private Set<SimHistory> simHistorySet = new HashSet<>();

}
