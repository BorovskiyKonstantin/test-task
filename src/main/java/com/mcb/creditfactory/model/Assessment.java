package com.mcb.creditfactory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ASSESSMENT")
public class Assessment implements Comparable<Assessment> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assessed_value")
    private BigDecimal value;

    @Column(name = "valuation_date")
    private LocalDate date;

    public Assessment(BigDecimal value) {
        this.value = value;
        this.date = LocalDate.now();
    }

    @Override
    public int compareTo(Assessment o) {
        int result = this.date.compareTo(o.date);
        if (result == 0)
            result = this.value.compareTo(o.value);
        return result;
    }
}
