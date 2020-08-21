package xyz.alexhaoge.zhuanglang.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "teacher")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Teacher implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "username", length = 32, nullable = false)
    private String username;

    @Column(name = "pwd")
    private String password;

    @Column(name = "salt")
    private String salt;

    //命名不符合java规范会导致无法识别
    @Column(name = "nkuorzhuanglang", nullable = false)
    @Enumerated(EnumType.STRING)
    private NkuorZl nkuorzl;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "person_no", length = 10)
    private String personNo;

    @Column(name = "mail", nullable = false)
    private String mail;

    @OneToMany(targetEntity = Book.class, mappedBy = "upload")
    @MapKey(name = "id")
    @JsonIgnoreProperties({"upload","sections","belong"})
    private List<Book> books;

    @OneToMany(targetEntity = Section.class, mappedBy = "upload")
    @MapKey(name = "id")
    @JsonIgnoreProperties({"upload","lessons","belong"})
    private List<Section> sections;

    @OneToMany(targetEntity = Lesson.class, mappedBy = "upload")
    @MapKey(name = "id")
    @JsonIgnoreProperties({"upload","resources","belong"})
    private List<Lesson> lessons;

    @OneToMany(targetEntity = Resource.class, mappedBy = "upload")
    @MapKey(name = "id")
    @JsonIgnoreProperties({"upload","belong"})
    private List<Resource> resources;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //命名不符合java规范会导致无法识别
    public NkuorZl getNkuorzl() {
        return nkuorzl;
    }
    public void setNkuorzl(NkuorZl nkuorzl) {
        this.nkuorzl = nkuorzl;
    }

    public String getPersonNo(String personNo) {
        return personNo;
    }
    public void setPersonNo(String personNo) {
        this.personNo = personNo;
    }

    public String getMail(String mail) {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Book> getBooks(){
        return books;
    }
    public void setBooks(List<Book> books){
        this.books = books;
    }

    public List<Section> getSections(){
        return sections;
    }
    public void setSections(List<Section> sections){
        this.sections = sections;
    }

    public List<Lesson> getLessons(){
        return lessons;
    }
    public void setLessons(List<Lesson> lessons){
        this.lessons = lessons;
    }

    public List<Resource> getResources(){
        return resources;
    }
    public void setResources(List<Resource> resources){
        this.resources = resources;
    }
}