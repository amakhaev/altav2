package com.alta_v2.rendering.tiledMapScreen.actors.npc;

import com.alta_v2.rendering.tiledMapScreen.actors.PersonActor;
import com.alta_v2.rendering.tiledMapScreen.state.NpcState;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class NpcActor extends PersonActor {

    private float stateTime;

    /**
     * Initialize new instance of {@link PersonActor}.
     *
     * @param texture    - the texture to be used for rendering.
     */
    public NpcActor(Texture texture, int tileWidth, int tileHeight) {
        super(texture, tileWidth, tileHeight);
    }

    /**
     * {@inheritDoc}
     */
    public void draw (Batch batch, float delta, NpcState npcState) {
        if (npcState == null) {
            log.debug("Npc state is null");
            return;
        }

        this.stateTime += delta;
        TextureRegion currentFrame = this.animations.get(npcState.getView()).getKeyFrame(this.stateTime, true);
        batch.draw(currentFrame, npcState.getCoordinates().x, npcState.getCoordinates().y);
    }
}
