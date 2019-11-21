package com.alta_v2.physicsModule;

import com.alta_v2.renderingModule.tiledMapScreen.TiledMapState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Provides the engine for calculation on tiled map.
 */
public class TiledMapPhysicEngine {

    private final AltitudeMap altitudeMap;

    private Vector2 focusPointLocal;
    private Vector2 focusPointGlobal;
    private Vector2 actorGlobal;

    /**
     * Initialize new instance of {@link TiledMapPhysicEngine}.
     *
     * @param focusPointCoordinates - the coordinates of focus point on tiled map.
     * @param mapPath               - the path to tiled map in assets.
     */
    public TiledMapPhysicEngine(Vector2 focusPointCoordinates, String mapPath) {
        this.focusPointLocal = focusPointCoordinates;
        this.altitudeMap = TiledMapParser.parse(mapPath);
    }

    public void init() {
        this.focusPointGlobal = new Vector2(
                TiledMapPhysicCalculator.focusPointCoordinate(this.focusPointLocal.x, this.altitudeMap.getTileWidth()),
                TiledMapPhysicCalculator.focusPointCoordinate(this.focusPointLocal.y, this.altitudeMap.getTileHeight())
        );

        this.actorGlobal = new Vector2(
                TiledMapPhysicCalculator.playerCoordinate(this.altitudeMap.getTileWidth(), Gdx.graphics.getWidth()),
                TiledMapPhysicCalculator.playerCoordinate(this.altitudeMap.getTileHeight(), Gdx.graphics.getHeight())
        );
    }

    /**
     * Updates the tiled map state.
     *
     * @param state - the state to be used in initialization.
     */
    public void updateState(TiledMapState state) {
        state.updateMapCoordinates(this.focusPointGlobal.x, this.focusPointGlobal.y);
        state.updateActorCoordinates(this.actorGlobal.x, this.actorGlobal.y);
    }

}
