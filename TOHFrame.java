//Satnam Waheguru
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class TOHFrame extends JFrame implements ActionListener
	{	static TOHFrame jp = new TOHFrame();
		
		int n1=1;
		int n2=5;
		surface sc ;
		surface surf ;
		showing p ;
		String d1;
		String d2;
		JLabel E1 = new JLabel("No. Of Plates");
		JLabel E2 = new JLabel("Speed");
		JButton Button = new JButton("Start");
		JButton Button1 = new JButton("Refresh");
		JComboBox field = new JComboBox();	
		JComboBox Speed = new JComboBox();
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		
		
		public static void main (String[] args)
		{
			jp.TOHFrame();
		}
		public void TOHFrame()
		{
			//System.out.println("here");
			field.addItem("1");
			field.addItem("2");
			field.addItem("3");
			field.addItem("4");
			field.addItem("5");
			field.addItem("6");
			field.addItem("7");
			Speed.addItem("1");
			Speed.addItem("5");
			Speed.addItem("10");
			Speed.addItem("15");
				
			
			panel.add(E1);
			panel.add(field);
			panel.add(E2);
			panel.add(Speed);
			panel.add(Button);
			panel1.add(Button1);
			jp.setTitle("Towers Of Hanoi Solution By Hardeep");
			jp.setSize(1000,300);
			
			Button.addActionListener(jp);
			Button1.addActionListener(jp);
			Button1.setEnabled(false);
			Button1.setVisible(false);
			Button.setRolloverEnabled(true);
			jp.setResizable(false);
			jp.add(panel,BorderLayout.WEST);
			jp.add(panel1,BorderLayout.EAST);
			jp.setVisible(true);
			
		}
	
public void actionPerformed(ActionEvent e)
			{		
				if(e.getSource() == Button)
				{ if(Button.getText() == "Start")
					{
						d2 =(String)Speed.getSelectedItem();
						d1 =(String)field.getSelectedItem();
						if(d1=="1")
							{n1=1;}
						if(d1=="2")
							{n1=2;}
						if(d1=="3")
							{n1=3;}
						if(d1=="4")
							{n1=4;}
						if(d1=="5")
							{n1=5;}
						if(d1=="6")
							{n1=6;}
						if(d1=="7")
							{n1=7;}
						if(d2=="1")
							{n2=15;}
						if(d2=="5")
							{n2=10;}
						if(d2=="10")
							{n2=5;}
						if(d2=="15")
							{n2=1;}
					
					sc=new surface(n1);
					surf = new surface(n1);
					surf.setVisible(false);
					jp.add(sc,BorderLayout.CENTER);
					jp.setVisible(true);	 
					sc.repaint();
					 p = new showing(sc,n1);
					field.setVisible(false);
					E1.setVisible(false);
					sc.t = n2;				
					p.start();
					Button.setText("Change Speed");
					Button1.setVisible(true);
					Button1.setEnabled(true);
				}
			
				else if(Button.getText()=="Change Speed")
				{	
					d2 =(String)Speed.getSelectedItem();
					if(d2=="1")
						{n2=15;}
					if(d2=="5")
						{n2=10;}
					if(d2=="10")
						{n2=5;}
					if(d2=="15")
						{n2=1;}
						surface.t = n2;
				}
			}
			else if(e.getSource()==Button1)
			{
				sc.setVisible(false);
				field.setVisible(true);
				E1.setVisible(true);
				jp.add(surf,BorderLayout.CENTER);
				jp.setVisible(true);	 
				Button.setText("Start");
				surf.repaint();
			}
		}
	}
 class surface extends JComponent 
		{	boolean abort = false;
			public static int t ;
			public int num;
			public ArrayList<Plate> platesA = new ArrayList<Plate>();
			public ArrayList<Plate> platesB = new ArrayList<Plate>();
			public ArrayList<Plate> platesC = new ArrayList<Plate>();
			pole poleA = new pole(150,50,10,100);
			pole poleB = new pole(280,50,10,100);
			pole poleC = new pole(410,50,10,100);
			Plate base  = new Plate(50,150,500,10);
			public surface(int k)
			{	
				for(int i=0;i<=k;i++)
					{	
						platesA.add(new Plate(90+10*i,150-10*i,130-20*i,10));
					}	   
						platesB.add(new Plate(210,150,130,10));	
						platesC.add(new Plate(340,150,130,10));
						
			}

			public void paint(Graphics g)
				{	
					Graphics2D g2 =(Graphics2D)g;
					Graphics2D g3  =(Graphics2D)g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					g3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
					g2.setColor(Color.GRAY);
					g2.fill(poleA);
					g2.fill(poleB);
					g2.fill(poleC);
					g2.fill(base);
					g3.setColor(Color.BLACK);
				for(Plate s:platesA)
					{	
						g3.fill(s);
					}
				for(Plate s:platesB)
					{	
						g3.fill(s);
					}
				for(Plate s:platesC)
					{	
						g3.fill(s);
					}
				}

	public void nice(int n,pole A,pole B,pole C)
			{
				if(n==1)
					{	
						A.top().move(A,C);
					}
				else if(n>1)
					{
						nice(n-1,A,C,B);
						A.top().move(A,C);
						nice(n-1,B,A,C);
					}
	         
			}

		
class pole extends Rectangle2D.Float
{	Plate tplate;
	int x_pos;
	int y_pos;
	int Length;
	int Width;	
	int Nplates ;
	public pole(int x,int y,int w,int l)
		{	
			super(x,y,w,l);
			this.x_pos=x;
			this.y_pos=y;
			this.Length = l;
			this.Width = w;
			
		}
	public Plate top()
	{	
		if(this.x_pos == 150)
		{	
			return platesA.get(platesA.size()-1);
		}
		if(this.x_pos == 280)
		{
			return platesB.get(platesB.size()-1);
		}
		if(this.x_pos == 410)
		{
			return platesC.get(platesC.size()-1);
		}			
	return null;
}
		
	public void pop()
	{ 
		
		if(this.x_pos == 150)
		{	
			platesA.remove(platesA.get(platesA.size()-1));
		}
		if(this.x_pos == 280)
		{
			platesB.remove(platesB.get(platesB.size()-1));
		}
		if(this.x_pos == 410)
		{
			platesC.remove(platesC.get(platesC.size()-1));
		}			
		
	}
	
	public void push(Plate pt)
	{	
		if(this.x_pos == 150)
		{	
			platesA.add(pt);
		}
		if(this.x_pos == 280)
		{
			platesB.add(pt);
		}
		if(this.x_pos == 410)
		{
			platesC.add(pt);
		}	
	}		

	public int topY()
		{
			if(this.x_pos == 150)
			{
				return 150-10*(platesA.size()-1);
			}
			if(this.x_pos == 280)
			{
				return 150-10*(platesB.size()-1);
			}
			if(this.x_pos == 410)
			{
				return 150-10*(platesC.size()-1);
			}
		return 0;
		}

}
 class Plate extends Rectangle2D.Float
{	
	pole pl;
	int x_pos;
	int y_pos;
	int width;
	int length;
	public Plate(int x,int y,int l,int w )
	{	
		super(x,y,l,w);
		this.x_pos = x;
		this.y_pos = y;
		this.width = width;
		this.length = l;
	}
	public Plate()
	{	super(0,0,0,0);
		this.x_pos = 0;
		this.y_pos = 0;
		this.width = 0;
		this.length = 0;
	}
	
	
	
	public void move(pole A,pole B)
		{System.out.println(abort);
				if(!abort)
				{
				if(A.x_pos==150 && B.x_pos == 280)
					{	
						
							if(super.y != 10 && super.x != (B.x_pos-( this.length/2)-2) )
							{
								while(super.y != 10 && super.x != (B.x_pos-( this.length/2)-2) )
									{	
										super.y =  super.y-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}
									}
						
							}
							
						if(super.y == 10 &&  super.x != (B.x_pos-( this.length/2)+5) )		
							{
								while(super.y == 10 &&  super.x != (B.x_pos-( this.length/2)+5))
									{
										super.x =  super.x+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
							
							}

						if( super.x == (B.x_pos-( this.length/2)+5) && super.y != (B.topY()-10))
							{
									while( super.x == (B.x_pos-( this.length/2)+5) && super.y != (B.topY()-10))
									{
										super.y =  super.y+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
								
								B.push(this);
								A.pop();	
								
							}
					}
								
				
				//for A to B end
				
				//for A to C start
				if(A.x_pos==150 && B.x_pos == 410)
					{			
						if(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
							{
								while(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
									{	
										super.y =  super.y-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
						
							}
							
						if(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))		
							{
								while(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))
									{
										super.x =  super.x+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
							
							}

						if( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
							{
									while( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
									{
										super.y =  super.y+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
								
								B.push(this);
								A.pop();	
								//System.out.println(B.topY());
							}
					}	
				//for A to C end
				
				//for B to A start
				if(A.x_pos==280 && B.x_pos == 150)
					{
						if(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
							{
								while(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
									{	
										super.y =  super.y-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
						
							}
							
						if(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))		
							{
								while(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))
									{
										super.x =  super.x-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
							
							}

						if( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
							{
									while( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
									{
										super.y =  super.y+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
								
								B.push(this);
								A.pop();	
								
							}	
					}
				//for B to A end
				
				//for B to C start
				if(A.x_pos==280 && B.x_pos == 410)
					{
						if(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
							{
								while(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
									{	
										super.y =  super.y-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
						
							}
							
						if(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))		
							{
								while(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))
									{
										super.x =  super.x+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
							
							}

						if( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
							{
									while( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
									{
										super.y =  super.y+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
								
								B.push(this);
								A.pop();	
								
							}
				
					}
				//for B to C end
				
				//for C to A start
				if(A.x_pos==410 && B.x_pos == 150)
					{				
						if(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
							{
								while(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
									{	
										super.y =  super.y-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
						
							}
							
						if(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))		
							{
								while(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))
									{
										super.x =  super.x-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
							
							}

						if( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
							{
									while( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
									{
										super.y =  super.y+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
								
								B.push(this);
								A.pop();	
								
							}
					}
				//for C to A end
				
				//for C to B start
				if(A.x_pos==410 && B.x_pos == 280)
					{			
						if(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
							{
								while(super.y != 10 && super.x != (5+B.x_pos-( this.length/2)))
									{	
										super.y =  super.y-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
						
							}
							
						if(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))		
							{
								while(super.y == 10 &&  super.x != (5+B.x_pos-( this.length/2)))
									{
										super.x =  super.x-1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
							
							}

						if( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
							{
									while( super.x == (5+B.x_pos-( this.length/2)) && super.y != (B.topY()-10))
									{
										super.y =  super.y+1;
										repaint();
										try{ Thread.sleep(t);}
										catch(InterruptedException e)
										{abort = true;}
										if(abort)
										{
											break;
										}

									}
								
								B.push(this);
								A.pop();	
								
							}	
						}
				//for C to B end
				
			}
		}
}
}

class showing extends Thread
	{	int no;
		surface s;
		boolean abort=true;
		showing(surface s,int l)
		{
		this.s=s;
		this.no = l;
		}
		public void run()
		{	s.nice(no,s.poleA,s.poleB,s.poleC);
				
		}
	
	}
