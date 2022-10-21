package lab06

import lab04.Main.Empty

// BMP images
object Matrix {
  // define your functions here
  type Img = List[List[Int]]

  def show(m: Img): String = {
    def showLine(l: List[Int]): String = {
      l.foldRight("")((elem, acc) => acc + elem + " ") + "\n"
    }

    m.foldRight("")((elem, acc) => acc + showLine(elem))
  }

  def hFlip(img: Img): Img = {
    img.foldLeft(List[List[Int]]())((acc: List[List[Int]], elem: List[Int]) => acc.::(elem))
  }

  def vFlip(img: Img): Img = {
    img.foldLeft(List[List[Int]]())((acc, elem) => (elem.reverse) :: acc)
  }

  def transpose (m: Img): Img =
    m match {
      case Nil :: _ => Nil
      case _ => m.map(_.head) :: transpose(m.map(_.tail))
    }

  def rot90Right(img: Img): Img = transpose(vFlip(img))
  def rot90Left(img: Img): Img = transpose(img)
  def inverse(img: Img): Img = img.map(elem => elem.map(x => 255 - x))

  def cropAt(img: Img, xSt:Int, ySt:Int, xEnd: Int, yEnd: Int): Img = {
    img.drop(ySt).take(yEnd - ySt + 1).map(elem => elem.drop(xSt).take(xEnd - xSt + 1))
  }

  def largerPos(img: Img, int: Int): List[(Int,Int)] = {
    var i: Int = -1
    var j: Int = -1
    for(l <- img)
      yield {
        i = i + 1
        for(x <- l)
          yield {
            j = j + 1
            if(x > int) (i, j)
            else Nil
          }
      }

  }

  def main(args: Array[String]) = {
    val img = List(List(0,0,1,0,0), List(0,1,0,1,0), List(0,1,1,1,0), List(1,0,0,0,1), List(1,0,0,0,1))

    // Test show
    printf("Image: \n" + show(img))

    // Test hFLip
    printf("HFlip: \n" + show(hFlip(img)))

    // Test vFLip
    printf("VFlip: \n" + show(vFlip(img)))

    // Test rot90Right
    printf("Rotate90Right: \n" + show(rot90Right(img)))

    // Test rot90Left
    printf("Rotate90Left: \n" + show(rot90Left(img)))
  }
}

