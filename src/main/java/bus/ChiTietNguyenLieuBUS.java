/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;

import dto.ChiTietNguyenLieuDTO;

import java.util.ArrayList;

import dao.ChiTietNguyenLieuDAO;

public class ChiTietNguyenLieuBUS {
	public static ArrayList<ChiTietNguyenLieuDTO> dsctnl;

	public ChiTietNguyenLieuBUS() {

	}

	public void docCTNL() throws Exception {
		ChiTietNguyenLieuDAO ctnl = new ChiTietNguyenLieuDAO();
		if (dsctnl == null) {
			dsctnl = new ArrayList<>();
		}
		dsctnl = ctnl.docCTNL(); // đọc dữ liệu từ database
	}

}
