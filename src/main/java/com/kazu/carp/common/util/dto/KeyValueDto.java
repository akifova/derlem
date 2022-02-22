package com.kazu.carp.common.util.dto;

import lombok.*;

/**
 * @author akifova
 * 15.08.2021
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class KeyValueDto {
    private String key;
    private String value;
}
