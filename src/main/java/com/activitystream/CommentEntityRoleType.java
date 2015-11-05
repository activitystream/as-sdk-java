package com.activitystream;

public class CommentEntityRoleType {
    public static final CommentEntityRoleType COMMENTS = new CommentEntityRoleType("COMMENTS");
    public static final CommentEntityRoleType COMMENTED_ON = new CommentEntityRoleType("COMMENTED_ON");
    public static final CommentEntityRoleType MENTIONS = new CommentEntityRoleType("MENTIONS");
    private final String actor;

    private CommentEntityRoleType(String actor) {
        this.actor = actor;
    }

    public String value() {
        return actor;
    }

    public CommentEntityRoleType extend(String ext) {
        return new CommentEntityRoleType(actor + ":" + ext);
    }
}
