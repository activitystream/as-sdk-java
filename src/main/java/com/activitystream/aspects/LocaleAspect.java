package com.activitystream.aspects;

import java.util.TimeZone;

public class LocaleAspect extends AspectBase {

    public LocaleAspect() {
        aspectPropertyMap.put("locale.locale", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("locale.currency", new AspectProperty(IsRequired.False));
        aspectPropertyMap.put("locale.timezone", new AspectProperty(IsRequired.False));
    }

    public LocaleAspect locale(String locale) {
        aspectPropertyMap.get("locale.locale").value = locale;
        return this;
    }

    public LocaleAspect currency(String currency) {
        aspectPropertyMap.get("locale.currency").value = currency;
        return this;
    }

    public LocaleAspect timezone(TimeZone timezone) {
        aspectPropertyMap.get("locale.timezone").value = timezone.getID();
        return this;
    }
}
