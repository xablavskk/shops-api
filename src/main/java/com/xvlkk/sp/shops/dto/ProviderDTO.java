package com.xvlkk.sp.shops.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link com.xvlkk.sp.shops.model.Provider}
 */
@Setter
@Getter
public class ProviderDTO implements Serializable {
    private Long cdProvider;
    private String nmProvider;
}