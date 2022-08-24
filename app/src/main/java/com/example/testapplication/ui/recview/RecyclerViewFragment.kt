package com.example.testapplication.ui.recview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.MainActivity
import com.example.testapplication.ProjectApplication
import com.example.testapplication.databinding.FragmentRecviewBinding
import com.example.testapplication.utils.Status
import dagger.Lazy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecyclerViewFragment : Fragment() {

    private var _binding: FragmentRecviewBinding? = null
    private val binding get() = _binding!!

    private var _adapter: RecViewAdapter? = null
    private val adapter get() = _adapter!!

    @Inject
    lateinit var viewModelFactory : Lazy<RecyclerVViewModel.Companion.RecyclerVViewModelFactory>

    private val viewModel : RecyclerVViewModel by viewModels{viewModelFactory.get()}

    override fun onAttach(context: Context) {
        (requireActivity().application as ProjectApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecviewBinding.inflate(inflater, container, false)
        _adapter = RecViewAdapter(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recView = binding.recView
        val layoutManager = LinearLayoutManager(requireContext())
        recView.layoutManager = layoutManager
        recView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.getResponse().collect {resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        adapter.setRequestsData(resource.data!!.articles)
                    }
                    Status.ERROR -> {
                        Toast.makeText(requireContext(), resource.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        adapter.setRequestsData(emptyList())
                    }
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }
}