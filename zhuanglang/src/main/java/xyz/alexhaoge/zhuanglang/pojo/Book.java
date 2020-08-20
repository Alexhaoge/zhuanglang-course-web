package xyz.alexhaoge.zhuanglang.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@NamedEntityGraph(
    name = "bookGraph",
    attributeNodes = {
        @NamedAttributeNode("sections")
    }
)
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
    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JsonIgnoreProperties({"books","sections","lessons","resources","password"})
    private Teacher upload;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "belong")
    @NotFound(action = NotFoundAction.IGNORE)
    @OrderBy("number ASC")
    @JsonIgnoreProperties({"belong","upload"})
    private List<Section> sections;

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

    public Teacher getUpload(){
        return upload;
    }
    public void setUpload(Teacher upload){
        this.upload = upload;
    }

    public List<Section> getSections(){
        return sections;
    }
    public void setSections(List<Section> sections){
        this.sections = sections;
    }
}