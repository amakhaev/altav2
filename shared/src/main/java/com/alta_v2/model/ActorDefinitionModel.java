package com.alta_v2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ActorDefinitionModel {

    private int id;
    private String texturePath;
    private float x;
    private float y;

}
