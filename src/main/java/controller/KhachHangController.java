package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.KhachHangDAO;
import model.KhachHang;
import util.Email;
import util.MaHoa;
import util.SoNgauNhien;

/**
 * Servlet implementation class KhachHangController
 */
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hanhDong = request.getParameter("hanhDong");
		if(hanhDong.equals("dang-nhap")) {
			dangNhap(request,response);
		}else if(hanhDong.equals("dang-xuat")) {
			dangXuat(request, response);
		}else if(hanhDong.equals("dang-ky")) {
			dangKy(request, response);
		}else if(hanhDong.equals("doi-mat-khau")) {
			doiMatKhau(request, response);
		}else if(hanhDong.equals("thay-doi-thong-tin")) {
			thayDoiThongTin(request, response);
		}else if(hanhDong.equals("xac-thuc")) {
			xacThuc(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dangNhap(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			matKhau = MaHoa.toSHA1(matKhau);
			
			
			KhachHang kh = new KhachHang();
			kh.setTenDangNhap(tenDangNhap);
			kh.setMatKhau(matKhau);
			
			KhachHangDAO khd = new KhachHangDAO();
			KhachHang khachHang = khd.selectByUsernameAndPassword(kh);
			String url = "";
			if(khachHang != null ) {
				HttpSession	session = request.getSession();
				session.setAttribute("khachHang", khachHang);
				url = "/index.jsp";
			}else {
				request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng / hoặc tài khoản xác thực chưa đúng");
				url = "/khachhang/dangnhap.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dangXuat(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			
			// huy bo session
			session.invalidate();
			String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath();
			response.sendRedirect(url+"/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dangKy(HttpServletRequest request, HttpServletResponse response) {
		try {
			String tenDangNhap = request.getParameter("tenDangNhap");
			String matKhau = request.getParameter("matKhau");
			String matKhauNhapLai = request.getParameter("matKhauNhapLai");
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");
			request.setAttribute("tenDangNhap", tenDangNhap);		
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("dongYNhanMail", dongYNhanMail);
			
			String url = "";
			
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();

			if(khachHangDAO.kiemTraTenDangNhap(tenDangNhap)) {
				baoLoi +="Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
			}
			
			if(!matKhau.equals(matKhauNhapLai)) {
				baoLoi +="Mẫu khẩu không khớp.<br/>";
			}else {
				matKhau = MaHoa.toSHA1(matKhau);
			}
			
			request.setAttribute("baoLoi", baoLoi);
			
			
			if(baoLoi.length()>0) {
				url = "/khachhang/dangky.jsp";
			}else {
				Random rd = new Random();
				String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) +"";
				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail!=null );
				if(khachHangDAO.insert(kh) >0){
					
					
					// dãy số xác thực
					String soNgauNhien = SoNgauNhien.getSoNgauNhien();
					
					// set thời gian hiệt lực
					Date todaysDate = new Date(new java.util.Date().getTime());
					Calendar c = Calendar.getInstance(); 
					c.setTime(todaysDate); 
					c.add(Calendar.DATE, 1);
					Date thoiGianHieuLucMaXacThuc = new Date(c.getTimeInMillis());
					
					// Trang thai xac thuc = false
					boolean trangThaiXacThuc = false;
					
					kh.setMaXacThuc(soNgauNhien);
					kh.setThoiGianHieuLucMaXacThuc(thoiGianHieuLucMaXacThuc);
					kh.setTrangThaiXacThuc(trangThaiXacThuc);
					
					if(khachHangDAO.updateVerifyInformation(kh)>0) {
						// gui email cho khach hang
						Email.senEmail(kh.getEmail(), "Xác thực tài khoản tạo BOOSTORE", getNoiDung(kh));
					}
				};
				url = "/khachhang/thanhcong.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void doiMatKhau(HttpServletRequest request, HttpServletResponse response) {
		try {
			String matKhauHienTai = request.getParameter("matKhauHienTai");
			String matKhauMoi = request.getParameter("matKhauMoi");
			String matKhauNhapLai = request.getParameter("matKhauNhapLai");
			
			String matKhauHienTai_Sha1 = MaHoa.toSHA1(matKhauHienTai);
			
			String baoLoi = "";
			String url = "/khachhang/doimatkhau.jsp";
			
			// kiểm tra mật khẩu cũ có giống hay không
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("khachHang");
			KhachHang khachHang = null;
			if( obj!= null) {
				khachHang = (KhachHang)obj;
			}
			if(khachHang == null) {
				baoLoi = "Bạn chưa đăng nhập hệ thống!";
			}else {
				// Nếu khách hàng đã đăng nhập
				if(!matKhauHienTai_Sha1.equals(khachHang.getMatKhau())) {
					baoLoi = "Mật khẩu hiện tại không chính xác!";
				}else {
					if(!matKhauMoi.equals(matKhauNhapLai)) {
						baoLoi = "Mật khẩu nhập lại không khớp!";
					}else {
						String matKhauMoi_Sha1 = MaHoa.toSHA1(matKhauMoi);
						khachHang.setMatKhau(matKhauMoi_Sha1);
						KhachHangDAO khd = new KhachHangDAO();
						if(khd.changePassword(khachHang)) {
							baoLoi = "Mật khẩu đã thay đổi thành công!";
						}else {
							baoLoi = "Thay đổi mật khẩu thất bại, vui lòng thử lại";
						}
				    } 
				}
			}
			request.setAttribute("baoLoi",baoLoi);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void thayDoiThongTin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String hoVaTen = request.getParameter("hoVaTen");
			String gioiTinh = request.getParameter("gioiTinh");
			String ngaySinh = request.getParameter("ngaySinh");
			String diaChiKhachHang = request.getParameter("diaChiKhachHang");
			String diaChiMuaHang = request.getParameter("diaChiMuaHang");
			String diaChiNhanHang = request.getParameter("diaChiNhanHang");
			String dienThoai = request.getParameter("dienThoai");
			String email = request.getParameter("email");
			String dongYNhanMail = request.getParameter("dongYNhanMail");		
			request.setAttribute("hoVaTen", hoVaTen);
			request.setAttribute("gioiTinh", gioiTinh);
			request.setAttribute("ngaySinh", ngaySinh);
			request.setAttribute("diaChiKhachHang", diaChiKhachHang);
			request.setAttribute("diaChiMuaHang", diaChiMuaHang);
			request.setAttribute("diaChiNhanHang", diaChiNhanHang);
			request.setAttribute("dienThoai", dienThoai);
			request.setAttribute("dongYNhanMail", dongYNhanMail);
			
			String url = "";
			
			String baoLoi = "";
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			
			request.setAttribute("baoLoi", baoLoi);
			
			
			if(baoLoi.length()>0) {
				url = "/khachhang/dangky.jsp";
			}else {
				Object obj = request.getSession().getAttribute("khachHang");
				KhachHang khachHang = null;
				if(obj!=null)
					khachHang = (KhachHang)obj;
					if(khachHang!=null) {
							String maKhachHang = khachHang.getMaKhacHang();
							KhachHang kh = new KhachHang(maKhachHang, "", "", hoVaTen, gioiTinh, diaChiKhachHang, diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail!=null);
							khachHangDAO.updateInfor(kh);
							KhachHang kh2 = khachHangDAO.selectById(kh);
							request.getSession().setAttribute("khachHang", kh2);
							url = "/khachhang/thanhcong.jsp";
					}
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void xacThuc(HttpServletRequest request, HttpServletResponse response) {
		try {
			String maKhachHang = request.getParameter("maKhachHang");
			String maXacThuc = request.getParameter("maXacThuc");
					
			request.setAttribute("maKhachHang", maKhachHang);
			request.setAttribute("maXacThuc", maXacThuc);
			
			String url = "";
			
			
			KhachHangDAO khachHangDAO = new KhachHangDAO();
			
			KhachHang kh = new KhachHang();
			kh.setMaKhacHang(maKhachHang);
			KhachHang khachHang = khachHangDAO.selectById(kh);
			
			
			
			String msg="";
			if(khachHang != null) {
				// kiem tra ma xac thuc co giong nhau hay không
				if(khachHang.getMaXacThuc().equals(maXacThuc)) {
					// thanh cong
					khachHang.setTrangThaiXacThuc(true);
					khachHangDAO.updateVerifyInformation(khachHang);
					msg = "Xác thực thành công";
				}else {
					// khong thanh cong
					msg = "Xác thực không thành công";
				}
			}else {
				msg ="Tài khoản không tồn tại";
			}
				
			url = "/khachhang/thongbao.jsp";
			request.setAttribute("baoLoi", msg);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getNoiDung(KhachHang kh) {
		String link = "http://localhost:8080/Bai4_Bootstrap/khach-hang?hanhDong=xac-thuc&maKhachHang="+kh.getMaKhacHang()+"&maXacThuc="+kh.getMaXacThuc();
		String noiDung = "<p>BOOKSTORE xin ch&agrave;o bạn <strong>"+kh.getHoVaTen()+"</strong></p>"
				+ "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"+kh.getMaXacThuc()+"</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
				+ "<p><a href=\""+link+"\">"+link+"</a></p>\r\n"
				+ "<p>Đ&acirc;y l&agrave; email tự động, vui l&ograve;ng kh&ocirc;ng phản hồi email n&agrave;y.</p>\r\n"
				+ "<p>Tr&acirc;n trọng cảm ơn.</p>";
		return noiDung;
	}
	
}
