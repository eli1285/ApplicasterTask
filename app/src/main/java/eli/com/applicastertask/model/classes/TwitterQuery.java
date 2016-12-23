package eli.com.applicastertask.model.classes;

import com.twitter.sdk.android.core.services.params.Geocode;

import eli.com.applicastertask.enums.TwitterResultType;

/**
 * Created by eli on 22/12/16.
 */

public class TwitterQuery extends Query {

    private Geocode geocode;
    private String lang;
    private String locale;
    private TwitterResultType resultType;
    private String until;
    private Long sinceId;
    private Long maxId;
    private Boolean includeEntities;

    public TwitterQuery(String queryText, Integer limit, TwitterResultType resultType) {
        super(queryText, limit);
        this.resultType = resultType;
    }

    public TwitterQuery(String queryText, Integer limit, TwitterResultType resultType, Geocode geocode, String lang, String locale, String until, Long sinceId, Long maxId, Boolean includeEntities) {
        super(queryText, limit);
        this.resultType = resultType;
        this.geocode = geocode;
        this.lang = lang;
        this.locale = locale;
        this.until = until;
        this.sinceId = sinceId;
        this.maxId = maxId;
        this.includeEntities = includeEntities;
    }

    public Geocode getGeocode() {
        return geocode;
    }

    public void setGeocode(Geocode geocode) {
        this.geocode = geocode;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public TwitterResultType getResultType() {
        return resultType;
    }

    public void setResultType(TwitterResultType resultType) {
        this.resultType = resultType;
    }

    public String getUntil() {
        return until;
    }

    public void setUntil(String until) {
        this.until = until;
    }

    public Long getSinceId() {
        return sinceId;
    }

    public void setSinceId(Long sinceId) {
        this.sinceId = sinceId;
    }

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Boolean getIncludeEntities() {
        return includeEntities;
    }

    public void setIncludeEntities(Boolean includeEntities) {
        this.includeEntities = includeEntities;
    }
}
