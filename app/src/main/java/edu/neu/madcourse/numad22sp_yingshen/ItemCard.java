package edu.neu.madcourse.numad22sp_yingshen;

public class ItemCard implements ItemClickListener{

    private final String websiteName;
    private final String websiteAddress;
    private boolean isChecked;

    //Constructor
    public ItemCard(String itemName, String itemDesc,boolean isChecked) {
        this.websiteName = itemName;
        this.websiteAddress = itemDesc;
        this.isChecked = isChecked;
    }

    //Getters for the imageSource, itemName and itemDesc
    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public boolean getStatus() {
        return isChecked;
    }



    @Override
    public void onItemClick(int position) {
        isChecked = !isChecked;
    }

    @Override
    public void onCheckBoxClick(int position) {
        isChecked = !isChecked;
    }

}
