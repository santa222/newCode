package com.mika.newcode.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mika.newcode.R;
import com.mika.newcode.models.CRModel;
import com.mika.newcode.models.User;

import java.util.List;

/**
 * Created by hugo on 6/18/14.
 */
public class ListAdapter extends ArrayAdapter<User>{

    public ListAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
       // list.addAll(objects);
    }


    private static class ListViewHolder{
        private TextView account;
        private TextView company;
        private TextView name;
        private TextView email;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewHolder viewHolder;

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_user,null);
            viewHolder = initViewHolder(convertView);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ListViewHolder) convertView.getTag();
        }

        User item = (User)getItem(position);
        viewHolder.account.setText("用户名： "+item.getAccount());
        viewHolder.name.setText("姓名： "+item.getName());
        viewHolder.company.setText("公司： "+item.getCompany());
        viewHolder.email.setText("邮箱： "+item.getMail());
        // AQuery query = new AQuery(getContext());
        //query.id(viewHolder.rowIcon).image(item.getImage().getImageForDensity(CRImage.IMAGE_TYPE.SMALL,(Activity)getContext()));

        return convertView;
    }

    private ListViewHolder initViewHolder(View view) {
        ListViewHolder viewHolder = new ListViewHolder();

        viewHolder.account = (TextView) view.findViewById(R.id.row_user_account);
      //  viewHolder.moreBtn=(ImageButton)view.findViewById(R.id.row_reference_more_btn);
        viewHolder.company=(TextView)view.findViewById(R.id.row_user_company);
        viewHolder.name=(TextView)view.findViewById(R.id.row_user_name);
        viewHolder.email=(TextView)view.findViewById(R.id.row_user_email);

        return viewHolder;
    }
}
