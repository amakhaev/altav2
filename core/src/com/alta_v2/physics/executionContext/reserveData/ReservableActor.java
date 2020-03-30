package com.alta_v2.physics.executionContext.reserveData;

import com.alta_v2.physics.executionContext.Reservable;
import lombok.Getter;

/**
 * Describes value that is distributable for read/write access.
 */
public class ReservableActor extends Reservable<Boolean> {

    @Getter
    private final int id;

    @Getter
    private final ReservablePoint globalPoint;

    @Getter
    private final ReservablePoint localPoint;

    @Getter
    private final ReservablePersonView view;

    @Getter
    private final ReservableBoolean isMoving;

    /**
     * Initialize new instance of {@link ReservableActor}.
     * @param id - the identifier of actor.
     */
    public ReservableActor(int id) {
        this.id = id;
        this.globalPoint = new ReservablePoint();
        this.view = new ReservablePersonView();
        this.isMoving = new ReservableBoolean();
        this.localPoint = new ReservablePoint();
        this.value = false;
    }
}
