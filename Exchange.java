import java.util.*;
import java.io.*;
public class Exchange
{
	public static list buy = new list();
	public static list sell = new list();
	public static int profit;
	//match orders
	public void transaction(inputnode current)
	{
		if(current.gettype().equals("sell"))
		{
			if(current.satisfy(buy)==0)
			{
				try//{sell.insertAtLast(current);
			{FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
			PrintStream pr = new PrintStream(fstream);
			long timelist =((System.currentTimeMillis()-Stock.time)/1000);
			pr.println(" "+ 'T' + " "+ timelist +" "+ current.T0 + " " + current.name + " " + current.Texp + " " + current.type + " " + current.qty + " " + current.stock + " " + current.price + " " + current.partial+ " " + Exchange.profit);
			}
			catch(Exception e)
			{e.printStackTrace();}}
			else 
				{sell.insertAtLast(current);
					//profit = profit + current.satisfy(buy);
					try//{sell.insertAtLast(current);
			{FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
			PrintStream pr = new PrintStream(fstream);
			long timelist =((System.currentTimeMillis()-Stock.time)/1000);
			pr.println(" "+ 'S' + " "+ timelist +" "+ current.T0 + " " + current.name + " " + current.Texp + " " + current.type + " " + current.qty + " " + current.stock + " " + current.price + " " + current.partial+ " " + Exchange.profit);
			}
			catch(Exception e)
			{e.printStackTrace();}
				}
		}
		else 
		{
			if(current.satisfy(sell)==0)
				{//buy.insertAtLast(current);
				try//{sell.insertAtLast(current);
			{FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
			PrintStream pr = new PrintStream(fstream);
			long timelist =((System.currentTimeMillis()-Stock.time)/1000);
			pr.println(" "+ 'T' + " "+ timelist +" "+ current.T0 + " " + current.name + " " + current.Texp + " " + current.type + " " + current.qty + " " + current.stock + " " + current.price + " " + current.partial+ " " + Exchange.profit);
			}
			catch(Exception e)
			{e.printStackTrace();}}
			else 
				{buy.insertAtLast(current);//profit = profit + current.satisfy(sell);
				try//{sell.insertAtLast(current);
			{FileOutputStream fstream = new FileOutputStream("Exchange.txt",true);
			PrintStream pr = new PrintStream(fstream);
			long timelist =((System.currentTimeMillis()-Stock.time)/1000);
			pr.println(" "+ 'P' + " "+ timelist +" "+ current.T0 + " " + current.name + " " + current.Texp + " " + current.type + " " + current.qty + " " + current.stock + " " + current.price + " " + current.partial+ " " + Exchange.profit);
			}
			catch(Exception e)
			{e.printStackTrace();}}
		}
		
	}

	
}
class list
{
	
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
	public inputnode delete(node tobedeleted)
	{
		/*try
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
			}*/
			node c =tobedeleted;
			inputnode temp = c.data;
			c.prev.next = c.next;
			c.next.prev = c.prev;
			counter--;
			return temp;
		
	}
	public int size()
	{return counter;}
	public inputnode maximise(int quant,String stockname,int stockprice,boolean ispartial,list a,String type)
	{
		int pb = stockprice;
		node c = a.head;
		node d = null;
		int r = quant;
		long timelist =((System.currentTimeMillis()-Stock.time)/1000);
		if(type .equals("sell")) 
		{
		while ( c!=null)
		{
			if(!ispartial)
			{
			if ((((c.data.getprice()>pb)&&(c.data.getstock().equals(stockname))&&(c.data.getqty()==r))||((c.data.getprice()>pb)&&(c.data.getstock().equals(stockname))&&(c.data.getpartial())&&(c.data.getqty()>r)))&&(c.data.qty!=0))
			{	
				if(timelist<(c.data.Texp+c.data.T0))
				{pb  = c.data.getprice();
				d = c;}
				else 
					a.delete(c);}
				//if((c.data.getpartial())&&(c.data.getqty()>=r))
				//c.data.qty = c.data.qty - r;
				c = c.next;
			
			}
			else
			{	if ((((c.data.getprice()>pb)&&(c.data.getstock().equals(stockname))&&(c.data.getqty()<=r))||((c.data.getprice()>pb)&&(c.data.getstock().equals(stockname))&&(c.data.getpartial())&&(c.data.getqty()>r)))&&(c.data.qty!=0))
			{
				if(timelist<(c.data.Texp+c.data.T0))
				{pb  = c.data.getprice();
				d = c;}
				else 
					a.delete(c);}
				//if((c.data.getpartial())&&(c.data.getqty()>=r))
				//c.data.qty = c.data.qty - r;
			//else 
				//c.data.qty = 0;
				c = c.next;
			

			}
		}}
		else
		{
			while ( c!=null)
		{
			if(!ispartial)
			{
			if ((((c.data.getprice()<pb)&&(c.data.getstock().equals(stockname))&&(c.data.getqty()==r))||((c.data.getprice()<pb)&&(c.data.getstock().equals(stockname))&&(c.data.getpartial())&&(c.data.getqty()>r)))&&(c.data.qty!=0))
			{
				if(timelist<(c.data.Texp+c.data.T0))
				{pb  = c.data.getprice();
				d = c;}
				else 
					a.delete(c);}
				c = c.next;
			
			}
			else
			{	if ((((c.data.getprice()<pb)&&(c.data.getstock().equals(stockname))&&(c.data.getqty()<=r))||((c.data.getprice()<pb)&&(c.data.getstock().equals(stockname))&&(c.data.getpartial())&&(c.data.getqty()>r)))&&(c.data.qty!=0))
				{if(timelist<(c.data.Texp+c.data.T0))
				{pb  = c.data.getprice();
				d = c;}
				else 
					a.delete(c);}
				c = c.next;
			

			}
		}
		}

		if(d==null)
			return null;
		

	return (d.data);
	}
	}
class inputnode
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
					long timelist =((System.currentTimeMillis()-Stock.time)/1000);
					
					pr.println(" "+ logs + " "+ timelist +" "+ maxstock.T0 + " " + maxstock.name + " " + maxstock.Texp + " " + maxstock.type + " " + maxstock.qty + " " + maxstock.stock + " " + maxstock.price + " " + maxstock.partial+ " " + Exchange.profit);
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
					long timelist =((System.currentTimeMillis()-Stock.time)/1000);
					
					pr.println(" "+ 'T' + " "+ timelist +" "+ maxstock.T0 + " " + maxstock.name + " " + maxstock.Texp + " " + maxstock.type + " " + maxstock.qty + " " + maxstock.stock + " " + maxstock.price + " " + maxstock.partial+ " " + Exchange.profit);
					}
					catch(Exception e)
					{e.printStackTrace();}
			}

			this.qty = r;
				maxstock = a.maximise(r,stockname,stockprice,ispartial,a,type);
			}}
			return r;

		}
	}
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