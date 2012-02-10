package druid.math.wrapper;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
//import edu.emory.mathcs.jtransforms.fft.DoubleFFT_2D;
import java.lang.Math;

public class AndroidMathWrapper implements AndroidMathWrapperInterface{
	@Override
	public double[] muged_1D_correlation(double[] fsignal, double[] ssignal) {
		//add padding with zeros
		//extend signals
		double logNbase2 =  Math.log(fsignal.length+ssignal.length-1)/Math.log(2);
		int len = (int)Math.pow(2.0,Math.ceil(logNbase2));
		
		DoubleFFT_1D dfft1d = new DoubleFFT_1D(len);
		
		double[] signalExtL = new double[2*len];
		for(int i=0; i < (fsignal.length+ssignal.length-1); i++) {
			if(i > fsignal.length-2)
				signalExtL[2*i] = ssignal[i-(ssignal.length-1)];				
		}
		
		double[] signalExtR = new double[2*len];
		for(int i=0; i < fsignal.length; i++) {
			signalExtR[2*i] = fsignal[i];				
		}
		
		dfft1d.complexForward(signalExtR);
		dfft1d.complexForward(signalExtL);
		
		for(int i = 0; i < signalExtR.length/2; i++) {
			signalExtR[2*i+1] = -1*signalExtR[2*i+1];
		}
		double xcorellation[] = new double[signalExtL.length];
		for(int i = 0; i < signalExtL.length/2; i++) {
			//complex multiplication
			xcorellation[2*i] = signalExtL[2*i]*signalExtR[2*i] - signalExtL[2*i+1]*signalExtR[2*i+1];
			xcorellation[2*i+1] = signalExtL[2*i]*signalExtR[2*i+1] + signalExtL[2*i+1]*signalExtR[2*i];
		}
		dfft1d.complexInverse(xcorellation, true);	 //scaling performed
		double[] outcorrelation = new double[fsignal.length+ssignal.length-1];
		
		for(int i = 0; i < outcorrelation.length; i++) {
			outcorrelation[i] = xcorellation[2*i]; 
		}
		
		return outcorrelation;
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
