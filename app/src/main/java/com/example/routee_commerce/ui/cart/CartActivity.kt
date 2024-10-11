package com.example.routee_commerce.ui.cart

import android.os.Bundle
import com.example.routee_commerce.databinding.ActivityCartBinding
import com.example.routee_commerce.domain.model.Cart
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.ui.base.BaseActivity
import com.example.routee_commerce.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CartActivity : BaseActivity<ActivityCartBinding>() {
    val adapter = CartAdapter()
    @Inject
    lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initViews()
        observerCart()
        initAdapter()
    }

    private fun initAdapter() {
        adapter.onCartItemClick = object : CartAdapter.OnCartItemClick{
            override fun increase(product: Product) {
                cartViewModel.updateCartQuantity(product.id!!, product.totalItemsInCart!! + 1)

            }

            override fun decrease(product: Product) {
                cartViewModel.updateCartQuantity(product.id!!, product.totalItemsInCart!! - 1)
            }

            override fun remove(product: Product) {
               cartViewModel.removeCartItem(product.id!!)
            }

        }
    }

    private fun observerCart() {
        cartViewModel.cart.observe(this){
            showLoading(it == Resource.Loading)
            when(it){
                is Resource.Error -> {
                    showError(it.error)
                }
                Resource.Loading -> {}
                is Resource.Success -> {
                    bindCart(it.data)
                }
            }
        }
    }

    private fun bindCart(cart: Cart) {
        adapter.bindCartItemsList(cart.productsList)
        binding!!.content.cartTotalPriceTv.text = "${cart.totalCartPrice} EGP"
    }

    private fun initViews() {
        binding!!.content.cartRv.adapter = adapter
        binding!!.backArrow.setOnClickListener {
            finish()
        }

    }
}
