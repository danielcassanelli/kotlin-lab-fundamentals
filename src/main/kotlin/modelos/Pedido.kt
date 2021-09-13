package modelos

class Pedido(val produto: Produto) {
    fun nomeProduto(): String {
        return produto.nome
    }


    override fun toString(): String = "Produto:".plus(produto.codigo).plus(" | ").plus(produto.nome)


}