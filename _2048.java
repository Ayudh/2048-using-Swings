import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
public class _2048 implements KeyListener,ActionListener
{
	boolean upMovable = true,downMovable = true	,rightMovable = true,leftMovable =true;
	JDialog jd;
	JFrame jf;
	 JLabel jlb[] = new JLabel[16];
	 JPanel jp[] = new JPanel[16];
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				new _2048();
			}
		});
	}
	public _2048()
	{
		jf = new JFrame("2048");
		jf.setSize(565, 580);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setResizable(false);
		jf.addKeyListener(this);
		Container c = jf.getContentPane();
		c.setBackground(new Color(51,51,51));
		for(int i=0;i<16;i++)
		{
			jlb[i]=new JLabel("");
			jp[i]=new JPanel();
			jp[i].setLayout(new GridBagLayout());
			jp[i].setOpaque(true);
			jp[i].setBackground(new Color(75,75,75));
			jf.add(jp[i]);
		}
		jp[0].setBounds(15,15,120,120);
		jp[1].setBounds(150, 15, 120, 120);
		jp[2].setBounds(285,15,120,120);
		jp[3].setBounds(420, 15, 120, 120);
		
		jp[4].setBounds(15	, 150, 120, 120);
		jp[5].setBounds(150, 150, 120, 120);
		jp[6].setBounds(285, 150, 120, 120);
		jp[7].setBounds(420, 150, 120, 120);
		
		jp[8].setBounds(15, 285, 120, 120);
		jp[9].setBounds(150, 285, 120, 120);
		jp[10].setBounds(285, 285, 120, 120);
		jp[11].setBounds(420, 285, 120, 120);
		
		jp[12].setBounds(15, 420, 120, 120);
		jp[13].setBounds(150, 420, 120, 120);
		jp[14].setBounds(285, 420, 120, 120);
		jp[15].setBounds(420, 420, 120, 120);
		Font f = new Font("Serif",Font.BOLD,40);
		
		for(int i=0;i<16;i++)
		{
			jlb[i].setForeground(Color.black);
			jlb[i].setFont(f);
			jp[i].add(jlb[i]);
		}
		Random r = new Random();
		int i = r.nextInt(16);
		jlb[i].setText("2");
		int j;
		do{
			j= r.nextInt(16);
		}while(i==j);
		jlb[j].setText("2");
		colorTiles();
		
		JButton jb;
		jd = new JDialog(jf,"Game Over",true);
		//jd.setBounds(60, 20, 200, 200);
		jd.setLayout(new GridBagLayout());
		jd.setSize(200, 200);
		jd.setLocationRelativeTo(null);
		 jb = new JButton("ok");
		// jb.setBounds(30,30,50,50);
		 JLabel gmovr = new JLabel("Game over");
		 Font f1 = new Font("Serif",Font.BOLD,30);
		 gmovr.setFont(f1);
		// gmovr.setBounds(95,95,100,100);
		jd.add(gmovr);
		jd.add(jb);
		
		jb.addActionListener(this);
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP && upMovable == true){
			upMove();colorTiles(); next();
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN && downMovable == true){
			downMove();colorTiles(); next();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && rightMovable == true){
			rightMove();colorTiles(); next();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT && leftMovable == true){
			leftMove();colorTiles(); next();
		}
		if(leftMovable == false &&rightMovable==false && upMovable==false&& downMovable==false)
		{
			jd.setVisible(true);
		}
	}
	private void movable()
	{
		upMovable = false;
		downMovable = false;
		rightMovable = false;
		leftMovable = false;
		//to know about right move
		int fir,sec;
		x:{ for(int i =3;i<=15;i=i+4)
		{
			for(int k = 3;k>0;k--)
			{
				for(int l = i;l>i-k;l--)
				{
					if(jlb[l].getText()=="")
					{
						if(jlb[l-1].getText()!="")
						{
							rightMovable  =true;
							break x;
						}
					}
				}
			}
			for(int j=i;j>i-3;j--)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j-1].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j-1].getText());
				if(fir==sec)
				{
					if(fir!=0&&sec!=0){
					rightMovable = true;
					break x;}
				}
			}
		}}
		//to know about left move
		y:{ for(int i =0;i<=12;i=i+4)
		{
			for(int k = 3;k>0;k--)
			{
				for(int l = i;l<i+k;l++)
				{
					if(jlb[l].getText()=="")
					{
						if(jlb[l+1].getText()!="")
						{
							leftMovable = true;
							break y;
						}
					}
				}
			}
			for(int j=i;j<i+3;j++)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j+1].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j+1].getText());
				if(fir==sec)
				{
					if(fir!=0&&sec!=0){
					leftMovable = true;
					break y;}
				}
			}
		}}
		//to know about down move
		z:{ for(int i =12;i<=15;i++)
		{
			for(int k = 12;k>0;k=k-4)
			{
				for(int l = i;l>i-k;l=l-4)
				{
					if(jlb[l].getText()=="")
					{
						if(jlb[l-4].getText()!="")
						{
							downMovable  = true;
							break z;
						}
					}
				}
			}
			for(int j=i;j>i-12;j=j-4)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j-4].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j-4].getText());
				if(fir==sec)
				{
					if(fir!=0&&sec!=0){
					downMovable = true;
					break z;}
				}
			}
		}}
		//to know about up move
		u:{ for(int i =0;i<=3;i++)
		{
			for(int k = 12;k>0;k=k-4)
			{
				for(int l = i;l<i+k;l=l+4)
				{
					if(jlb[l].getText()=="")
					{
						if(jlb[l+4].getText()!="")
						{
							upMovable = true;
							break u;
						}
					}
				}
			}
			 for(int j=i;j<i+12;j=j+4)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j+4].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j+4].getText());
				if(fir==sec)
				{
					if(fir!=0&&sec!=0){
					upMovable = true;
					break u;}
				}
			}
		}}
	}
	private void next() 
	{
		Random r = new Random();
		boolean flag = false;
		for(int i=0;i<16;i++)
		{
			if(jlb[i].getText()=="")
			{
				flag = true;
				break;
			}
		}
		int i;
		if(flag)
		{
		do{
			i = r.nextInt(16);
		}while(jlb[i].getText()!="");
		jlb[i].setText("2");
		colorTiles();
		}
		movable();
		if(flag == false && upMovable == false && downMovable == false && rightMovable == false && leftMovable == false)
		{
			jd.setVisible(true);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getActionCommand()=="ok")
		{
			initialize(); jd.setVisible(false);
		}
	}
	private void initialize()
	{
		leftMovable =true;
		rightMovable=true;
		upMovable=true;
		downMovable = true;
		for(int i=0;i<16;i++)
		{
			jlb[i].setText("");
		}
		colorTiles();
		Random r = new Random();
		int i = r.nextInt(16);
		jlb[i].setText("2");
		int j;
		do{
			j= r.nextInt(16);
		}while(i==j);
		jlb[j].setText("2");
		colorTiles();
	}
	private void rightMove() 
	{
		int fir,sec,fin;
		for(int i =3;i<=15;i=i+4)
		{
			rightify(i);
			//code to add consecutive same terms
			for(int j=i;j>i-3;j--)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j-1].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j-1].getText());
				if(fir==sec)
				{
					fin = fir+sec;
					if(fin!=0)
						jlb[j].setText(""+fin);
					else jlb[j].setText("");
					jlb[j-1].setText("");
				}
			}
			rightify(i);
		}
	}
	private void rightify(int i)
	{
		//code to righify
		for(int k = 3;k>0;k--)
		{
			for(int l = i;l>i-k;l--)
			{
				if(jlb[l].getText()=="")
				{
					if(jlb[l-1].getText()!="")
					{
						jlb[l].setText(jlb[l-1].getText());
						jlb[l-1].setText("");
					}
				}
			}
		}
	}
	private void leftMove() 
	{
		int fir,sec,fin;
		for(int i =0;i<=12;i=i+4)
		{
			leftify(i);
			//code to add consecutive same terms
			for(int j=i;j<i+3;j++)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j+1].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j+1].getText());
				if(fir==sec)
				{
					fin = fir+sec;
					if(fin!=0)
						jlb[j].setText(""+fin);
					else jlb[j].setText("");
					jlb[j+1].setText("");
				}
			}
			leftify(i);
		}
	}
	private void leftify(int i)
	{
		//code to leftify
		for(int k = 3;k>0;k--)
		{
			for(int l = i;l<i+k;l++)
			{
				if(jlb[l].getText()=="")
				{
					if(jlb[l+1].getText()!="")
					{
						jlb[l].setText(jlb[l+1].getText());
						jlb[l+1].setText("");
					}
				}
			}
		}
	}
	private void downMove() 
	{
		int fir,sec,fin;
		for(int i =12;i<=15;i++)
		{
			downify(i);
			//code to add consecutive same terms
			for(int j=i;j>i-12;j=j-4)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j-4].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j-4].getText());
				if(fir==sec)
				{
					fin = fir+sec;
					if(fin!=0)
						jlb[j].setText(""+fin);
					else jlb[j].setText("");
					jlb[j-4].setText("");
				}
			}
			downify(i);
		}
	}
	private void downify(int i) 
	{
		//code to downify
		for(int k = 12;k>0;k=k-4)
		{
			for(int l = i;l>i-k;l=l-4)
			{
				if(jlb[l].getText()=="")
				{
					if(jlb[l-4].getText()!="")
					{
						jlb[l].setText(jlb[l-4].getText());
						jlb[l-4].setText("");
					}
				}
			}
		}
	}
	private void upMove() 
	{
		int fir,sec,fin;
		for(int i =0;i<=3;i++)
		{
			upify(i);
			//code to add consecutive same terms
			for(int j=i;j<i+12;j=j+4)
			{
				if(jlb[j].getText()=="")
					fir = 0;
				else fir = Integer.parseInt(jlb[j].getText());
				if(jlb[j+4].getText()=="")
					sec = 0;
				else sec = Integer.parseInt(jlb[j+4].getText());
				if(fir==sec)
				{
					fin = fir+sec;
					if(fin!=0)
						jlb[j].setText(""+fin);
					else jlb[j].setText("");
					jlb[j+4].setText("");
				}
			}
			upify(i);
		}
	}
	private void upify(int i) 
	{
		//code to upify
		for(int k = 12;k>0;k=k-4)
		{
			for(int l = i;l<i+k;l=l+4)
			{
				if(jlb[l].getText()=="")
				{
					if(jlb[l+4].getText()!="")
					{
						jlb[l].setText(jlb[l+4].getText());
						jlb[l+4].setText("");
					}
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	public void colorTiles()
	{
		for(int i=0;i<16;i++)
		{
			Color c = new Color(75,75,75);
			String s = jlb[i].getText();
			if(s.equals("2"))
				c= Color.WHITE;
			if(s.equals("4"))
				c= Color.WHITE;
			if(s.equals("8"))
				c= new Color(255,255,170);
			if(s.equals("16"))
				c= new Color(255,255,128);
			if(s.equals("32"))
				c= new Color(255,255,85);
			if(s.equals("64"))
				c= new Color(255,255,43);
			if(s.equals("128"))
				c= new Color(255,255,0);
			if(s.equals("256"))
				c= new Color(213,213,0);
			if(s.equals("512"))
				c= new Color(170,170,0);
			if(s.equals("1024"))
				c= new Color(128,128,0);
			if(s.equals("2048"))
				c= new Color(85,85,0);
			jp[i].setBackground(c);
		}
	}
}