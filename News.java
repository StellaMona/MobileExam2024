public class News {
    private int id;
    private String title;
    private String description;
    private int image;
    private String category;

    public News(int id, String title, String description, int image, String category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }
}
