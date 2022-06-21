package com.zpc.operations.pop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Outward_Items_Details_Report")
public class PensionPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pen")
    @SequenceGenerator(name = "pen", sequenceName = "pen_1", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    private String employeeName;
    private String staffId;
    private String pfaCode;
    private String rsaPin;
    private double employer;
    private double employee;
    private double vcEmployer;
    private double vcEmployee;
    private double total;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
