package com.example.uniporter_app;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.uniporter_app.Add_New_Ride_Sequence.AddNewRide;
import com.example.uniporter_app.Authentication.Login;
import com.example.uniporter_app.Authentication.MainActivity;
import com.example.uniporter_app.Storage.SharedPreferenceManager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class DrawerUtil {

    public static void getDrawer(final Activity activity, Toolbar toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.new_ride).withIcon(R.drawable.ic_add_circle_outline_black_24dp);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName(R.string.pending_rides).withIcon(R.drawable.ic__car_24dp);
       /* PrimaryDrawerItem notifications = new PrimaryDrawerItem()
                .withIdentifier(3).withName("Notifications").withIcon(R.drawable.ic_notifications_active_black_24dp);*/
        PrimaryDrawerItem logout = new PrimaryDrawerItem()
                .withIdentifier(4).withName(R.string.logout).withIcon(R.drawable.ic_exit_to_app_black_24dp);

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.uniporter_background)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //create the drawer and remember the `Drawer` result object
        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withActivity(activity);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.withAccountHeader(headerResult);
        drawerBuilder.withActionBarDrawerToggle(true);
        drawerBuilder.withActionBarDrawerToggleAnimated(true);
        drawerBuilder.withCloseOnClick(true);
        drawerBuilder.withSelectedItem(-1);
        drawerBuilder.addDrawerItems(
                drawerItemManagePlayers,
                drawerItemManagePlayersTournaments,
                logout,
                new DividerDrawerItem()
        );

        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                if (drawerItem.getIdentifier() == 1 && !(activity instanceof AddNewRide)) {
                    // load screen
                    Intent intent = new Intent(activity, AddNewRide.class);
                    view.getContext().startActivity(intent);
                }
                else if (drawerItem.getIdentifier() == 2 && !(activity instanceof MainActivity)) {
                    // load screen
                    Intent intent = new Intent(activity, MainActivity.class);
                    view.getContext().startActivity(intent);
                }
                else if (drawerItem.getIdentifier() == 4) {
                    // load screen
                    SharedPreferenceManager.getInstance(activity).logoutUser();
                    Intent intent = new Intent(activity, Login.class);
                    view.getContext().startActivity(intent);
                }
                return true;
            }
        });
        Drawer result = drawerBuilder.build();
    }
}
