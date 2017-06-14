package ua.nure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"level", "statistics", "roles"})
@Table(name = "m_user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    @ColumnDefault("true")
    private boolean enabled;

    @OneToOne
    private Level level;

    @OneToMany(mappedBy = "user")
    private List<TaskStatistic> statistics;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserRole> roles;
}
