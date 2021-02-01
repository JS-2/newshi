package com.newha.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newha.vo.User;

public interface UserMapper {
	public ArrayList<User> selectAll();
	public ArrayList<Integer> follow(int userNo);

	public int insert(User u);
	public void delete(String id);
	public void update(User u);
	public User selectUser(int userNo);
	public User selectOne(User u);
	public User selectOneById(String id);
	public List<User> selectAllByKeyword(String keyword);
	public int selectId(String id);
	public int userNo(String id);
	
	public void thumbnailPath(@Param("userNo") String userNo, @Param("thumbnail_path") String thumbnail_path);
	public void inserttag(@Param("id")String id, @Param("name")String name);


}
