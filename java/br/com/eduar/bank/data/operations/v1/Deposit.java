package br.com.eduar.bank.data.operations.v1;

public class Deposit {

    private String accountName;
    private Double depositValue;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(Double depositValue) {
        this.depositValue = depositValue;
    }

}
