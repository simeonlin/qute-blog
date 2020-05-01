package ch.hftm.vsblog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Entry  extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public Long id;
    
    @NotBlank(message="Title may not be blank")
    public String title;
    public String author;
    public String content;
}