package druid.math.wrapper;

public interface AndroidMathWrapperInterface {
	
	/**
	 * @fn muged_1D_correlation(muged_array& fsignal, muged_array& ssignal,
	 *													muged_array& correlation)
	 *
	 * Calculates 1D correlation of signals
	 *
	 * @param fsignal - first 1D signal
	 * @param ssignal - second 1D signal
	 * @return correlation - result
	 */
	double[] muged_1D_correlation(double[] fsignal, double[] ssignal); 
	//muged_array fsignal, muged_array ssignal,	size_t min_lag, size_t max_lag, muged_array& correlation) = 0;

	/**
	 * Calculates 2D correlation of signals in ranges: -max_lag : -min_lag and min_lag : max_lag
 	 *
	 * @param fsignal - first 2D signal
	 * @param ssignal - second 2D signal
	 * @param min_lag - minimum range
	 * @param max_lag - maximum range
	 * @param correlation - result (memory will be allocated)
	 */
	double[][] muged_2D_correlation(double[][] fsignal, double[][] ssignal, int min_lag, int max_lag); 
	//muged_matrix& fsignal, muged_matrix& ssignal, muged_matrix& correlation) = 0;
	
	/**
	 * Calculates 1D Fast Fourier Transform. If signal length is not power of two,
	 * signal is padded with zeros to the next power of two.
	 * 
	 * @param signal - 1D signal
	 * @return spectrum - result
	 * 
	 */
	MugedComplex[] muged_1D_fft(double[] signal); //muged_array& signal, muged_array& spectrum) = 0;

	/**
	 * Calculates 1D Inverse Fast Fourier Transform. If signal length is not power of two,
	 * signal is padded with zeros to the next power of two.
	 * 
	 * @param spectrum - 1D signal in frequency domain
	 * @return signal - result in time domain
	 * 
	 */
	double[] muged_1D_ifft(MugedComplex[] spectrum); //muged_array& signal, muged_array& spectrum) = 0;
	
	/**
	 * @fn muged_2D_fft(muged_matrix& ssignal, muged_matrix& spectrum)
	 *
	 * Calculates 2D Fast Fourier Transform
	 *
	 * @param signal - 1D signal
	 * @param spectrum - result (memory will be allocated)
	 */
	MugedComplex[][] muged_2D_fft(double[][] signal); 
	//muged_matrix& signal, muged_matrix& spectrum) = 0;

	/**
	 * Calculates mean of 1D signal
	 *
	 * @param signal - 1D signal
	 * @return muged_scalar - result
	 */
	double muged_mean(double[] signal);
	//virtual muged_scalar muged_mean(); //muged_array& signal) = 0;

	/**
	 * Calculates RMS of 1D signal
	 *
	 * @param signal - 1D signal
	 * @return muged_scalar - result
	 */
	double muged_root_mean_square(double[] signal);
	//virtual muged_scalar muged_root_mean_square(muged_array& signal) = 0;

	/**
	 * Calculates mean square of 1D signal
	 *
	 * @param signal - 1D signal
	 * @return muged_scalar - result
	 */
	double muged_mean_square(double[] signal);//
	//virtual muged_scalar muged_mean_square(muged_array& signal) = 0;

	/**
	 * Calculates standard deviation of 1D signal
	 *
	 * @param signal - 1D signal
	 * @return muged_scalar - result
	 */
	//virtual muged_scalar muged_standard_deviation(muged_array& signal) = 0;
	double muged_standard_deviation(double[] signal); 

	/**
	 * @fn muged_kalman(muged_array& signal, muged_array& filtered_signal)
	 *
	 * Performs Kalman filtration on 1D signal
	 *
	 * @param signal - 1D signal
	 * @param filtered_signal - result (memory will be allocated)
	 */
	//virtual void muged_kalman(muged_array& signal, muged_array& filtered_signal) = 0;
	double muged_kalman(double[] signal, double[] filtered_signal);
}
