package es.jjlop.cityserver.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="destination")
    private String destination;

    @Column(name="departure_time")
    private String departureTime;

    @Column(name="arrival_time")
    private String arrivalTime;


    @java.beans.ConstructorProperties({"id", "name", "destination", "departureTime", "arrivalTime"})
    public City(Long id, String name, String destination, String departureTime, String arrivalTime) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public City() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public String getArrivalTime() {
        return this.arrivalTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof City)) return false;
        final City other = (City) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$destination = this.getDestination();
        final Object other$destination = other.getDestination();
        if (this$destination == null ? other$destination != null : !this$destination.equals(other$destination))
            return false;
        final Object this$departureTime = this.getDepartureTime();
        final Object other$departureTime = other.getDepartureTime();
        if (this$departureTime == null ? other$departureTime != null : !this$departureTime.equals(other$departureTime))
            return false;
        final Object this$arrivalTime = this.getArrivalTime();
        final Object other$arrivalTime = other.getArrivalTime();
        if (this$arrivalTime == null ? other$arrivalTime != null : !this$arrivalTime.equals(other$arrivalTime))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $destination = this.getDestination();
        result = result * PRIME + ($destination == null ? 43 : $destination.hashCode());
        final Object $departureTime = this.getDepartureTime();
        result = result * PRIME + ($departureTime == null ? 43 : $departureTime.hashCode());
        final Object $arrivalTime = this.getArrivalTime();
        result = result * PRIME + ($arrivalTime == null ? 43 : $arrivalTime.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof City;
    }

    public String toString() {
        return "City(id=" + this.getId() + ", name=" + this.getName() + ", destination=" + this.getDestination() + ", departureTime=" + this.getDepartureTime() + ", arrivalTime=" + this.getArrivalTime() + ")";
    }
}
