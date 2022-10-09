package gui;

import bus.*;
import dto.*;
import Excel.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.BorderFactory;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import javax.swing.plaf.FontUIResource;

public class GUINhanVien extends GUIFormContent {
	private NhanVienBUS BUS = new NhanVienBUS();
	
	public static String[] arr_nv = constant.Category.arr_nv;
	private String[] arr_gend = constant.Category.arr_gend;
	private String[] arr_cv = constant.Category.arr_cv;
	
	public GUIMyTable table_NhanVien;
	private static JDialog Them_NhanVien;
	private static JDialog Sua;
	private JComboBox cbSearch;
	private JComboBox cbGioiTinh_Them, cbGioiTinh_Sua;
	private JComboBox cbChucVu_Them, cbChucVu_Sua;
	private final JLabel label_NhanVien[] = new JLabel[arr_nv.length];
	private JTextField txt_NhanVien_Them[] = new JTextField[arr_nv.length];
	private JTextField txt_NhanVien_Sua[] = new JTextField[arr_nv.length];
	private JTextField search;
	private int cohieu = 0;
	
	JMenuItem menuThem, menuSua, menuXoa;
	JPopupMenu popup;

	public GUINhanVien() {
		super();
	}

	@Override
	protected JPanel Table() {
		JPanel panel = new JPanel(null);
		table_NhanVien = new GUIMyTable();
		table_NhanVien.setHeaders(arr_nv);
		table_NhanVien.pane.setPreferredSize(new Dimension(GUImenu.width_content * 90 / 100, 300));
		table_NhanVien.setBounds(0, 0, GUImenu.width_content, 600);
		docDB();
		panel.add(table_NhanVien);
		ShowMenu(table_NhanVien);
		return panel;
	}

