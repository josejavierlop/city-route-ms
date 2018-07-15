package es.jjlop.cityserver.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="routes")
public class Route {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private City origin;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private City destination;

    @OneToMany( targetEntity=Step.class, mappedBy = "route", fetch = FetchType.EAGER )
    private List<Step> steps;

    @java.beans.ConstructorProperties({"id", "origin", "destination", "steps"})
    public Route(Long id, City origin, City destination, List<Step> steps) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.steps = steps;
    }

    public Route() {
    }

    public Long getId() {
        return this.id;
    }

    public City getOrigin() {
        return this.origin;
    }

    public City getDestination() {
        return this.destination;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Route)) return false;
        final Route other = (Route) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$origin = this.getOrigin();
        final Object other$origin = other.getOrigin();
        if (this$origin == null ? other$origin != null : !this$origin.equals(other$origin)) return false;
        final Object this$destination = this.getDestination();
        final Object other$destination = other.getDestination();
        if (this$destination == null ? other$destination != null : !this$destination.equals(other$destination))
            return false;
        final Object this$steps = this.getSteps();
        final Object other$steps = other.getSteps();
        if (this$steps == null ? other$steps != null : !this$steps.equals(other$steps)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $origin = this.getOrigin();
        result = result * PRIME + ($origin == null ? 43 : $origin.hashCode());
        final Object $destination = this.getDestination();
        result = result * PRIME + ($destination == null ? 43 : $destination.hashCode());
        final Object $steps = this.getSteps();
        result = result * PRIME + ($steps == null ? 43 : $steps.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Route;
    }

    public String toString() {
        return "Route(id=" + this.getId() + ", origin=" + this.getOrigin() + ", destination=" + this.getDestination() + ", steps=" + this.getSteps() + ")";
    }
}
