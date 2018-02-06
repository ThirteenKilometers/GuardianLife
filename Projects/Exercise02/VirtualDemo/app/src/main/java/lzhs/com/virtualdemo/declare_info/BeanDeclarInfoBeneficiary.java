package lzhs.com.virtualdemo.declare_info;

import java.io.Serializable;

/**
 * 申报详情  受益人<br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/17 09:48<br/>
 * 邮箱：1050629507@qq.com
 */
public class BeanDeclarInfoBeneficiary implements Serializable {


    /**
     * id : 458
     * beneficiaryTypeId : distributor
     * beneficiaryName : 张大柱
     * beneficiaryPhone : 13812345678
     * beneficiaryAddress : 成都市青羊区商业街188号
     * beneficiaryIsMain : 1
     * amount : 2
     * type : 1
     * num : 0
     * price : 0
     * paymentWayId : 1
     * postCode :
     * staffName :
     * customerUuid : 8aaff8325ec7053a015ec80401360012
     * customerName : 范德萨范德萨
     * status : 0
     * paymentBenefitId : null
     * paymentWayName : 员工代付
     * realAmount : null
     * expectAmount : null
     * realPrice : null
     * expectNum : null
     * realNum : null
     * beneficiaryAmountTypeName : null
     * beneficiaryTypeName : 员工
     * costPoolName : 四川剑南春(集团)有限责任公司
     * cost_type : 物料
     * costPoolBalance : 2323
     * optionTitle : 54瓶
     * writeOffUnit : 元/瓶
     * isScan : 1
     * writeOffType : 现金折扣
     * writeOffNum : 34
     * cashUnit :
     * writeOffUnitStart : 元
     * writeOffUnitEnd : 瓶
     * cashUnitStart :
     * cashUnitEnd :
     */

    private int id;
    private String beneficiaryTypeId;
    private String beneficiaryName;
    private String beneficiaryPhone;
    private String beneficiaryAddress;
    private int beneficiaryIsMain;
    private int amount;
    private int type;
    private int num;
    private int price;
    private int paymentWayId;
    private String postCode;
    private String staffName;
    private String customerUuid;
    private String customerName;
    private int status;
    private String paymentBenefitId;
    private String paymentWayName;
    private String realAmount;
    private String expectAmount;
    private String realPrice;
    private String expectNum;
    private String realNum;
    private String beneficiaryAmountTypeName;
    private String beneficiaryTypeName;
    private String costPoolName;
    private String cost_type;
    private int costPoolBalance;
    private String optionTitle;
    private String writeOffUnit;
    private String isScan;
    private String writeOffType;
    private String writeOffNum;
    private String cashUnit;
    private String writeOffUnitStart;
    private String writeOffUnitEnd;
    private String cashUnitStart;
    private String cashUnitEnd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeneficiaryTypeId() {
        return beneficiaryTypeId;
    }

    public void setBeneficiaryTypeId(String beneficiaryTypeId) {
        this.beneficiaryTypeId = beneficiaryTypeId;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryPhone() {
        return beneficiaryPhone;
    }

    public void setBeneficiaryPhone(String beneficiaryPhone) {
        this.beneficiaryPhone = beneficiaryPhone;
    }

    public String getBeneficiaryAddress() {
        return beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public int getBeneficiaryIsMain() {
        return beneficiaryIsMain;
    }

    public void setBeneficiaryIsMain(int beneficiaryIsMain) {
        this.beneficiaryIsMain = beneficiaryIsMain;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPaymentWayId() {
        return paymentWayId;
    }

    public void setPaymentWayId(int paymentWayId) {
        this.paymentWayId = paymentWayId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getCustomerUuid() {
        return customerUuid;
    }

    public void setCustomerUuid(String customerUuid) {
        this.customerUuid = customerUuid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPaymentBenefitId() {
        return paymentBenefitId;
    }

    public void setPaymentBenefitId(String paymentBenefitId) {
        this.paymentBenefitId = paymentBenefitId;
    }

    public String getPaymentWayName() {
        return paymentWayName;
    }

    public void setPaymentWayName(String paymentWayName) {
        this.paymentWayName = paymentWayName;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getExpectAmount() {
        return expectAmount;
    }

    public void setExpectAmount(String expectAmount) {
        this.expectAmount = expectAmount;
    }

    public String getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(String realPrice) {
        this.realPrice = realPrice;
    }

    public String getExpectNum() {
        return expectNum;
    }

    public void setExpectNum(String expectNum) {
        this.expectNum = expectNum;
    }

    public String getRealNum() {
        return realNum;
    }

    public void setRealNum(String realNum) {
        this.realNum = realNum;
    }

    public String getBeneficiaryAmountTypeName() {
        return beneficiaryAmountTypeName;
    }

    public void setBeneficiaryAmountTypeName(String beneficiaryAmountTypeName) {
        this.beneficiaryAmountTypeName = beneficiaryAmountTypeName;
    }

    public String getBeneficiaryTypeName() {
        return beneficiaryTypeName;
    }

    public void setBeneficiaryTypeName(String beneficiaryTypeName) {
        this.beneficiaryTypeName = beneficiaryTypeName;
    }

    public String getCostPoolName() {
        return costPoolName;
    }

    public void setCostPoolName(String costPoolName) {
        this.costPoolName = costPoolName;
    }

    public String getCost_type() {
        return cost_type;
    }

    public void setCost_type(String cost_type) {
        this.cost_type = cost_type;
    }

    public int getCostPoolBalance() {
        return costPoolBalance;
    }

    public void setCostPoolBalance(int costPoolBalance) {
        this.costPoolBalance = costPoolBalance;
    }

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public String getWriteOffUnit() {
        return writeOffUnit;
    }

    public void setWriteOffUnit(String writeOffUnit) {
        this.writeOffUnit = writeOffUnit;
    }

    public String getIsScan() {
        return isScan;
    }

    public void setIsScan(String isScan) {
        this.isScan = isScan;
    }

    public String getWriteOffType() {
        return writeOffType;
    }

    public void setWriteOffType(String writeOffType) {
        this.writeOffType = writeOffType;
    }

    public String getWriteOffNum() {
        return writeOffNum;
    }

    public void setWriteOffNum(String writeOffNum) {
        this.writeOffNum = writeOffNum;
    }

    public String getCashUnit() {
        return cashUnit;
    }

    public void setCashUnit(String cashUnit) {
        this.cashUnit = cashUnit;
    }

    public String getWriteOffUnitStart() {
        return writeOffUnitStart;
    }

    public void setWriteOffUnitStart(String writeOffUnitStart) {
        this.writeOffUnitStart = writeOffUnitStart;
    }

    public String getWriteOffUnitEnd() {
        return writeOffUnitEnd;
    }

    public void setWriteOffUnitEnd(String writeOffUnitEnd) {
        this.writeOffUnitEnd = writeOffUnitEnd;
    }

    public String getCashUnitStart() {
        return cashUnitStart;
    }

    public void setCashUnitStart(String cashUnitStart) {
        this.cashUnitStart = cashUnitStart;
    }

    public String getCashUnitEnd() {
        return cashUnitEnd;
    }

    public void setCashUnitEnd(String cashUnitEnd) {
        this.cashUnitEnd = cashUnitEnd;
    }
}