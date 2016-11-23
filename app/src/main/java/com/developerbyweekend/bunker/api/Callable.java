package com.developerbyweekend.bunker.api;

/**
 * Created by sunit on 20/11/16.
 */

public interface Callable {
    public void onResponse(Object data);
    public void onError(Exception error);
}
