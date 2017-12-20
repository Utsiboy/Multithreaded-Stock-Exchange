import java.util.*;
import java.io.*;
public class inputnode
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
					
					pr.println(" "+ 'T' + " "+ timelist+" "+ maxstock.T0 + " " + maxstock.name + " " + maxstock.Texp + " " + maxstock.type + " " + maxstock.qty + " " + maxstock.stock + " " + maxstock.price + " " + maxstock.partial+ " " + Exchange.profit);
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