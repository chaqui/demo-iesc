package com.demo.pagos.constants;

public enum State {

    CREATED(1L),
    PAID(2L),
    SEND(3L),
    CANCELLED(4L);

    private Long id;

    State(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
