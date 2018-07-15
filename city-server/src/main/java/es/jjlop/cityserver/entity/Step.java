package es.jjlop.cityserver.entity;

import javax.persistence.*;

@Entity
@Table(name="steps")
public class Step {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="route_id")
    private Route route;

    @Column(name="step_order")
    private Integer order;

    @ManyToOne
    @JoinColumn(name="origin_id")
    private City origin;

    @ManyToOne
    @JoinColumn(name="destination_id")
    private City destination;

    @Column(name="hours")
    private Double hours;

    @java.beans.ConstructorProperties({"id", "route", "order", "origin", "destination", "hours"})
    public Step(Long id, Route route, Integer order, City origin, City destination, Double hours) {
        this.id = id;
        this.route = route;
        this.order = order;
        this.origin = origin;
        this.destination = destination;
        this.hours = hours;
    }

    public Step() {
    }

    public Long getId() {
        return this.id;
    }

    public Route getRoute() {
        return this.route;
    }

    public Integer getOrder() {
        return this.order;
    }

    public City getOrigin() {
        return this.origin;
    }

    public City getDestination() {
        return this.destination;
    }

    public Double getHours() {
        return this.hours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setOrigin(City origin) {
        this.origin = origin;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Step)) return false;
        final Step other = (Step) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$route = this.getRoute();
        final Object other$route = other.getRoute();
        if (this$route == null ? other$route != null : !this$route.equals(other$route)) return false;
        final Object this$order = this.getOrder();
        final Object other$order = other.getOrder();
        if (this$order == null ? other$order != null : !this$order.equals(other$order)) return false;
        final Object this$origin = this.getOrigin();
        final Object other$origin = other.getOrigin();
        if (this$origin == null ? other$origin != null : !this$origin.equals(other$origin)) return false;
        final Object this$destination = this.getDestination();
        final Object other$destination = other.getDestination();
        if (this$destination == null ? other$destination != null : !this$destination.equals(other$destination))
            return false;
        final Object this$hours = this.getHours();
        final Object other$hours = other.getHours();
        if (this$hours == null ? other$hours != null : !this$hours.equals(other$hours)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $route = this.getRoute();
        result = result * PRIME + ($route == null ? 43 : $route.hashCode());
        final Object $order = this.getOrder();
        result = result * PRIME + ($order == null ? 43 : $order.hashCode());
        final Object $origin = this.getOrigin();
        result = result * PRIME + ($origin == null ? 43 : $origin.hashCode());
        final Object $destination = this.getDestination();
        result = result * PRIME + ($destination == null ? 43 : $destination.hashCode());
        final Object $hours = this.getHours();
        result = result * PRIME + ($hours == null ? 43 : $hours.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Step;
    }

    public String toString() {
        return "Step(id=" + this.getId() + ", route=" + this.getRoute() + ", order=" + this.getOrder() + ", origin=" + this.getOrigin() + ", destination=" + this.getDestination() + ", hours=" + this.getHours() + ")";
    }
}
