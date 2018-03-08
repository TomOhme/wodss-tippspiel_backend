package ch.fhnw.wodss.tippspiel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
public class Bet {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer homeTeamGoals;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer awayTeamGoals;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer score;

    @Column
    @OneToMany(fetch = FetchType.EAGER)
    private Game game;

    public Bet(Long id, Integer homeTeamGoals, Integer awayTeamGoals, Integer score, Game game) {
        this.id = id;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.score = score;
        this.game = game;
    }
}