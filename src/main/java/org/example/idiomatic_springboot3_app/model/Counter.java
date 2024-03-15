package org.example.idiomatic_springboot3_app.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "counters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Counter {

    private @Id String name;
    private int count;
}
