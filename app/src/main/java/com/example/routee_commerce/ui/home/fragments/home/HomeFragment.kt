package com.example.routee_commerce.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentHomeBinding

import com.example.routee_commerce.domain.model.Category
import com.example.routee_commerce.domain.model.Product
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.cart.CartViewModel
import com.example.routee_commerce.ui.home.fragments.home.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.ProductsAdapter
import com.example.routee_commerce.ui.productDetails.ProductDetailsActivity
import com.example.routee_commerce.ui.userAuthentication.fragments.login.LoginViewModel
import com.example.routee_commerce.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val categoriesAdapter = CategoriesAdapter()
    private val mostSellingProductsAdapter = ProductsAdapter()
    private val categoryProductsAdapter = ProductsAdapter()
    private val viewModel: HomeViewModel by viewModels()
    @Inject
    lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        cartViewModel.loadCart()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initProductsClicks()
        observeProducts()
        observerCategory()
        observerCart()
    }

    override fun onResume() {
        super.onResume()

    }

    private fun observerCart() {
        cartViewModel.cart.observe(viewLifecycleOwner) {
            showLoading(it == Resource.Loading)
            when (it) {
                is Resource.Error -> {
                    showError(it.error)
                }
                Resource.Loading -> {}
                is Resource.Success -> {
                    mostSellingProductsAdapter.updateCart(it.data)
                }
            }
        }
    }

    private fun initProductsClicks() {
        mostSellingProductsAdapter.addProductToCartClicked = {
            if(mostSellingProductsAdapter.isInCart(it.id!!)){
                cartViewModel.removeCartItem(it.id)
            }else {
                cartViewModel.addCartItem(it.id)
            }

        }
    }

    private fun observerCategory() {
        viewModel.categories.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding!!.categoriesShimmerViewContainer.stopShimmer()
                }

                Resource.Loading -> binding!!.categoriesShimmerViewContainer.startShimmer()
                is Resource.Success -> {
                    binding!!.categoriesShimmerViewContainer.stopShimmer()
                    categoriesAdapter.bindCategories(it.data)
                }
            }
        }
    }

    private fun observeProducts() {
        viewModel.products.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> {
                    binding!!.mostSellingProductsShimmerViewContainer.stopShimmer()
                }

                Resource.Loading -> binding!!.mostSellingProductsShimmerViewContainer.startShimmer()
                is Resource.Success -> {
                    binding!!.mostSellingProductsShimmerViewContainer.stopShimmer()
                    mostSellingProductsAdapter.bindProducts(it.data)
                }
            }
        }
    }

    private fun initViews() {
        categoriesAdapter.categoryClicked = { position, category ->
//            navigateToCategoriesFragment(category)
        }

        binding!!.categoriesRv.adapter = categoriesAdapter
        binding!!.mostSellingProductsRv.adapter = mostSellingProductsAdapter
        binding!!.categoryProductsRv.adapter = categoryProductsAdapter
        binding!!.categoryNameTv.text = getString(R.string.electronics)
    }


    private fun navigateToProductDetailsFragment(product: Product) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(Product.PRODUCT, product)
        startActivity(intent)
    }


    private fun navigateToCategoriesFragment(category: Category) {
//        val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(category)
//        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding!!.unbind()

    }

}
