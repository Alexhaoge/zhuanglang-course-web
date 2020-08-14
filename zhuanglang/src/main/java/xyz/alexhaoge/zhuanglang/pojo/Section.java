package xyz.alexhaoge.zhuanglang.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "section")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
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
    @ManyToOne
    private Teacher upload;

    @JoinColumn(name = "belong", referencedColumnName = "id")
    @ManyToOne
    private Book belong;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "id")
    private Set<Lesson> lessonCollection = new HashSet<>();

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
}