package org.fasttrackit.onlineshop.transfer.product;

public class GetProductsRequest {

    private String patialName;
    private Integer minQuantity;

    public String getPatialName() {
        return patialName;
    }

    public void setPatialName(String patialName) {
        this.patialName = patialName;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public String toString() {
        return "GetProductsRequest{" +
                "patialName='" + patialName + '\'' +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
