package druid.math.wrapper;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
//import edu.emory.mathcs.jtransforms.fft.DoubleFFT_2D;
import java.lang.Math;

public class AndroidMathWrapper implements AndroidMathWrapperInterface{
	@Override
	public double[] muged_1D_correlation(double[] fsignal, double[] ssignal) {
		
		return null;
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public double[][] muged_2D_correlation(double[][] fsignal, double[][] ssignal,
			int minLag, int maxLag) {
				return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public MugedComplex[] muged_1D_fft(double[] signal) {
		
		int len = 0;
		double logNbase2 =  Math.log(signal.length)/Math.log(2);   
		
		len = (int)Math.pow(2.0,Math.ceil(logNbase2));
		DoubleFFT_1D dfft1d = new DoubleFFT_1D(len);
		double[] signal_ext = new double[2*len];
		for(int i=0; i < signal.length; i++) {
			signal_ext[2*i] = signal[i];				
		}
		dfft1d.complexForward(signal_ext);
		
		MugedComplex[] outSpectrum = new MugedComplex[len];
		
		for(int i=0; i < len; i++) {
			outSpectrum[i] = new MugedComplex(signal_ext[2*i], signal_ext[2*i+1]);				
		}
		
		return outSpectrum;	
	}
	
	@Override
	public MugedComplex[][] muged_2D_fft(double[][] signal) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public double muged_kalman(double[] signal, double[] filteredSignal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double muged_mean(double[] signal) {
		double sum = 0; 
		for (int i=0; i < signal.length; i++)
			sum+=signal[i];
		return sum / signal.length; 
	}

	@Override
	public double muged_mean_square(double[] signal) {
		double sum = 0; 
		for (int i=0; i < signal.length; i++)
			sum+=signal[i]*signal[i];
		return sum / signal.length; 
	}

	@Override
	public double muged_root_mean_square(double[] signal) {
		double sum = 0; 
		for (int i=0; i < signal.length; i++)
			sum+=signal[i]*signal[i];
		return java.lang.Math.sqrt(sum / signal.length);
		
	}

	@Override
	public double muged_standard_deviation(double[] signal) {
		StandardDeviation sd = new StandardDeviation();
		return sd.evaluate(signal);
	}
	
}
