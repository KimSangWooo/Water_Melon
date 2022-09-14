import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import kr.co.greenart.dbutil.DBUtil;

public class MusicPlayerGUI extends JFrame {
	private String user_id;
	private JTable table1;
	private List<Integer> playingIDs = new ArrayList<>();
	private int size;
	private Connection conn;
	private PlayListDaoImp PLdao = new PlayListDaoImp();
	private MusicPlayer mp;

	public MusicPlayerGUI(String user_id) throws SQLException {
		this.user_id = user_id;
		// --------------------------------------------------22-0726
		setTitle(PLdao.findUsersName(conn, user_id) + "님의 플레이 리스트");
		// --------------------------------------------------22-0726
		setResizable(false);
		getContentPane().setBackground(Color.black); // 전체창 백그라운드배경
		setBounds(100, 100, 780, 550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 71, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 372, SpringLayout.WEST, getContentPane());
		panel.setBackground(Color.BLACK);
		getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel lbl_info = new JLabel(new ImageIcon("src/img/상단이미지.png"));
		sl_panel.putConstraint(SpringLayout.NORTH, lbl_info, 0, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lbl_info, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, lbl_info, 61, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lbl_info, 360, SpringLayout.WEST, panel);
		lbl_info.setForeground(Color.white);
		lbl_info.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_info.setBackground(Color.BLACK);
		lbl_info.setFont(new Font(" 고딕", Font.PLAIN, 18));
		panel.add(lbl_info);

		JButton btnNewButton_3 = new JButton(new ImageIcon("src/img/뒤로감기.png"));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton_3, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton_3, 33, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton_3, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton_3, 109, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnNewButton_3);
		btnNewButton_3.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton_3.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton_3.setFocusPainted(false);

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.prePlay();
				NowMusic song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});
		btnNewButton_3.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton startBtn = new JButton(new ImageIcon("src/img/재생.png"));
		springLayout.putConstraint(SpringLayout.NORTH, startBtn, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, startBtn, 116, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, startBtn, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, startBtn, 192, SpringLayout.WEST, getContentPane());
		getContentPane().add(startBtn);
		startBtn.setBorderPainted(false); // 버튼외곽선 없애줌
		startBtn.setContentAreaFilled(false); // 버튼 투명하게설정
		startBtn.setFocusPainted(false);

		// 버튼에 액션을 감지할 수 있는 액션부여
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NowMusic song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});

		startBtn.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton stopBtn = new JButton(new ImageIcon("src/img/정지버튼.png"));
		springLayout.putConstraint(SpringLayout.NORTH, stopBtn, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, stopBtn, 194, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, stopBtn, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, stopBtn, 270, SpringLayout.WEST, getContentPane());
		getContentPane().add(stopBtn);
		stopBtn.setBorderPainted(false); // 버튼외곽선 없애줌
		stopBtn.setContentAreaFilled(false); // 버튼 투명하게설정
		stopBtn.setFocusPainted(false);

		stopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.stop();
				lbl_info.setText("STOP");
			}
		});
		stopBtn.setFont(new Font("굴림", Font.PLAIN, 25));

		JButton btnNewButton = new JButton(new ImageIcon("src/img/앞으로감기.png"));
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 418, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, 274, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 479, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, 350, SpringLayout.WEST, getContentPane());
		getContentPane().add(btnNewButton);
		btnNewButton.setBorderPainted(false); // 버튼외곽선 없애줌
		btnNewButton.setContentAreaFilled(false); // 버튼 투명하게설정
		btnNewButton.setFocusPainted(false);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mp.nextPlay();
				NowMusic song = mp.play();
				lbl_info.setText(mp.musicInfo(song));
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 25));

		JLabel lblNewLabel = new JLabel(new ImageIcon("src/img/흔들어.png"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 341, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 116, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 408, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 270, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("src/img/배경사진.jpg"));
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 22, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 22, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -32, SpringLayout.NORTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -10, SpringLayout.EAST, panel);
		getContentPane().add(lblNewLabel_1);

		JPanel playlistPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, playlistPanel, 764, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, playlistPanel, 65, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, playlistPanel, 669, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, playlistPanel, 1131, SpringLayout.EAST, getContentPane());
		getContentPane().add(playlistPanel);

		String[] headings = new String[] { "", "ID", "노래 제목", "아티스트", "장르" }; // 테이블 열

		size = PLdao.findCnt(conn, user_id);
		Object[][] data = new Object[size][5];

		for (int i = 0; i < size; i++) {
			data[i][0] = new Boolean(false);
			data[i][1] = "";
			data[i][2] = "";
			data[i][3] = "";
			data[i][4] = "";
		}
		mp = PLdao.printPlaylist(conn, user_id, data, mp);

		// 각 셀마다 노래 제목, 아티스트 데이터를 삽입해야함.
		getContentPane().add(playlistPanel);
		playlistPanel.setLayout(new SpringLayout());

		DefaultTableModel model = new DefaultTableModel(data, headings) { // 체크박스열만 수정이 가능하게 수정함.

			public boolean isCellEditable(int i, int c) {
				if (c > 0)
					return false;

				else
					return true;
			}
		};

		table1 = new JTable(model) {

			@Override
			public Class<?> getColumnClass(int column) {
				if (column == 0) {
					return Boolean.class;
				}
				return super.getColumnClass(column);
			}

		};

		table1.setBackground(Color.cyan);
		table1.setPreferredScrollableViewportSize(new Dimension(200, 200));

		JScrollPane scrollPane = new JScrollPane(table1);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 27, SpringLayout.EAST, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -101, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 393, SpringLayout.EAST, lblNewLabel_1);
		getContentPane().add(scrollPane);

		table1.getColumnModel().getColumn(0).setPreferredWidth(5); // JTable 의 컬럼 길이 조절
		table1.getColumnModel().getColumn(1).setPreferredWidth(0);
		table1.getColumnModel().getColumn(2).setPreferredWidth(80);
		table1.getColumnModel().getColumn(3).setPreferredWidth(80);
		table1.getColumnModel().getColumn(4).setPreferredWidth(80);

		table1.getColumn("ID").setWidth(0);
		table1.getColumn("ID").setMinWidth(0);
		table1.getColumn("ID").setMaxWidth(0);

		JButton deleteBtn = new JButton(new ImageIcon("src/img/휴지통.png"));
		springLayout.putConstraint(SpringLayout.NORTH, deleteBtn, 6, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, deleteBtn, 365, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.SOUTH, deleteBtn, -56, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, deleteBtn, 0, SpringLayout.EAST, scrollPane);
		deleteBtn.setBorderPainted(false); // 창희추가
		deleteBtn.setContentAreaFilled(false); // 창희추가
		getContentPane().add(deleteBtn);

		JLabel lblNewLabel_2 = new JLabel("Play List");
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_2, -466, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 6, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 16, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -258, SpringLayout.EAST, getContentPane());
		lblNewLabel_2.setForeground(Color.CYAN);
		lblNewLabel_2.setFont(new Font("12롯데마트드림Medium", Font.ITALIC, 26));
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("       Delete");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 6, SpringLayout.SOUTH, deleteBtn);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 346, SpringLayout.EAST, btnNewButton);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_3, 9, SpringLayout.EAST, scrollPane);
		lblNewLabel_3.setForeground(Color.white);
		getContentPane().add(lblNewLabel_3);
		
		JCheckBox allSelectBtn = new JCheckBox("전체 선택");
		springLayout.putConstraint(SpringLayout.NORTH, allSelectBtn, -23, SpringLayout.SOUTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.SOUTH, allSelectBtn, 0, SpringLayout.SOUTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.EAST, allSelectBtn, -6, SpringLayout.WEST, lblNewLabel_3);
