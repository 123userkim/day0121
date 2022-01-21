package com.sist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sist.db.DBManager;
import com.sist.vo.GoodsVO;

@Repository
public class GoodsDAO {
	public List<GoodsVO> findAll(){
		return DBManager.findAll();
	}
	
	public int insert(GoodsVO g){
		return DBManager.insert(g);
	}
	
	public int update(GoodsVO g){
		return DBManager.update(g);
	}
	
	public int delete(int no){
		return DBManager.delete(no);
	}
	
	public GoodsVO findByNo(int no){
		return DBManager.findByNo(no);
	}
	
}
