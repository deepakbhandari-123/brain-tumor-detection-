import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class FinalClass
{
    private static final double CANNY_THRESHOLD_RATIO = .2; 
    private static final int CANNY_STD_DEV = 1;             
    
    private static String imgFileName;
    private static String imgOutFile = "E:\\JavaProgs\\project";
    private static String imgExt;

    public static void main(String[] args) 
    {
        imgFileName = "E:\\JavaProgs\\project\\yes\\Y18.jpg";
        imgExt = "jpg";
        String[] arr = imgFileName.split("\\.");
        
        for (int i = 0; i < arr.length - 1; i++) 
	{
            imgOutFile += arr[i];
        }
        
        imgOutFile="NewImg18.jpg";
        try 
	{
            BufferedImage input = ImageIO.read(new File(imgFileName));
            BufferedImage output = JCanny.CannyEdges(input, CANNY_STD_DEV, CANNY_THRESHOLD_RATIO);
            ImageIO.write(output, imgExt, new File(imgOutFile));
		System.out.println("Done");
        } catch (Exception ex) {
            System.out.println("ERROR ACCESING IMAGE FILE:\n" + ex.getMessage());
        }
    }    
}