package se.skanskan.mittskanskan;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RSSAdapter extends BaseAdapter implements OnClickListener {
    private Context context;
   
    String categorySport = "SPORT";
    String categoryOpinion = "OPINION";
    
    
    private List<Rssitem> listRssItems;

    public RSSAdapter(Context context, List<Rssitem> listRssItems) {
        this.context = context;
        this.listRssItems = listRssItems;
    }

    public int getCount() {
        return listRssItems.size();
    }

    public Object getItem(int position) {
        return listRssItems.get(position);
    }

    public long getItemId(int position) 
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) 
    {
        Rssitem entry = listRssItems.get(position);
        
        if (convertView == null) 
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rsslist, null);
        }
        
        
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(entry.getTitle());

        TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
        tvDesc.setText(entry.getDesc());
        
        
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        tvDate.setText(entry.getDate().substring(0, entry.getDate().length()-9));

        TextView tvLink = (TextView) convertView.findViewById(R.id.tvLink);
        tvLink.setText(entry.getLink());
        tvLink.setBackgroundResource(android.R.color.white);
        
        
        LinearLayout ly1 = (LinearLayout) convertView.findViewById(R.id.LinearLayout01);
        ly1.setBackgroundResource(android.R.color.darker_gray);
        
        
        // Kollar om länken innehåller "sport" och ändrar isf parent till txtviewn.
        
        if (tvLink.getText().toString().contains(categorySport))
		{
        	View parent = (View) tvTitle.getParent();
			parent.setBackgroundResource(android.R.color.holo_blue_light);
		}
        
        if (tvLink.getText().toString().contains(categoryOpinion))
        {
        	View parent = (View) tvTitle.getParent();
        	parent.setBackgroundResource(android.R.color.holo_orange_light);
        }
        
        return convertView;
    }
    
    @Override
    public void onClick(View view) 
    {
        Rssitem entry = (Rssitem) view.getTag();
        listRssItems.remove(entry);
      
        notifyDataSetChanged();

    }


}

