public class Gaussian 
{
    private static final double SQRT2PI = Math.sqrt(2 * Math.PI);
    
    public static int[][][] BlurRGB(int[][][] raw, int rad, double intens) 
    {
        int height = raw.length;
        int width = raw[0].length;
        double intensSquared2 = 2 * intens * intens;
        double invIntensSqrPi = 1 / (SQRT2PI * intens);
        double norm = 0.;
        double[] mask = new double[2 * rad + 1];
        int[][][] outRGB = new int[height - 2 * rad][width - 2 * rad][3];
        
        for (int x = -rad; x < rad + 1; x++) {
            double exp = Math.exp(-((x * x) / intensSquared2));
            
            mask[x + rad] = invIntensSqrPi * exp;
            norm += mask[x + rad];
        }
        
        for (int r = rad; r < height - rad; r++) {
            for (int c = rad; c < width - rad; c++) {
                double[] sum = new double[3];
                
                for (int mr = -rad; mr < rad + 1; mr++) {
                    for (int chan = 0; chan < 3; chan++) {
                        sum[chan] += (mask[mr + rad] * raw[r][c + mr][chan]);
                    }
                }
                
                for (int chan = 0; chan < 3; chan++) {
                    sum[chan] /= norm;
                    outRGB[r - rad][c - rad][chan] = (int) Math.round(sum[chan]);
                }
            }
        }
        
        for (int r = rad; r < height - rad; r++) {
            for (int c = rad; c < width - rad; c++) {
                double[] sum = new double[3];
                
                for (int mr = -rad; mr < rad + 1; mr++) {
                    for(int chan = 0; chan < 3; chan++) {
                        sum[chan] += (mask[mr + rad] * raw[r + mr][c][chan]);
                    }
                }
                for (int chan = 0; chan < 3; chan++) {
                    sum[chan] /= norm;
                    outRGB[r - rad][c - rad][chan] = (int) Math.round(sum[chan]);
                }
            }
        }        
        return outRGB;
    }
    
    public static int[][] BlurGS (int[][] raw, int rad, double intens) 
    {
        int height = raw.length;
        int width = raw[0].length;
        double norm = 0.;
        double intensSquared2 = 2 * intens * intens;
        double invIntensSqrPi = 1 / (SQRT2PI * intens);
        double[] mask = new double[2 * rad + 1];
        int[][] outGS = new int[height - 2 * rad][width - 2 * rad];
        
        for (int x = -rad; x < rad + 1; x++) 
	{
            double exp = Math.exp(-((x * x) / intensSquared2));
            
            mask[x + rad] = invIntensSqrPi * exp;
            norm += mask[x + rad];
        }
        
        for (int r = rad; r < height - rad; r++) {
            for (int c = rad; c < width - rad; c++) {
                double sum = 0.;
                
                for (int mr = -rad; mr < rad + 1; mr++) {
                    sum += (mask[mr + rad] * raw[r][c + mr]);
                }
                
                sum /= norm;
                outGS[r - rad][c - rad] = (int) Math.round(sum);
            }
        }
        
        for (int r = rad; r < height - rad; r++) {
            for (int c = rad; c < width - rad; c++) {
                double sum = 0.;
                
                for(int mr = -rad; mr < rad + 1; mr++) {
                    sum += (mask[mr + rad] * raw[r + mr][c]);
                }
                
                sum /= norm;
                outGS[r - rad][c - rad] = (int) Math.round(sum);
            }
        }
        
        return outGS;
    }
}
