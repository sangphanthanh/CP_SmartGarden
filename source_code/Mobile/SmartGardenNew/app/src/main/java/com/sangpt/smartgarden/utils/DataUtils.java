package com.sangpt.smartgarden.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

//import com.google.android.gms.common.api.GoogleApiClient;

public class DataUtils {
    public static String URL = "http://smartgarden-thanhsang.rhcloud.com/SmartGarden";



    private static DataUtils INSTANCE = null;
    private SharedPreferences mPreferences;
    private Context mContext;

    private DataUtils(Context context) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences("SmartGarden", 0);
    }

    public static DataUtils getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DataUtils(context);
        }
        return INSTANCE;
    }

    public static String getFilePath(String name) {
        String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + name;
        return dir;
    }


    public static File getImageFileFromUri(Context context, Uri imageUri, String imagePath, int maxPicel, Bitmap.CompressFormat imageType, int imageQuality) {

        File f = new File(imagePath);
        try {
            f.createNewFile();
            InputStream imageStream = context.getContentResolver().openInputStream(imageUri);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            bitmap = DataUtils.scaleDown(bitmap, 2000, false);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(imageType, imageQuality /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static Date parseDatetime(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(s);
        return date;
    }

    public static String getTime(String s) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = sdf.parse(s);

        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();
        long time = 0;
        time = currentDate.getTime() - date.getTime();
        String interval = "";
        long temp = 0;
        if ((temp = TimeUnit.SECONDS.convert(time, TimeUnit.MILLISECONDS)) < 60) {
            interval = temp + " second" + (temp > 1 ? "s" : "");
        } else if ((temp = TimeUnit.MINUTES.convert(time, TimeUnit.MILLISECONDS)) < 60) {
            interval = temp + " minute" + (temp > 1 ? "s" : "");
        } else if ((temp = TimeUnit.HOURS.convert(time, TimeUnit.MILLISECONDS)) < 24) {
            interval += temp + " hour" + (temp > 1 ? "s" : "");
        } else if ((temp = TimeUnit.DAYS.convert(time, TimeUnit.MILLISECONDS)) < 30) {
            interval += temp + " day" + (temp > 1 ? "s" : "");
        } else {
            interval = s;
        }
        return interval;
    }

    public SharedPreferences getmPreferences() {
        return mPreferences;
    }

    public static void getAlphaAmination(View v){
        if (v!=null) {
            AlphaAnimation animation1 = new AlphaAnimation(1.0f, 0.6f);
            animation1.setDuration(200);
            animation1.setRepeatCount(1);
            animation1.setRepeatMode(Animation.REVERSE);
            v.startAnimation(animation1);
        }
    }

    public void setDobField(final EditText editText) {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Calendar newCalendar = Calendar.getInstance();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editText.setText(dateFormatter.format(newDate.getTime()));
            }

        }, (newCalendar.get(Calendar.YEAR)), (newCalendar.get(Calendar.MONTH)), (newCalendar.get(Calendar.DATE)));
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    datePickerDialog.show();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        });


    }

}
