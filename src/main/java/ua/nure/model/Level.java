package ua.nure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "tasks")
@ToString(exclude = "tasks")
@Entity
public class Level {

    @Id @GeneratedValue private long id;

    @OneToMany(mappedBy = "level", fetch = FetchType.EAGER)
    private Set<Task> tasks;
}
