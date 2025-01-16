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

    public static State getById(Long id) {
        for (State state : State.values()) {
            if (state.getId().equals(id)) {
                return state;
            }
        }
        return null;
    }
}
