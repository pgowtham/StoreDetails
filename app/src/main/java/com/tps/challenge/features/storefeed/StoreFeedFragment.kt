package com.tps.challenge.features.storefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tps.challenge.R
import com.tps.challenge.TCApplication
import com.tps.challenge.ViewModelFactory
import com.tps.challenge.viewmodel.StoreFeedViewModel
import javax.inject.Inject

/**
 * Displays the list of Stores with its title, description and the cover image to the user.
 */
class StoreFeedFragment : Fragment() {
    companion object {
        const val TAG = "StoreFeedFragment"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<StoreFeedViewModel>

    private val viewModel: StoreFeedViewModel by lazy {
        viewModelFactory.get<StoreFeedViewModel>(requireActivity())
    }
    private lateinit var storeFeedAdapter: StoreFeedAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var swipeRefreshLayout : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        TCApplication.getAppComponent().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_feed, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
        // Enable if Swipe-To-Refresh functionality will be needed
        swipeRefreshLayout.isEnabled = false

        viewModel.getRecipeDetails()



        storeFeedAdapter = StoreFeedAdapter()
        recyclerView = view.findViewById(R.id.stores_view)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            // TODO uncomment the line below whe Adapter is implemented
             adapter = storeFeedAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the storeData LiveData
        viewModel.storeData.observe(viewLifecycleOwner, Observer { storeData ->
            // Update your RecyclerView or UI with the storeData
          //  storeFeedAdapter.updateData(storeData)
        })

        // Observe the error LiveData
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            // Handle and display the error
            showError(error)
        })


        viewModel.recipeData.observe(viewLifecycleOwner, Observer { recipeData ->
            // Update your RecyclerView or UI with the storeData
            storeFeedAdapter.updateData(recipeData)
        })

        // Observe the error LiveData
        viewModel.errorRecipe.observe(viewLifecycleOwner, Observer { error ->
            // Handle and display the error
            showError(error)
        })


    }

    private fun showError(errorMessage: String) {
        // Implement error handling logic here, e.g., show a Toast or display an error message on the screen.
    }

}
