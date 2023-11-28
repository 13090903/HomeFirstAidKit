package com.study.project.util.exceptions;

public class IllnessNotFoundException extends RuntimeException{
    public IllnessNotFoundException() {
        super("Illness not found");
    }
}
