package com.johnmelodyme.whiteflag.components;

import android.content.Context;
import android.widget.Filter;

import com.johnmelodyme.whiteflag.model.WhiteFlags;
import com.johnmelodyme.whiteflag.model.WhiteFlagsGet;

import java.util.ArrayList;

public class FilterHelper extends Filter
{
    public ArrayList<WhiteFlagsGet> whiteFlagsArrayList;
    public FlagsAdapter flagsAdapter;
    public Context context;

    public FilterHelper(ArrayList<WhiteFlagsGet> whiteFlagsArrayList, FlagsAdapter flagsAdapter,
                        Context context)
    {
        this.whiteFlagsArrayList = whiteFlagsArrayList;
        this.flagsAdapter = flagsAdapter;
        this.context = context;
    }


    /**
     * <p>Invoked in a worker thread to filter the data according to the
     * constraint. Subclasses must implement this method to perform the
     * filtering operation. Results computed by the filtering operation
     * must be returned as a {@link FilterResults} that
     * will then be published in the UI thread through
     * {@link #publishResults(CharSequence,
     * FilterResults)}.</p>
     *
     * <p><strong>Contract:</strong> When the constraint is null, the original
     * data must be restored.</p>
     *
     * @param constraint the constraint used to filter the data
     * @return the results of the filtering operation
     * @see #filter(CharSequence, FilterListener)
     * @see #publishResults(CharSequence, FilterResults)
     * @see FilterResults
     */
    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        FilterResults filterResults = new FilterResults();

        if (constraint != null && constraint.length() > 0)
        {
            constraint = constraint.toString().toUpperCase();

            ArrayList<WhiteFlagsGet> foundFilters = new ArrayList<WhiteFlagsGet>();

            WhiteFlagsGet whiteFlags = null;

            for (int i = 0; i < whiteFlagsArrayList.size(); i++)
            {

                whiteFlags = whiteFlagsArrayList.get(i);

                if (whiteFlags.getHomeAddress().toUpperCase().contains(constraint))
                {
                    foundFilters.add(whiteFlags);
                }

            }

            filterResults.count = foundFilters.size();
            filterResults.values = foundFilters;

        }
        else
        {
            filterResults.count = whiteFlagsArrayList.size();
            filterResults.values = whiteFlagsArrayList;
        }

        return filterResults;
    }

    /**
     * <p>Invoked in the UI thread to publish the filtering results in the
     * user interface. Subclasses must implement this method to display the
     * results computed in {@link #performFiltering}.</p>
     *
     * @param constraint the constraint used to filter the data
     * @param results    the results of the filtering operation
     * @see #filter(CharSequence, FilterListener)
     * @see #performFiltering(CharSequence)
     * @see FilterResults
     */
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        flagsAdapter.setWhiteFlags((ArrayList<WhiteFlagsGet>) results.values);
        flagsAdapter.refresh();
    }
}
