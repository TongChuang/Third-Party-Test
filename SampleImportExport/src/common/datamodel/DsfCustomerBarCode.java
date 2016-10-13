/**
 * 
 */
package common.datamodel;

/**
 * @author zjn
 * @createTime 2016-10-9
 */
public class DsfCustomerBarCode implements java.io.Serializable {

	private String customerid;
	private String barcode;

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public DsfCustomerBarCode() {
	}

	public DsfCustomerBarCode(String customerid, String barcode) {
		this.customerid = customerid;
		this.barcode = barcode;
	}

}
