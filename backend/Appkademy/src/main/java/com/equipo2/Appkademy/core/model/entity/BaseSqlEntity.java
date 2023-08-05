package com.equipo2.Appkademy.core.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSqlEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private ID id;

    @Nullable
    public ID getId(){
        return id;
    }

    protected void setId(@Nullable ID id){
        this.id = id;
    }


}
