package com.avaglir.abercrombiepromo;

import android.net.Uri;

/**
 * Created by mammothbane on 9/4/2015.
 *
 * promo model
 */
public class Promo {
    private String buttonTitle;
    private Uri buttonTarget;
    private String title;
    private String description;
    private String footer;
    private Uri imageUri;

    public Promo(String buttonTitle, Uri buttonTarget, String title, String description, String footer, Uri imageUri) {
        this.buttonTitle = buttonTitle;
        this.buttonTarget = buttonTarget;
        this.title = title;
        this.description = description;
        this.footer = footer;
        this.imageUri = imageUri;
    }

    public void updateFrom(Promo p) {
        this.buttonTitle = p.buttonTitle;
        this.buttonTarget = p.buttonTarget;
        this.title = p.title;
        this.description = p.description;
        this.footer = p.footer;
        this.imageUri = p.imageUri;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Promo
                && ((Promo) o).getButtonTarget().equals(this.buttonTarget)
                && ((Promo) o).getButtonTitle().equals(this.buttonTitle)
                && ((Promo) o).getTitle().equals(this.title)
                && ((Promo) o).getDescription().equals(this.description)
                && ((Promo) o).getFooter().equals(this.footer)
                && ((Promo) o).getImageUri().equals(this.imageUri);
    }

    @Override
    public int hashCode() {
        return buttonTitle.hashCode() + buttonTarget.hashCode() + title.hashCode() + description.hashCode()
                + footer.hashCode() + imageUri.hashCode();
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public Uri getButtonTarget() {
        return buttonTarget;
    }

    public void setButtonTarget(Uri buttonTarget) {
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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }
}
