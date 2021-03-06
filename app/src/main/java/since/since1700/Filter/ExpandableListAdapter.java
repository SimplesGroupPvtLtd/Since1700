package since.since1700.Filter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import since.since1700.R;


public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public static int ITEM1 = 0;
    public static int ITEM2 = 1;
    public static int ITEM3 = 2;
    public static int ITEM4 = 3;


    public static int SUBITEM1_1 = 0;
    public static int SUBITEM1_2 = 1;
    public static int SUBITEM1_3 = 2;
    public static int SUBITEM1_4 = 3;


    public static int SUBITEM2_1 = 0;
    public static int SUBITEM2_2 = 1;
    public static int SUBITEM2_3 = 2;
    public static int SUBITEM2_4 = 3;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public  static  final String SelectedCategory="category";
    String categoryitems;
    List<String> CategoryList;
   Button checkbutton;
    public ExpandableListAdapter(Context context, List<String> expandableListTitle,
                                 HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;


    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }


      Button checkbutton;
       /* String splash = "fonts/LATO-REGULAR.TTF";
        final Typeface tf = Typeface.createFromAsset(context.getAssets(), splash);*/

        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        //
        // expandedListTextView.setTypeface(tf);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, final boolean isExpanded,
                             View convertView, final ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        sharedpreferences =context. getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        categoryitems=sharedpreferences.getString(SelectedCategory,"");

         CategoryList = Arrays.asList(categoryitems.split(","));
        Log.e("CATS",listTitle.toString());
        ImageView plus = (ImageView) convertView.findViewById(R.id.plus);
         checkbutton= (Button) convertView.findViewById(R.id.btn_check);


        if (listPosition % 2 == 0) {
            convertView.setBackgroundResource(R.color.gray);
        } else {
            convertView.setBackgroundResource(R.color.lightgray);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);

       /* String splash = "fonts/LATO-REGULAR.TTF";
        final Typeface tf = Typeface.createFromAsset(context.getAssets(), splash);
*/

        //listTitleTextView.setTypeface(tf);
        listTitleTextView.setText(listTitle);

        String s;
        Log.e("List",expandableListDetail.keySet().toString());
        for (int i=0;i<expandableListTitle.size();i++){
            s=expandableListTitle.get(i).toString()+",";
            Log.e("ITEM",s.toString());
            // String ss=CategoryList.get(i).toString();
            if (categoryitems!=null){
                if(s.equals(categoryitems)){
                    checkbutton.setVisibility(View.VISIBLE);
                    checkbutton.setBackgroundResource(R.mipmap.tickwhiteblue);
                }
            }else {

            }
        }
        Log.e("SIZE", String.valueOf(getGroupCount()));
        for(int j = 0; j< expandableListTitle.size(); j++) {


        }


        checkbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    // Toast.makeText(getApplicationContext(), modellist.get(position).getProductname() + " selected!", Toast.LENGTH_SHORT).show();
                   checkbutton.setVisibility(View.VISIBLE);
                  checkbutton.setBackgroundResource(R.mipmap.tickwhiteblue);

            }
        });


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isExpanded) ((ExpandableListView) parent).collapseGroup(listPosition);
                else ((ExpandableListView) parent).expandGroup(listPosition,true);

            }
        });

       /* listTitleTextArrowView.setTypeface(null, Typeface.NORMAL);
        listTitleTextArrowView.setTypeface(FontManagerTamil.getTypeface(context, FontManagerTamil.FONTAWESOME));
*/
        // set icons for menu items
        /*TextView listTitleTextIconView = (TextView) convertView
                .findViewById(R.id.listTitleIcon);
        listTitleTextIconView.setTypeface(null, Typeface.NORMAL);
        listTitleTextIconView.setTypeface(FontManagerTamil.getTypeface(context, FontManagerTamil.FONTAWESOME));
      */  /*if (listPosition == ITEM1)
            listTitleTextIconView.setText(context.getResources().getString(R.string.fa_glass));
        else if (listPosition == ITEM2)
            listTitleTextIconView.setText(context.getResources().getString(R.string.fa_music));
        else if (listPosition == ITEM3)
            listTitleTextIconView.setText(context.getResources().getString(R.string.fa_search));
        else if (listPosition == ITEM4)
            listTitleTextIconView.setText(context.getResources().getString(R.string.fa_envelope_o));
*/
        // set arrow icons for relevant items
        /*if (listPosition == ITEM1 || listPosition == ITEM2 || listPosition == ITEM3) {
            if (!isExpanded)
                listTitleTextArrowView.setText(context.getResources().getString(R.string.fa_chevron_right));
            else
                listTitleTextArrowView.setText(context.getResources().getString(R.string.fa_chevron_down));
        } else {
            listTitleTextArrowView.setText("");

        }*/
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);

    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }

}
