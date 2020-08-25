package xyz.alexhaoge.zhuanglang.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import xyz.alexhaoge.zhuanglang.pojo.Lesson;
import xyz.alexhaoge.zhuanglang.pojo.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Integer> {
    
    List<Resource> findAllByBelong(Lesson belong);

    @Modifying
    @Query("update Resource r set r.name = ?1, r.note = ?2, r.bilibili = ?3 where r.id = ?4")
    void updateNameNoteAndBilibiliByID(String name, String note, String bilibili, int id);
}