package druid.math.wrapper;

import android.util.Log;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class AndroidMathWrapperTest extends Activity {
    /** Called when the activity is first created. */
    //@Override
    private static Context CONTEXT;
    private TextView tv;
    private static final String TAG = "MugedActivity";
    
    public static void printComplexArray(MugedComplex[] complexArray) {
    	for(int i = 0; i < complexArray.length; i++)
	    	Log.v(TAG, Double.toString(complexArray[i].getReal()) + " " + Double.toString(complexArray[i].getImaginary()));
    }

    //private long lastTime = 0;
    public void onCreate(Bundle savedInstanceState) {
    	CONTEXT = this;
        super.onCreate(savedInstanceState);
        
        tv = new TextView(this);
        AndroidMathWrapper amw = new AndroidMathWrapper();
		double[] testVector = {10,10,60,10,10};
		//System.out.println(amw.muged_mean(testVector));
		
		printComplexArray(amw.muged_1D_fft(testVector));
		Log.v(TAG, "TEST1111oneoneone");
		
		
		
        tv.setText(Double.toString(amw.muged_mean(testVector)) + "\n" + 
        		Double.toString(amw.muged_mean_square(testVector)) + "\n" + 
        		Double.toString(amw.muged_root_mean_square(testVector)) + "\n" + 
        		Double.toString(amw.muged_standard_deviation(testVector)));
        
        
        
        setContentView(tv);
        //setContentView(R.layout.main);
    }
    
    public static Context getContext(){
		return CONTEXT;
	}
}