package ch.fhnw.wodss.tippspiel.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class BetGroup {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @Column
    @Size(min = 10, max = 300)
    private String password;

    @Column
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer score;

    @Column
    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> members;

    public BetGroup(String name, String password, Integer score, List<User> members){
        this.name = name;
        this.password = password;
        this.score = score;
        this.members = members;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof BetGroup))return false;
        BetGroup otherBetGroup = (BetGroup) other;
        return otherBetGroup.getId().equals(this.getId());
    }
}
