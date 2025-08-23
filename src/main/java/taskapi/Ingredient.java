package taskapi;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName(value="_id")
    String id;
    String type;

    public Ingredient(String id, String type) {
        this.id = id;
        this.type = type;
    }  
    
}
