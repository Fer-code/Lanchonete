package com.example.lanchonete;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Endereco implements Serializable {
    private String _latitude;
    private String _longitude;
    private String _time;
    private String _endereco;

    public Endereco(){}

    public Endereco(String latitude, String longitude, String time, String endereco) {
        _latitude = latitude;
        _longitude = longitude;
        _time = time;
        _endereco = endereco;
    }

    public String get_latitude() {
        return _latitude;
    }

    public void set_latitude(String _latitude) {
        this._latitude = _latitude;
    }

    public String get_longitude() {
        return _longitude;
    }

    public void set_longitude(String _longitude) {
        this._longitude = _longitude;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public String get_endereco() {
        return _endereco;
    }

    public void set_endereco(String _endereco) {
        this._endereco = _endereco;
    }

    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("Latitude", this._latitude);
            obj.put("Longitude", this._longitude);
            obj.put("Time", this._time);
            obj.put("Endereco", this._endereco);
        } catch (JSONException e) {
            //trace("DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }
}
