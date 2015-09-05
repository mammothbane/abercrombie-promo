package com.avaglir.abercrombiepromo;

/**
 * Created by mammothbane on 9/4/2015.
 *
 * promo model
 */
public class Promo {
    private String buttonTitle;
    private String buttonTarget;
    private String title;
    private String description;
    private String footer;
    private String imageUrl;

    public Promo(String buttonTitle, String buttonTarget, String title, String description, String footer, String imageUrl) {
        this.buttonTitle = buttonTitle;
        this.buttonTarget = buttonTarget;
        this.title = title;
        this.description = description;
        this.footer = footer;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Promo
                && ((Promo) o).getButtonTarget().equals(this.buttonTarget)
                && ((Promo) o).getButtonTitle().equals(this.buttonTitle)
                && ((Promo) o).getTitle().equals(this.title)
                && ((Promo) o).getDescription().equals(this.description)
                && ((Promo) o).getFooter().equals(this.footer)
                && ((Promo) o).getImageUrl().equals(this.imageUrl);
    }

    @Override
    public int hashCode() {
        return buttonTitle.hashCode() + buttonTarget.hashCode() + title.hashCode() + description.hashCode()
                + footer.hashCode() + imageUrl.hashCode();
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public String getButtonTarget() {
        return buttonTarget;
    }

    public void setButtonTarget(String buttonTarget) {
        this.buttonTarget = buttonTarget;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
