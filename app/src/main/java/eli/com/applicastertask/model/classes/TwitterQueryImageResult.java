package eli.com.applicastertask.model.classes;

import com.twitter.sdk.android.core.models.Tweet;

/**
 * Created by eli on 22/12/16.
 */

public class TwitterQueryImageResult extends TwitterQueryResult {

    protected String imagePath;

    public TwitterQueryImageResult(Long id, String text, String userImagePath, String userName, String imagePath) {
        super(id, text, userImagePath, userName);
        this.imagePath = imagePath;
    }

    public TwitterQueryImageResult(Tweet t) {
        super(t);
        if (t.entities != null && t.entities.media != null && !t.entities.media.isEmpty()) {
            imagePath = t.entities.media.get(0).mediaUrl;
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
