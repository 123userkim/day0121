package com.sist.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.GoodsDAO;
import com.sist.vo.GoodsVO;

@Controller
public class GoodsController {
	@Autowired
	private GoodsDAO dao;

	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}
	//-----------------------리스트보기-----------------------
	@RequestMapping("/listGoods.do")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", dao.findAll());
		return mav;
	}
	
	
	//-----------------------추가하기-----------------------
	@RequestMapping(value = "/insertGoods.do", method = RequestMethod.GET)
	public void insertForm() {		
	}
	
	@RequestMapping(value = "/insertGoods.do", method = RequestMethod.POST)
	public ModelAndView insertSubmit(
			HttpServletRequest request, //업로드할 폴더의 실경로를 알아오기 위하여 requst변수를 매개변수로 선언합니다. 
			GoodsVO g) {
		
		
		//request객체를 통하여 업로드할 폴더의 실제경로을 옴
		String path = request.getRealPath("images");
		System.out.println("path:"+path);
		
		
		//vo에 업로드한 파일의 정보를 받아 옵니다.
		MultipartFile uploadFile = g.getUploadFile();
		
		//업로드한 파일이름을 알아봅니다.
		String fname = uploadFile.getOriginalFilename();
		
		
		byte[] data ;
		try {
			//업로드한 파일의 내용을 받아옴
			data = uploadFile.getBytes();
		
			//서버에 파일을 출력하기 위한 스트림을 생성
			FileOutputStream fos = new FileOutputStream(path + "/"+ fname);
			
			//서버에 파일을 출력
			fos.write(data);		
			
			fos.close();
		}catch (Exception e) {
		}
		
		g.setFname(fname);
		int re = dao.insert(g);
		ModelAndView mav = new ModelAndView("redirect:/listGoods.do");
		if(re != 1) {
			mav.setViewName("error");
			mav.addObject("msg", "상품등록에 실패하였습니다.");
		}
		return mav;
	}
	
	
	//-----------------------자세히-----------------------
	@RequestMapping("/detailGoods.do")	
	public ModelAndView detail(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", dao.findByNo(no));
		return mav;
	}
	
	
	//-----------------------수정하기-----------------------
	@RequestMapping(value = "/updateGoods.do", method = RequestMethod.GET)
	public ModelAndView updateForm(int no) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", dao.findByNo(no));
		return mav;
	}
	
	@RequestMapping(value = "/updateGoods.do", method = RequestMethod.POST)
	public ModelAndView updateSubmit(HttpServletRequest request, GoodsVO g) {
		String path= request.getRealPath("images");
		
		//원래사진이름을 미리 변수에 담기
		String oldFname = g.getFname();
		
		//업로드 파일의 정보를 받아옴
		MultipartFile uploadFile = g.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		byte []data;
		try {
			data = uploadFile.getBytes(); 
			//만약에 사진도 수정했다면 파일을 복사
			if(fname!=null && !fname.equals("")){
				FileOutputStream fos = new FileOutputStream(path+"/"+fname);
				fos.write(data);
				fos.close();
				g.setFname(fname);
			}
		}catch(Exception e) {
			
		}
				
		ModelAndView mav = new ModelAndView("redirect:/listGoods.do");
		int re = dao.update(g);
		if(re != 1) {
			mav.setViewName("error");
			mav.addObject("msg", "상품수정에 실패하였습니다.");
		}else {//수정에 성공했고
			if(fname!=null & !fname.equals("")) { //사진도 수정했다면
				File file = new File(path+"/"+oldFname);
				file.delete();
			}
		}
		return mav;
	}
	//-----------------------삭제하기-----------------------
	@RequestMapping("/deleteGoods.do")
	public ModelAndView delete(HttpServletRequest request, int no) {
		String path = request.getRealPath("images"); //경로
	 
		String oldFname =dao.findByNo(no).getFname();
		ModelAndView mav = new ModelAndView("redirect:/listGoods.do");
		int re = dao.delete(no);
		if(re != 1) {
			mav.setViewName("error");
			mav.addObject("msg", "상품삭제에 실패하였습니다.");
		}else {
			 
			File file = new File(path+"/"+oldFname);
			file.delete();
		}
		return mav;
	}
	
}