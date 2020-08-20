package xyz.alexhaoge.zhuanglang.pojo;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "resources")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "path", nullable = false, length = 64)
    String path;

    @Column(name = "md5", nullable = true, length = 32)
    private String md5;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "videoorslide")
    @Enumerated(EnumType.STRING)
    private VorS vors;

    @Column(name = "bilibili", nullable = true, length = 64)
    private String bilibili;

    @Column(name = "note", length=255, nullable = true)
    String note;

    @JoinColumn(name = "upload", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.EAGER, optional = true, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JsonIgnoreProperties({"books","sections","lessons","resources","password","name","mail"})
    private Teacher upload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "belong", referencedColumnName = "id")
    @JsonIgnoreProperties({"resources","upload"})
    private Lesson belong;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getPath(){
        return path;
    }
    public void setPath(String path){
        this.path = path;
    }

    public String getMD5(){
        return md5;
    }
    public void setMD5(String md5){
        this.md5 = md5;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public VorS getVorS(){
        return vors;
    }
    public void setVorS(VorS vors){
        this.vors = vors;
    }

    public String getBilibili(){
        return bilibili;
    }
    public void setBilibili(String bilibili){
        this.bilibili = bilibili;
    }

    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }

    public Lesson getBelong(){
        return belong;
    }
    public void setBelong(Lesson belong){
        this.belong = belong;
    }

    public Teacher getUpload(){
        return upload;
    }
    public void setUpload(Teacher upload){
        this.upload = upload;
    }
}