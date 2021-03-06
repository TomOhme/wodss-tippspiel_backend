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
    @Column
    private Long id;

    @NotNull
    @JoinColumn
    @ManyToOne
    private User user;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer homeTeamGoals;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer awayTeamGoals;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer score;

    @JoinColumn
    @ManyToOne(fetch = FetchType.EAGER)
    private Game game;

    public Bet(Integer homeTeamGoals, Integer awayTeamGoals, Integer score, Game game, User user) {
        this.user = user;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.score = score;
        this.game = game;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Bet))return false;
        Bet otherBet = (Bet) other;
        return otherBet.getId().equals(this.getId());
    }
}
