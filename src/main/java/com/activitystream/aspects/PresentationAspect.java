package com.activitystream.aspects;

import java.net.URL;

public class PresentationAspect extends AspectBase{

    public static final String PRESENTATION_LABEL = "presentation.label";
    public static final String PRESENTATION_DESCRIPTION = "presentation.description";
    public static final String PRESENTATION_URL = "presentation.details_url";
    public static final String PRESENTATION_THUMBNAIL = "presentation.thumbnail";
    public static final String PRESENTATION_ICON = "presentation.icon";

    public PresentationAspect() {
        aspectPropertyMap.put(PRESENTATION_LABEL, new AspectProperty(IsRequired.False));
        aspectPropertyMap.put(PRESENTATION_DESCRIPTION, new AspectProperty(IsRequired.False));
        aspectPropertyMap.put(PRESENTATION_URL, new AspectProperty(IsRequired.False));
        aspectPropertyMap.put(PRESENTATION_THUMBNAIL, new AspectProperty(IsRequired.False));
        aspectPropertyMap.put(PRESENTATION_ICON, new AspectProperty(IsRequired.False));
    }

    public PresentationAspect label(String label) {
        aspectPropertyMap.get(PRESENTATION_LABEL).value = label;
        return this;
    }
    public PresentationAspect description(String description) {
        aspectPropertyMap.get(PRESENTATION_DESCRIPTION).value = description;
        return this;
    }
    public PresentationAspect detailsUrl(URL detailsUrl) {
        aspectPropertyMap.get(PRESENTATION_URL).value = detailsUrl.toString();
        return this;
    }
    public PresentationAspect thumbnail(URL thumbnail) {
        aspectPropertyMap.get(PRESENTATION_THUMBNAIL).value = thumbnail.toString();
        return this;
    }
    public PresentationAspect icon(URL icon) {
        aspectPropertyMap.get(PRESENTATION_ICON).value = icon.toString();
        return this;
    }
}
