import SubsecuenciaMasLarga._

val e1 = subindices(0,3)

val e2 = subSecuenciaAsoc(Seq(2,4,1), Seq(0,2))

val e3 = subSecuenciasDe(Seq(2,4,1))

val e4 = incremental(Seq(1,2))

val e5= subSecuenciasInc(Seq(2,4,1))

val e6 = ssimlComenzandoEn(0,Seq(2,4,1,5,3,50,90,6))

val s: Secuencia = List(20, 30, 10, 40, 15, 16, 17)

val e7 = subsecuenciaIncrementalMasLarga(s)

val e8 = ssimlComenzandoEn(4,Seq(10,9,8,7,6,5,4,3,2,1,22,21,20,19,18,17,16,15,14,13,12,11))

val e9 = subSecIncMasLargaV2(Seq(10,9,8,7,6,5,4,3,2,1,22,21,20,19,18,17,16,15,14,13,12,11))
