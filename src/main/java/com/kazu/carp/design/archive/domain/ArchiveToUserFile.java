package com.kazu.carp.design.archive.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.common.file.userFile.domain.UserFile;
import com.kazu.carp.design.archive.dto.ArchiveFileType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * @author akifova
 * 07.06.2021
 */
@Entity
@AttributeAccessor(value = "field")
@Table(name = "archive_to_user_file", schema = "design",
        indexes = {@Index(name = "ix_archive_to_user_file_archive", columnList = "archive"),
                @Index(name = "ix_archive_to_user_file_user_file", columnList = "user_file")})
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Audited
public class ArchiveToUserFile extends BaseEntity {
    @Column(name = "version_number", nullable = true, length = 30, columnDefinition = "SMALLINT default 0")
    private short versionNumber;

    @Column(name = "version_date", nullable = true)
    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime versionDate;

    @JoinColumn(name = "archive", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Archive archive;

    @JoinColumn(name = "user_file", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserFile userFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type", nullable = false, length = 30)
    private ArchiveFileType fileType;
}
    
