package com.capgemini.cappay.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class AccountDto {

    @NotNull
    private Long userId;

    @NotNull
    private String accountType;
}
