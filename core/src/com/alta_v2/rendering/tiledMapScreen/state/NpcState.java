package com.alta_v2.rendering.tiledMapScreen.state;

import com.alta_v2.rendering.tiledMapScreen.actors.PersonView;
import com.badlogic.gdx.math.Vector2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class NpcState {

    @Getter
    private final Integer id;

    @Getter
    private Vector2 coordinates = new Vector2();

    @Getter
    @Setter
    private PersonView view = PersonView.FULL_FACE;

    public void updateCoordinates(float x, float y) {
        this.coordinates.x = x;
        this.coordinates.y = y;
    }
}
