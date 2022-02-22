package com.kazu.carp.common.file.userFile.domain;

import com.kazu.carp.app.jpa.BaseEntity;
import com.kazu.carp.design.plan.domain.DesignPlanToUserFile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.AttributeAccessor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.List;

@Entity
@AttributeAccessor(value = "field")
@Table(schema = "common", name = "user_file",
        indexes = {@Index(name = "ix_user_file_file_name", columnList = "file_name")})

@Getter
@Setter
@NoArgsConstructor
@Audited
@SuperBuilder
public class UserFile extends BaseEntity {

    @Column(name = "file_name", nullable = false, length = 256)
    private String fileName;

    @Column(name = "original_file_name", nullable = false, length = 256)
    private String originalFileName;

    @Column(name = "location", nullable = false, length = 1000)
    private String location;

    @Column(name = "url", nullable = false, length = 1000)
    private String url;

    @Column(name = "file_type", length = 100)
    private String fileType;

    @Column(name = "file_size", nullable = false)
    private long fileSize;

    @OneToMany(mappedBy = "userFile", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DesignPlanToUserFile> designPlanToFiles;
}
    
