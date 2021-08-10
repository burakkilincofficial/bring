package com.bring.project.bring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BaseEntity {
    @Column(name = "created_date",
            nullable = false,
            updatable = false,
            columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "last_modified_date")
    @UpdateTimestamp
    private Date lastModifiedDate;


}
