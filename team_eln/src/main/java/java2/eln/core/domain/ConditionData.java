package java2.eln.core.domain;

import javax.persistence.*;
import java.time.Duration;

@Entity
@Table(name = "ConditionData")
public class ConditionData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "structure_solvent_id")
    private StructureData solvent;

    @Column(name = "temperature")
    private int temperature;

    @Column(name = "environment")
    private String environment;

    @Column(name = "pressure")
    private int pressure;

    @Column(name = "reactionTime")
    private Duration reactionTime;

    @Column(name = "reaction_id")
    private int reactionId;

    public ConditionData() {
    }

    public void setSolvent(StructureData solvent) {
        this.solvent = solvent;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public Duration getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(Duration reactionTime) {
        this.reactionTime = reactionTime;
    }

    @Override
    public String toString() {
        if (reactionTime == null) {reactionTime = Duration.ZERO;}
        double hours = (double)reactionTime.toMinutes() / 60;
        return "** baseClasses.ConditionData {" +
                "\n solvent = " + solvent +
                "\n temperature = " + temperature +
                "\n environment = '" + environment + '\'' +
                "\n pressure = " + pressure + " Bar" +
                "\n reactionTime=" + String.format("%.2f", hours) + " h" +
                "}";
    }
}
