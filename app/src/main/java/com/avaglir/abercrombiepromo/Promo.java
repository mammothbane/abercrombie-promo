package com.avaglir.abercrombiepromo;

/**
 * Created by mammothbane on 9/4/2015.
 *
 * promo model
 */
public class Promo {
    Button button;
    String title;
    String description;
    String footer;
    String image;

    public static class Button {
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
        this.button = new Button(buttonTitle, buttonTarget);
        this.title = title;
        this.description = description;
        this.footer = footer;
        this.image = image;
    }

    public void updateFrom(Promo p) {
        this.button.setTitle(p.getButtonTitle());
        this.button.setTarget(p.getButtonTarget());
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
        return button.getTitle();
    }

    public void setButtonTitle(String buttonTitle) {
        button.setTitle(buttonTitle);
    }

    public String getButtonTarget() {
        return button.getTarget();
    }

    public void setButtonTarget(String buttonTarget) {
        button.setTarget(buttonTarget);
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
