package org.rodnet.tdddiversoscenarios.wrapMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

record Trabalho(LocalDate data, BigDecimal valor){
    public boolean feitoNeste(YearMonth anoMes) {
        return data.getYear() == anoMes.getYear() && data.getMonth() == anoMes.getMonth();
    }
}
