/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dto.*;
import gui.*;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Tool {

	public static String IDNhanVienHienHanh = "";

	public static boolean isNumber(String number) {
		try {
			if (Double.parseDouble(number) < 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean haveSpace(String string) {
		if (string.contains(" ")) {
			return true;
		}
		return false;
	}

	public static boolean haveNumber(String string) {
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	public static boolean isGmail(String gmail) {
		Pattern pattern = Pattern.compile("^[\\w.+\\-]+@gmail\\.com$");
		Matcher matcher = pattern.matcher(gmail);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

	public static boolean ngayBDTruocNgayKT(String ngayBatDau, String ngayKetThuc) {
		LocalDate ngayTruoc = LocalDate.parse(ngayBatDau);
		LocalDate ngaySau = LocalDate.parse(ngayKetThuc);
		if (ngayTruoc.isBefore(ngaySau)) {
			return true;
		}
		return false;
	}

	public static boolean isTenThousandToOneMil(String donGia) {
		try {
			if (Integer.parseInt(donGia) > 1000000 || Integer.parseInt(donGia) < 0)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isLength50(String ten) {
		if (ten.length() > 50 || ten.length() < 1) {
			return false;
		}
		return true;
	}

	public static boolean isOneToOneThousand(String donGia) {

		try {
			if (Integer.parseInt(donGia) > 1000 || Integer.parseInt(donGia) < 1)
				return false;
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isHinhAnh(String hinhAnh) {
		if (hinhAnh.contains(".png") || hinhAnh.contains(".jpg"))
			return true;
		return false;
	}

	public static boolean isSpecialChar(String specialChar) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]*$");
		Matcher matcher = pattern.matcher(specialChar);
		if (matcher.find())
			return true;
		return false;

	}

	public static boolean isName(String name) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			return false;
		}
		return true;

	}

	public static boolean isTongTien(String name) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9. ]");
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			return false;
		}
		return true;

	}

	public static boolean isCongThuc(String congThuc) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9, ]");
		Matcher matcher = pattern.matcher(congThuc);
		if (matcher.find()) {
			return false;
		}
		return true;

	}

	public static boolean isPhoneNumber(String number) 
	{
		Pattern pattern = Pattern.compile(
				"^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$");
		Matcher matcher = pattern.matcher(number);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

	public static boolean isMa(String ma) {
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
		Matcher matcher = pattern.matcher(ma);
		if (matcher.find()) {
			return false;
		}
		return true;

	}

	public static boolean isDate(String date) {
		Pattern pattern = Pattern.compile("\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}");
		Matcher matcher = pattern.matcher(date);
		if (matcher.find()) {
			return true;
		}
		return false;

	}

	public static String chuanHoa(String string) {
		string = string.trim();
		string = string.replaceAll("\\s+", " ");
		return string;
	}

	public static boolean isDuplicateMaMonAn(String string) // ki???m tra tr??ng nhau hay kh??ng
	{
		for (int i = 0; i < MonAnBUS.dsMonAn.size(); i++) {
			if (MonAnBUS.dsMonAn.get(i).getIDMonAn().contains(string))
				return true;
		}
		return false;
	}

	public static boolean isDuplicateMaNhanVien(String string) // ki???m tra tr??ng nhau hay kh??ng
	{
		for (int i = 0; i < NhanVienBUS.dsnv.size(); i++) {
			if (NhanVienBUS.dsnv.get(i).getIDNhanVien().contains(string)
					&& NhanVienBUS.dsnv.get(i).getTrangThai().equals("Hi???n"))
				return true;
		}
		return false;
	}

	public static String chuanHoaDanhTuRieng(String string) {
		string = chuanHoa(string);
		String temp[] = string.split(" ");
		string = ""; // ? ^-^
		for (int i = 0; i < temp.length; i++) {
			string += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
			if (i < temp.length - 1) //
			{
				string += " ";
			}
		}
		return string;
	}

	public static ImageIcon showIcon(int width, int height, String fileName) {
		ImageIcon icon;
		try {
			BufferedImage image = ImageIO.read(new File(fileName));
			icon = new ImageIcon(image.getScaledInstance(width, height, image.SCALE_SMOOTH));
			return icon;
		} catch (IOException e) {
			return null;
		}
	}

	private static final char[] SOURCE_CHARACTERS = { '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??',
			'??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '??',
			'??', '??', '??', '??', '??', '??', '??', '??', '??', '??', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???',
			'???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???',
			'???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???',
			'???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???', '???',
			'???', '???', '???', '???', '???', '???', '???', '???', };

	private static final char[] DESTINATION_CHARACTERS = { 'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O',
			'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
			'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a',
			'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
			'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
			'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U',
			'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', };

	public static char removeAccent(char ch) {
		int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
		if (index >= 0) {
			ch = DESTINATION_CHARACTERS[index];
		}
		return ch;
	}

	public static String removeAccent(String str) {
		StringBuilder sb = new StringBuilder(str);
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, removeAccent(sb.charAt(i)));
		}
		return sb.toString();
	}

	public static ArrayList<MonAnDTO> searchMA(String value, double donGiaTu, double donGiaDen, int soLuongTu,
			int soLuongDen) { // c?? thay ?????i
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<MonAnDTO> result = new ArrayList<>();
		// T??m theo t??n
		MonAnBUS.dsMonAn.forEach((MonAnDTO) -> {
			if (MonAnDTO.getTenMonAn().toLowerCase().contains(value.toLowerCase())
					&& MonAnDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
					|| removeAccent(MonAnDTO.getTenMonAn().toLowerCase()).contains(removeAccent(value.toLowerCase()))
							&& MonAnDTO.getTrangThai().equals("Hi???n"))// T??m ki???m theo ch??? VN
			{
				result.add(MonAnDTO);
			}
		});

		for (int i = result.size() - 1; i >= 0; i--) {
			MonAnDTO monAn = result.get(i);
			double donGia = monAn.getDonGia();
			int soLuong = monAn.getSoLuong();
			Boolean donGiaKhongThoa = (donGiaTu != -1 && donGia < donGiaTu) || (donGiaDen != -1 && donGia > donGiaDen);
			Boolean soLuongKhongThoa = (soLuongTu != -1 && soLuong < soLuongTu)
					|| (soLuongDen != -1 && soLuong > soLuongDen);
			if (donGiaKhongThoa || soLuongKhongThoa) {
				result.remove(i);
			}
		}
		return result;
	}

	public static ArrayList<NguyenLieuDTO> searchNL(String value, double donGiaTu, double donGiaDen, int soLuongTu,
			int soLuongDen) { // c?? thay ?????i

		// ph????ng ph??p t??m t??? arraylist
		ArrayList<NguyenLieuDTO> result = new ArrayList<>();

		NguyenLieuBUS.dsnl.forEach((NguyenLieuDTO) -> {
			if (NguyenLieuDTO.getTenNguyenLieu().toLowerCase().contains(value.toLowerCase())
					&& NguyenLieuDTO.getTrangThai().equals("Hi???n")
					|| Tool.removeAccent(NguyenLieuDTO.getTenNguyenLieu().toLowerCase()).contains(
							removeAccent(value.toLowerCase())) && NguyenLieuDTO.getTrangThai().equals("Hi???n")) {
				result.add(NguyenLieuDTO);
			}
		});

		for (int i = result.size() - 1; i >= 0; i--) {
			NguyenLieuDTO monAn = result.get(i);
			double donGia = monAn.getDonGia();
			int soLuong = monAn.getSoLuong();
			Boolean donGiaKhongThoa = (donGiaTu != -1 && donGia < donGiaTu) || (donGiaDen != -1 && donGia > donGiaDen);
			Boolean soLuongKhongThoa = (soLuongTu != -1 && soLuong < soLuongTu)
					|| (soLuongDen != -1 && soLuong > soLuongDen);
			if (donGiaKhongThoa || soLuongKhongThoa) {
				result.remove(i);
			}
		}
		return result;
	}

	public static ArrayList<CongThucDTO> searchCT(String value, String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<CongThucDTO> result = new ArrayList<>();
		if (type == "M?? c??ng th???c")
			// T??m theo t??n
			CongThucBUS.CT.forEach((CongThucDTO) -> {
				if (CongThucDTO.getIDCongThuc().toLowerCase().contains(value.toLowerCase())
						&& CongThucDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(CongThucDTO);
				}
			});
		if (type == "M?? m??n ??n")
			// T??m theo t??n
			CongThucBUS.CT.forEach((CongThucDTO) -> {
				if (CongThucDTO.getIDMonAn().toLowerCase().contains(value.toLowerCase())
						&& CongThucDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(CongThucDTO);
				}
			});
		if (type == "M?? t??? c??ng th???c")
			// T??m theo t??n
			CongThucBUS.CT.forEach((CongThucDTO) -> {
				if (CongThucDTO.getMoTaCongThuc().toLowerCase().contains(value.toLowerCase())
						&& CongThucDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(CongThucDTO.getMoTaCongThuc().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && CongThucDTO.getTrangThai().equals("Hi???n"))// T??m
																												// ki???m
																												// theo
																												// ch???
																												// VN
				{
					result.add(CongThucDTO);
				}
			});

		return result;
	}

	public static ArrayList<KhuyenMaiDTO> searchKM(String value, String tuNgayBatDau, String denNgayKetThuc,
			double tuTienGiam, double denTienGiam, String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<KhuyenMaiDTO> result = new ArrayList<>();
		if (type == "M?? khuy???n m??i")
			// T??m theo t??n
			KhuyenMaiBUS.dskm.forEach((KhuyenMaiDTO) -> {
				if (KhuyenMaiDTO.getIDKhuyenMai().toLowerCase().contains(value.toLowerCase())
						&& KhuyenMaiDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(KhuyenMaiDTO);
				}
			});
		if (type == "T??n ch????ng tr??nh")
			// T??m theo t??n
			KhuyenMaiBUS.dskm.forEach((KhuyenMaiDTO) -> {
				if (KhuyenMaiDTO.getTenChuongTrinh().toLowerCase().contains(value.toLowerCase())
						&& KhuyenMaiDTO.getTrangThai().equals("Hi???n")// T??m ki???m theo chu???i th?????ng
						|| removeAccent(KhuyenMaiDTO.getTenChuongTrinh().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && KhuyenMaiDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(KhuyenMaiDTO);
				}
			});
		if (type == "N???i dung")
			// T??m theo t??n
			KhuyenMaiBUS.dskm.forEach((KhuyenMaiDTO) -> {
				if (KhuyenMaiDTO.getNoiDungGiamGia().toLowerCase().contains(value.toLowerCase())
						&& KhuyenMaiDTO.getTrangThai().equals("Hi???n")// T??m ki???m theo chu???i th?????ng
						|| removeAccent(KhuyenMaiDTO.getNoiDungGiamGia().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && KhuyenMaiDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(KhuyenMaiDTO);
				}
			});

		for (int i = result.size() - 1; i >= 0; i--) {
			KhuyenMaiDTO khuyenMai = result.get(i);
			double tienGiam = khuyenMai.getTienGiam();
			Boolean tienGiamKhongThoa = (tuTienGiam != -1 && tienGiam < tuTienGiam)
					|| (denTienGiam != -1 && tienGiam > denTienGiam);
			if (tienGiamKhongThoa) {
				result.remove(i);
			}

		}

		for (int i = result.size() - 1; i >= 0; i--) {
			KhuyenMaiDTO khuyenMai = result.get(i);
			LocalDate ngayBatDau = khuyenMai.getNgayBatDau();
			LocalDate ngayKetThuc = khuyenMai.getNgayKetThuc();
			Boolean ngayBatDauKhongThoa = (!(tuNgayBatDau).equals("")
					&& ngayBatDau.isBefore(LocalDate.parse(tuNgayBatDau)));
			Boolean ngayKetThucKhongThoa = (!(denNgayKetThuc).equals("")
					&& ngayKetThuc.isAfter(LocalDate.parse(denNgayKetThuc)));
			if (ngayBatDauKhongThoa || ngayKetThucKhongThoa) {
				result.remove(i);
			}

		}

		return result;
	}

	public static ArrayList<KhachHangDTO> searchKH(String value, double tuTongChiTieu, double denTongChiTieu,
			String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<KhachHangDTO> result = new ArrayList<>();
		if (type == "M?? kh??ch h??ng")
			// T??m theo t??n
			KhachHangBUS.dskh.forEach((KhachHangDTO) -> {
				if (KhachHangDTO.getIDKhachHang().toLowerCase().contains(value.toLowerCase())
						&& KhachHangDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(KhachHangDTO);
				}
			});
		if (type == "H???")
			// T??m theo t??n
			KhachHangBUS.dskh.forEach((KhachHangDTO) -> {
				if (KhachHangDTO.getHoKhachHang().toLowerCase().contains(value.toLowerCase())
						&& KhachHangDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(KhachHangDTO.getHoKhachHang().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && KhachHangDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(KhachHangDTO);
				}
			});
		if (type == "T??n")
			// T??m theo t??n
			KhachHangBUS.dskh.forEach((KhachHangDTO) -> {
				if (KhachHangDTO.getTenKhachHang().toLowerCase().contains(value.toLowerCase())
						&& KhachHangDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(KhachHangDTO.getTenKhachHang().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && KhachHangDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(KhachHangDTO);
				}
			});
		if (type == "Gmail")
			// T??m theo t??n
			KhachHangBUS.dskh.forEach((KhachHangDTO) -> {
				if (KhachHangDTO.getGmail().toLowerCase().contains(value.toLowerCase())
						&& KhachHangDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng

				{
					result.add(KhachHangDTO);
				}
			});
		if (type == "Gi???i t??nh")
			// T??m theo t??n
			KhachHangBUS.dskh.forEach((KhachHangDTO) -> {
				if (KhachHangDTO.getGioiTinh().toLowerCase().contains(value.toLowerCase())
						&& KhachHangDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(KhachHangDTO.getGioiTinh().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && KhachHangDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(KhachHangDTO);
				}
			});
		if (type == "S??T")
			// T??m theo t??n
			KhachHangBUS.dskh.forEach((KhachHangDTO) -> {
				if (KhachHangDTO.getSoDienThoai().toLowerCase().contains(value.toLowerCase())
						&& KhachHangDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(KhachHangDTO);
				}
			});
		for (int i = result.size() - 1; i >= 0; i--) {
			KhachHangDTO khachHang = result.get(i);
			double tongChiTieu = khachHang.getTongChiTieu();
			Boolean chiTieuKhongThoa = (tuTongChiTieu != -1 && tongChiTieu < tuTongChiTieu)
					|| (denTongChiTieu != -1 && tongChiTieu > denTongChiTieu);
			if (chiTieuKhongThoa) {
				result.remove(i);
			}

		}

		return result;
	}

	public static ArrayList<NhanVienDTO> searchNV(String value, String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<NhanVienDTO> result = new ArrayList<>();
		if (type == "M?? nh??n vi??n")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(NhanVienDTO);
				}
			});
		if (type == "H???")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getHoNhanVien().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(NhanVienDTO.getHoNhanVien().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hi???n"))// T??m
																												// ki???m
																												// theo
																												// ch???
																												// VN
				{
					result.add(NhanVienDTO);
				}
			});
		if (type == "T??n")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getTenNhanVien().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(NhanVienDTO.getTenNhanVien().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hi???n"))// T??m
																												// ki???m
																												// theo
																												// ch???
																												// VN
				{
					result.add(NhanVienDTO);
				}
			});
		if (type == "Gmail")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getGmail().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng

				{
					result.add(NhanVienDTO);
				}
			});
		if (type == "Gi???i t??nh")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getGioiTinh().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(NhanVienDTO.getGioiTinh().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hi???n"))// T??m
																												// ki???m
																												// theo
																												// ch???
																												// VN
				{
					result.add(NhanVienDTO);
				}
			});
		if (type == "S??T")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getSoDienThoai().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(NhanVienDTO);
				}
			});
		if (type == "Ch???c v???")
			// T??m theo t??n
			NhanVienBUS.dsnv.forEach((NhanVienDTO) -> {
				if (NhanVienDTO.getChucVu().toLowerCase().contains(value.toLowerCase())
						&& NhanVienDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(NhanVienDTO.getChucVu().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && NhanVienDTO.getTrangThai().equals("Hi???n"))

				{
					result.add(NhanVienDTO);
				}
			});

		return result;
	}

	public static ArrayList<NhaCungCapDTO> searchNCC(String value, String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<NhaCungCapDTO> result = new ArrayList<>();
		if (type == "M?? nh?? cung c???p")
			// T??m theo t??n
			NhaCungCapBUS.dsncc.forEach((NhaCungCapDTO) -> {
				if (NhaCungCapDTO.getIDNhaCungCap().toLowerCase().contains(value.toLowerCase())
						&& NhaCungCapDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(NhaCungCapDTO);
				}
			});
		if (type == "T??n")
			// T??m theo t??n
			NhaCungCapBUS.dsncc.forEach((NhaCungCapDTO) -> {
				if (NhaCungCapDTO.getTenNhaCungCap().toLowerCase().contains(value.toLowerCase())
						&& NhaCungCapDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(NhaCungCapDTO.getTenNhaCungCap().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && NhaCungCapDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(NhaCungCapDTO);
				}
			});

		if (type == "S??T")
			// T??m theo t??n
			NhaCungCapBUS.dsncc.forEach((NhaCungCapDTO) -> {
				if (NhaCungCapDTO.getSoDienThoai().toLowerCase().contains(value.toLowerCase())
						&& NhaCungCapDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(NhaCungCapDTO);
				}
			});
		if (type == "Gmail")
			// T??m theo t??n
			NhaCungCapBUS.dsncc.forEach((NhaCungCapDTO) -> {
				if (NhaCungCapDTO.getGmail().toLowerCase().contains(value.toLowerCase())
						&& NhaCungCapDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng

				{
					result.add(NhaCungCapDTO);
				}
			});
		if (type == "?????a ch???")
			// T??m theo t??n
			NhaCungCapBUS.dsncc.forEach((NhaCungCapDTO) -> {
				if (NhaCungCapDTO.getDiaChi().toLowerCase().contains(value.toLowerCase())
						&& NhaCungCapDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(NhaCungCapDTO.getDiaChi().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && NhaCungCapDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(NhaCungCapDTO);
				}
			});

		return result;
	}

	public static ArrayList<TaiKhoanDTO> searchTK(String value, String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<TaiKhoanDTO> result = new ArrayList<>();
		if (type == "T??i kho???n")
			// T??m theo t??i kho???n
			TaiKhoanBUS.dstk.forEach((TaiKhoanDTO) -> {
				if (TaiKhoanDTO.getTaiKhoan().toLowerCase().contains(value.toLowerCase())
						&& TaiKhoanDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(TaiKhoanDTO);
				}
			});
		if (type == "M?? nh??n vi??n")
			// T??m theo m?? nh??n vi??n
			TaiKhoanBUS.dstk.forEach((TaiKhoanDTO) -> {
				if (TaiKhoanDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase())
						&& TaiKhoanDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng

				{
					result.add(TaiKhoanDTO);
				}
			});

		if (type == "M?? quy???n")
			// T??m theo t??n
			TaiKhoanBUS.dstk.forEach((TaiKhoanDTO) -> {
				if (TaiKhoanDTO.getIDPhanQuyen().toLowerCase().contains(value.toLowerCase())
						&& TaiKhoanDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(TaiKhoanDTO);
				}
			});
		if (type == "M???t kh???u")
			// T??m theo m???t kh???u
			TaiKhoanBUS.dstk.forEach((TaiKhoanDTO) -> {
				if (TaiKhoanDTO.getMatKhau().toLowerCase().contains(value.toLowerCase())
						&& TaiKhoanDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng

				{
					result.add(TaiKhoanDTO);
				}
			});

		return result;
	}

	public static ArrayList<PhanQuyenDTO> searchPQ(String value, String type) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<PhanQuyenDTO> result = new ArrayList<>();
		if (type == "M?? quy???n")
			// T??m theo t??n
			PhanQuyenBUS.dspq.forEach((PhanQuyenDTO) -> {
				if (PhanQuyenDTO.getIDPhanQuyen().toLowerCase().contains(value.toLowerCase())
						&& PhanQuyenDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(PhanQuyenDTO);
				}
			});
		if (type == "T??n quy???n")
			// T??m theo t??n
			PhanQuyenBUS.dspq.forEach((PhanQuyenDTO) -> {
				if (PhanQuyenDTO.getTenQuyen().toLowerCase().contains(value.toLowerCase())
						&& PhanQuyenDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
						|| removeAccent(PhanQuyenDTO.getTenQuyen().toLowerCase()).contains(
								removeAccent(value.toLowerCase())) && PhanQuyenDTO.getTrangThai().equals("Hi???n"))// T??m
																													// ki???m
																													// theo
																													// ch???
																													// VN
				{
					result.add(PhanQuyenDTO);
				}
			});

		if (type == "M?? t??? quy???n")
			// T??m theo t??n
			PhanQuyenBUS.dspq.forEach((PhanQuyenDTO) -> {
				if (PhanQuyenDTO.getMoTaQuyen().toLowerCase().contains(value.toLowerCase())
						&& PhanQuyenDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(PhanQuyenDTO);
				}
			});

		return result;

	}

	public static ArrayList<MonAnDTO> searchBH(String value) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<MonAnDTO> result = new ArrayList<>();

		// T??m theo t??n
		MonAnBUS.dsMonAn.forEach((MonAnDTO) -> {
			if (MonAnDTO.getTenMonAn().toLowerCase().contains(value.toLowerCase())
					&& MonAnDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
					|| removeAccent(MonAnDTO.getTenMonAn().toLowerCase()).contains(removeAccent(value.toLowerCase()))
							&& MonAnDTO.getTrangThai().equals("Hi???n"))// T??m ki???m theo ch??? VN
			{
				result.add(MonAnDTO);
			}
		});

		return result;
	}

	public static ArrayList<NguyenLieuDTO> searchNH(String value) {
		// ph????ng ph??p t??m t??? arraylist
		ArrayList<NguyenLieuDTO> result = new ArrayList<>();

		// T??m theo t??n
		NguyenLieuBUS.dsnl.forEach((NguyenLieuDTO) -> {
			if (NguyenLieuDTO.getTenNguyenLieu().toLowerCase().contains(value.toLowerCase())
					&& NguyenLieuDTO.getTrangThai().equals("Hi???n") // T??m ki???m theo chu???i th?????ng
					|| removeAccent(NguyenLieuDTO.getTenNguyenLieu().toLowerCase())
							.contains(removeAccent(value.toLowerCase())) && NguyenLieuDTO.getTrangThai().equals("Hi???n"))// T??m
																														// ki???m
																														// theo
																														// ch???
																														// VN
			{
				result.add(NguyenLieuDTO);
			}
		});

		return result;
	}

	public static boolean docDB() throws Exception {
		try {
			ChiTietHoaDonBUS cthdBUS = new ChiTietHoaDonBUS();
			ChiTietHoaDonNhapBUS cthdnBUS = new ChiTietHoaDonNhapBUS();
			ChiTietNguyenLieuBUS ctnlBUS = new ChiTietNguyenLieuBUS();
			CongThucBUS ctBUS = new CongThucBUS();
			HoaDonBUS hdBUS = new HoaDonBUS();
			HoaDonNhapBUS hdnBUS = new HoaDonNhapBUS();
			KhachHangBUS khBUS = new KhachHangBUS();
			KhuyenMaiBUS kmBUS = new KhuyenMaiBUS();
			MonAnBUS maBUS = new MonAnBUS();
			NguyenLieuBUS nlBUS = new NguyenLieuBUS();
			NhaCungCapBUS nccBUS = new NhaCungCapBUS();
			NhanVienBUS nvBUS = new NhanVienBUS();
			PhanQuyenBUS pqBUS = new PhanQuyenBUS();
			TaiKhoanBUS tkBUS = new TaiKhoanBUS();
			cthdBUS.docCTHD();
			cthdnBUS.docCTHDN();
			ctnlBUS.docCTNL();
			ctBUS.docCT();
			hdBUS.docHD();
			hdnBUS.docHDN();
			khBUS.docDSKH();
			kmBUS.docDSKM();
			maBUS.docDSMonAn();
			nlBUS.docDSNL();
			nccBUS.docDSNCC();
			nvBUS.docDSNV();
			pqBUS.docDSPQ();
			tkBUS.docDSTK();
		} catch (NullPointerException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static LocalDate getNgayLap() {
		LocalDate ngayLap = java.time.LocalDate.now();
		return ngayLap;
	}

	// H??m l???y gi??? hi???n t???i
	public static LocalTime getGioLap() {
		LocalTime gioLap = java.time.LocalTime.now();
		return gioLap;
	}

	// H??m t??ng m?? t??? ?????ng khi th??m m???i
	public static String tangMa(String ma) {
		// VD: HD12
		if (ma != null) {
			String maSo = ma.substring(2);// l???y 12
			String maChu = ma.substring(0, 2); // l???y HD
			int maSo1 = Integer.parseInt(maSo) + 1;// t??ng 12+1
			if (maSo1 < 10) {
				String maMoi = maChu + "0" + maSo1;
				return maMoi;
			} else {
				String maMoi = maChu + maSo1;
				return maMoi;
			}

		}
		return null;
	}

	// H??m t??ng m?? ?????t bi???t khi c?? ?????n 3 k?? t???
	public static String tangMa3(String ma) {
		// VD: NCC12
		if (ma != null) {
			String maChu = ma.substring(0, 3); // l???y NCC
			String maSo = ma.substring(3);// l???y 12
			int maSo1 = Integer.parseInt(maSo) + 1;// t??ng 12+1
			if (maSo1 < 10) {
				String maMoi = maChu + "0" + maSo1;
				return maMoi;
			} else {
				String maMoi = maChu + maSo1;
				return maMoi;
			}
		}
		return null;
	}

	// m???i th??m lu??n
	public static ArrayList<HoaDonNhapDTO> searchHDN(String value, String tuNgayNhap, String denNgayNhap,
			double tuTongTien, double denTongTien, String type) {
		ArrayList<HoaDonNhapDTO> result = new ArrayList<>();
		if (type.equals("M?? h??a ????n")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonNhapBUS.dshdn.forEach((HoaDonNhapDTO) -> {
				if (HoaDonNhapDTO.getIDHoaDonNhap().toLowerCase().contains(value.toLowerCase())
						&& HoaDonNhapDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonNhapDTO);
				}
			});
		}
		if (type.equals("M?? nh??n vi??n")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonNhapBUS.dshdn.forEach((HoaDonNhapDTO) -> {
				if (HoaDonNhapDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase())
						&& HoaDonNhapDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonNhapDTO);
				}
			});
		}
		if (type.equals("M?? nh?? cung c???p")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonNhapBUS.dshdn.forEach((HoaDonNhapDTO) -> {
				if (HoaDonNhapDTO.getIDNhaCungCap().toLowerCase().contains(value.toLowerCase())
						&& HoaDonNhapDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonNhapDTO);
				}
			});
		}

		for (int i = result.size() - 1; i >= 0; i--) {
			HoaDonNhapDTO hoaDonNhap = result.get(i);
			double tongTien = hoaDonNhap.getTongTien();
			Boolean donGiaKhongThoa = (tuTongTien != -1 && tongTien < tuTongTien)
					|| (denTongTien != -1 && tongTien > denTongTien);
			if (donGiaKhongThoa) {
				result.remove(i);
			}

		}

		for (int i = result.size() - 1; i >= 0; i--) {
			HoaDonNhapDTO hoaDonNhap = result.get(i);
			LocalDate ngayNhap = hoaDonNhap.getNgayNhap();
			Boolean ngayNhapKhongThoa = (!(tuNgayNhap).equals("") && ngayNhap.isBefore(LocalDate.parse(tuNgayNhap)))
					|| (!(denNgayNhap).equals("") && ngayNhap.isAfter(LocalDate.parse(denNgayNhap)));
			if (ngayNhapKhongThoa) {
				result.remove(i);
			}

		}

		return result;
	}

	public static ArrayList<HoaDonDTO> searchHD(String value, String tuNgayLap, String denNgayLap, double tuTongTien,
			double denTongTien, String type) {
		ArrayList<HoaDonDTO> result = new ArrayList<>();
		if (type.equals("M?? h??a ????n")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonBUS.HD.forEach((HoaDonDTO) -> {
				if (HoaDonDTO.getIDHoaDon().toLowerCase().contains(value.toLowerCase())
						&& HoaDonDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonDTO);
				}
			});
		}
		if (type.equals("M?? nh??n vi??n")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonBUS.HD.forEach((HoaDonDTO) -> {
				if (HoaDonDTO.getIDNhanVien().toLowerCase().contains(value.toLowerCase())
						&& HoaDonDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonDTO);
				}
			});
		}
		if (type.equals("M?? kh??ch h??ng")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonBUS.HD.forEach((HoaDonDTO) -> {
				if (HoaDonDTO.getIDKhachHang().toLowerCase().contains(value.toLowerCase())
						&& HoaDonDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonDTO);
				}
			});
		}
		if (type.equals("M?? khuy???n m??i")) {
			// duy???t xem m?? gi???ng m?? nh???p v??o th?? th??m v??o arraylist result
			HoaDonBUS.HD.forEach((HoaDonDTO) -> {
				if (HoaDonDTO.getIDKhuyenMai().toLowerCase().contains(value.toLowerCase())
						&& HoaDonDTO.getTrangThai().equals("Hi???n")) // T??m ki???m theo chu???i th?????ng
				{
					result.add(HoaDonDTO);
				}
			});
		}

		for (int i = result.size() - 1; i >= 0; i--) {
			HoaDonDTO hoaDon = result.get(i);
			double tongTien = hoaDon.getTongTien();
			Boolean donGiaKhongThoa = (tuTongTien != -1 && tongTien < tuTongTien)
					|| (denTongTien != -1 && tongTien > denTongTien);
			if (donGiaKhongThoa) {
				result.remove(i);
			}

		}

		for (int i = result.size() - 1; i >= 0; i--) {
			HoaDonDTO hoaDon = result.get(i);
			LocalDate ngayLap = hoaDon.getNgayLap();
			Boolean ngayNhapKhongThoa = (!(tuNgayLap).equals("") && ngayLap.isBefore(LocalDate.parse(tuNgayLap)))
					|| (!(denNgayLap).equals("") && ngayLap.isAfter(LocalDate.parse(denNgayLap)));
			if (ngayNhapKhongThoa) {
				result.remove(i);
			}

		}

		return result;
	}

}
