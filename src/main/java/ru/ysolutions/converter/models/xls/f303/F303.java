package ru.ysolutions.converter.models.xls.f303;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class F303 {
    private LocalDate reportDate;
    private List<F303Contract> contracts;

    public void addContract(F303Contract contract) {
        if (CollectionUtils.isEmpty(contracts)){
            this.contracts = new ArrayList<>();
        }
        this.contracts.add(contract);
    }
}
