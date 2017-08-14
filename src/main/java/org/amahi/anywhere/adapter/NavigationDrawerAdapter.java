package org.amahi.anywhere.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.amahi.anywhere.R;

import java.util.Arrays;
import java.util.List;

/**
 * Navigation Drawer adapter. Visualizes predefined values
 * for the {@link org.amahi.anywhere.fragment.NavigationFragment}.
 */

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavigationDrawerViewHolder> {

    public static final class NavigationItems
    {
        private NavigationItems() {
        }

        public static final int SHARES = 0;
        public static final int APPS = 1;
        public static final int NON_ADMIN_USERS = 2;
        public static final int NO_VALUE = 3;
    }

    private final List<Integer> navigationItems;
    private Context mContext;

    public static NavigationDrawerAdapter newLocalAdapter(Context context) {
        return new NavigationDrawerAdapter(context, Arrays.asList(NavigationItems.SHARES, NavigationItems.APPS, NavigationItems.NON_ADMIN_USERS, NavigationItems.NO_VALUE));
    }

    public static NavigationDrawerAdapter newRemoteAdapter(Context context) {
        return new NavigationDrawerAdapter(context, Arrays.asList(NavigationItems.SHARES, NavigationItems.NON_ADMIN_USERS, NavigationItems.NO_VALUE));
    }

    public NavigationDrawerAdapter(Context context, List<Integer> navigationItems){
        this.navigationItems = navigationItems;
        mContext = context;
    }

    class NavigationDrawerViewHolder extends RecyclerView.ViewHolder{
        TextView titleShare;
        NavigationDrawerViewHolder(View itemView) {
            super(itemView);
            titleShare = (TextView)itemView.findViewById(R.id.text_share_title);
        }
    }

    @Override
    public NavigationDrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationDrawerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_navigation_item,parent,false));
    }

    @Override
    public void onBindViewHolder(NavigationDrawerViewHolder holder, int position) {
        holder.titleShare.setText(getNavigationName(mContext,position));
        if(getNavigationName(mContext,position).matches("")){
            holder.titleShare.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return navigationItems.size();
    }

    private String getNavigationName(Context context, int navigationItem) {
        switch (navigationItem) {
            case NavigationDrawerAdapter.NavigationItems.SHARES:
                return context.getString(R.string.title_shares);

            case NavigationDrawerAdapter.NavigationItems.APPS:
                return context.getString(R.string.title_apps);

            case NavigationDrawerAdapter.NavigationItems.NON_ADMIN_USERS:
                return context.getString(R.string.title_non_admin_users);

            case NavigationItems.NO_VALUE:
                return "";
            default:
                return null;
        }
    }

}
