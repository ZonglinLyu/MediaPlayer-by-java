package lvzonglin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.media.*;
import javax.media.bean.playerbean.MediaPlayer;

public class VideoPlayer extends JFrame implements ActionListener{

	//定义一个可分裂面板和两个分面板
	JSplitPane splitPanel = null;
	JPanel playPanel = null;
	JPanel listPanel = null;
	//为了流式布局中label占满一行，放到单独的JPanel中
	JPanel labelPanel = null;
	
	//定义菜单栏组件
	//菜单栏
	JMenuBar jmb = null;
	//菜单
	JMenu jm = null;
	//菜单下拉选项
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	JMenuItem jmi4 = null;
	
	//定义JPanel内的组件
	JList jl= null;
	JLabel jb1,jb2= null;
	JScrollPane jsp = null;
	
	//定义文件名；
	String fileName = null;
	//定义一个播放器；
	MediaPlayer myPlayer;
	//定义一组集合，在播放列表上动态添加播放文件
	Vector<File> files = null;
	Vector<String> list = null;
	public static void main(String[] args) {
		VideoPlayer vp = new VideoPlayer();
	}
	//创建构造函数
	public VideoPlayer(){
		files = new Vector<File>();
		list = new Vector<String>();
		//创建面板
		splitPanel = new JSplitPane();
		playPanel = new JPanel();
		listPanel = new JPanel();
		labelPanel = new JPanel();
		
		//创建窗口组件
		jmb = new JMenuBar();
		jm = new JMenu("LZL Player");
		jmi1 = new JMenuItem("打开本地文件（O）");
		jmi2 = new JMenuItem("设置（S）");
		jmi3 = new JMenuItem("详细信息（M）");
		jmi4 = new JMenuItem("帮助（H）");
		
		//创建JPanel内组件

		jl = new JList(list);
		jsp = new JScrollPane(jl);
		jb1 = new JLabel("视频");
		jb2 = new JLabel("播放列表  ");
		
		//菜单栏
		jmb.add(jm);
		jm.add(jmi1);
		jm.add(jmi2);
		jm.add(jmi3);
		jm.add(jmi4);
		
		
		//为组件设置快捷键
		jm.setMnemonic('L');
		jmi1.setMnemonic('O');
		jmi2.setMnemonic('S');
		jmi3.setMnemonic('M');
		jmi4.setMnemonic('H');
		
		//为组件设置监听
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2.setActionCommand("set");
		jmi3.setActionCommand("message");
		jmi4.setActionCommand("help");
		
		//设置布局，均为默认，JFrame默认边界布局，JPanel默认流式布局
		
		//设置可分割面板
		splitPanel.setLeftComponent(playPanel);
		splitPanel.setRightComponent(listPanel);
		splitPanel.setDividerSize(5);
		splitPanel.setResizeWeight(1);
		splitPanel.setEnabled(false);
		
		//playPanel面板
		playPanel.add(jb1);
		
		//listPanel面板
		listPanel.setLayout(new BorderLayout());
		labelPanel.add(jb2);
		listPanel.add(labelPanel,BorderLayout.NORTH);
		listPanel.add(jsp,BorderLayout.CENTER);
		
		//设置图标 太丑  放弃了
		//ImageIcon icon = new ImageIcon("./img/tubiao.png"); 
		//jm.setIcon(icon);
		
		//设置窗口
		this.add(splitPanel);
		this.setJMenuBar(jmb);
		this.setVisible(true);
		this.setLocation(100, 150);
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("播放器 by 吕宗霖");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean isInList = false;
		// TODO Auto-generated method stub
		//不同组件的不同命令
		//“打开”
		if(e.getActionCommand().equals("open")){
			//打开文件选择器
			//这里使用FileDialog，而没有使用JFileChooser
			//是因为FileDialog使用系统界面，JFileChooser使用java共同界面，太丑了！
			FileDialog fd = new FileDialog(new JFrame(), "选择要播放的文件", FileDialog.LOAD);
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
		//“设置”
		else if(e.getActionCommand().equals("set")){
			
		}
		//“详细信息”
		else if(e.getActionCommand().equals("message")){
			
		}
		//“帮助”
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






































