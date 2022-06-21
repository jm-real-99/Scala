
/*
//Suma
def add(x:Int,y:Int):Int=
  x+y

//Suma con valor
def addV:(Int,Int)=>Int =
  (x,y)=>x+y

//Suma con valor currificada
def addVC: Int =>(Int=>Int) =
  x=>y => x+y


//Ver si el tamaño de una palabra es par
def isEven(s:String):Boolean =
  s.length %2 == 0

isEven("abcd") //True
isEven("abc") //False

def length(s:String):Int=
  s.length

//COMPOSICIÓN DE FUNCIONES

def compose(g:Int => Boolean, f:String=> Int): String =>Boolean =
  (s:String) => g(f(s)) : Boolean


//Definimos la función "isEvenLength" utilizando la funcion length y isEven, así empleamos la
//composición de funciones
val isEvenLength:String => Boolean = {
  compose(isEven,length)
}


//Otr a manera de realizarlo
val isEvenLengthOther:String =>Boolean=
  ((_:String).length) andThen ??? //TErminar, no me ha dado tiempo a copiar

//Esto nos permite polimorfismo, ya que podremos usar esta función con distintos tipos
def compose[A,B,C](f2:B=>C , f1: A=>B): A=>C =
    (a:String)=>f2(f1(a))

//Currificado, es decir,
def composeCurri[A,B,C]: (B=>C,A=>B) => (A=>C) =
  (f2,f1) => (a:A) => f2(f1(a))


//CLASES

class Bicycle(startCadence: Int, startSpeed: Int, startGear: Int){
  var cadence: Int = startCadence
  var speed:Int = startSpeed
  var gear: Int = startGear

  def this()=
    this(0,0,1)

  def changeCadence(newValue:Int):Unit={
    cadence=newValue
  }

}

var bike1: Bicycle = new Bicycle(1,2,3)
var bike2: Bicycle = new Bicycle()

bike1.changeCadence(6)
bike2 changeCadence 10
*/

/*
//Funciones como valores
def inc(number:Int):Int=
  number+1

val incV:Int => Int=
  (a:Int)=>a+1

inc(5)
incV(5)

 */

/*
//Azuquitar sintáctico de Funciones como valores
val incVMEjor: Int => Int=
  _+1

incVMEjor(5)

val ejemplo:(Int,Int)=> Int=
  _*_

ejemplo(4,2)

 */

//CURRIFICACIÓN
val sumV:(Int,Int)=>Int=
  (a,b)=>a+b

sumV(3,2)

val sumVs:(Int,Int)=>Int=
  _+_

sumVs(3,2)


//HOJA 1 EJER 5
def call[A,B](f:A=>B)(a:A):B=
  f(a)

call[Int,Boolean](_%2==0)(3)
call[Double,Int](_.toInt)(2.034)

//HOJA 2 EJER2
def f1(e: Either[Unit, Either[Unit, Unit]]): Boolean =
  e match{
    case Left(())=> true
    case Right(x)=>
      x match {
        case Left(())=>true
        case Right(())=>false
      }
 }

lazy val f1: Either[Unit, Either[Unit, Unit]] => Boolean =
    _ match{
      case Left(())=> true
      case Right(x)=>
        x match {
          case Left(())=>true
          case Right(())=>false
        }
    }

//HOJA 2 Ejer 3
def f1(b: Boolean): Either[Unit, Either[Unit, Unit]] =
  if(b) Left() //Left(Unit)
  else Right(Right()) //Right(Right(Unit))
def f2(b: Boolean): Either[Unit, Either[Unit, Unit]] =
  if(b) Left()
  else Right(Left())
def f3(b: Boolean): Either[Unit, Either[Unit, Unit]] =
  if(!b) Left()
  else Right(Right())
def f3(b: Boolean): Either[Unit, Either[Unit, Unit]] =
  if(!b) Left()
  else Right(Left())

