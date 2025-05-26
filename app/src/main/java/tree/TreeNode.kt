package tree


/**
 * Node с названием, детьми и ссылкой на родителя
 */
class TreeNode<T>(
    val name: T
) {
    private val _children: MutableList<TreeNode<T>> = mutableListOf()
    var parent: TreeNode<T>? = null
        private set

    val children: List<TreeNode<T>>
        get() = _children.toList()

    fun addChild(child: TreeNode<T>) {
        child.parent = this
        _children.add(child)
    }
}