	private void Them_Frame() {
		/////////////////////////////////// LAYOUT ///////////////////////////////////////////
		JFrame f = new JFrame();
		cohieu = 0;
		int y = 50;
		Them_NhanVien = new JDialog(f);
		Them_NhanVien.setLayout(null);
		Them_NhanVien.setSize(500, 500);
		Them_NhanVien.setLocationRelativeTo(null);
		Them_NhanVien.setUndecorated(true);
		JLabel Title = new JLabel("Thêm nhân viên");
		JButton Luu = new JButton("Lưu");
		JButton Thoat = new JButton("Thoát");
		Title.setFont(constant.Format.time21);
		Title.setForeground(Color.decode("#FF4081"));
		Title.setBounds(150, 0, 200, 40);
		Luu.setBackground(Color.decode("#90CAF9"));
		Luu.setBounds(100, y, 100, 50);
		Thoat.setBackground(Color.decode("#90CAF9"));
		Thoat.setBounds(250, y, 100, 50);
		Them_NhanVien.add(Title);
		Them_NhanVien.add(Luu);
		Them_NhanVien.add(Thoat);
		Them_NhanVien.setVisible(true);
		//////////////////////////////////// END LAYOUT ////////////////////////////////////////////////////
		
		for (int i = 0; i < arr_nv.length; i++) {
			label_NhanVien[i] = new JLabel(arr_nv[i]);
			label_NhanVien[i].setBounds(100, y, 100, 30);
			Them_NhanVien.add(label_NhanVien[i]);
			if (i == 4) {
				cbGioiTinh_Them = new JComboBox(arr_gend);
				cbGioiTinh_Them.setBounds(200, y, 150, 30);
				Them_NhanVien.add(cbGioiTinh_Them);
				y += 40;
				continue;
			}
			if (i == 6) {
				cbChucVu_Them = new JComboBox(arr_cv);
				cbChucVu_Them.setBounds(200, y, 150, 30);
				Them_NhanVien.add(cbChucVu_Them);
				y += 40;
				continue;
			}
			txt_NhanVien_Them[i] = new JTextField();
			txt_NhanVien_Them[i].setBounds(200, y, 150, 30);
			y += 40;
			Them_NhanVien.add(txt_NhanVien_Them[i]);
		}
	
		Luu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				cohieu = 1;
				int a = JOptionPane.showConfirmDialog(Them_NhanVien, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					if (checkTextThem(txt_NhanVien_Them[1].getText(), txt_NhanVien_Them[2].getText(),
							txt_NhanVien_Them[3].getText(), cbGioiTinh_Them.getSelectedItem().toString(),
							txt_NhanVien_Them[5].getText(), cbChucVu_Them.getSelectedItem().toString())) {
						NhanVienDTO DTO = new NhanVienDTO(txt_NhanVien_Them[0].getText(),
								txt_NhanVien_Them[1].getText(), txt_NhanVien_Them[2].getText(),
								txt_NhanVien_Them[3].getText(), cbGioiTinh_Them.getSelectedItem().toString(),
								txt_NhanVien_Them[5].getText(), cbChucVu_Them.getSelectedItem().toString(), "Hiện");

						BUS.them(DTO); 
						table_NhanVien.addRow(DTO);
						String tenTaiKhoan = Tool
								.removeAccent(txt_NhanVien_Them[1].getText().trim().replaceAll(" ", ""))
								+ Tool.removeAccent(txt_NhanVien_Them[2].getText().trim().replaceAll(" ", ""));
						TaiKhoanDTO tk = new TaiKhoanDTO(tenTaiKhoan.trim(), txt_NhanVien_Them[0].getText(),
								traVeMaQuyenTuChucVu(cbChucVu_Them.getSelectedItem().toString()), "123456", "Hiện");
						TaiKhoanBUS tkBUS = new TaiKhoanBUS();
						tkBUS.them(tk);
						for (int i = 0; i < arr_nv.length; i++) {
							if (i != 4 && i != 6)
								txt_NhanVien_Them[i].setText("");
						}
						Them_NhanVien.dispose();
					}

				} else {
					cohieu = 0;
				}
			}
		});
		
		Thoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				for (int i = 0; i < arr_nv.length; i++) {
					if (i != 4 && i != 6)
						txt_NhanVien_Them[i].setText("");
				}
				cohieu = 1;
				Them_NhanVien.dispose();
			}
		});

		
		Them_NhanVien.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				if (cohieu == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
				}
			}

		});

		String ma = Tool.tangMa(NhanVienBUS.getMaNhanVienCuoi()); 
		txt_NhanVien_Them[0].setText(ma); 
		txt_NhanVien_Them[0].setEditable(false);
		

	}

	@SuppressWarnings("unchecked")
	private void Sua_Frame() {
		int y = 50;
		cohieu = 0;
		JFrame f = new JFrame();
		JLabel Title = new JLabel("Sửa nhân viên");
		JButton Luu = new JButton("Lưu");
		JButton Thoat = new JButton("Thoát");
		Sua = new JDialog(f);
		Sua.setLayout(null);
		Sua.setSize(500, 500);
		Sua.setLocationRelativeTo(null);
		Sua.setUndecorated(true);
		Sua.add(Title);
		Title.setFont(constant.Format.time21);
		Title.setForeground(Color.decode("#FF4081"));
		Title.setBounds(150, 0, 200, 40);
		Luu.setBackground(Color.decode("#90CAF9"));
		Luu.setBounds(100, y, 100, 50);
		Thoat.setBackground(Color.decode("#90CAF9"));
		Thoat.setBounds(250, y, 100, 50);
		
		for (int i = 0; i < arr_nv.length; i++) {
			label_NhanVien[i] = new JLabel(arr_nv[i]);
			label_NhanVien[i].setBounds(100, y, 100, 30);
			Sua.add(label_NhanVien[i]);
			if (i == 4) {
				cbGioiTinh_Sua = new JComboBox(arr_gend);
				cbGioiTinh_Sua.setBounds(200, y, 150, 30);
				Sua.add(cbGioiTinh_Sua);
				y += 40;
				continue;
			}
			if (i == 6) {
				cbChucVu_Sua = new JComboBox(arr_cv);
				cbChucVu_Sua.setBounds(200, y, 150, 30);
				Sua.add(cbChucVu_Sua);
				y += 40;
				continue;
			}
			txt_NhanVien_Sua[i] = new JTextField();
			txt_NhanVien_Sua[i].setBounds(200, y, 150, 30);

			y += 40;
			Sua.add(txt_NhanVien_Sua[i]);
		}
		
		Luu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				cohieu = 1;
				int a = JOptionPane.showConfirmDialog(Sua, "Bạn chắc chứ ?", "", JOptionPane.YES_NO_OPTION);
				if (a == JOptionPane.YES_OPTION) {
					if (checkTextSua(txt_NhanVien_Sua[1].getText(), txt_NhanVien_Sua[2].getText(),
							txt_NhanVien_Sua[3].getText(), cbGioiTinh_Sua.getSelectedItem().toString(),
							txt_NhanVien_Sua[5].getText(), cbChucVu_Sua.getSelectedItem().toString())) {
						buttonLuu_Sua();

						Sua.dispose();
					}

				} else 
				{
					cohieu = 0;
				}

			}
		});
		Sua.add(Luu);

		
		Thoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				cohieu = 1;
				Sua.dispose();
			}
		});
		Sua.add(Thoat);
		Sua.addWindowListener(new WindowAdapter() {

			@Override
			public void windowDeactivated(WindowEvent e) {
				if (cohieu == 0) {
					JOptionPane.showMessageDialog(null, "Vui lòng tắt Dialog khi muốn làm thao tác khác");
				}
			}

		});
		Sua.setVisible(true);

	}

	public void buttonLuu_Sua() {
		int row = table_NhanVien.tb.getSelectedRow();
		int colum = table_NhanVien.tb.getSelectedColumn();
		String maNhanVien = table_NhanVien.tbModel.getValueAt(row, colum).toString();
		for (int j = 0; j < arr_nv.length; j++) {
			if (j != 4 && j != 6)
				table_NhanVien.tbModel.setValueAt(txt_NhanVien_Sua[j].getText(), row, j);
			else if (j == 4)
				table_NhanVien.tbModel.setValueAt(cbGioiTinh_Sua.getSelectedItem().toString(), row, j);
			else if (j == 6)
				table_NhanVien.tbModel.setValueAt(cbChucVu_Sua.getSelectedItem().toString(), row, j);
		}

		table_NhanVien.tb.setModel(table_NhanVien.tbModel);
		NhanVienDTO DTO = new NhanVienDTO(txt_NhanVien_Sua[0].getText(), txt_NhanVien_Sua[1].getText(),
				txt_NhanVien_Sua[2].getText(), txt_NhanVien_Sua[3].getText(),
				cbGioiTinh_Sua.getSelectedItem().toString(), txt_NhanVien_Sua[5].getText(),
				cbChucVu_Sua.getSelectedItem().toString());
		int index = NhanVienBUS.timViTri(maNhanVien);
		BUS.sua(DTO, index);

	}

	@Override
	protected void Them_click(MouseEvent evt) {

		Them_Frame();
	}

	@Override
	protected void Sua_click(MouseEvent evt) {

		int i = table_NhanVien.tb.getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 hàng để sửa");
		} else {
			Sua_Frame();
			txt_NhanVien_Sua[0].setEnabled(false);
			for (int j = 0; j < arr_nv.length; j++) {
				if (j != 4 && j != 6)
					txt_NhanVien_Sua[j].setText(table_NhanVien.tb.getValueAt(i, j).toString());
				else if (j == 4) {
					int k;
					for (k = 0; k < arr_gend.length; k++)
						if (table_NhanVien.tb.getValueAt(i, j).toString().equals(arr_gend[k]))
							cbGioiTinh_Sua.setSelectedIndex(k);
				} else if (j == 6) {
					int k;
					for (k = 0; k < arr_cv.length; k++)
						if (table_NhanVien.tb.getValueAt(i, j).toString().equals(arr_cv[k]))
							cbChucVu_Sua.setSelectedIndex(k);
				}
			}

		}
	}

	@Override
	protected void Xoa_click(MouseEvent evt) {
		int row = table_NhanVien.tb.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn hàng muốn xóa");
		} else {
			int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xóa?", "", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
				String maNhanVien = table_NhanVien.tbModel.getValueAt(row, 0).toString();
				int index = NhanVienBUS.timViTri(maNhanVien);
				// Xóa hàng ở table
				table_NhanVien.tbModel.removeRow(row);
				BUS.xoa(maNhanVien, index);
			}
		}

	}

	private void LamMoi() {
		table_NhanVien.clear();
		for (NhanVienDTO DTO : NhanVienBUS.dsnv) {
			if (DTO.getTrangThai().equals("Hiện")) {
				table_NhanVien.addRow(DTO);
			}
		}
	}

	public void docDB() {
		NhanVienBUS NhanVienBus = new NhanVienBUS();
		if (NhanVienBUS.dsnv == null) {
			try {
				NhanVienBus.docDSNV();
			} catch (Exception ex) {
				Logger.getLogger(GUINhanVien.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

		for (NhanVienDTO NhanVienDTO : NhanVienBUS.dsnv) {
			if (NhanVienDTO.getTrangThai().equals("Hiện")) {
				table_NhanVien.addRow(NhanVienDTO);
			}
		}
	}

	@Override
	protected JPanel TimKiem() {
		JPanel TimKiem = new JPanel(null);

		JLabel lbsearch = new JLabel("");
		lbsearch.setBorder(new TitledBorder("Tìm kiếm"));
		int x = 400;
		cbSearch = new JComboBox<>(arr_nv);
		cbSearch.setBounds(5, 20, 150, 40);
		lbsearch.add(cbSearch);

		search = new JTextField();
		search.setBorder(new TitledBorder(arr_nv[0]));
		search.setBounds(155, 20, 150, 40);
		lbsearch.add(search);
		addDocumentListener(search);
//        search.addActionListener((ActionEvent e) -> {
//            if (!search.getText().equals("")) {
//                txtSearchOnChange();
//            }
//        });
		cbSearch.addActionListener((ActionEvent e) -> {
			search.setBorder(BorderFactory.createTitledBorder(cbSearch.getSelectedItem().toString()));
			search.requestFocus();
//            if (!txTim.getText().equals("")) {
//                txSearchOnChange();
//            }
		});
		lbsearch.setBounds(x, 0, 315, 70);
		TimKiem.add(lbsearch);

		JButton LamMoi = new JButton("Làm mới");
		LamMoi.setIcon(new ImageIcon(this.getClass().getResource("/Images/Icon/lammoi1-30.png")));
		LamMoi.setFont(new Font("Segoe UI", 0, 14));
		LamMoi.setBorder(BorderFactory.createLineBorder(Color.decode("#BDBDBD"), 1));
		LamMoi.setBackground(Color.decode("#90CAF9"));
		LamMoi.setBounds(x += 320, 10, 110, 30);
		LamMoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				search.setText("");
				LamMoi();

			}
		});
		TimKiem.add(LamMoi);

		return TimKiem;
	}

	private void addDocumentListener(JTextField tx) { 
		tx.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				txtSearchOnChange();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				txtSearchOnChange();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				txtSearchOnChange();
			}
		});
	}

	public void txtSearchOnChange() {
		table_NhanVien.clear();
		ArrayList<NhanVienDTO> arraylist = Tool.searchNV(search.getText(), cbSearch.getSelectedItem().toString());
		for (NhanVienDTO DTO : arraylist) {
			if (DTO.getTrangThai().equals("Hiện")) {
				table_NhanVien.addRow(DTO);

			}
		}
	}

	@Override
	protected void XuatExcel_click(MouseEvent evt) {
		new XuatExcel().xuatFileExcelNhanVien();
	}

	@Override
	protected void NhapExcel_click(MouseEvent evt) {
		new DocExcel().docFileExcelNhanVien();

	}

	public boolean checkTextThem(String hoNhanVien, String tenNhanVien, String gmail, String gioiTinh,
			String soDienThoai, String chucVu) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
		if (hoNhanVien.equals("") || tenNhanVien.equals("") || gmail.equals("") || gioiTinh.equals("")
				|| soDienThoai.equals("") || chucVu.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
		} else if (!Tool.isName(Tool.removeAccent(hoNhanVien))) {
			JOptionPane.showMessageDialog(null, "Họ nhân viên không được chứa ký tự đặc biệt");
			txt_NhanVien_Them[1].requestFocus();
		} else if (!Tool.isLength50(hoNhanVien)) {
			JOptionPane.showMessageDialog(null, "Họ nhân viên không được quá 50 ký tự");
			txt_NhanVien_Them[1].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(tenNhanVien))) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa ký tự đặc biệt");
			txt_NhanVien_Them[2].requestFocus();
		} else if (!Tool.isLength50(tenNhanVien)) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 50 ký tự");
			txt_NhanVien_Them[2].requestFocus();
		} else if (Tool.haveSpace(tenNhanVien.trim())) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 2 từ");
			txt_NhanVien_Them[2].requestFocus();
		} else if (!Tool.isGmail(gmail)) {
			JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
			txt_NhanVien_Them[3].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
			JOptionPane.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
			txt_NhanVien_Them[4].requestFocus();
		} else if (!Tool.isLength50(gioiTinh)) {
			JOptionPane.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
			txt_NhanVien_Them[4].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
			txt_NhanVien_Them[5].requestFocus();
		} else if (!Tool.isLength50(soDienThoai)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
			txt_NhanVien_Them[5].requestFocus();
		} else if (!Tool.isPhoneNumber(soDienThoai)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
			txt_NhanVien_Them[5].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(chucVu))) {
			JOptionPane.showMessageDialog(null, "Chức vụ không được chứa ký tự đặc biệt");
			txt_NhanVien_Them[6].requestFocus();
		} else if (!Tool.isLength50(chucVu)) {
			JOptionPane.showMessageDialog(null, "Chức vụ không được quá 50 ký tự");
			txt_NhanVien_Them[6].requestFocus();
		} else {
			return true;
		}
		return false;
	}

	public boolean checkTextSua(String hoNhanVien, String tenNhanVien, String gmail, String gioiTinh,
			String soDienThoai, String chucVu) {
		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Segoe UI", 0, 20)));
		if (hoNhanVien.equals("") || tenNhanVien.equals("") || gmail.equals("") || gioiTinh.equals("")
				|| soDienThoai.equals("") || chucVu.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
		} else if (!Tool.isName(Tool.removeAccent(hoNhanVien))) {
			JOptionPane.showMessageDialog(null, "Họ nhân viên không được chứa ký tự đặc biệt");
			txt_NhanVien_Sua[1].requestFocus();
		} else if (!Tool.isLength50(hoNhanVien)) {
			JOptionPane.showMessageDialog(null, "Họ nhân viên không được quá 50 ký tự");
			txt_NhanVien_Sua[1].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(tenNhanVien))) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa ký tự đặc biệt");
			txt_NhanVien_Sua[2].requestFocus();
		} else if (!Tool.isLength50(tenNhanVien)) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 50 ký tự");
			txt_NhanVien_Sua[2].requestFocus();
		} else if (Tool.haveSpace(tenNhanVien.trim())) {
			JOptionPane.showMessageDialog(null, "Tên nhân viên không được quá 2 từ");
			txt_NhanVien_Sua[2].requestFocus();
		} else if (!Tool.isGmail(gmail)) {
			JOptionPane.showMessageDialog(null, "Gmail phải đúng định dạng và không được chứa ký tự đặc biệt ");
			txt_NhanVien_Sua[3].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(gioiTinh))) {
			JOptionPane.showMessageDialog(null, "Giới tính không được chứa ký tự đặc biệt");
			txt_NhanVien_Sua[4].requestFocus();
		} else if (!Tool.isLength50(gioiTinh)) {
			JOptionPane.showMessageDialog(null, "Giới tính không được quá 50 ký tự");
			txt_NhanVien_Sua[4].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(soDienThoai))) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được chứa ký tự đặc biệt");
			txt_NhanVien_Sua[5].requestFocus();
		} else if (!Tool.isLength50(soDienThoai)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không được quá 50 ký tự");
			txt_NhanVien_Sua[5].requestFocus();
		} else if (!Tool.isPhoneNumber(soDienThoai)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại không chính xác");
			txt_NhanVien_Sua[5].requestFocus();
		} else if (!Tool.isName(Tool.removeAccent(chucVu))) {
			JOptionPane.showMessageDialog(null, "Chức vụ không được chứa ký tự đặc biệt");
			txt_NhanVien_Sua[6].requestFocus();
		} else if (!Tool.isLength50(chucVu)) {
			JOptionPane.showMessageDialog(null, "Chức vụ không được quá 50 ký tự");
			txt_NhanVien_Sua[6].requestFocus();
		} else {
			return true;

		}
		return false;
	}

	public String traVeMaQuyenTuChucVu(String chucVu) {

		if (chucVu.equals("Admin")) {
			return "PQ0";
		}
		if (chucVu.equals("Nhân viên bán và nhập hàng")) {
			return "PQ01";
		}
		if (chucVu.equals("Bếp trưởng")) {
			return "PQ02";
		}

		if (chucVu.equals("Nhân viên chăm sóc khách hàng")) {
			return "PQ03";
		}

		if (chucVu.equals("Quản lý")) {
			return "PQ04";
		}

		return null;
	}

	public JPopupMenu createPopUp(int rowIndex, GUIMyTable Table) {

		menuThem = new JMenuItem("Thêm");
		menuThem.setIcon(new ImageIcon(constant.Dir.menuThemImg));
		menuSua = new JMenuItem("Sửa");
		menuSua.setIcon(new ImageIcon(constant.Dir.menuSuaImg));
		menuXoa = new JMenuItem("Xóa");
		menuXoa.setIcon(new ImageIcon(constant.Dir.menuXoaImg));

		popup = new JPopupMenu();
		popup.add(menuThem);
		popup.addSeparator(); 
		popup.add(menuSua);
		popup.addSeparator();
		popup.add(menuXoa);

		menuThem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				Them_click(evt);
			}
		});

		menuSua.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				Sua_click(evt);
			}
		});

		menuXoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				Xoa_click(evt);
			}
		});

		return popup;
	}

	public void ShowMenu(GUIMyTable table) {
		table.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent me) {
				int r = table.getTable().rowAtPoint(me.getPoint());
				if (r >= 0 && r < table.getTable().getRowCount()) {
					table.getTable().setRowSelectionInterval(r, r);
				} else {
					table.getTable().clearSelection();
				}

				int rowIndex = table.getTable().getSelectedRow();
				if (rowIndex < 0)
					return;
				if (me.isPopupTrigger() && me.getComponent() instanceof JTable) {
					JPopupMenu popup = createPopUp(rowIndex, table);
					popup.show(me.getComponent(), me.getX(), me.getY());
				}
			}
		});
	}

}
