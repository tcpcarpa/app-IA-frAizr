package tcp.last.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Parcelable {
    private int id;
    private String title;
    private String image;
    private int servings;
    private int readyInMinutes;
    private String sourceName;
    private String source;
    private String instructions;
    private String summary;
    private String pairing;
    private String ingredientes;
    private List<String> extendedIngredietsList;

    public Recipe() {
    }




    public Recipe(int id, String title, String image, int servings, int readyInMinutes) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
    }

    //FIREBASE
    public Recipe(String title, int servings, int readyInMinutes, String sourceName, String instructions, String summary, String ingredientes) {
        this.title = title;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.sourceName = sourceName;
        this.instructions = instructions;
        this.summary = summary;
        this.ingredientes = ingredientes;
    }

    public Recipe(int id, String title, String image, int servings, int readyInMinutes, String sourceName, String source, String instructions, String summary, List<String> extendedIngredietsList) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.sourceName = sourceName;
        this.source = source;
        this.instructions = instructions;
        this.summary = summary;
        this.extendedIngredietsList = extendedIngredietsList;
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        title = in.readString();
        image = in.readString();
        servings = in.readInt();
        readyInMinutes = in.readInt();
        sourceName = in.readString();
        source = in.readString();
        instructions = in.readString();
        summary = in.readString();
        pairing = in.readString();
        ingredientes = in.readString();
        extendedIngredietsList = in.createStringArrayList();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getPairing() {
        return pairing;
    }

    public void setPairing(String pairing) {
        this.pairing = pairing;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public List<String> getExtendedIngredietsList() {
        return extendedIngredietsList;
    }

    public void setExtendedIngredietsList(List<String> extendedIngredietsList) {
        this.extendedIngredietsList = extendedIngredietsList;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(image);
        dest.writeInt(servings);
        dest.writeInt(readyInMinutes);
        dest.writeString(sourceName);
        dest.writeString(source);
        dest.writeString(instructions);
        dest.writeString(summary);
        dest.writeString(pairing);
        dest.writeString(ingredientes);
        dest.writeStringList(extendedIngredietsList);
    }
}
