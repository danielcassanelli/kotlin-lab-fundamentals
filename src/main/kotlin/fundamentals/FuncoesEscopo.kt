import modelos.Pedido
import modelos.Produto
import java.math.BigDecimal

fun main() {

    //--

    println("Android".run {
        plus("AndKotlin")
            .plus("Nice")
    }.plus("Run"))

    println("Android".apply {
        plus("AndKotlin")
            .plus("Nice")
        println(this)
    }.plus("Apply"))

    println("Android".also {
        it.plus("AndKotlin")
            .plus("Nice")
        println(it)
    }.plus("Also"))

    //--

    println(BigDecimal.ZERO.run {
        add(BigDecimal(1))
    }.add(BigDecimal(1)))

    println(BigDecimal.ZERO.apply {
        add(BigDecimal(1))
        println(this)
    }.add(BigDecimal(1)))

    println(BigDecimal.ZERO.also {
        it.add(BigDecimal(1))
        println(it)
    }.add(BigDecimal(1)))

    //--

    println(Pedido(Produto(0L, "produto um")).run {
        produto.codigo = 1
        produto.nome = "Produto alterado"
    })

    println(Pedido(Produto(0L, "produto um")).apply {
        produto.codigo = 1
        produto.nome = "Produto alterado"
    })

    println(Pedido(Produto(0L, "produto um")).also {
        it.produto.codigo = 1
        it.produto.nome = "Produto alterado"
    })



    // --
    lateinit var number: BigDecimal
    val n = BigDecimal(-1).also {
        number = it
    }.abs()
    println(number)
    println(n)
    //--
}