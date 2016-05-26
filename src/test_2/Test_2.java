package test_2;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class Test_2 {

    
    
// This is a ready made implementation for the DTW algorithm.	
public static class DTW {

	protected float[] seq1;
	protected float[] seq2;
	protected int[][] warpingPath;
	
	protected int n;
	protected int m;
	protected int K;
	
	protected double warpingDistance;
	
	/**
	 * Constructor
	 * 
	 * @param query		
	 * @param templete	
	 */
	public DTW(float[] sample, float[] templete) {
		seq1 = sample;
		seq2 = templete;
		
		n = seq1.length;	
		m = seq2.length;
		K = 1;
		
		warpingPath = new int[n + m][2];	// max(n, m) <= K < n + m
		warpingDistance = 0.0;
		
		this.compute();
	}

		DTW(double[] radiuses, float[] n2) {
			throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	
	public void compute() {
		double accumulatedDistance = 0.0;
		
		double[][] d = new double[n][m];	// local distances
		double[][] D = new double[n][m];	// global distances
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				d[i][j] = distanceBetween(seq1[i], seq2[j]);
			}
		}
		
		D[0][0] = d[0][0];
		
		for (int i = 1; i < n; i++) {
			D[i][0] = d[i][0] + D[i - 1][0];
		}

		for (int j = 1; j < m; j++) {
			D[0][j] = d[0][j] + D[0][j - 1];
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				accumulatedDistance = Math.min(Math.min(D[i-1][j], D[i-1][j-1]), D[i][j-1]);
				accumulatedDistance += d[i][j];
				D[i][j] = accumulatedDistance;
			}
		}
		accumulatedDistance = D[n - 1][m - 1];

		int i = n - 1;
		int j = m - 1;
		int minIndex = 1;
	
		warpingPath[K - 1][0] = i;
		warpingPath[K - 1][1] = j;
		
		while ((i + j) != 0) {
			if (i == 0) {
				j -= 1;
			} else if (j == 0) {
				i -= 1;
			} else {	// i != 0 && j != 0
				double[] array = { D[i - 1][j], D[i][j - 1], D[i - 1][j - 1] };
				minIndex = this.getIndexOfMinimum(array);

				if (minIndex == 0) {
					i -= 1;
				} else if (minIndex == 1) {
					j -= 1;
				} else if (minIndex == 2) {
					i -= 1;
					j -= 1;
				}
			} // end else
			K++;
			warpingPath[K - 1][0] = i;
			warpingPath[K - 1][1] = j;
		} // end while
		warpingDistance = accumulatedDistance / K;
		
		this.reversePath(warpingPath);
	}
	
	/**
	 * Changes the order of the warping path (increasing order)
	 * 
	 * @param path	the warping path in reverse order
	 */
	protected void reversePath(int[][] path) {
		int[][] newPath = new int[K][2];
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < 2; j++) {
				newPath[i][j] = path[K - i - 1][j];
			}
		}
		warpingPath = newPath;
	}
	/**
	 * Returns the warping distance
	 * 
	 * @return
	 */
	public double getDistance() {
		return warpingDistance;
	}
	
	/**
	 * Computes a distance between two points
	 * 
	 * @param p1	the point 1
	 * @param p2	the point 2
	 * @return		the distance between two points
	 */
	protected double distanceBetween(double p1, double p2) {
		return (p1 - p2) * (p1 - p2);
	}

	/**
	 * Finds the index of the minimum element from the given array
	 * 
	 * @param array		the array containing numeric values
	 * @return				the min value among elements
	 */
	protected int getIndexOfMinimum(double[] array) {
		int index = 0;
		double val = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] < val) {
				val = array[i];
				index = i;
			}
		}
		return index;
	}

	/**
	 *	Returns a string that displays the warping distance and path
	 */
	public String toString() {
		String retVal = "Warping Distance: " + warpingDistance + "\n";
		retVal += "Warping Path: {";
		for (int i = 0; i < K; i++) {
			retVal += "(" + warpingPath[i][0] + ", " +warpingPath[i][1] + ")";
			retVal += (i == K - 1) ? "}" : ", ";
			
		}
		return retVal;
	}
	
	/**
	 * Tests this class
	 * 
	 * @param args	ignored
	 */
	
    } // end DTW CLASS // end DTW CLASS // end DTW CLASS
    



// Testing the DTW Algorithm
public static void main(String[] args) {
	
		float[] n2 = {1.85f}; //, 3.9f, 4.1f, 3.3f}; // X^2 + y^2 = optimal
		float[] n1 = {1.5f}; //, 3.9f, 4.1f, 3.3f}; // ( midean( n^2 )


		
		DTW dtw = new DTW(n1, n2);
		System.out.println(dtw);
	} 


	





	
 private boolean flage = false;
private static int kk = 0 ,stander = 200;
 public static boolean left = false,right = false,up = false,booton = false,havegreen=false;
 private static ArrayList<org.opencv.core.Point> circel = new ArrayList<>();
 

