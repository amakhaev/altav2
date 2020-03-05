package com.alta_v2.rendering.tiledMapScreen.state;

import com.alta_v2.rendering.actors.PersonView;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.Setter;

/**
 * Provides the state of {@link com.alta_v2.rendering.actors.player.PlayerActor}
 */
public class PlayerState {

    @Getter
    private Vector2 coordinates = new Vector2();

    @Getter
    @Setter
    private PersonView view = PersonView.FULL_FACE;

    @Getter
    @Setter
    private boolean isAnimationEnabled = false;

    @Getter
    @Setter
    private long animationChangeTime;

    public void updateCoordinates(float x, float y) {
        this.coordinates.x = x;
        this.coordinates.y = y;
    }

}
