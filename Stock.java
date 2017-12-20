import java.util.*;
import java.io.*;
public class Stock
{
	//Perform i/O operation
	public static long time;
	public static queimp orders = new queimp();
	public static Exchange market= new Exchange();
	
	
	void performAction(String actionString)
	{
				try
				{
				String[] inputs = actionString.split("\\s+");
				//Exchange market = new Exchange();
				int a = Integer.parseInt(inputs[0]);if(a<=0)throw new Exception();
				String b = inputs[1].toLowerCase();
				int c =Integer.parseInt(inputs[2]);if(c<=0)throw new Exception();
				String d = inputs[3].toLowerCase();if(!((d.equals("buy"))||(d.equals("sell"))))throw new Exception();
				int e = Integer.parseInt(inputs[4]);if(e<=0)throw new Exception();
				String f = inputs[5].toLowerCase();
				int g = Integer.parseInt(inputs[6]);if(g<=0)throw new Exception();
				boolean h = (inputs[7].toLowerCase()).charAt(0)=='t';if(!((inputs[7].toLowerCase().charAt(0)=='t')||(inputs[7].toLowerCase()).charAt(0)=='f'))throw new Exception();
				inputnode read = new inputnode(a,b,c,d,e,f,g,h);
				long t2 = System.currentTimeMillis();
				long times = read.getT0()*1000-(t2 - this.time);
				if(times>0)
				Thread.sleep(times);
				orders.enqueue(read);
				//System.out.println("enqueue called");
				long timeofinput = ((System.currentTimeMillis()-this.time)/1000);
				if(((System.currentTimeMillis()-this.time)/1000) < (read.getTexp()+read.getT0()) )
				{
					//FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
					//PrintStream pr = new PrintStream(fstream);
					//long timelist =((System.currentTimeMillis()-this.time)/1000);
					market.transaction(read);
					//pr.println(" "+ log + " "+ timelist +" "+ read.T0 + " " + read.name + " " + read.Texp + " " + read.type + " " + read.qty + " " + read.stock + " " + read.price + " " + read.partial+ " " + Exchange.profit);
					
				}

				FileOutputStream ofstream = new FileOutputStream("output.txt",true);
				PrintStream p = new PrintStream(ofstream);
				p.println(""+ timeofinput + " "+  actionString);
				

			}
			catch(Exception e)
			{	
				//e.printStackTrace();
				try
				{FileOutputStream ofstream = new FileOutputStream("output.txt",true);
				PrintStream p = new PrintStream(ofstream);
				p.println("EXCEPTION " + actionString);}
				catch(Exception f)
				{
				f.printStackTrace();}

			}

	}
	public Stock(long t1)
	{
		time = t1;
		
	}
}
/*class inputnode
	{	
		public int T0;
		public String name;
		public int Texp;
		public String type;
		public int qty;
		public String stock;
		public int price;
		public boolean partial;
		
		public inputnode(int a,String b,int c,String d,int e,String f,int g,boolean h)
		{
			T0 = a;
			name = b;
			Texp = c;
			type = d;
			qty = e;
			stock = f;
			price = g;
			partial = h;
		}	
		
		public int getT0()
		{return T0;}
		public String getname()
		{return name;}
		public int getTexp()
		{return Texp;}
		public String gettype()
		{return type;}
		public int getqty()
		{return qty;}
		public String getstock()
		{return stock;}
		public int getprice()
		{return price;}
		public boolean getpartial()
		{return partial;}
		public int satisfy(list a)
		{
			int quant = this.getqty();
			String stockname = this.getstock();
			int stockprice = this.getprice();
			boolean ispartial = this.getpartial();
			String type = this.gettype();
			//int profit = 0;
			int r = quant;
			inputnode maxstock = a.maximise(quant,stockname,stockprice,ispartial,a,type);
			if ((!ispartial)&&(maxstock!= null))
			{
				
				
				Exchange.profit = Exchange.profit + Math.abs(maxstock.getprice()-stockprice);
			}
			else if(ispartial&&(maxstock!=null)){
			while ((r>0)&&(maxstock!=null))
			{
				
				
				
				
				if((maxstock.getpartial())&&(maxstock.getqty()>=r))
			{maxstock.qty = maxstock.qty - r;
				Exchange.profit = Exchange.profit + (Math.abs(maxstock.getprice()-stockprice)*this.qty);
				r = 0;
			try{FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
					PrintStream pr = new PrintStream(fstream);
					String types = maxstock.type;char logs;
					if(types.equals("buy"))
						logs = 'P';
					else
						logs = 'S';
					//long timelist =((System.currentTimeMillis()-this.time)/1000);
					
					pr.println(" "+ logs + " "+ /*timelist +" "+ maxstock.T0 + " " + maxstock.name + " " + maxstock.Texp + " " + maxstock.type + " " + maxstock.qty + " " + maxstock.stock + " " + maxstock.price + " " + maxstock.partial+ " " + Exchange.profit);
					}
					catch(Exception e)
					{e.printStackTrace();}}
		else 
			{r = r - maxstock.getqty();
				Exchange.profit = Exchange.profit + (Math.abs(maxstock.getprice()-stockprice)*maxstock.qty);
				maxstock.qty = 0;
				//a.delete(maxstock);

				try{FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
					PrintStream pr = new PrintStream(fstream);
					//long timelist =((System.currentTimeMillis()-this.time)/1000);
					
					pr.println(" "+ 'T' + " "+ /*timelist +" "+ maxstock.T0 + " " + maxstock.name + " " + maxstock.Texp + " " + maxstock.type + " " + maxstock.qty + " " + maxstock.stock + " " + maxstock.price + " " + maxstock.partial+ " " + Exchange.profit);
					}
					catch(Exception e)
					{e.printStackTrace();}
			}

			this.qty = r;
				maxstock = a.maximise(r,stockname,stockprice,ispartial,a,type);
			}}
			return r;

		}
		
	}*/
		class queimp
{
	class node
	{
		private inputnode Element;
		private node next;
		public node(inputnode e,node b)
		{
			Element = e;
			next = b;
		}
		public node(inputnode e)
		{
			Element = e;
			next = null;
		}
		public inputnode getElement()
		{return Element;}
		public node getnext()
		{return next;}
		public void setnext(node newnext)
		{
			next = newnext;
		}
	}
	private node head;
	private node tail;
	public node gethead()
	{
		return head;
	}
	public void sethead(node n)
	{head=n;}
	public node gettail(node n)
	{return n;}
	public void settail(node n)
	{tail=n;}
	public void enqueue(inputnode b)
	{
		if (head==null)
		{
			node newnode = new node(b);
			this.sethead(newnode);
			this.settail(newnode);
		}
		else if(head.getnext()==null)
		{
			node newnode = new node(b);
			head.setnext(newnode);
			this.settail(newnode);
		}
		else 
		{
			node newnode = new node(b);
			tail.setnext(newnode);
			this.settail(newnode);
		}

	}
	public inputnode dequeue()
	{
		if (head.getnext()==null)
		{
			inputnode temp = head.getElement();
			head = null;
			return temp;
		}
		else if (head==null) 
		{
			throw new NullPointerException();
		}
		else
		{
			inputnode temp = head.getElement();
			head = head.getnext();
			return temp;
		}

	}
	
}
/*class list
{
	class node
	{
		public inputnode data;
		public node next;
		public node prev;
		public node()
		{
			
			next = null;
			prev = null;
		}
		public node(inputnode data)
		{
			this.data = data;
			next = null;
			prev = null;
		}
		public node(inputnode data,node prev)
		{
			this.data = data;
			this.prev = prev;
			next = null;
		}
		public node(inputnode data, node prev, node next)
		{
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	public node head;
	public node tail;
	public int counter;
	public void insertAtFirst(inputnode data)
	{
		if(head==null && tail == null)
		{
			node newnode = new node(data);
			head = newnode;
			tail = newnode;
			counter++;

		}
		
		else 
		{
			node newnode = new node(data);
			newnode.next = head;
			head.prev = newnode;
			head = newnode;
			counter++;
		}
	}
	public void insertAtLast(inputnode data)
	{
		if(head == null)
		{
			node newnode = new node(data);
			head = newnode;
			tail = newnode;
			counter++;
		}
		else
		{
			node newnode = new node(data,tail);
			tail.next = newnode;
			tail = newnode;
			counter++;
		}
	}
	
	public inputnode deleteAtFirst()
	{
		try
	{if (head == null) 
		{
			throw new Exception();
		}}
		catch (Exception e)
	{
		e.printStackTrace();
	}
		if(head.next == null)
		{
			inputnode temp = head.data;
			head = null;
			tail = null;
			counter--;
			return temp;
			
		}
		else
		{
			inputnode temp = head.data;
			head = head.next;
			counter--;
			return temp;
			

		}
	
	
	}
	
	
		public inputnode deleteAtLast()
		{
			try
	{if (head == null)
			{
				throw new Exception();
			}}
			catch (Exception e)
	{
		e.printStackTrace();
	}
			if(tail.prev == null)
			{
				inputnode temp = tail.data;
				head = null;
				tail = null;
				counter--;
				return temp;


			}
			else
			{
				inputnode temp = tail.data;
				tail = tail.prev;
				counter--;
				return temp;
			}
		

	}
	public void insert(int i,inputnode data)
	{
		try
		{
			if((i>counter)||(i<0))
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (i==1) 
		{
			this.insertAtFirst(data);
		}
		else if(i==counter)
		{
			this.insertAtLast(data);
		}
		else
		{
			node c =head;
			node newnode = new node(data);
			c=head;
			for (int j=1;j<i;j++)
			{
				c = c.next;
			}
			newnode.prev = c.prev;
			newnode.next = c;
			c.prev.next = newnode;
			c.prev = newnode;
			counter++;

		}
	}
	public inputnode delete(int i)
	{
		try
		{
			if((i>counter)||(i<0))
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (i==1) 
		{
			return this.deleteAtFirst();
		}
		else if(i==counter)
		{
			return this.deleteAtLast();
		}
		else
		{
			node c =head;
			for (int j=1;j<i;j++)
			{
				c = c.next;
			}
			inputnode temp = c.data;
			c.prev.next = c.next;
			c.next.prev = c.prev;
			counter--;
			return temp;
		}
	}
	public int size()
	{return counter;}
	public inputnode maximise(int quant,String stockname,int stockprice,boolean ispartial)
	{
		int pb = stockprice;
		node c = head;
		node d = null;
		int r = quant;
		
		while ( c!=null)
		{
			if(!ispartial)
			{
			if ((c.data.getprice()>pb)&&(c.data.getstock().equals(stockname))&&(c.data.getqty()==r))
			{
				pb  = c.data.getprice();
				d = c;
				c = c.next;
			}
			}
			else
			{	if ((c.data.getprice()>pb)&&(c.data.getstock().equals(stockname))&&(c.data.getqty()<=r))
			{
				pb  = c.data.getprice();
				d = c;
				c = c.next;
			}

			}
		}

	return (d.data);
	}
	}*/


		
	