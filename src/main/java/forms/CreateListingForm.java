package forms;

public class CreateListingForm extends Form{


    private float price;
    private String description;
    private String[] images;

    public CreateListingForm(String title, float price, String description, String[] images) {
        super(title);
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public float getPrice() {
        return price;
    }
    public String getDescription(){
        return description;
    }
    public String getImage(int imageIndex){
        if (imageIndex >= images.length){
            return "No Image";
        }
        else{
            return images[imageIndex];
        }
    }

    @Override
    protected boolean validateForm() {
        return false;
    }

    @Override
    protected void submitForm() {
    }
}
