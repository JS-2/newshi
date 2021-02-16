package com.newha.mapper;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.HashTag;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.Post;
import com.newha.vo.PostTag;
import com.newha.vo.User;
import com.newha.vo.UserGoodNews;
import com.newha.vo.UserScrapNews;

public interface NewsMapper {
	public int insertNews(News news);
	public int insertImages(ArrayList<NewsImage> images);
	public int insertPost(Post post);
	public int insertUserScrapNews(UserScrapNews post);
	public int insertHashTag(String tag);
	public int insertPostTag(PostTag tag);
	public int insertUserGoodNews(UserGoodNews userGoodNews);
	public List<News> selectByUrl(String url);
	public List<Post> selectPostByName(String name);
	public List<Post> selectPostByLikeName(String name);
	public List<Post> selectPostById(String id);
	public List<Post> selectPostByTag(String tag);
	public User selectUserById(String id);
	public User selectUserByName(String name);
	public User selectUserByUserNo(String userNo);
	public List<UserScrapNews> selectUserScrapNewsByPostNo(String postNo);
	public List<UserScrapNews> selectUserScrapNewsByTagName(String tagName);
	public List<UserScrapNews> selectUserScrapNewsByUserNo(String userNo);
	public List<UserScrapNews> selectUserScrapNewsByNewsNo(String newsNo);
	public UserScrapNews selectUserScrapNewsByScrapNo(String scrapNo);
	public News selectNewsByScrapNo(String scrapNo);
	public List<News> selectAllNews();
	public List<HashTag> selectHashTagByName(String name);
	public UserGoodNews selectLikeNews(UserGoodNews userGoodNews);
	public UserGoodNews selectDisLikeNews(UserGoodNews userGoodNews);
	public List<UserGoodNews> selectUserGoodNewsByUserNo(String userNo);
	public List<NewsImage> selectNewImageByNewsNo(String newsNo);
	public List<News> selectNewsByTagName(String tagName);
	public List<News> selectNewsByUserNo(String userNo);
	public News selectNewsByNewsNo(String newsNo);
	public int deletePost(String postNo);
	public int deleteScrap(String scarpNo);
	public int updatePost(Post post);
	public int updateScrap(UserScrapNews scrap);
	public List<UserScrapNews> selectUserScrapNews(String userNo);
	public News selectNews(String newsNo);
	public int increaseLike(String scrapNo);
	public int increaseDisLike(String scrapNo);
	public int decreaseLike(String scrapNo);
	public int decreaseDisLike(String scrapNo);
	public int updateLike(UserGoodNews userGoodNews);
	public int updateDisLike(UserGoodNews userGoodNews);
	public int updateSave(UserGoodNews userGoodNews);
}
