package com.mcb.creditfactory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentDto implements Comparable<AssessmentDto> {
    private BigDecimal value;
    private LocalDate date;

    @Override
    public int compareTo(AssessmentDto o) {
        int result = this.date.compareTo(o.date);
        if (result == 0)
            result = this.value.compareTo(o.value);
        return result;
    }
}
