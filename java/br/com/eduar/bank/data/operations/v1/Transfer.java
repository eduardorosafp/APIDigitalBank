package br.com.eduar.bank.data.operations.v1;

public class Transfer {

    private String destinyAccountName;
    private Double valueTransfer;

    public String getDestinyAccountName() {
        return destinyAccountName;
    }
    public Double getValueTransfer() {
        return valueTransfer;
    }
    public void setDestinyAccountName(String destinyAccountName) {
        this.destinyAccountName = destinyAccountName;
    }
    public void setValueTransfer(Double valueTransfer) {
        this.valueTransfer = valueTransfer;
    }
}
