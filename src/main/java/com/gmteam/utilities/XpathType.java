package com.gmteam.utilities;

public enum XpathType {
    TEXT("text"), CLAZZ("class"), CONTENT_DESC("contentDescription"), CHECKABLE("checkable"), CHECKED("checked"),
    CLICKABLE("clickable"), ENABLED("enabled"), FOCUSABLE("focusable"), FOCUSED("focused"), SCROLLABLE("scrollable"),
    LONG_CLICKABLE("long-clickable"), PASSWORD("password"), SELECTED("selected");

    private final String val;

    private XpathType(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }

    @Override
    public String toString() {
        return val;
    }
}