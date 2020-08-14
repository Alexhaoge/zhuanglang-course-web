package xyz.alexhaoge.zhuanglang.pojo;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "discipline", nullable = false)
    @Enumerated(EnumType.STRING)
    Discipline discipline;

    @JoinColumn(name = "upload", referencedColumnName = "username")
    @ManyToOne
    private Teacher upload;

    @OneToMany(targetEntity = Section.class, mappedBy = "username")
    private Set<Section> sectionCollection = new HashSet<>();

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Discipline getDiscipline(){
        return discipline;
    }
    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }
}