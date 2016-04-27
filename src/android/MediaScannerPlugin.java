package org.sfl.MediaScannerPlugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class MediaScannerPlugin extends CordovaPlugin {
    public static final String ACTION = "scanFile";
    private static final String TAG = "MediaScannerPlugin";

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if (action.equals(ACTION)) {
            /* Invoke the system's media scanner to add your photo to the Media Provider's database,
            * making it available in the Android Gallery application and to other apps. */
            String path = args.getString(0);

            Log.d(TAG, "path is "+path);


            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.parse(path));
            cordova.getActivity().sendBroadcast(intent);
            callbackContext.success();

            return true;
        } else {
            Log.w(TAG, "Wrong action was provided: "+action);
            return false;
        }
    }
}
