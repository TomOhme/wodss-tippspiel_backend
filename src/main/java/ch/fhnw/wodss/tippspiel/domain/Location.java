package ch.fhnw.wodss.tippspiel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class Location {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    public Location(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}