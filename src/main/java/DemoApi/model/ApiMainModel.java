package DemoApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiMainModel {
    private String name;
    private String height;
    private String hair_color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHair_color() {
        return hair_color;
    }

    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", hair_color='" + hair_color + '\'' +
                '}';
    }
}
