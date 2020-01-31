package com.alta_v2.physicsModule.executionContext;

import com.alta_v2.renderingModule.actors.PersonView;

public class ReservablePersonView extends SimpleReservable<PersonView> {

    /**
     * Initialize new instance of {@link ReservablePersonView}
     */
    public ReservablePersonView() {
        this.value = PersonView.FULL_FACE;
    }

    /**
     * Gets the current value of person view.
     */
    public PersonView getValue() {
        return this.value;
    }

}
