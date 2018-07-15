package es.jjlop.calculationserver.contoller.vo;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponseVO<T> {
    private T results;

    public ResponseVO(T results) {
        this.results = results;
    }

    public T getResults() {
        return this.results;
    }
}
