package com.example.mapping.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="sim_provider")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="sim_type")
    private String simType;
    @Column(name="is_active")
    private Integer isActive;
    @Column(name="created_at")
    private Long createdAt;
    @Column(name="created_by")
    private Integer createdBy;
    @Column(name="modified_at")
    private Long modifiedAt;
    @Column(name="modified_by")
    private Integer modifiedBy;

}
