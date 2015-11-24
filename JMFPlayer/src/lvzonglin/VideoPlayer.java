package lvzonglin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.media.*;
import javax.media.bean.playerbean.MediaPlayer;

public class VideoPlayer extends JFrame implements ActionListener{

	//����һ���ɷ����������������
	JSplitPane splitPanel = null;
	JPanel playPanel = null;
	JPanel listPanel = null;
	//Ϊ����ʽ������labelռ��һ�У��ŵ�������JPanel��
	JPanel labelPanel = null;
	
	//����˵������
	//�˵���
	JMenuBar jmb = null;
	//�˵�
	JMenu jm = null;
	//�˵�����ѡ��
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	JMenuItem jmi4 = null;
	
	//����JPanel�ڵ����
	JList jl= null;
	JLabel jb1,jb2= null;
	JScrollPane jsp = null;
	
	//�����ļ�����
	String fileName = null;
	//����һ����������
	MediaPlayer myPlayer;
	//����һ�鼯�ϣ��ڲ����б��϶�̬��Ӳ����ļ�
	Vector<File> files = null;
	Vector<String> list = null;
	public static void main(String[] args) {
		VideoPlayer vp = new VideoPlayer();
	}
	//�������캯��
	public VideoPlayer(){
		files = new Vector<File>();
		list = new Vector<String>();
		//�������
		splitPanel = new JSplitPane();
		playPanel = new JPanel();
		listPanel = new JPanel();
		labelPanel = new JPanel();
		
		//�����������
		jmb = new JMenuBar();
		jm = new JMenu("LZL Player");
		jmi1 = new JMenuItem("�򿪱����ļ���O��");
		jmi2 = new JMenuItem("���ã�S��");
		jmi3 = new JMenuItem("��ϸ��Ϣ��M��");
		jmi4 = new JMenuItem("������H��");
		
		//����JPanel�����

		jl = new JList(list);
		jsp = new JScrollPane(jl);
		jb1 = new JLabel("��Ƶ");
		jb2 = new JLabel("�����б�  ");
		
		//�˵���
		jmb.add(jm);
		jm.add(jmi1);
		jm.add(jmi2);
		jm.add(jmi3);
		jm.add(jmi4);
		
		
		//Ϊ������ÿ�ݼ�
		jm.setMnemonic('L');
		jmi1.setMnemonic('O');
		jmi2.setMnemonic('S');
		jmi3.setMnemonic('M');
		jmi4.setMnemonic('H');
		
		//Ϊ������ü���
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2.setActionCommand("set");
		jmi3.setActionCommand("message");
		jmi4.setActionCommand("help");
		
		//���ò��֣���ΪĬ�ϣ�JFrameĬ�ϱ߽粼�֣�JPanelĬ����ʽ����
		
		//���ÿɷָ����
		splitPanel.setLeftComponent(playPanel);
		splitPanel.setRightComponent(listPanel);
		splitPanel.setDividerSize(5);
		splitPanel.setResizeWeight(1);
		splitPanel.setEnabled(false);
		
		//playPanel���
		playPanel.add(jb1);
		
		//listPanel���
		listPanel.setLayout(new BorderLayout());
		labelPanel.add(jb2);
		listPanel.add(labelPanel,BorderLayout.NORTH);
		listPanel.add(jsp,BorderLayout.CENTER);
		
		//����ͼ�� ̫��  ������
		//ImageIcon icon = new ImageIcon("./img/tubiao.png"); 
		//jm.setIcon(icon);
		
		//���ô���
		this.add(splitPanel);
		this.setJMenuBar(jmb);
		this.setVisible(true);
		this.setLocation(100, 150);
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("������ by ������");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isInList = false;
		// TODO Auto-generated method stub
		//��ͬ����Ĳ�ͬ����
		//���򿪡�
		if(e.getActionCommand().equals("open")){
			//���ļ�ѡ����
			//����ʹ��FileDialog����û��ʹ��JFileChooser
			//����ΪFileDialogʹ��ϵͳ���棬JFileChooserʹ��java��ͬ���棬̫���ˣ�
			FileDialog fd = new FileDialog(new JFrame(), "ѡ��Ҫ���ŵ��ļ�", FileDialog.LOAD);
			fd.setVisible(true);
			File file = new File(fd.getDirectory(), fd.getFile());
			System.out.println(file.fileFullName);
			if(!file.fileFullName.equals("nullnull")){
				for(int i=0;i<files.size();i++){
					File fileInVector = files.get(i);
					if(file.fileFullName.equals(fileInVector.fileFullName)){
						isInList = true;
					}
					
				}
				
				if(!isInList){
					files.add(file);
					
					list.addElement(file.fileName);
			//		js.setListData(files);
					System.out.println(files.size());
					jl.updateUI();
					
					
				}
				
				myPlayer = new MediaPlayer();
				myPlayer.setMediaLocator(new MediaLocator("file:///" + file.fileFullName));
				myPlayer.realize();
				myPlayer.start();
			}
		}
		//�����á�
		else if(e.getActionCommand().equals("set")){
			
		}
		//����ϸ��Ϣ��
		else if(e.getActionCommand().equals("message")){
			
		}
		//��������
		else if(e.getActionCommand().equals("help")){
			
		}
	}

}


class File{
	String fileAdress;
	String fileName;
	String fileFullName;
	
	public File(String fileAdress, String fileName) {
		this.fileAdress = fileAdress;
		this.fileName = fileName;
		fileFullName = fileAdress+fileName;
	}
}






































