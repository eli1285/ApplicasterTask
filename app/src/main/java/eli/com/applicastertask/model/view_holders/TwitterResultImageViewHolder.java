package eli.com.applicastertask.model.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import eli.com.applicastertask.R;

/**
 * Created by eli on 22/12/16.
 */

public class TwitterResultImageViewHolder extends RecyclerView.ViewHolder {

    private TextView tvUserName;
    private TextView tvText;
    private ImageView ivUserImage;
    private ImageView ivImage;

    public TwitterResultImageViewHolder(View v) {
        super(v);
        ivImage = (ImageView) v.findViewById(R.id.ivImage);
        ivUserImage = (ImageView) v.findViewById(R.id.ivUserImage);
        tvUserName = (TextView) v.findViewById(R.id.tvUserName);
        tvText = (TextView) v.findViewById(R.id.tvText);
    }

    public TextView getTvUserName() {
        return tvUserName;
    }


    public TextView getTvText() {
        return tvText;
    }


    public ImageView getIvUserImage() {
        return ivUserImage;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

}
