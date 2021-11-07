package com.hedgehog.gdzietabiedra.ui.list

import android.os.Bundle
import android.view.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.github.asvid.biedra.domain.Shop
import com.hedgehog.gdzietabiedra.ui.styles.BiedraTheme
import org.koin.androidx.viewmodel.compat.ViewModelCompat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.hedgehog.gdzietabiedra.R
import timber.log.Timber

class ListJetpack : Fragment() {

    private val vm: ListViewModel by ViewModelCompat.viewModel(this, ListViewModel::class.java)

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_view_menu, menu)
    }

    override fun onStart() {
        Timber.d("on start")
        vm.loadData()
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView")
        setHasOptionsMenu(true)
        return ComposeView(requireContext()).apply {
            setContent {
                BiedraTheme {
                    ListContent(
                        vm.shopList
                    )
                }
            }
        }
    }

    @Composable
    fun ListContent(
        liveData: LiveData<List<Shop>>
    ) {

        Timber.d("${liveData.value}")
        Timber.d("${liveData.observeAsState()}")

        val shops: List<Shop>? by liveData.observeAsState()
        val listState = rememberLazyListState()

        if (shops?.isEmpty() != false) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colors.onSecondary,
                    strokeWidth = 2.dp
                )
            }
        } else {
            LazyColumn(state = listState, horizontalAlignment = Alignment.CenterHorizontally) {
                itemsIndexed(items = shops!!) { _, shop ->
                    Text("${shop.name}")
                }
            }
        }
    }
}