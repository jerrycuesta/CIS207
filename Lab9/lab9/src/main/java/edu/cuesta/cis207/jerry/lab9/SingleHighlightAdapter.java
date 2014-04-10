package edu.cuesta.cis207.jerry.lab9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * List Adapter with single highlighted value
 */

public class SingleHighlightAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<String> items;
    private int selectedIndex;
    private int selectedColor;
    private int backgroundColor;
    final ListView view;
    private int clickedPosition;

    public static final int NO_SELECTION = -1;


    public SingleHighlightAdapter(Context ctx, ListView view, ArrayList<String> items, int selected, int background)
    {
        this.context = ctx;
        this.items = items;
        this.selectedIndex = NO_SELECTION;
        this.selectedColor = selected;
        this.backgroundColor = background;
        this.view = view;
        clickedPosition = NO_SELECTION;
    }

    public void setSelectedIndex(int ind)
    {
        selectedIndex = clickedPosition = ind;
        notifyDataSetChanged();
    }

    public int getSelectedIndex()
    {
        return clickedPosition;
    }

    public String getSelectedText()
    {
        return items.get(clickedPosition);
    }

    public boolean haveSelection()
    {
        return clickedPosition != NO_SELECTION;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    @Override
    public Object getItem(int position)
    {
        return items.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TextView view;

        if (convertView == null)
        {
            view = (TextView) LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
        }
        else
        {
            view = (TextView)convertView;
        }

        if (selectedIndex != NO_SELECTION && position == selectedIndex)
        {
            view.setBackgroundColor(selectedColor);
        }
        else
        {
            view.setBackgroundColor(backgroundColor);
        }
        view.setText(items.get(position));

        return view;
    }

}
