package net.apexclient.utils.serial;

import com.google.gson.JsonObject;

public interface Storable
{
    void read(JsonObject object);
    void write(JsonObject object);
}
