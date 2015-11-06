package com.activitystream.aspects;

import com.activitystream.Aspect;

import java.util.*;

public class AttachmentsAspect implements Aspect {

    private List<Attachment> attachment = new ArrayList<>();

    public AttachmentsAspect(Attachment... attachments) {
        this.attachment.addAll(Arrays.asList(attachments));
    }

    public AttachmentsAspect add(Attachment... attachments) {
        this.attachment.addAll(Arrays.asList(attachments));
        return this;
    }

    @Override
    public void addToObject(Map jsonObject, Set<String> processed) {
        List inv = new ArrayList();
        for (Attachment item : attachment) {
            if (item != null) inv.add(item.toJson());
        }
        jsonObject.put("attachments", inv);

    }
}
