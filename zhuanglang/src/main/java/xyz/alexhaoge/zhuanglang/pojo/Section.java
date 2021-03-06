package xyz.alexhaoge.zhuanglang.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "section")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@NamedEntityGraph(
    name = "sectionGraph",
    attributeNodes = {
        @NamedAttributeNode("lessons")
    }
)
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "note", length=255)
    private String note;

    @JoinColumn(name = "upload", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JsonIgnoreProperties({"books","sections","lessons","resources","password","salt","nkuorzl","personNo","mail"})
    private Teacher upload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belong", referencedColumnName = "id")
    @JsonIgnoreProperties({"sections","upload"})
    private Book belong;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "belong")
    @NotFound(action = NotFoundAction.IGNORE)
    @OrderBy("number ASC")
    @JsonIgnoreProperties({"belong","upload"})
    private List<Lesson> lessons;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public int getNumber(){
        return number;
    }
    public void setNumber(int number){
        this.number = number;
    }

    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public Book getBelong(){
        return belong;
    }
    public void setBelong(Book belong){
        this.belong = belong;
    }

    public Teacher getUpload(){
        return upload;
    }
    public void setUpload(Teacher upload){
        this.upload = upload;
    }

    public List<Lesson> getLessons(){
        return lessons;
    }
    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
    }
}