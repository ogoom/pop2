package com.zpc.operations.pop.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "Outward_Items_Details_Report")
public class OutwardItemsDetailsReport {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "sn")
    private int sn;

    @Column(name = "t_date")
    private Date tDate;

    @Column(name = "drawee_bank")
    private String draweeBank;

    @Column(name = "seq_no")
    private String seqNo;

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "sort_code")
    private String sortCode;

    @Column(name = "t_code")
    private String tCode;

    @Column(name = "amount")
    private double amount;

    @Column(name = "receiving_account_no")
    private String receivingAccountNo;

    @Column(name = "receiving_name")
    private String receivingName;

    @Column(name = "presenting_account_no")
    private String presentingAccountNo;

    @Column(name = "presenting_account_name")
    private String presentingAccountName;

    @Column(name = "trans_details")
    private String transDetails;

    @Column(name = "presenting_bank")
    private String presentingBank;
    @Column(name = "window")
    private String window;

    public OutwardItemsDetailsReport(Long id) {
        this.id = id;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSn() {
        return sn;
    }

    public void setSn(int sn) {
        this.sn = sn;
    }

    public Date gettDate() {
        return tDate;
    }

    public void settDate(Date tDate) {
        this.tDate = tDate;
    }

    public String getDraweeBank() {
        return draweeBank;
    }

    public void setDraweeBank(String draweeBank) {
        this.draweeBank = draweeBank;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceivingAccountNo() {
        return receivingAccountNo;
    }

    public void setReceivingAccountNo(String receivingAccountNo) {
        this.receivingAccountNo = receivingAccountNo;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }

    public String getPresentingAccountNo() {
        return presentingAccountNo;
    }

    public void setPresentingAccountNo(String presentingAccountNo) {
        this.presentingAccountNo = presentingAccountNo;
    }

    public String getPresentingAccountName() {
        return presentingAccountName;
    }

    public void setPresentingAccountName(String presentingAccountName) {
        this.presentingAccountName = presentingAccountName;
    }

    public String getTransDetails() {
        return transDetails;
    }

    public void setTransDetails(String transDetails) {
        this.transDetails = transDetails;
    }

    public String getPresentingBank() {
        return presentingBank;
    }

    public void setPresentingBank(String presentingBank) {
        this.presentingBank = presentingBank;
    }

    @Override
    public String toString() {
        return "OutwardItemsDetailsReport{" +
                "id=" + id +
                ", sn=" + sn +
                ", tDate=" + tDate +
                ", draweeBank='" + draweeBank + '\'' +
                ", seqNo=" + seqNo +
                ", serialNo=" + serialNo +
                ", sortCode='" + sortCode + '\'' +
                ", tCode=" + tCode +
                ", amount=" + amount +
                ", receivingAccountNo='" + receivingAccountNo + '\'' +
                ", receivingName='" + receivingName + '\'' +
                ", presentingAccountNo='" + presentingAccountNo + '\'' +
                ", presentingAccountName='" + presentingAccountName + '\'' +
                ", transDetails='" + transDetails + '\'' +
                ", presentingBank='" + presentingBank + '\'' +
                ", Window='" + window + '\'' +
                '}';
    }
}
