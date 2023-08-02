package com.example.mapping.one_to_one_bidirectional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="employee_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="state")
    private String state;

    @Column(name="country")
    private String country;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;


}
