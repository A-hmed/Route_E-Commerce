package com.example.routee_commerce.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.routee_commerce.databinding.ItemCartBinding
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.utils.BindingAdapters

class CartAdapter(private var cartItemsList: List<Product>? = null) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(val itemCartBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(itemCartBinding.root) {

        fun bind(cartItem: Product) {
            BindingAdapters.bindImage(itemCartBinding.itemImage, cartItem.imageCover)
            itemCartBinding.itemTitle.text = cartItem.title
            itemCartBinding.productPrice.text = "${cartItem.price} EGP"
            itemCartBinding.totalItemsInCart.text = "${cartItem.totalItemsInCart}"
            itemCartBinding.colorName.text = cartItem.description
            itemCartBinding.increaseQuantityButton.setOnClickListener {
                onCartItemClick?.increase(cartItem)
            }
            itemCartBinding.decreaseQuantityButton.setOnClickListener {
                onCartItemClick?.decrease(cartItem)
            }
            itemCartBinding.removeFromCartButton.setOnClickListener {
                onCartItemClick?.remove(cartItem)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCartBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = cartItemsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cartItemsList!![position]
        holder.bind(product)

    }

    fun bindCartItemsList(cartItemsList: List<Product>) {
        this.cartItemsList = cartItemsList
        notifyDataSetChanged()
    }

    var onCartItemClick: OnCartItemClick? = null
    interface OnCartItemClick{
        fun increase(product: Product)
        fun decrease(product: Product)
        fun remove(product: Product)
    }

}
