package com.activitystream;

public class CommentEntityRoleType {
    public static final CommentEntityRoleType COMMENT = new CommentEntityRoleType("COMMENT");
    public static final CommentEntityRoleType COMMENTS = COMMENT.extend("COMMENTS");
    public static final CommentEntityRoleType COMMENTED_ON = COMMENT.extend("COMMENTED_ON");
    public static final CommentEntityRoleType MENTIONS = COMMENT.extend("MENTIONS");
    private final String role;

    private CommentEntityRoleType(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }

    public CommentEntityRoleType extend(String ext) {
        return new CommentEntityRoleType(role + ":" + ext);
    }
}
