package xyz.alexhaoge.zhuanglang.dto;

/**
 * @deprecated
 */
@Deprecated
public class TeacherDTO {

    private String username;

    private String name;

    public TeacherDTO(String username, String name){
        this.username = username;
        this.name = name;
    }

    public String getUsername(){
        return username;
    }

    public String getName(){
        return name;
    }
}