//		springLayout.putConstraint(SpringLayout.WEST, playBtn, 6, SpringLayout.EAST, allSelectBtn);
		getContentPane().add(allSelectBtn);

		allSelectBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (allSelectBtn.isSelected()) {
					for (int i = 0; i < size; i++) {
						table1.setValueAt(Boolean.TRUE, i, 0);
					}
				} else {
					for (int i = 0; i < size; i++) {
						table1.setValueAt(Boolean.FALSE, i, 0);
					}
				}

			}});
		
		table1.getTableHeader().setReorderingAllowed(false); // 테이블 열,행 조정불가 (창희추가)
		table1.getTableHeader().setResizingAllowed(false); // 테이블 열,행 조절불가 (창희추가)
		
		// ------------------------------220726
		// 추가---------------------------------------------
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<Integer> check = new ArrayList<>();
				List<Integer> deleteJtable = new ArrayList<>();
				for (int i = 0; i < size; i++) {
					if (table1.getValueAt(i, 0) == Boolean.TRUE) {
						check.add((Integer) table1.getValueAt(i, 1));
						deleteJtable.add(i);
					}
				}

				try {
					PLdao.DeletePL(conn, user_id, check);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				for (int i = size - 1; i >= 0; i--) {
					if (table1.getValueAt(i, 0) == Boolean.TRUE) {
						((DefaultTableModel) table1.getModel()).removeRow(i);
					}
				}

				try {
					size = PLdao.findCnt(conn, user_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		setLocationRelativeTo(null);// 창이 가운데 나오게
	}
	// ------------------------------220726

	// ------------------------------220726
	// 추가---------------------------------------------

}
