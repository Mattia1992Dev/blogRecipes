package com.recipe.blogRecipes.entity.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class CreationUpdate extends Creation implements Serializable {

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
