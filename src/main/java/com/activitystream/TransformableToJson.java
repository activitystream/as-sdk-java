package com.activitystream;

/**
 * Implement this interface in all classes that should be tranformable into JSON format
 *  
 * @author milan
 *
 */
public interface TransformableToJson {

    public String toJson();
}
