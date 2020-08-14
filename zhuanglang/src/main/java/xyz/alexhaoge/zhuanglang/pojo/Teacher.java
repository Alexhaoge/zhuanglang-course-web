package xyz.alexhaoge.zhuanglang.pojo;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "teacher")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class Teacher implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "pwd", nullable = false)
    private String password;

    @Column(name = "nku_or_zhuanglang", nullable = false)
    @Enumerated(EnumType.STRING)
    private NKUorZL nkuorzl;

    @Column(name = "name", nullable = false, columnDefinition = "varchar(20) not null")
    private String name;

    @Column(name = "person_no", columnDefinition = "varchar(10)")
    private String personNo;

    @Column(name = "mail", nullable = false, columnDefinition = "varchar(30) not null")
    private String mail;

    @OneToMany(targetEntity = Book.class, mappedBy = "username")
    private Set<Book> bookCollection = new HashSet<>();

    @OneToMany(targetEntity = Section.class, mappedBy = "username")
    private Set<Section> sectionCollection = new HashSet<>();

    @OneToMany(targetEntity = Lesson.class, mappedBy = "username")
    private Set<Lesson> classCollection = new HashSet<>();

    @OneToMany(targetEntity = Resource.class, mappedBy = "username")
    private Set<Resource> resourceCollection = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    /**
     * 
     * @param password must in sha-1 format
     */
    public void setPassword(String password) {
        this.password = password;
    }


}