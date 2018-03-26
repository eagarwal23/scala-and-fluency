package pioneer.pictures

import java.awt.Color
import java.awt.image.BufferedImage
import java.io._
import javax.imageio.ImageIO

/**
  * A small, image-processing library. Thanks to
  *    http://otfried.org/scala/image.html
  * for help on how to perform basic image processing in Scala
  */
object Picture {
  
  /** Flips an image along its horizontal axis */
  def flipHorizontal(image: BufferedImage): BufferedImage = {

    // create a new, empty image to copy pixels into
    val width = image.getWidth
    val height = image.getHeight
    val result = new BufferedImage(width, height, image.getType)

    // copy the pixels over, column-by-column, from right to left
    for (column <- 0 until width)
      for (row <- 0 until height)
        result.setRGB(column, row, image.getRGB(width - column - 1, row))

    result
  }

  /** Flips an image along its vertical axis */
  def flipVertical(image: BufferedImage): BufferedImage = {

    // create a new, empty image to copy pixels into
    val width = image.getWidth
    val height = image.getHeight
    val result = new BufferedImage(width, height, image.getType)

    // copy the pixels over, column-by-column, from top to bottom
    for (column <- 0 until width)
      for (row <- 0 until height)
        result.setRGB(column, row, image.getRGB(column, height - row - 1))

    result
  }

  /** Rotates an image counter-clockwise 90 degrees */
  def rotateLeft(image: BufferedImage): BufferedImage = {

    // create a new, empty image to copy pixels into
    val width = image.getHeight
    val height = image.getWidth
    val result = new BufferedImage(width, height, image.getType)

    // copy the pixels over, column-by-column, while rotating
    for (column <- 0 until width)
      for (row <- 0 until height)
        result.setRGB(column, row, image.getRGB(height - row - 1, column))

    result
  }

  /** Rotates an image clockwise 90 degrees */
  def rotateRight(image: BufferedImage): BufferedImage = {
    // create a new, empty image to copy pixels into
    val width = image.getHeight
    val height = image.getWidth
    val result = new BufferedImage(width, height, image.getType)

    // copy the pixels over, column-by-column, while rotating
    for (column <- 0 until width)
      for (row <- 0 until height)
        result.setRGB(column, row, image.getRGB(row, width - column - 1))

    result
  }

  /** Coverts an image to grayscale */
  def grayScale(image:BufferedImage): BufferedImage = {
    // create a new, empty image to copy pixels into
    val width = image.getWidth
    val height = image.getHeight
    val result = new BufferedImage(width, height, image.getType)

    // copy the pixels over, column-by-column, grayscaling each pixel
    for (column <- 0 until width)
      for (row <- 0 until height) {
        // grayscale the pixel by taking the average of the red, green, and
        // blue parts of the pixel
        val pixel = new Color(image.getRGB(column, row))
        val gray: Int =
          Math.round((pixel.getRed + pixel.getGreen + pixel.getBlue) / 3f)
        val newPixel = new Color(gray, gray, gray)
        result.setRGB(column, row, newPixel.getRGB)
      }

    result
  }

  def bisectHorizontally(image: BufferedImage): BufferedImage = {
    // create a new, empty image to copy pixels into
    val width = image.getWidth/2
    val height = image.getHeight
    val result = new BufferedImage(width, height, image.getType)
    for (column <- 0 until width)
      for (row <- 0 until height){
        result.setRGB(column, row, image.getRGB(column, row))
      }
    result
  }

  def bisectVertically(image: BufferedImage): BufferedImage = {
    // create a new, empty image to copy pixels into
    val width = image.getWidth
    val height = image.getHeight/2
    val result = new BufferedImage(width, height, image.getType)
    for (column <- 0 until width)
      for (row <- 0 until height){
        result.setRGB(column, row, image.getRGB(column, row))
      }
    result
  }
// I'm not entirely sure as to whether or not the condition implemented for the border pixels for the blur function is correct.

  def blur(image:BufferedImage): BufferedImage = {
    val height = image.getHeight
    val width = image.getWidth
    val result = new BufferedImage(width, height, image.getType)
    for (column <- 0 until width)
      for (row <- 0 until height){
        if (column != 0 && column != width && row != 0 && row != height){
          val blurPixel = Math.round((image.getRGB(column, row) + image.getRGB(column + 1, row) + image.getRGB(column - 1, row) + image.getRGB(column, row +1) + image.getRGB(column, row - 1))/5)
          result.setRGB(column, row, blurPixel)
        }else {
          result.setRGB(column, row, image.getRGB(column, row))}
      }
    result
  }
  /*****************************************************************************
    * Helper functions
    *
    * You shouldn't need to modify anything below this line (unless you want
    * to change the names of some functions).
    ***************************************************************************/

  /**
    * Given a path to an image file, loads an image from that file
    * @param filename the path to the image file
    * @return a `BufferedImage` object that contains the image data loaded from
    *         the file
    * @throws FileNotFoundException if the file doesn't exist
    * @throws IllegalArgumentException if the file doesn't contain an image
    */
  def loadImage(filename: String): BufferedImage = {
    loadImage(new FileInputStream(filename))
  }

  /**
    * Given an input stream that contains image data, loads the image data and
    * returns it as a `BufferedImage`.
    * @return a `BufferedImage` object that contains the image data
    * @throws IllegalArgumentException if the file doesn't contain an image
    */
  def loadImage(inputStream: InputStream): BufferedImage = {
    val image = ImageIO.read(inputStream)

    // if the file is not an image file, then image is null (which is bad)
    require(image != null, "The file is not an image")

    // create a `BufferedImage` object from the image data
    val bufferedImage =
      new BufferedImage(image.getWidth, image.getHeight, image.getType)
    bufferedImage.setData(image.getData)
    bufferedImage
  }

  /**
    * Given a `BufferedImage` object and path to a file, saves the image to the
    * `filename`. By default, the image format is PNG
    * @param format "png" by default
    * @return true if the file was successfully written
    * @throws IllegalArgumentException if any of the arguments are `null`
    * @throws IOException if there's an error writing the file
    */
  def saveImage(image: BufferedImage, filename: String,
                format: String = "png"): Boolean = {
    ImageIO.write(image, format, new File(filename))
  }
}
