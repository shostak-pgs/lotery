package org.java.training.helpdesk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.search.annotations.Field;
import org.java.training.helpdesk.entity.enums.Role;
import org.java.training.helpdesk.entity.enums.State;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    private static final String DATE_PATTERN = "dd/MM/yyyy";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String password;

    @JsonFormat(pattern = DATE_PATTERN)
    private Date registrationDate;

    @JsonFormat(pattern = DATE_PATTERN)
    private Date loginDate;

    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<Comment> comments;

    private String activationCode;
    private boolean isEnabled;

    public User(){}

    public User(String firstName, String lastName, Role role, String email, String password, State state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.email = email;
        this.password = password;
        this.registrationDate = new Date();
        this.state = state;
        this.comments = new ArrayList<>();
        this.isEnabled = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                role == user.role &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", loginDate='" + loginDate + '\'' +
                ", state='" + state + '\'' +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }
}