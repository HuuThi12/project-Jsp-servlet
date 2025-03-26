<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Store</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" >
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
	integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
	crossorigin="anonymous"></script>
</head>
	
<body>
	
	<jsp:include page="../header.jsp" />
	
<!-- dòng code 26 33
		kiểm tra xem khách hàng đã đăng nhập hay chưa
		nếu KH đã đăng nhập thì sẽ được lưu trong session	
	 -->
	<%
		Object obj = session.getAttribute("khachHang");
		KhachHang khachHang = null;
		if(obj!=null)
			khachHang = (KhachHang)obj;
			if(khachHang==null){
				
	%>
	<h1>Bạn chưa đăng nhập hệ thống. Vui lòng quay lại trang chủ</h1>
	<%
			}else{
				String baoLoi = request.getAttribute("baoLoi")+"";
				if(baoLoi.equals(null)){
					baoLoi = "";
				}
	%>
	<div class="container">
		<div class="text-center"><h1>ĐỔI MẬT KHẨU</h1></div>
		<span style="color: red">
		<%=baoLoi%>
		</span>
		<%
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
		%>
		<form  action="<%=url %>/khach-hang" method="post">
		<input type="hidden" name="hanhDong" value="doi-mat-khau"/>
		  <div class="form-group">
		    <label for="matKhauHienTai" class="form-label">Mật khẩu hiện tại</label>
		    <input type="password" class="form-control" id="matKhauHienTai" name="matKhauHienTai">
		  </div>
		  <div class="form-group">
		    <label for="matKhauMoi" class="form-label">Mật khẩu mới</label>
		    <input type="password" class="form-control" id="matKhauMoi" name="matKhauMoi">
		  </div>
		  <div class="form-group ">
		    <label for="matKhauNhapLai" class="form-label">Mật khẩu nhập lại</label>
		    <input type="password" class="form-control" id="matKhauNhapLai" name="matKhauNhapLai">
		  </div>
		  <button type="submit" class="btn btn-primary">Lưu mật khẩu</button>
		</form>
	</div>
	<%} %>
	
	<%@ include file ="../footer.jsp" %>
</body>
</html>