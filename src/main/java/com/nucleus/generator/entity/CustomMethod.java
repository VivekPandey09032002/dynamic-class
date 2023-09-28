package com.nucleus.generator.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
public class CustomMethod {
    @Id
    private String methodName;

    private String methodDescription;
}
