package edu.neu.madcourse.numad22sp_yingshen;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class ItemCard {

    private final String websiteName;
    private final String websiteAddress;

    public ItemCard(String websiteName, String websiteAddress) {
        this.websiteName = websiteName;
        this.websiteAddress = websiteAddress;
    }

    public void onLinkClicked(Context context) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteAddress));
        context.startActivity(browserIntent);
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public boolean isValid() {
        try {
            new URL(websiteAddress).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }
        return Patterns.WEB_URL.matcher(websiteAddress).matches();
    }

}