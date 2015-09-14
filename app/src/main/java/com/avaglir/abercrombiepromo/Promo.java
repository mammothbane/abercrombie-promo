package com.avaglir.abercrombiepromo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mammothbane on 9/4/2015.
 *
 * promo model
 */
public class Promo implements Serializable {
    List<Button> button;
    String title;
    String description;
    String footer;
    String image;

    public static class Button implements Serializable {
        private String title;
        private String target;

        public Button(String title, String target) {
            this.title = title;
            this.target = target;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public Promo(String buttonTitle, String buttonTarget, String title, String description, String footer, String image) {
        this.button = new ArrayList<>();
        button.add(new Button(buttonTitle, buttonTarget));
        this.title = title;
        this.description = description;
        this.footer = footer;
        this.image = image;
    }

    public void updateFrom(Promo p) {
        this.button.get(0).setTitle(p.getButtonTitle());
        this.button.get(0).setTarget(p.getButtonTarget());
        this.title = p.title;
        this.description = p.description;
        this.footer = p.footer;
        this.image = p.image;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Promo && ((Promo) o).getTitle().equals(this.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    public String getButtonTitle() {
        return button.get(0).getTitle();
    }

    public void setButtonTitle(String buttonTitle) {
        button.get(0).setTitle(buttonTitle);
    }

    public String getButtonTarget() {
        return button.get(0).getTarget();
    }

    public void setButtonTarget(String buttonTarget) {
        button.get(0).setTarget(buttonTarget);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
