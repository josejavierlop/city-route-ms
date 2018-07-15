package es.jjlop.calculationserver.contoller.vo;

import java.util.List;

public class RouteVO {

    private Long id;
    private String origin;
    private String destination;
    private Integer stepNumber;
    private Double totalDuration;
    private List<StepVO> steps;

    @java.beans.ConstructorProperties({"id", "origin", "destination", "stepNumber", "totalDuration", "steps"})
    public RouteVO(Long id, String origin, String destination, Integer stepNumber, Double totalDuration, List<StepVO> steps) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.stepNumber = stepNumber;
        this.totalDuration = totalDuration;
        this.steps = steps;
    }

    public RouteVO() {
    }

    public Long getId() {
        return this.id;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getDestination() {
        return this.destination;
    }

    public Integer getStepNumber() {
        return this.stepNumber;
    }

    public Double getTotalDuration() {
        return this.totalDuration;
    }

    public List<StepVO> getSteps() {
        return this.steps;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public void setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public void setSteps(List<StepVO> steps) {
        this.steps = steps;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof RouteVO)) return false;
        final RouteVO other = (RouteVO) o;
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
        final Object this$stepNumber = this.getStepNumber();
        final Object other$stepNumber = other.getStepNumber();
        if (this$stepNumber == null ? other$stepNumber != null : !this$stepNumber.equals(other$stepNumber))
            return false;
        final Object this$totalDuration = this.getTotalDuration();
        final Object other$totalDuration = other.getTotalDuration();
        if (this$totalDuration == null ? other$totalDuration != null : !this$totalDuration.equals(other$totalDuration))
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
        final Object $stepNumber = this.getStepNumber();
        result = result * PRIME + ($stepNumber == null ? 43 : $stepNumber.hashCode());
        final Object $totalDuration = this.getTotalDuration();
        result = result * PRIME + ($totalDuration == null ? 43 : $totalDuration.hashCode());
        final Object $steps = this.getSteps();
        result = result * PRIME + ($steps == null ? 43 : $steps.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof RouteVO;
    }

    public String toString() {
        return "RouteVO(id=" + this.getId() + ", origin=" + this.getOrigin() + ", destination=" + this.getDestination() + ", stepNumber=" + this.getStepNumber() + ", totalDuration=" + this.getTotalDuration() + ", steps=" + this.getSteps() + ")";
    }
}
