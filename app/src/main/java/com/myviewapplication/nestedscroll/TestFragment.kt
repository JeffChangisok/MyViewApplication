package com.myviewapplication.nestedscroll

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.myviewapplication.databinding.FragmentTestBinding
import com.myviewapplication.databinding.TestRVAdapter

/**
 * Created by Jeff on 2021/9/22
 */
class TestFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTestBinding.inflate(inflater, container, false)
        binding.adapter = TestRVAdapter()
        return binding.root
    }
}