<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Đăng Ký</title>
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

	<jsp:include page="../header.jsp" />
	<%
		String baoLoi = request.getAttribute("baoLoi")+"";
		baoLoi = (baoLoi.equals("null"))?"":baoLoi;
		
		String tenDangNhap = request.getAttribute("tenDangNhap")+"";
		tenDangNhap = (tenDangNhap.equals("null"))?"":tenDangNhap;
		
		String hoVaTen = request.getAttribute("hoVaTen")+"";
		hoVaTen = (hoVaTen.equals("null"))?"":hoVaTen;
		
		String gioiTinh = request.getAttribute("gioiTinh")+"";
		gioiTinh = (gioiTinh.equals("null"))?"":gioiTinh;
		
		String ngaySinh = request.getAttribute("ngaySinh")+"";
		ngaySinh = (ngaySinh.equals("null"))?"":ngaySinh;
		
		String diaChiKhachHang = request.getAttribute("diaChiKhachHang")+"";
		diaChiKhachHang = (diaChiKhachHang.equals("null"))?"":diaChiKhachHang;
		
		String diaChiMuaHang = request.getAttribute("diaChiMuaHang")+"";
		diaChiMuaHang = (diaChiMuaHang.equals("null"))?"":diaChiMuaHang;
		
		String diaChiNhanHang = request.getAttribute("diaChiNhanHang")+"";
		diaChiNhanHang = (diaChiNhanHang.equals("null"))?"":diaChiNhanHang;
		
		String dienThoai = request.getAttribute("dienThoai")+"";
		dienThoai = (dienThoai.equals("null"))?"":dienThoai;
		
		String email = request.getAttribute("email")+"";
		email = (email.equals("null"))?"":email;
		
		String dongYNhanMail = request.getAttribute("dongYNhanMail")+"";
		dongYNhanMail = (dongYNhanMail.equals("null"))?"":dongYNhanMail;
		
	%>

	<div class="container">
		<h1 style="text-align: center;">ĐĂNG KÝ TÀI KHOẢN</h1>
		<div class="red" id="baoLoi">
			<%=baoLoi %>
		</div>
		<%
		 String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
		 %>
		<form class="form" action="<%=url %>/khach-hang" method="post">
		<input type="hidden" name="hanhDong" value="dang-ky" />
		<div class="row">
			<div class="col-md-6">
				<h2>Tài khoản</h2>
				<div class="mb-3">
					<label for="tenDangNhap" class="tenDangNhap">Tên đăng nhập<span class="red">*</span></label> <input
						type="text" class="form-control" id="tenDangNhap" name="tenDangNhap" required="required"  value="<%=tenDangNhap %>"	>
				</div>
				<div class="mb-3">
					<label for="matKhau" class="matKhau">Mật khẩu<span class="red">*</span></label> <input
						type="password" class="form-control" id="matKhau" name="matKhau" required="required" onkeyup="kiemTraMatKhau()">
				</div>
				<div class="mb-3">
					<label for="matKhauNhapLai" class="matKhauNhapLai">Nhập lại mật khẩu<span class="red">*</span> <span id="msg" class="red"></span> </label> <input
						type="password" class="form-control" id="matKhauNhapLai" name="matKhauNhapLai" required="required" onkeyup="kiemTraMatKhau()">
				</div>
				
				
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
					<label for="dongYDieuKhoan" class="form-label">Đồng ý <a>với điều khoản</a></label> <input
						type="checkbox" class="form-check-input" id="dongYDieuKhoan" name="dongYDieuKhoan" onchange="xuLyChonDongY()">
				</div>
				<div class="mb-3">
					<label for="dongYNhanMail" class="form-label">Đồng ý nhận email</label> <input
						type="checkbox" class="form-check-input" id="dongYNhanMail" name="dongYNhanMail">
				</div>
				<input type="submit" class="btn btn-primary form-control" value="submit" id="submit" name="submit" style="visibility: hidden ;"></input>
			</div>
		</div>
		</form>
	</div>
	<%@ include file ="../footer.jsp" %>
</body>

<script>
	function kiemTraMatKhau(){
		matKhau = document.getElementById("matKhau").value;
		matKhauNhapLai = document.getElementById("matKhauNhapLai").value;
		if(matKhau != matKhauNhapLai){
			document.getElementById("msg").innerHTML = "mật khẩu không khớp";
			return false;
		}else{
			document.getElementById("msg").innerHTML = "";
			return true;
		}
	}
	function xuLyChonDongY() {
		dongYDieuKhoan = document.getElementById("dongYDieuKhoan");
		if(dongYDieuKhoan.checked==true){
			document.getElementById("submit").style.visibility = "visible";
		}else{
			document.getElementById("submit").style.visibility = "hidden";
		}
	}
	
</script>
</html>