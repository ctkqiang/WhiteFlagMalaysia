package com.johnmelodyme.whiteflag.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.johnmelodyme.whiteflag.R;
import com.johnmelodyme.whiteflag.constants.LogLevel;
import com.johnmelodyme.whiteflag.functions.FlagFunctions;
import com.johnmelodyme.whiteflag.model.WhiteFlags;
import com.johnmelodyme.whiteflag.model.WhiteFlagsGet;

import java.util.ArrayList;
import java.util.List;

public class FlagsAdapter extends BaseAdapter implements Filterable
{
    public Context context;
    public ArrayList<WhiteFlagsGet> whiteFlagsList;
    public ArrayList<WhiteFlagsGet> wflist;
    public FilterHelper filterHelper;

    public FlagsAdapter(Context context, ArrayList<WhiteFlagsGet> whiteFlagsList)
    {
        this.whiteFlagsList = whiteFlagsList;
        this.context = context;
        this.wflist = whiteFlagsList;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount()
    {
        return whiteFlagsList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position)
    {
        return whiteFlagsList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position)
    {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item
     *                    whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not
     *                    possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that
     *                    this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @SuppressLint({"InflateParams", "ViewHolder", "ResourceType", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        WhiteFlagsGet whiteFlags = whiteFlagsList.get(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.helper_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.insert_name);
        TextView phone = (TextView) convertView.findViewById(R.id.insert_phone);
        TextView address = (TextView) convertView.findViewById(R.id.insert_address);
        TextView description = (TextView) convertView.findViewById(R.id.insert_description);
        TextView date = (TextView) convertView.findViewById(R.id.insert_date);
        Button send_help = (Button) convertView.findViewById(R.id.help_user);

        name.setText(context.getResources().getString(R.string.name) + ":\n" + whiteFlagsList.get(position).getUserName());
        phone.setText(context.getResources().getString(R.string.phone) + ":\n" + whiteFlagsList.get(position).getPhoneNumber());
        address.setText(context.getResources().getString(R.string.home) + ":\n" + whiteFlagsList.get(position).getHomeAddress());
        description.setText("\"" + whiteFlagsList.get(position).getDescription() + "\"");
        date.setText(context.getResources().getString(R.string.request_on) + "\t" + whiteFlagsList.get(position).getCreatedAt());

        send_help.setOnClickListener(v ->
        {
            FlagFunctions.log_output(
                    "send_help/n, return ~> " + whiteFlags.getUserName() + " | " + whiteFlags.getPhoneNumber(),
                    0,
                    LogLevel.DEBUG
            );
            FlagFunctions.log_output(
                    "send_help/n <<CallBack>> ~>  Initiate Call",
                    0,
                    LogLevel.DEBUG
            );

            FlagFunctions.call_user(whiteFlags.getPhoneNumber(), context);
        });

        return convertView;
    }

    public void setWhiteFlags(ArrayList<WhiteFlagsGet> filteredWhiteFlagsList)
    {
        this.wflist = filteredWhiteFlagsList;
    }

    public void refresh()
    {
        notifyDataSetChanged();
    }

    /**
     * <p>Returns a filter that can be used to constrain data with a filtering
     * pattern.</p>
     *
     * <p>This method is usually implemented by {@link Adapter}
     * classes.</p>
     *
     * @return a filter used to constrain data
     */
    @Override
    public Filter getFilter()
    {
        if (filterHelper == null)
        {
            filterHelper = new FilterHelper(whiteFlagsList, this, context);
        }

        return filterHelper;
    }
}
