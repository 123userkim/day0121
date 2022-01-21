package com.sist.db;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.GoodsVO;
 

public class DBManager {
	private static SqlSessionFactory factory;
	
	//SqlSessionFactory가 자동으로 동작하기 위함
	static {
		try {
			Reader reader =
					Resources.getResourceAsReader("com/sist/db/sqlMapConfig.xml");			
			
			factory = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
		}catch(Exception e) {
			System.out.println("예외발생 : "+e.getMessage());
		}
	}
	
	public static List<GoodsVO> findAll(){
		SqlSession session = factory.openSession();
		List<GoodsVO>list=	session.selectList("goods.findAll");
		session.close();
		return list;
	}
	
	public static int insert(GoodsVO g) {
		SqlSession session= factory.openSession();
		int re = session.insert("goods.insert",g);
		session.commit();
		session.close();
		return re;
	}
	
	public static int update(GoodsVO g) {
		SqlSession session = factory.openSession();
		int re = session.update("goods.update",g);
		session.commit();
		session.close();
		return re;
	}
	
	public static int delete(int no) {
		SqlSession session = factory.openSession();
		int re = session.delete("goods.delete",no);
		session.commit();
		session.close();
		return re;
	}
	
	public static GoodsVO findByNo(int no) {
		SqlSession session = factory.openSession();
		GoodsVO g= 	session.selectOne("goods.findByNo",no);
		//반환되는 것이 한 건이라서 selectOne
		session.close();
		return g;
	}
}
