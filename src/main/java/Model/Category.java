package Model;


public class Category {
    private String catId;
    private String catName;
    private String catImage;

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catId='" + catId + '\'' +
                ", catName='" + catName + '\'' +
                ", catImage='" + catImage + '\'' +
                '}';
    }
}
