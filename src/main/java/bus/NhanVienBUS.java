package bus;

import dto.NhanVienDTO;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.NhanVienDAO;

public class NhanVienBUS {
	public static ArrayList<NhanVienDTO> dsnv;

	public NhanVienBUS() {

	}

	public void docDSNV() throws Exception {
		NhanVienDAO nvdata = new NhanVienDAO();
		if (dsnv == null)
			dsnv = new ArrayList<NhanVienDTO>();
		dsnv = nvdata.docDSNV();
	}

	public void them(NhanVienDTO nv) {
		try {
			NhanVienDAO nvdata = new NhanVienDAO();
			nvdata.them(nv);
			if (dsnv != null)
				dsnv.add(nv);
		} catch (Exception ex) {
			Logger.getLogger(NhanVienBUS.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void sua(NhanVienDTO nv, int i) {
		try {

			NhanVienDAO nvdata = new NhanVienDAO();
			nvdata.sua(nv);
			if (dsnv != null)
				dsnv.set(i, nv);
		} catch (Exception ex) {
			Logger.getLogger(NhanVienBUS.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void xoa(NhanVienDTO nv, int index) {
		NhanVienDAO nvDao = new NhanVienDAO();
		String xoanv = dsnv.get(index).getIDNhanVien();
		nvDao.xoa(xoanv);
		if (dsnv != null)
			dsnv.set(index, nv);
	}

	
	public void xoa(String ID, int index) {
		NhanVienDAO data = new NhanVienDAO();
		data.xoa(ID); 
		NhanVienDTO DTO = dsnv.get(index); 
		DTO.setTrangThai("Ẩn");
		if (dsnv != null)
			dsnv.set(index, DTO);
	}

	// tìm vị trí của thằng có chứa mã mà mình cần
	public static int timViTri(String ID) {
		for (int i = 0; i < dsnv.size(); i++) {
			if (dsnv.get(i).getIDNhanVien().equals(ID)) {
				return i;
			}
		}
		return 0;
	}

	public NhanVienDTO getNhanVienDTO(String idnv) {
		for (NhanVienDTO nvDTO : dsnv) {
			if (nvDTO.getIDNhanVien().equals(idnv)) {
				return nvDTO;
			}
		}
		return null;
	}

	public ArrayList<NhanVienDTO> getNhanVienDTO() {
		return dsnv;
	}

	public static String getMaNhanVienCuoi() // lấy mã cuối dể tăng
	{
		if (dsnv == null) {
			dsnv = new ArrayList<>();
		}
		if (dsnv.size() > 0) {
			String ma;
			ma = dsnv.get(dsnv.size() - 1).getIDNhanVien();
			return ma;
		}
		return null;
	}

	public static String getChucVuTuMaNhanVien(String maNhanVien)// trả về chức vụ từ mã nhân viên
	{
		for (NhanVienDTO nhanVienDTO : NhanVienBUS.dsnv) {
			if (nhanVienDTO.getIDNhanVien().equals(maNhanVien)) {
				return nhanVienDTO.getChucVu();
			}
		}
		return null;
	}
}
