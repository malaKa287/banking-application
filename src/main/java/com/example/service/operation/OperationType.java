package com.example.service.operation;

public enum OperationType {

    ENRICH("Enrich deposit"),
    //ENRICH,
    //WITHDRAW;
    WITHDRAW("Withdraw deposit");

    private final String displayType;

    private OperationType(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayType() {
        return displayType;
    }
}
