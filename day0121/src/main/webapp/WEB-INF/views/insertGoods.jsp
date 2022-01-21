<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>상품등록</h2>
	<form action="insertGoods.do" method="post" enctype="multipart/form-data">
		상품번호 <input type="text" name="no"><br>
		상품이름 <input type="text" name="name"><br>
		상품가격 <input type="text" name="price"><br>
		상품수량 <input type="text" name="qty"><br>
		<!-- 업로드할 파일의 입력상자의 이름은 vo의 것과 일치시킴 -->
		상품사진 <input type="file" name="uploadFile"><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>