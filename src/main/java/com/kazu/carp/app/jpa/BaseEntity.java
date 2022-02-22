package com.kazu.carp.app.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@Where(clause="is_active='true'")
public abstract class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1721800761158823311L;

    @Id
    @Column(name = "id", nullable = false, length = 36)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

	@Column(name = "saves_user", length = 36)
	private String savesUser;

	@Column(name = "insert_date", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime insertDate;

	@Column(name = "is_active", nullable = false)
	private boolean active;

	@Version
	private Integer version;

	@PrePersist
	protected void onCreate() {
		insertDate = LocalDateTime.now();
		var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		savesUser = "anonymous";
		try {
			if(user instanceof String) {
				savesUser = (String) user;
			} else {
				savesUser = ((User) user).getUsername();
			}
		} catch (Exception ex) {
			savesUser = "unknown";
		}

		version = 1;
		active = true;
	}
}
