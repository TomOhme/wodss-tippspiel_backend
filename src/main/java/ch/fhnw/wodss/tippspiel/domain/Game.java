package ch.fhnw.wodss.tippspiel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue
    @NotNull
    @Column
    private Long id;

    @Column
    @NotNull
    @Max(Integer.MAX_VALUE)
    private Date dateTime;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer homeTeamGoals;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer awayTeamGoals;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private TournamentTeam homeTeam;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private TournamentTeam awayTeam;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Location location;

    @Column
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Phase phase;

    public Game(Long id, Date dateTime, Integer homeTeamGoals, Integer awayTeamGoals, TournamentTeam homeTeam, TournamentTeam awayTeam, Location location, Phase phase) {
        this.id = id;
        this.dateTime = dateTime;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
        this.phase = phase;
    }
}