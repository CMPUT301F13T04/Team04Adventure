package com.example.team04adventure.Model;

/**
 * ElasticSearchResponse gets the response from ElasticSearch. Copied from ESDemo.
 * 
 * @author Team04Adventure
 * @param <T>
 */
public class ElasticSearchResponse<T> {
    String _index;
    String _type;
    String _id;
    int _version;
    boolean exists;
    T _source;
    double max_score;
    public T getSource() {
        return _source;
    }
}
