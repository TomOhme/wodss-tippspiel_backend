package ch.fhnw.wodss.tippspiel.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonAutoDetect
@NoArgsConstructor
public class GameDTO {

    @JsonProperty("started")
    private Boolean started;

    @JsonProperty("game_id")
    private long gameId;
    @JsonProperty("homeTeam_id")
    private long homeTeamId;
    @JsonProperty("awayTeam_id")
    private long awayTeamId;
    @JsonProperty("homeTeamName")
    private String homeTeamName;
    @JsonProperty("awayTeamName")
    private String awayTeamName;
    @JsonProperty("locationName")
    private String locationName;
    @JsonProperty("phaseName")
    private String phaseName;
    @JsonProperty("homeTeamGoals")
    private Integer homeTeamGoals;
    @JsonProperty("awayTeamGoals")
    private Integer awayTeamGoals;
    @JsonProperty("date")
    private String date;
    @JsonProperty("time")
    private String time;
    @JsonProperty("tournamentgroup")
    private String tournamentGroup;

}
