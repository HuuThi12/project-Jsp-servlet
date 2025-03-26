<%@page import="model.KhachHang"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Thay đổi thông tin</title>
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
<style>
	.red{
		color:red;
	}
</style>	
</head>
<body>
	
	<jsp:include page="../header.jsp"/>

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
	%>

 <div class="container">
	<%
		String baoLoi = request.getAttribute("baoLoi")+"";
		baoLoi = (baoLoi.equals("null"))?"":baoLoi;
		
		String hoVaTen = khachHang.getHoVaTen();
		
		String gioiTinh = khachHang.getGioiTinh();
		
		String ngaySinh = khachHang.getNgaySinh().toString();
		
		String diaChiKhachHang = khachHang.getDiaChi();
		
		String diaChiMuaHang = khachHang.getDiaChiMuaHang();
		
		String diaChiNhanHang = khachHang.getDiaChiNhanHang();
		
		String dienThoai = khachHang.getSoDienThoai();
		
		String email = khachHang.getEmail();
		
		boolean dongYNhanMail = khachHang.isDangKyNhanBangTin();
		
	%>

	<div class="container">
		<h1 style="text-align: center;">THAY ĐỔI THÔNG TIN</h1>
		<div class="red" id="baoLoi">
			<%=baoLoi %>
		</div>
		<%
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
		%>
		<form class="form" action="<%=url %>/khach-hang" method="post">
		<input type="hidden" name="hanhDong" value="thay-doi-thong-tin"/>
		<div class="row">
			<div class="col-md-6">
				
				
				<h2>Thông tin khách hàng</h2>
				<div class="mb-3">
					<label for="hoVaTen" class="hoVaTen">Họ và tên</label> <input
						type="text" class="form-control" id="hoVaTen" name="hoVaTen" value="<%=hoVaTen %>">
				</div>
				<div class="mb-3">
					<label for="gioiTinh" class="gioiTinh">Giới tính</label> <select
						class="form-control" id="gioiTinh" name="gioiTinh">
						<option></option>
						<option value="Nam" <%=(gioiTinh.equals("Nam"))?"selected='selected'": "" %> >Nam</option>
						<option value="Nữ"  <%=(gioiTinh.equals("Nữ"))?"selected='selected'": "" %> >Nữ</option>
						<option value="Khác"  <%=(gioiTinh.equals("Khác"))?"selected='selected'": "" %> >Khác</option>
					</select>
				</div>
				<div class="mb-3">
					<label for="ngaySinh" class="ngaySinh">Ngày sinh</label> <input
						type="date" class="form-control" id="ngaySinh" name="ngaySinh" value="<%=ngaySinh %>">
				</div>
			</div>
			
			
			<div class="col-md-6">
				<h2>Địa chỉ</h2>
				<div class="mb-3">
					<label for="diaChiKhachHang" class="form-label">Địa chỉ khách hàng</label> <input
						type="text" class="form-control" id="diaChiKhachHang" name="diaChiKhachHang" value="<%=diaChiKhachHang %>">
				</div>
				<div class="mb-3">
					<label for="diaChiMuaHang" class="form-label">Địa chỉ mua hàng</label> <input
						type="text" class="form-control" id="diaChiMuaHang" name="diaChiMuaHang" value="<%=diaChiMuaHang %>">
				</div>
				<div class="mb-3">
					<label for="diaChiNhanHang" class="form-label">Địa chỉ nhận hàng</label> <input
						type="text" class="form-control" id="diaChiNhanHang" name="diaChiNhanHang" value="<%=diaChiNhanHang %>">
				</div>
				<div class="mb-3">
					<label for="dienThoai" class="form-label">Điện thoại</label> <input
						type="tel" class="form-control" id="dienThoai" name="dienThoai" value="<%=dienThoai %>">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Email</label> <input
						type="email" class="form-control" id="email" name="email" value="<%=email %>">
				</div>
				
				
				<div class="mb-3">
					<label for="dongYNhanMail" class="form-label">Đồng ý nhận email</label> <input
						type="checkbox" class="form-check-input" id="dongYNhanMail" name="dongYNhanMail" <%=(dongYNhanMail?"checked":"") %>>
				</div>
				<input type="submit" class="btn btn-primary form-control" value="Lưu thông tin" id="submit" name="submit" ></input>
			</div>
		</div>
		</form>
	</div>
 </div>
 <%} %>
 
 <%@ include file="../footer.jsp" %>
</body>


</html>