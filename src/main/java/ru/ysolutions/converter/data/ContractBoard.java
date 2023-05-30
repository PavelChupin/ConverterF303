package ru.ysolutions.converter.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@ToString
@Accessors(fluent = true)
public class ContractBoard {
    private Integer startNumRow;
    private Integer endNumRow;
    private List<ContractBoard> tranches;

    public Integer getNumRowEndContract() {
        if (CollectionUtils.isNotEmpty(tranches)) {
            return tranches
                    .stream()
                    .mapToInt(ContractBoard::startNumRow)
                    .min()
                    .orElse(this.endNumRow) - 1;
        }

        return this.endNumRow;
    }

    public void addTranche(ContractBoard contractBoard){
        if (CollectionUtils.isEmpty(tranches)){
            this.tranches = new ArrayList<>();
        }
        this.tranches.add(contractBoard);
    }

    public List<ContractBoard> tranches() {
        if (CollectionUtils.isEmpty(tranches)){
            this.tranches = new ArrayList<>();
        }

        return tranches;
    }
}
