package nrp.model;
import java.io.Serializable;

	public class Mobile implements Serializable
	{
		private int productid;
		
		private String mobilename;
		private String model;
		private float price;
		
		
		public int getProductid() {
			return productid;
		}
		public void setProductid(int productid) {
			this.productid = productid;
		}
		public String getMobilename() {
			return mobilename;
		}
		public void setMobilename(String mobilename) {
			this.mobilename = mobilename;
		}
		public String getModel() {
			return model;
		}
		public void setModel(String model) {
			this.model = model;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		
		
	}


