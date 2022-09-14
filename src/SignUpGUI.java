import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import kr.co.greenart.dbutil.DBUtil;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class SignUpGUI extends JDialog implements MouseListener {
	private ImageIcon icon;
	private JButton jb0; // 가입완료
	private JButton jb1; // 중복확인
	private JButton jb; // 돌아가기
	private JTextField jf; // id입력 텍스트필드
	private JPasswordField pf; // pw입력 텍스트필드
	private JPasswordField pf2; // pw재입력 텍스트필드
	private JTextField jfName; // 이름입력 텍스트필드
	private boolean idCheck;
	private boolean pwCheck;
	private UserDaoImp dao = new UserDaoImp();
	private WorkingLogin wl = new WorkingLogin();

	public SignUpGUI(JFrame owner) {
		super(owner, true);

		icon = new ImageIcon("src/img/회원가입아이콘1.png");
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				setTitle("Sign Up");
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};
		setSize(400, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(background);
		setResizable(false);
		SpringLayout sl_background = new SpringLayout();
		background.setLayout(sl_background);

		JButton btnNewButton_1 = new JButton(new ImageIcon("src/img/검색아이콘.png")); // 중복확인 버튼
		sl_background.putConstraint(SpringLayout.EAST, btnNewButton_1, -79, SpringLayout.EAST, background);
		background.add(btnNewButton_1);
		btnNewButton_1.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_1.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_1.setFocusPainted(false);
		
		btnNewButton_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String id = jf.getText();
			try {
				if (wl.isIDSame(id)) {
					System.out.println("사용가능한 id");
					idCheck = true;
				} else {
					System.out.println("중복.");
					idCheck = false;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	});

		JButton btnNewButton = new JButton(new ImageIcon("src/img/완료버튼.png")); // 가입완료 버튼
		sl_background.putConstraint(SpringLayout.WEST, btnNewButton, 78, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, btnNewButton, -205, SpringLayout.EAST, background);
		background.add(btnNewButton);
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (idCheck && pwCheck) {
					System.out.println("회원가입 완료");

					Connection conn = null;
					try {
						conn = DBUtil.getConnection();
						int result = dao.create(conn, jf.getText(), pf.getText(), jfName.getText());
					} catch (SQLException e1) {
						e1.printStackTrace();
					} finally {
						DBUtil.closeConn(conn);
					}
				} else {
					System.out.println("아이디와 비밀번호를 확인해주세요");
				}

				dispose();

			}
		});

		jf = new JTextField(); // 아이디 입력란 텍스트필드
		sl_background.putConstraint(SpringLayout.NORTH, jf, 115, SpringLayout.NORTH, background);
		sl_background.putConstraint(SpringLayout.NORTH, btnNewButton_1, 0, SpringLayout.NORTH, jf);
		sl_background.putConstraint(SpringLayout.WEST, btnNewButton_1, 6, SpringLayout.EAST, jf);
		sl_background.putConstraint(SpringLayout.SOUTH, btnNewButton_1, 0, SpringLayout.SOUTH, jf);
		sl_background.putConstraint(SpringLayout.WEST, jf, 121, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, jf, -115, SpringLayout.EAST, background);
		background.add(jf);
		jf.setColumns(10);

		jfName = new JTextField(); // 이름 입력란 텍스트필드
		sl_background.putConstraint(SpringLayout.NORTH, jfName, 167, SpringLayout.NORTH, background);
		sl_background.putConstraint(SpringLayout.SOUTH, jfName, -272, SpringLayout.SOUTH, background);
		sl_background.putConstraint(SpringLayout.SOUTH, jf, -20, SpringLayout.NORTH, jfName);
		sl_background.putConstraint(SpringLayout.WEST, jfName, 0, SpringLayout.WEST, jf);
		sl_background.putConstraint(SpringLayout.EAST, jfName, 0, SpringLayout.EAST, jf);
		background.add(jfName);
		jfName.setColumns(10);

		pf = new JPasswordField(); // 비번입력
		sl_background.putConstraint(SpringLayout.NORTH, pf, 21, SpringLayout.SOUTH, jfName);
		sl_background.putConstraint(SpringLayout.WEST, pf, 0, SpringLayout.WEST, jf);
		sl_background.putConstraint(SpringLayout.SOUTH, pf, 53, SpringLayout.SOUTH, jfName);
		sl_background.putConstraint(SpringLayout.EAST, pf, 0, SpringLayout.EAST, jf);
		background.add(pf);
		pf.setColumns(10);

		pf2 = new JPasswordField(); // 비번재입력
		sl_background.putConstraint(SpringLayout.NORTH, btnNewButton, 36, SpringLayout.SOUTH, pf2);
		sl_background.putConstraint(SpringLayout.NORTH, pf2, 19, SpringLayout.SOUTH, pf);
		sl_background.putConstraint(SpringLayout.SOUTH, pf2, 51, SpringLayout.SOUTH, pf);
		sl_background.putConstraint(SpringLayout.WEST, pf2, 0, SpringLayout.WEST, jf);
		sl_background.putConstraint(SpringLayout.EAST, pf2, 0, SpringLayout.EAST, jf);
		background.add(pf2);
		pf2.setColumns(10);

		pf2.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (wl.isPassWordSame(pf.getText(), pf2.getText())) {
					System.out.println("비번 사용가능");
					pwCheck = true;
				} else {
					System.out.println("비번 다름니다");
					pwCheck = false;
				}
			}
		});

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/아이디1.png"));
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel, 115, SpringLayout.NORTH, background);
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel, 82, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel, 0, SpringLayout.WEST, jf);
		background.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/패스워드.png")); // 패스워드라벨
		sl_background.putConstraint(SpringLayout.SOUTH, lblNewLabel, -73, SpringLayout.NORTH, lblNewLabel_1);
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 53, SpringLayout.NORTH, jfName);
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		sl_background.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, 0, SpringLayout.SOUTH, pf);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		background.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(new ImageIcon("src/img/패스워드.png")); // 패스워드라벨
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 51, SpringLayout.NORTH, pf);
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		sl_background.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, 0, SpringLayout.SOUTH, pf2);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, lblNewLabel);
		background.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(new ImageIcon("src/img/이름아이콘.png")); // 이름라벨
		sl_background.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 0, SpringLayout.NORTH, jfName);
		sl_background.putConstraint(SpringLayout.WEST, lblNewLabel_3, 82, SpringLayout.WEST, background);
		sl_background.putConstraint(SpringLayout.SOUTH, lblNewLabel_3, -104, SpringLayout.SOUTH, pf2);
		sl_background.putConstraint(SpringLayout.EAST, lblNewLabel_3, 0, SpringLayout.EAST, lblNewLabel);
		background.add(lblNewLabel_3);

		JButton btnNewButton_2 = new JButton(new ImageIcon("src/img/돌아가기.png"));
		sl_background.putConstraint(SpringLayout.NORTH, btnNewButton_2, 36, SpringLayout.SOUTH, pf2);
		sl_background.putConstraint(SpringLayout.WEST, btnNewButton_2, 3, SpringLayout.EAST, btnNewButton);
		sl_background.putConstraint(SpringLayout.EAST, btnNewButton_2, -91, SpringLayout.EAST, background);
		background.add(btnNewButton_2);
		btnNewButton_2.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_2.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			pf.setText("");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}