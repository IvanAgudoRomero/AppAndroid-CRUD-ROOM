package com.example.trabajomovilesroom.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from user order by puntos desc")
    List<User> getAll();

    @Query("select * from user where id in (:userIds)")
    List<User> loadAllByIds(int userIds);

    @Query("select * from user where nombre in (:userNames)")
    List<User> loadAllByNames(String userNames);

    @Query("select * from user where nombre = (:userNames) and password = (:userPasswords)")
    List<User> verify(String userNames, String userPasswords);

    @Update
    void update(User user);

    @Insert
    void insert(User user);
}
