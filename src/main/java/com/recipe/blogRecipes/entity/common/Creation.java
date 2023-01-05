package com.recipe.blogRecipes.entity.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class Creation implements Serializable {

    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime created_at;
}
