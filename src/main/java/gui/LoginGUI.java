package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import dto.*;
import bus.*;

//import Excel.WorkWithFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.event.PrintJobEvent;

public class LoginGUI extends JFrame {
	public static String taiKhoan;
	protected static final String default_font = "Courier New";
	public LoginGUI() {
		initComponents();
		this.setLocationRelativeTo(null);
		KeyAdapter ka = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {

					btnDangnhap.doClick();
				}
			}
		};
		txtTendangnhap.addKeyListener(ka);
		txtMatkhau.addKeyListener(ka);

	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel_Login = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		txtTendangnhap = new javax.swing.JTextField();
		btnDangnhap = new javax.swing.JButton();
		txtMatkhau = new javax.swing.JPasswordField();
		btnHuy = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setUndecorated(true);
//        jPanel1.setBackground(Color.decode("#AFEEFF"));
		jPanel1.setBackground(new Color(3, 169, 244));
		jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(51, 255, 255)),
				javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED)));
		jPanel1.setForeground(new Color(255, 255, 255));
		jPanel1.setToolTipText("");

//        jLabel_Login.setBackground(Color.decode("#AFEEFF"));
		jLabel_Login.setBackground(new Color(129, 212, 250));
		jLabel_Login.setFont(new Font(default_font, 1, 24)); // NOI18N
		jLabel_Login.setForeground(new Color(255, 255, 255));
		jLabel_Login.setText("ĐĂNG NHẬP");

//        jPanel2.setBackground(Color.decode("#AFEEFF"));
		jPanel2.setBackground(new Color(128, 216, 255));

		txtTendangnhap.setFont(new Font(default_font, 0, 18)); // NOI18N
		txtTendangnhap.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		txtTendangnhap.setBorder(BorderFactory.createCompoundBorder(txtTendangnhap.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		txtTendangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				txtTendangnhapActionPerformed(evt);
			}
		});

		btnDangnhap.setBackground(Color.decode("#00E676"));
//        btnDangnhap.setBackground(new Color(129, 212, 250));
		btnDangnhap.setFont(new java.awt.Font(default_font, 1, 14)); // NOI18N
		btnDangnhap.setForeground(new Color(255, 255, 255));
		btnDangnhap.setText("Đăng nhập");
		btnDangnhap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				btnDangnhapMouseClicked(evt);
			}
		});
		btnDangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					btnDangnhapActionPerformed(evt);
				} catch (Exception ex) {
					Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
		btnDangnhap.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt) {
				btnDangnhapKeyPressed(evt);
			}
		});

		txtMatkhau.setFont(new java.awt.Font(default_font, 0, 18)); // NOI18N
		txtMatkhau.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
		txtMatkhau.setBorder(BorderFactory.createCompoundBorder(txtMatkhau.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		btnHuy.setBackground(Color.decode("#FF3D00"));
//        btnHuy.setBackground(new Color(255, 144, 144));
		btnHuy.setFont(new java.awt.Font(default_font, 1, 14)); // NOI18N
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setText("Hủy");
		btnHuy.setAutoscrolls(true);
		btnHuy.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				btnHuyMouseClicked(evt);
			}
		});
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnHuyActionPerformed(evt);
			}
		});

		jLabel1.setIcon(new ImageIcon(getClass().getResource("/Images/Icon/khachhang-30.png"))); // NOI18N

		jLabel2.setIcon(new ImageIcon(getClass().getResource("/Images/Icon/password-30.png"))); // NOI18N

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
										.addGroup(jPanel2Layout.createSequentialGroup().addGap(0, 68, Short.MAX_VALUE)
												.addComponent(jLabel1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
												.addComponent(txtTendangnhap, GroupLayout.PREFERRED_SIZE,
														229, GroupLayout.PREFERRED_SIZE))
										.addGroup(jPanel2Layout.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(jPanel2Layout.createParallelGroup(
														GroupLayout.Alignment.TRAILING)
														.addGroup(jPanel2Layout.createSequentialGroup().addComponent(
																jLabel2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(txtMatkhau,
																		GroupLayout.PREFERRED_SIZE, 229,
																		GroupLayout.PREFERRED_SIZE))
														.addGroup(jPanel2Layout.createSequentialGroup()
																.addComponent(btnHuy,
																		GroupLayout.PREFERRED_SIZE, 67,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(btnDangnhap)))))
								.addGap(68, 68, 68)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap(27, Short.MAX_VALUE)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(txtTendangnhap, GroupLayout.Alignment.TRAILING,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, GroupLayout.Alignment.TRAILING))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2, GroupLayout.Alignment.TRAILING)
								.addComponent(txtMatkhau, GroupLayout.Alignment.TRAILING,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(btnHuy, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDangnhap, GroupLayout.PREFERRED_SIZE, 40,
										GroupLayout.PREFERRED_SIZE))
						.addGap(20, 20, 20)));
//        jLabel3.setIcon(SwingConstants.LEADING); // NOI18N
		jLabel3.setIcon(new ImageIcon(getClass().getResource("/Images/Icon/dangnhap-30.png"))); // NOI18N

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(110, 110, 110).addComponent(jLabel_Login)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jLabel3)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(30, 30, 30).addComponent(
										jLabel_Login, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel3)))
						.addGap(30, 30, 30)
						.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 272, Short.MAX_VALUE));

		pack();
	}

	private void btnHuyActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
		System.exit(0);
	}

	private void btnHuyMouseClicked(MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void btnDangnhapKeyPressed(KeyEvent evt) {
		// TODO add your handling code here:
	}

	private void btnDangnhapActionPerformed(ActionEvent evt) throws Exception {
		taiKhoan = txtTendangnhap.getText();
		@SuppressWarnings("deprecation")
		String matKhau = txtMatkhau.getText();

		if (checkLogin(taiKhoan, matKhau)) {
			this.setVisible(false);
			GUImenu GiaoDien = new GUImenu();

		}
	}

	private void btnDangnhapMouseClicked(MouseEvent evt) {

	}

	private void txtTendangnhapActionPerformed(ActionEvent evt) {
		// TODO add your handling code here:
	}

	public boolean checkLogin(String tenDangNhap, String matKhau) {
		if (!checkEmpty()) {
			for (int i = 0; i < TaiKhoanBUS.dstk.size(); i++) {
				if (TaiKhoanBUS.dstk.get(i).getTaiKhoan().equals(tenDangNhap)) {
					if (TaiKhoanBUS.dstk.get(i).getMatKhau().equals(matKhau)) {
						Tool.IDNhanVienHienHanh = TaiKhoanBUS.timKiemMaNhanVienTheoTenTaiKhoan(taiKhoan);
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "Mật khẩu không chính xác");
						break;
					}
				}
				if (i == TaiKhoanBUS.dstk.size() - 1) {
					JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại");
				}
			}

		}
		return false;
	}

	@SuppressWarnings("deprecation")
	public boolean checkEmpty() {
		if (txtTendangnhap.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền tên đăng nhập");
			return true;
		} else if (txtMatkhau.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền mật khẩu");
			return true;
		}
		return false;
	}

	public static String saveFileName = "temp";
	public static PhanQuyenDTO quyenLogin;
	public static NhanVienDTO nhanVienLogin;
	public static TaiKhoanDTO taiKhoanLogin;
	private javax.swing.JButton btnDangnhap;
	private javax.swing.JButton btnHuy;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel_Login;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPasswordField txtMatkhau;
	private javax.swing.JTextField txtTendangnhap;
}
