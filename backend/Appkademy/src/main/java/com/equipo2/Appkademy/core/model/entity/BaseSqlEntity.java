package com.equipo2.Appkademy.core.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
