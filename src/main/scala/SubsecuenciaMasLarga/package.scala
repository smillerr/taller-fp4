import scala.annotation.tailrec

package object SubsecuenciaMasLarga {
  type Secuencia = Seq[Int]
  type Subsecuencia = Seq[Int]

  def subindices(i: Int, n: Int): Set[Subsecuencia] = {

    @tailrec
    def subindicesAux(i: Int, n: Int, resultados: Set[Subsecuencia]): Set[Subsecuencia] = {

      if (i >= n) {
        resultados
      } else {

        val nuevosResultados =
          for {
            secuencia <- resultados
          } yield {
            secuencia :+ i
          }

        subindicesAux(i + 1, n, resultados ++ nuevosResultados)
      }
    }

    subindicesAux(i, n, Set(Seq.empty)
    )
  }

  def subSecuenciaAsoc(s: Secuencia, inds: Seq[Int]): Subsecuencia = {
    val asoc = for{
      i <- inds
    }
    yield {
      s(i)
    }
    asoc
  }

  def subSecuenciasDe(s: Secuencia): Set[Subsecuencia] = {
    // Generar todos los subíndices posibles
    val n = s.length
    val todosLosSubindices = subindices(0, n)

    val subsecuenciasAsoc = for {
      subindice <- todosLosSubindices
    } yield {
      subSecuenciaAsoc(s, subindice)
    }
    subsecuenciasAsoc
  }

  def incremental(subsecuencia: Subsecuencia): Boolean = {
    // Función auxiliar recursiva para verificar si la subsecuencia es incremental
    @tailrec
    def esIncrementalRec(subseq: Subsecuencia): Boolean = subseq match {
      case Seq() => true
      case Seq(_) => true
      case a +: b +: rest if a <= b => esIncrementalRec(b +: rest)
      case _ => false
    }

    esIncrementalRec(subsecuencia)
  }
  def subSecuenciasInc(s:Secuencia): Set[Subsecuencia] = {
    val n = s.length
    val todosLosSubindices = subindices(0, n)

    val subsecuenciasInc = for {
      subindice <- todosLosSubindices
      if(incremental(subSecuenciaAsoc(s,subindice)))
    } yield {
      subSecuenciaAsoc(s,subindice)
    }
    subsecuenciasInc
  }
/*
  def ssimlComenzandoEn(i: Int, s: Secuencia): Subsecuencia = {

    def ssimlAux(i: Int): Subsecuencia = {
      if (i >= s.length) Seq()
      else {
        val mayores = s.slice(i + 1, s.length).filter(_ > s(i))
        val mejores = mayores.map(ssimlAux)
        val mejor = if (mejores.isEmpty) Seq() else mejores.maxBy(_.length)
        s(i) +: mejor
      }
    }

    ssimlAux(i)
  }
  */
def ssimlComenzandoEn(i: Int, s: Secuencia): Subsecuencia = {

  def ssimlAux(i: Int): Subsecuencia = {

    if (i >= s.length) Seq()
    else {
      val mayores = for {
        j <- i + 1 until s.length
        if s(j) > s(i)
      } yield ssimlAux(j)

      val mejor = if (mayores.isEmpty) Seq() else mayores.maxBy(_.length)
      s(i) +: mejor
    }
  }

  ssimlAux(i)
}


  def subSecIncMasLargaV2(s: Secuencia): Subsecuencia = {
    val subsecuencias = for {
      i <- 0 until s.length
    } yield ssimlComenzandoEn(i, s)
    subsecuencias.maxBy(_.length)
  }

}