//HOJA 2 Ejer 4
def curry[X,Y,Z](f:(X,Y)=>Z):X=>Y=>Z=
  (x:X)=>(y:Y)=>f(x,y)

def uncurry[X,Y,Z](f:X=>Y=>Z):(X,Y)=>Z=
  (x,y) => f(x)(y)







//HOJA 3 EJER 0
def drop[A](list:List[A],n:Int):List[A] ={

  def dropAux(list: List[A], out: List[A], n: Int): List[A] =
    if (n == 0) out
    else
      list match {
      case Nil => List ()
      case e :: Nil => List ()
      case e :: tail =>
      dropAux (tail, out :+ e, n - 1)
      }

  if (n == 0) list
  else
  dropAux (list, List (), n)
}




drop(List(true, false), 0)
drop(List(true, false, false, true), 2)
drop(List(true, false, true, true, false, true), 3)

drop(List(), 0)
drop(List(true, false, true, true, false, true), 6)
drop(List(), 2)


//HOJA 3 EJER 1

//a)Sin tail-recursion
def ocurrencesR[A](list:List[A],a:A):Int=
  list match{
    case Nil=>0
    case x::tail=>
      if(x==a) 1+ocurrencesR(tail,a)
      else ocurrencesR(tail,a)
  }

//a)tail-recursion
def ocurrencesTR[A](list:List[A],a:A):Int= {
  def ocurrencesTRaux(list:List[A],a:A,out:Int):Int=
    list match{
      case Nil=>out
      case x::tail=>
        if(x==a) ocurrencesTRaux(tail,a,out+1)
        else ocurrencesTRaux(tail,a,out)
    }
  ocurrencesTRaux(list,a,0)
}


ocurrencesR(List("1","1","1"), "1") //shouldBe 3
ocurrencesR(List("1","2","3"), "2") //shouldBe 1
ocurrencesR(List(), "3") //shouldBe 0
ocurrencesR(List("1","2","3"), "5") //shouldBe 0

ocurrencesTR(List("1","1","1"), "1") //shouldBe 3
ocurrencesTR(List("1","2","3"), "2") //shouldBe 1
ocurrencesTR(List(), "3") //shouldBe 0
ocurrencesTR(List("1","2","3"), "5") //shouldBe 0


//HOJA 3 EJER 2
def take[A](list:List[A],n:Int):List[A]=
  list match{
    case Nil=>Nil
    case x::tail=>
      if(n==0)Nil
      else x::take(tail,n-1)
  }

take(List(), 0) //shouldBe List()
take(List(), 5) //shouldBe List()
take(List('1','2','3'), 0) //shouldBe List()
take(List('1','2','3'), 2) //shouldBe List('1','2')
take(List('1','2','3'), 3) //shouldBe List('1','2','3')
take(List('1','2','3'), 10) //shouldBe List('1','2','3')


//HOJA 3 EJER 5
def zip[A, B](list1: List[A], list2: List[B]): List[(A, B)] =
  list1 match{
    case Nil => Nil
    case x::tailx =>
      list2 match{
        case Nil => Nil
        case y::taily =>
          (x,y)::zip(tailx,taily)
      }
  }


zip(List(), List())                   //shouldBe List()
zip(List(), List('a','b'))            //shouldBe List()
zip(List(1,2,3), List())              //shouldBe List()
zip(List(1,2,3), List('a','b','c'))    //shouldBe List((1,'a'), (2,'b'), (3, 'c'))
zip(List(1,2), List('a','b','c'))     //shouldBe List((1,'a'), (2,'b'))
zip(List(1,2,3), List('a','b'))       //shouldBe List((1,'a'), (2,'b'))


//HOJA 3 EJER 7
def concatenate(list: List[Either[String, Int]]): String =
  list match{
    case Nil=>""
    case Left(x)::tail=> x+concatenate(tail)
    case Right(x)::tail => concatenate(tail)
  }



