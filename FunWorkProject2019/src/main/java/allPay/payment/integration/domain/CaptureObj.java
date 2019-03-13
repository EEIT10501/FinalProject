package allPay.payment.integration.domain;

/**
 * 會員申請撥款/退款物件
 * @author mark.chiu
 *
 */
public class CaptureObj {
	
	/**
	 * MerchantID
	 * 會員編號(由allPay提供)
	 */
	private String MerchantID = "";
	
	/**
	 * MerchantTradeNo
	 * 會員交易編號
	 */
	private String MerchantTradeNo = "";
	
	/**
	 * CaptureAMT
	 * 會員申請撥款金額
	 */
	private String CaptureAMT = "";
	
	/**
	 * UserRefundAMT
	 * 要退款給買方的金額
	 */
	private String UserRefundAMT = "";
	
	/**
	 * PlatformID
	 * 特約合作平台商代號(由allPay提供)
	 */
	private String PlatformID = "";
	
	/**
	 * UpdatePlatformChargeFee
	 * 是否更改特約合作平台商手續費
	 */
	private String UpdatePlatformChargeFee = "";
	
	/**
	 * PlatformChargeFee
	 * 特約合作平台商手續費
	 */
	private String PlatformChargeFee = "";
	
	/**
	 * Remark
	 * 備註
	 */
	private String Remark = "";
	
	/********************* getters and setters *********************/
	
	/**
	 * 取得MerchantID 會員編號(由allPay提供)
	 * @return MerchantID
	 */
	public String getMerchantID() {
		return MerchantID;
	}
	/**
	 * 設定MerchantID 會員編號(由allPay提供)
	 * @param merchantID
	 */
	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}
	/**
	 * 取得MerchantTradeNo 會員交易編號
	 * @return MerchantTradeNo
	 */
	public String getMerchantTradeNo() {
		return MerchantTradeNo;
	}
	/**
	 * 設定MerchantTradeNo 會員交易編號
	 * @param merchantTradeNo
	 */
	public void setMerchantTradeNo(String merchantTradeNo) {
		MerchantTradeNo = merchantTradeNo;
	}
	/**
	 * 取得CaptureAMT 會員申請撥款金額
	 * @return CaptureAMT
	 */
	public String getCaptureAMT() {
		return CaptureAMT;
	}
	/**
	 * 設定CaptureAMT 會員申請撥款金額
	 * @param captureAMT
	 */
	public void setCaptureAMT(String captureAMT) {
		CaptureAMT = captureAMT;
	}
	/**
	 * 取得UserRefundAMT 要退款給買方的金額
	 * @return UserRefundAMT
	 */
	public String getUserRefundAMT() {
		return UserRefundAMT;
	}
	/**
	 * 設定UserRefundAMT 要退款給買方的金額
	 * @param userRefundAMT
	 */
	public void setUserRefundAMT(String userRefundAMT) {
		UserRefundAMT = userRefundAMT;
	}
	/**
	 * 取得PlatformID 特約合作平台商代號(由allPay提供)
	 * @return PlatformID
	 */
	public String getPlatformID() {
		return PlatformID;
	}
	/**
	 * 設定PlatformID 特約合作平台商代號(由allPay提供)
	 * @param platformID
	 */
	public void setPlatformID(String platformID) {
		PlatformID = platformID;
	}
	/**
	 * 取得UpdatePlatformChargeFee 是否更改特約合作平台商手續費
	 * @return UpdatePlatformChargeFee
	 */
	public String getUpdatePlatformChargeFee() {
		return UpdatePlatformChargeFee;
	}
	/**
	 * 設定UpdatePlatformChargeFee 是否更改特約合作平台商手續費
	 * @param updatePlatformChargeFee
	 */
	public void setUpdatePlatformChargeFee(String updatePlatformChargeFee) {
		UpdatePlatformChargeFee = updatePlatformChargeFee;
	}
	/**
	 * 取得PlatformChargeFee 特約合作平台商手續費
	 * @return PlatformChargeFee
	 */
	public String getPlatformChargeFee() {
		return PlatformChargeFee;
	}
	/**
	 * 設定PlatformChargeFee 特約合作平台商手續費
	 * @param platformChargeFee
	 */
	public void setPlatformChargeFee(String platformChargeFee) {
		PlatformChargeFee = platformChargeFee;
	}
	/**
	 * 取得Remark 備註
	 * @return Remark
	 */
	public String getRemark() {
		return Remark;
	}
	/**
	 * 設定Remark 備註
	 * @param remark
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "CaptureObj [MerchantID=" + MerchantID + ", MerchantTradeNo=" + MerchantTradeNo + ", CaptureAMT="
				+ CaptureAMT + ", UserRefundAMT=" + UserRefundAMT + ", PlatformID=" + PlatformID
				+ ", UpdatePlatformChargeFee=" + UpdatePlatformChargeFee + ", PlatformChargeFee=" + PlatformChargeFee
				+ ", Remark=" + Remark + "]";
	}
}
