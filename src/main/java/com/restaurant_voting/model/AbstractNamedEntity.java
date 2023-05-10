package com.restaurant_voting.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(max = 128)
    protected String name;

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        String id = super.toString();
        return id + ", name=" + name;
    }
}