concatenate(List()) //shouldBe ""
concatenate(List(Right(1), Right(2), Right(3))) //shouldBe ""
concatenate(List(Left("hello"), Left(", "), Left("world!"))) //shouldBe "hello, world!"
concatenate(List(Right(1), Left("hello"), Right(2),
  Left(", "), Left("world!"), Right(5))) //shouldBe "hello, world!"


//ED árboles
sealed abstract class Tree[A]
case class Empty[A]() extends Tree[A]
case class Node[A](left: Tree[A], root: A, right: Tree[A]) extends Tree[A]

object Tree{

  def void[A]: Tree[A] =
    Empty()

  def leaf[A](a: A): Node[A] =
    Node(Empty(), a, Empty())

  def right[A](a: A, tree: Tree[A]): Node[A] =
    Node(Empty(), a, tree)

  def left[A](tree: Tree[A], a: A): Node[A] =
    Node(tree, a, Empty())

  def node[A](left: Tree[A], a: A, right: Tree[A]): Node[A] =
    Node(left, a, right)
}

import Tree._

//HOJA 3 EJER 10
def isDegenerate[A](tree: Tree[A]): Boolean =
  tree match{
    case Empty() => true
    case Node(x,_,Empty())=>true && isDegenerate(x)
    case Node(Empty(),_,x) => true && isDegenerate(x)
    case Node(l,_,r) => false

  }

isDegenerate(void) //shouldBe true
isDegenerate(leaf(1)) //shouldBe true
isDegenerate(left(leaf(1), 2)) //shouldBe true
isDegenerate(right(2, leaf(1)))  //shouldBe true
isDegenerate(node(leaf(1), 2, leaf(3))) //shouldBe false
isDegenerate(left(left(leaf(3), 2), 1)) //shouldBe true
isDegenerate(left(right(2, left(right(4, leaf(5)), 3)),1)) //shouldBe true
isDegenerate(left(node(leaf(3), 2, leaf(3)), 1)) //shouldBe false


//HOJA 3 EJER 13
def sum(tree: Tree[(Int, Int)]): Tree[Int] =
  tree match{
    case Empty()=>void
    case Node(l,(x,y),r) => Node(sum(l),x+y,sum(r))
  }

sum(void) //shouldBe void
sum(leaf((1,1))) //shouldBe leaf(2)
sum(left(leaf((1,3)), (2,5))) //shouldBe left(leaf(4), 7)
sum(right((0,2), leaf((-1,2)))) //shouldBe right(2, leaf(1))
sum(left(left(leaf((-3,6)), (2,0)), (-5,6))) //shouldBe left(left(leaf(3), 2), 1)


def foldRight[A, B](list: List[A])(nil: B, cons: (A, B) => B): B =
  list match {
    case Nil => nil
    case head :: tail => cons(head, foldRight(tail)(nil, cons))
  }

//HOJA 5 EJER 1

/*
def concatenate[A](list1:List[A],list2:List[A]):List[A]= {
  def aux[A](x:A,y:A):A= {
    (x,y) match{
      //case(Nil,Nil)=>Nil
      case(Nil,y)=>y
      case(x,_) =>x
    }
  }
  def listaTuplas[A](list1:List[A],list2:List[A]):List[(A,A)]=
    (list1,list2) match{
      case(Nil,Nil)=>Nil
      case(x::tx,y::ty)=>(x,y)::listaTuplas(tx,ty)
    }
  val l=listaTuplas(list1,list2)
  foldRight[List[(A,A)],List[A]](l)(Nil,(h,t)=>aux(h._1,h._2)):List[A]

}
*/
def concatenate[A](list1:List[A],list2:List[A]):List[A]=
  list1.foldRight(list2)((head,tail)=>head::tail):List[A]

