package ua.nure.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Table(name = "m_user")
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles;

    public User() {
        enabled = true;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled()
    {
        return enabled;
    }
    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public Set<UserRole> getRoles()
    {
        return roles;
    }
    public void setRoles(Set<UserRole> roles)
    {
        this.roles = roles;
    }
}
