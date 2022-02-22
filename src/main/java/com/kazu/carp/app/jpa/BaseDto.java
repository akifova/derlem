package com.kazu.carp.app.jpa;

import com.kazu.carp.app.validation.common.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by sarparda on 31.07.2016.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class BaseDto implements Serializable {
    private static final long serialVersionUID = -8426110490108339844L;

    @Schema(
            description = "Satır ID Numarası"
    )
    @NotEmpty(groups = OnUpdate.class, message = "{id.field.isEmpty}")
    private String id;

    @Schema(title = "Kayıt Yapan Kullanıcı Maili", accessMode = Schema.AccessMode.READ_ONLY)
    private String savesUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(title = "Kayıt Tarihi", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime insertDate;
    @Schema(title = "Satır Aktiflik Durumu", accessMode = Schema.AccessMode.READ_ONLY)
    private boolean active;
    @Schema(title = "Satır Versiyon Numarası", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer version;
}