concatenate(List(), List()) //shouldBe List()
concatenate(List(1), List()) //shouldBe List(1)
concatenate(List(), List(1)) //shouldBe List(1)
concatenate(List(1,2,3), List(1,3)) //shouldBe List(1,2,3,1,3)

//HOJA 5 EJER 2
def headOption[A](list: List[A]): Option[A] = {
  def headOptionAux[A](x:A):Option[A]=
    x match{
      case Nil=>Option.empty[A]
      case x => Some(x)
    }
  foldRight[A,Option[A]](list:List[A])(None,(x,res)=>headOptionAux(x)):Option[A]
}

headOption(List()) //shouldBe None
headOption(List(1)) //shouldBe Some(1)
headOption(List(1,2,3)) //shouldBe Some(1)

//HOJA 5 EJER 3
def insertLast[A](list: List[A], elem: A): List[A] =
  foldRight[A,List[A]](list:List[A])(elem::Nil,(h,t)=>h::t):List[A]

insertLast(List(), 1) //shouldBe List(1)
insertLast(List(1), 2) //shouldBe List(1,2)
insertLast(List(1,2,3), 4) //shouldBe List(1,2,3,4)

//HOJA 5 EJER 4
def concatenateEither(list: List[Either[String, Int]]): String = {
  def aux(eit:Either[String,Int]):String=
    eit match{
      case Left(x)=>x
      case Right(_)=>""
  }
  foldRight[Either[String,Int],String](list)("",(h,t)=>aux(h)+t):String
}

concatenateEither(List()) //shouldBe ""
concatenateEither(List(Right(1), Right(2), Right(3))) //shouldBe ""
concatenateEither(List(Left("hello"), Left(", "), Left("world!"))) //shouldBe "hello, world!"
concatenateEither(List(Right(1), Left("hello"), Right(2),
  Left(", "), Left("world!"), Right(5))) //shouldBe "hello, world!"



@annotation.tailrec
def foldLeft[A, B](list: List[A])(out: B)(update: (B, A) => B): B =
  list match {
    case Nil => out
    case head :: tail =>
      foldLeft(tail)(update(out, head))(update)
  }


//HOJA 5 EJER 5
def greatestTR(list: List[Int]): Option[Int] = {
  def aux(x: Int, acum: Int): Int =
    if (x>acum) x
    else acum

  if(list.length==0) None
  else Some(foldLeft[Int, Int](list)(-1)((acum, head) => aux(head, acum)): Int)
}


greatestTR(List()) //shouldBe None
greatestTR(List(1,2,3)) //shouldBe Some(3)
greatestTR(List(3,2,1)) //shouldBe Some(3)
greatestTR(List(1)) //shouldBe Some(1)



def map[A, B](list: List[A])(f: A => B): List[B] =
  list match {
    case Nil => Nil
    case head :: tail =>
      f(head) :: map(tail)(f)
  }

def flatMap[A](list: List[A])(f: A => List[A]): List[A] =
  list.map(f).flatten


//HOJA 5 EJER 6
def filter[A](list: List[A])(pred: A => Boolean): List[A] =
  list.filter(pred):List[A]



val isEven: Int => Boolean = _ % 2 == 0
filter(List())(isEven) //shouldBe List()
filter(List(1))(isEven) //shouldBe List()
filter(List(1,3,5))(isEven) //shouldBe List()
filter(List(2,4,6))(isEven) //shouldBe List(2,4,6)


//Ejer examen
/*
def splitR_A[A,B](l:List[(A,B)]):(List[A],List[B])=
  l.foldRight((Nil,Nil)){
    case ((x,y),(a,b))=> (x::a,y::b)
  }

*/

//Prueba ejer exam
def prueba[A](l:List[A]):Int=>Boolean=
  (n:Int)=>
    n<=l.length


prueba(List(1,2,3,4,5,6))(2) //shouldBe True
prueba(List(1,2,3,4,5,6))(20) //shouldBe False
prueba(List(1))(2) //shouldBe False

