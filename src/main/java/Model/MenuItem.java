package Model;

public class MenuItem {

    private String itemId;
    private String catId;
    private String itemName;
    private String itemDesc;
    private double price;
    private String itemImage;
    private boolean deal;
    private boolean inStock;
    private String categoryName;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public boolean isDeal() {
        return deal;
    }

    public void setDeal(boolean deal) {
        this.deal = deal;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId='" + itemId + '\'' +
                ", catId='" + catId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", price=" + price +
                ", itemImage='" + itemImage + '\'' +
                ", deal=" + deal +
                ", inStock=" + inStock +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
