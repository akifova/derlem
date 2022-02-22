package com.kazu.carp.app.validation.common;

import lombok.*;

/**
 * @author akifova
 * 20.09.2021
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class KazuError {
    private String name;
    private String type;
    private String message;
    private String code;
    private String trace;
}
