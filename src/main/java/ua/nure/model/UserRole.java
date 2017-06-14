package ua.nure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@EqualsAndHashCode(exclude = "user")
@Entity
public class UserRole {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private User user;
    private String role;

    @Override
    public String toString() {
        return role;
    }
}
