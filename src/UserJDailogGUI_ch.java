
//UserJDailogGUI.java
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


//창희가 추가로 넣음 
public class UserJDailogGUI_ch extends JDialog implements ActionListener{
	
	JPanel pw=new JPanel(new GridLayout(4,1));
	JPanel pc=new JPanel(new GridLayout(4,1));
	JPanel ps=new JPanel();

	JTextField id=new JTextField();
	JTextField name=new JTextField();
	JTextField age=new JTextField();
	JTextField addr=new JTextField();
	

	JButton confirm;
	JButton reset=new JButton("취소");

   MenuJTabaleExam_ch me;

   JPanel idCkP =new JPanel(new BorderLayout());
   JButton idCkBtn = new JButton("IDCheck");
   
   UserDefaultJTableDAO_ch dao =new UserDefaultJTableDAO_ch();
   

	public UserJDailogGUI_ch(MenuJTabaleExam_ch me, String index){
		super(me,"다이어로그");
		this.me=me;
		if(index.equals("가입")){
			confirm=new JButton(index);
		}else{
			confirm=new JButton("수정");	
			
			//text박스에 선택된 레코드의 정보 넣기
			int row = me.jt.getSelectedRow();//선택된 행
			id.setText( me.jt.getValueAt(row, 0).toString() );
			name.setText( me.jt.getValueAt(row, 1).toString() );
			age.setText( me.jt.getValueAt(row, 2).toString() );
			addr.setText( me.jt.getValueAt(row, 3).toString() );
			
			//id text박스 비활성
			id.setEditable(false);
	
			//IDCheck버튼 비활성화
			idCkBtn.setEnabled(false);
		}
		
		
		//Label추가부분
		
		idCkP.add(id,"Center");
		idCkP.add(idCkBtn,"East");
		
		//TextField 추가
		pc.add(idCkP);
		pc.add(name);
		pc.add(age);
		pc.add(addr);
		
		ps.add(confirm); 
		ps.add(reset);
	
		add(pw,"West"); 
		add(pc,"Center");
		add(ps,"South");
		
		setSize(300,250);
		setVisible(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		//이벤트등록
        confirm.addActionListener(this); //가입/수정 이벤트등록
        reset.addActionListener(this); //취소 이벤트등록
        idCkBtn.addActionListener(this);// ID중복체크 이벤트 등록
		
	}//생성자끝
    
	/**
	 * 가입/수정/삭제 기능에 대한 부분
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
	   String btnLabel =e.getActionCommand();//이벤트주체 대한 Label 가져오기
	    
	   if(btnLabel.equals("가입")){
		   if(dao.userListInsert(this) > 0 ){//가입성공
			   messageBox(this , name.getText()+"님 가입축드립니다.");
			   dispose();//JDialog 창닫기
			   
			   dao.userSelectAll(me.dt);//모든레코드가져와서 DefaultTableModel에 올리기
			   
			   if(me.dt.getRowCount() > 0)
				   me.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			   
		   }else{//가입실패
			   messageBox(this,"가입되지 않았습니다.");
		   }
		   
	   }else if(btnLabel.equals("수정")){
		   
		    if( dao.userUpdate(this) > 0){
		    	messageBox(this, "수정완료되었습니다.");
		    	dispose();
		    	dao.userSelectAll(me.dt);
		    	if(me.dt.getRowCount() > 0 ) me.jt.setRowSelectionInterval(0, 0);
		    	
		    }else{
		    	messageBox(this, "수정되지 않았습니다.");
		    }
		   
		   
		   
	   }else if(btnLabel.equals("취소")){
		   dispose();
		   
	   }else if(btnLabel.equals("IDCheck")){
		   //id텍스트박스에 값 없으면 메세지 출력 있으면 DB연동한다.
		   if(id.getText().trim().equals("")){
			   messageBox(this,"ID를 입력해주세요.");
			   id.requestFocus();//포커스이동
		   }else{
			   
			  if(  dao.getIdByCheck(id.getText()) ){ //중복아니다.(사용가능) 
				  messageBox(this, id.getText()+"는 사용가능합니다.");  
			  }else{ //중복이다.
				  messageBox(this,id.getText()+"는 중복입니다.");
				  
				  id.setText("");//text박스지우기
				  id.requestFocus();//커서놓기
			  }
			   
		   }
		   
	   }
	   
		
	}//actionPerformed끝
	
	/**
	 * 메시지박스 띄워주는 메소드 작성
	 * */
	public static void messageBox(Object obj , String message){
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}//클래스끝