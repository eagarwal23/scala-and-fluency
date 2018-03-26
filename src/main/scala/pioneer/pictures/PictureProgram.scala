package pioneer.pictures
import pioneer.resource
import Picture.loadImage
object PictureProgram extends App {
  // flip a picture horizontally, grayscale it, and rotate it left
  val image1 = loadImage(resource("/Users/eeshaagarwal/Desktop/scala-and-fluency/src/main/resources/image.png"))
  val finalResult = Picture.rotateLeft(Picture.grayScale(Picture.flipHorizontal(image1)))
  Picture.saveImage(finalResult, "output-01")}
