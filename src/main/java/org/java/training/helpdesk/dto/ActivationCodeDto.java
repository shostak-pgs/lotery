package org.java.training.helpdesk.dto;

import java.util.Objects;

public class ActivationCodeDto {
    private String activationCode;

    public ActivationCodeDto() {
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationCodeDto that = (ActivationCodeDto) o;
        return Objects.equals(activationCode, that.activationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activationCode);
    }

    @Override
    public String toString() {
        return "ActivationCodeDto{" +
                "activationCode='" + activationCode + '\'' +
                '}';
    }
}