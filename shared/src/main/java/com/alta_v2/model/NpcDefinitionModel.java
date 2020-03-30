package com.alta_v2.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NpcDefinitionModel extends ActorDefinitionModel {

    private int repeatMovementInterval = 5000;

}
