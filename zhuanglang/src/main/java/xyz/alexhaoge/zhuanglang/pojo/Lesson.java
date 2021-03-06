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
@Table(name = "class")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
@NamedEntityGraph(
    name = "lessonGraph",
    attributeNodes = {
        @NamedAttributeNode("resources")
    }
)
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "note", length=255, nullable = true)
    private String note;

    @JoinColumn(name = "upload", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JsonIgnoreProperties({"books","sections","lessons","resources","password","salt","nkuorzl","personNo","mail"})
    private Teacher upload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belong", referencedColumnName = "id")
    @JsonIgnoreProperties({"lessons","upload"})
    private Section belong;

    @OneToMany(cascade = CascadeType.ALL ,targetEntity = Resource.class, mappedBy = "belong")
    @OrderBy("name ASC")
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonIgnoreProperties({"belong"})
    private List<Resource> resources;

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

    public Teacher getUpload(){
        return upload;
    }
    public void setUpload(Teacher upload){
        this.upload = upload;
    }

    public Section getBelong(){
        return belong;
    }
    public void setBelong(Section belong){
        this.belong = belong;
    }

    public List<Resource> getResources(){
        return resources;
    }
    public void setResources(List<Resource> resources){
        this.resources = resources;
    }
}