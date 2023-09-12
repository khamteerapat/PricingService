package com.axonstech.PricingService.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class LogisticExpensePayload {
    @NotNull
    @Size(max = 20)
    private String zoneArea;
    @NotNull
    @Size(max = 255)
    private String zoneClassPrice;
    @NotNull
    private LocalDate effectiveDate;
    @NotNull
    private String productGroup;
    @NotNull
    private String productGroupName;
    @NotNull
    private String productSubGroup1;
    @NotNull
    private String productSubGroup1Name;
    @NotNull
    private String productSubGroup2;
    @NotNull
    private String productSubGroup2Name;
    @NotNull
    private Double logisticExp;
    private LocalDate expireDate;
    @NotNull
    private String status;
}
