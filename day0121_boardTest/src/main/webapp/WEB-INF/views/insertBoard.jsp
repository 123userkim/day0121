<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="listBoard.do?no=${b.no }">목록</a>
	<h2>게시물등록</h2>
	<form action="insertBoard.do" method="post"  enctype="multipart/form-data">
		게시물번호 : <input type="text" name="no"><br>
		제목 : <input type="text" name="title"><br>
		작성자 : <input type="text" name="writer"><br>
		내용 : <br><textarea rows=" 10" cols="80" name="content"></textarea>
		첨부파일 : <input type="file" name="uploadFile"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html> 