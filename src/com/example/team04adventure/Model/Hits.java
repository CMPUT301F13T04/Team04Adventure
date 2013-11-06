package com.example.team04adventure.Model;

import java.util.Collection;

/**
 * Hits gets the response from ElasticSearchResponse. Copied from ESDemo.
 * @author Team04Adventure
 * @param <T>
 */

public class Hits<T> {
    int total;
    double max_score;
    Collection<ElasticSearchResponse<T>> hits;
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits;
    }
    public String toString() {
        return (super.toString()+","+total+","+max_score+","+hits);
    }
}