/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package an.product;

/**
 *
 * @author tranq
 */
public class ProductDTO {
    private String sku;
    private String name;
    private int price;
    private int description;

    public ProductDTO() {
    }

    public ProductDTO(String sku, String name, int price, int description) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public int getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(int description) {
        this.description = description;
    }
    
    
}
