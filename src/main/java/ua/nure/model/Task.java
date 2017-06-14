package ua.nure.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = {""})
@EqualsAndHashCode(exclude = {"owner", "level", "labels"})
@Entity
public class Task {

    @Id @GeneratedValue
    private long id;

    private String name;
    private String description;

    @Lob private String source;
    @Lob private String test;

    @ManyToOne
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Level level;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Label> labels;
}
