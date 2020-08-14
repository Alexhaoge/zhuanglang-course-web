package xyz.alexhaoge.zhuanglang.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    @ManyToOne
    private Teacher upload;

    @JoinColumn(name = "belong", referencedColumnName = "id")
    @ManyToOne
    private Section belong;

    @OneToMany(targetEntity = Resource.class, mappedBy = "id")
    private Set<Resource> resourceCollection = new HashSet<>();

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

    public Section getBelong(){
        return belong;
    }
    public void setBelong(Section belong){
        this.belong = belong;
    }

    public Teacher getUpload(){
        return upload;
    }
    public void setUpload(Teacher upload){
        this.upload = upload;
    }
}