// Function
public static Mat points(Mat comat,Mat mat,int choise, int[] array)
    {
            ArrayList<org.opencv.core.Point> colorArrayList = new ArrayList<>();
        try { 
            //havegreen=false;
            //Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY); // no grayscale
            int width = mat.width();
            int height = mat.height();
            MatOfByte m = new MatOfByte();
            Imgcodecs.imencode(".jpg", mat, m);
            InputStream in = new ByteArrayInputStream(m.toArray());
            BufferedImage buffer = ImageIO.read(in);
            java.awt.Color co = new java.awt.Color(buffer.getRGB(0, 0));
            int max = co.getRed(),Xmax=0,Ymax=0;
            int wap=0;


	// Edit : Taha Magdy
	// Here you can control the colors 
    	for(int i =0;i<height;i++)
            {
	
                for(int j=1;j<width;j++)
                {

			
				    
			
                    co = new java.awt.Color(buffer.getRGB(j, i)); // co is a color
		    
		    
		    if( ( co.getRed() >= 255 ) )
                    {
			    
			
                        colorArrayList.add(new org.opencv.core.Point(j, i));
			
			array[0] = i; // hight
			array[1] = j; // width
			

                    }
		    
                } // end for width
            } // end for hight


	
	    // Working on a specific range of light density.
            if( colorArrayList.size() < 30 && colorArrayList.size() < 120  )
           {
               if(choise == 1)
                  return mat;
               else
                  return comat;
           }
           if(colorArrayList.size()!=0)
           {
                if(circel.size() >= 16)
                {
                    circel = new ArrayList<>();
                }
                else
                {
                    circel.add(colorArrayList.get(colorArrayList.size()/2));
                }
           }
           else
           {
               circel = new ArrayList<>();
           }
            
            for(org.opencv.core.Point p : colorArrayList)
            {
                if(p.x < width * 0.01)
                {
                    left = false;
                    right = true;
                    up = false;
                    booton = false;
                }
                else if(p.x > width * 0.99)
                {
                    right = false;
                    left = true;
                    up = false;
                    booton = false;
                }
                else if(p.y < height * 0.01)
                {
                    up = true;
                    left = false;
                    right = false;
                    booton = false;
                }
                else if(p.y > height * 0.99)
                {
                    booton = true;
                    up = false;
                    left = false;
                    right = false;
                }
            }
           Mat ma;
        
                ma = draw(colorArrayList,mat,choise);
          
           return ma; // return the edited picture
        } catch (IOException ex) {
            System.out.println("error in function points");
        }
        return null;
    } // NED >> // Edit : Taha Magdy
 
  
  
  
  
  







 
 
 public static boolean isleft()
 {
     return left;
 }

 public static boolean isBooton() {
      return booton;
 }

 public static boolean isUp() {
      return up;
 }

 public static boolean isRight() {
     return right;
 }

    public static void setLeft(boolean left) {
        Test_2.left = left;
    }

    public static void setBooton(boolean booton) {
        Test_2.booton = booton;
    }

    public static void setRight(boolean right) {
        Test_2.right = right;
    }

    public static void setUp(boolean up) {
        Test_2.up = up;
    }
 
 


 

 public static void setKk(int kk) {
      Test_2.kk = kk;
 }
 
 
 
 
 
 public static Mat pointsimg(Mat mat) // What returns the mat with points
    {
            ArrayList<org.opencv.core.Point> color = new ArrayList<>(); // array list of pixels

        try {
            Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY); // truns img to grays
	    
            int width = mat.width();
            int height = mat.height();


            MatOfByte m = new MatOfByte();  // because it can be truned into array
            Imgcodecs.imencode(".jpg", mat, m); // tuen mat to mat of byte
            InputStream in = new ByteArrayInputStream(m.toArray()); // connect between and the mat
            BufferedImage buffer = ImageIO.read(in); // takes input stream










	    
            java.awt.Color co = new java.awt.Color(buffer.getRGB(0, 0)); // buffer to get RBG
            int max = co.getRed(); // Xmax=0,Ymax=0; int wap=0;
	    
            for(int i =0;i<height;i++)
            {
                kk = 0;
                for(int j=1;j<width;j++)
                {
                    co = new java.awt.Color(buffer.getRGB(j, i));
                    if(max==co.getRed())
                    {
                        color.add(new org.opencv.core.Point(j, i));
                    }
                    else if(co.getRed() > max)
                    {
                        max = co.getRed();
                        color = new ArrayList<>();
                        color.add(new org.opencv.core.Point(j, i));
                    }
                }
            }



           Mat ma = draw(color,mat,1); // draw() : draw the the points on mat.
           return ma;
        } catch (IOException ex) {
            System.out.println("error in function points");
        }
        return null;
    }
       
            

    private static Mat draw(ArrayList<org.opencv.core.Point> points,Mat mat,int chose)
    {
        org.opencv.core.Point p1 ; // pixel of openCV not of java
        if(chose==1)
		// this is to allow the colors
           Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2BGR); // converting...
        for(int i=0 ; i< points.size(); i++)
        {
            p1 = points.get(i);
	    // circle it 
            Imgproc.circle(mat,p1,5,new Scalar(0,255,0));
        }

//Imgproc.circle(mat, new org.opencv.core.Point( MainFrame.xCenter, MainFrame.yCenter) , 50, new Scalar( 255, 0, 0) );

	
        return mat;
    }







    // Function
    private static Mat print (Mat mat)
    {
        Imgproc.putText(mat, "welcome left",new org.opencv.core.Point(50, 50), 2, 2,new Scalar(255, 0, 0) );
        return mat ;
    }


}
