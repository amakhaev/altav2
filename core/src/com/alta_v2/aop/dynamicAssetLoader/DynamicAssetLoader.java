package com.alta_v2.aop.dynamicAssetLoader;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides the annotation that loading of required external resource.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@BindingAnnotation
public @interface DynamicAssetLoader {

    /**
     * The list of textures to be loaded into {@link com.badlogic.gdx.assets.AssetManager}.
     */
    String[] textures() default {};

    /**
     * The list of tiled map textures to be loaded into {@link com.badlogic.gdx.assets.AssetManager}.
     */
    String[] tiledMap() default {};

}
