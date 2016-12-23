package eli.com.applicastertask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import eli.com.applicastertask.R;
import eli.com.applicastertask.enums.TweetRowType;
import eli.com.applicastertask.model.classes.TwitterQueryImageResult;
import eli.com.applicastertask.model.classes.TwitterQueryResult;
import eli.com.applicastertask.model.view_holders.TwitterResultImageViewHolder;
import eli.com.applicastertask.model.view_holders.TwitterResultViewHolder;

/**
 * Created by eli on 22/12/16.
 */

public class TwitterSearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<TwitterQueryResult> tweets;

    public TwitterSearchResultAdapter(Context context, List<TwitterQueryResult> tweets) {
        this.tweets = tweets;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TweetRowType.TEXT.ordinal()) {
            View itemView = inflater.inflate(R.layout.search_result_text_row, parent, false);
            return new TwitterResultViewHolder(itemView);
        } else {
            View itemView = inflater.inflate(R.layout.search_result_image_row, parent, false);
            return new TwitterResultImageViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TwitterQueryResult tweet = tweets.get(position);

        if (holder instanceof TwitterResultImageViewHolder && tweet instanceof TwitterQueryImageResult) {
            TwitterResultImageViewHolder imageViewHolder = (TwitterResultImageViewHolder) holder;
            TwitterQueryImageResult tweetImage = (TwitterQueryImageResult) tweet;

            Picasso.with(context).load(tweetImage.getImagePath()).into(imageViewHolder.getIvImage());

            bindUser(tweet, imageViewHolder.getIvUserImage(), imageViewHolder.getTvText(), imageViewHolder.getTvUserName());
        } else {
            TwitterResultViewHolder textViewHolder = (TwitterResultViewHolder) holder;

            bindUser(tweet, textViewHolder.getIvUserImage(), textViewHolder.getTvText(), textViewHolder.getTvUserName());
        }
    }

    /**
     * Set the image, name and text of the user
     * @param tweet
     * @param ivUserImage
     * @param tvText
     * @param tvUserName
     */
    private void bindUser(TwitterQueryResult tweet, ImageView ivUserImage, TextView tvText, TextView tvUserName) {
        Picasso.with(context).load(tweet.getUserImagePath()).into(ivUserImage);
        tvText.setText(tweet.getText());
        tvUserName.setText(tweet.getUserName());
    }


    @Override
    public int getItemCount() {
        return tweets == null ? 0 : tweets.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (tweets == null || tweets.isEmpty()) {
            return TweetRowType.TEXT.ordinal();
        } else {
            int type = tweets.get(position) instanceof TwitterQueryImageResult ? TweetRowType.IMAGE.ordinal() : TweetRowType.TEXT.ordinal();
            return type;
        }
    }


    public void refreshItems(List<TwitterQueryResult> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }
}
