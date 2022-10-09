package com.cs590.springbatch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;

    public String getRole() {
        return role;
    }

    public Role(String role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}