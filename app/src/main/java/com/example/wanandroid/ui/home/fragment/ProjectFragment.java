package com.example.wanandroid.ui.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.databinding.FragmentProjectBinding;
import com.example.wanandroid.entity.Project;
import com.example.wanandroid.ui.user.adapter.ProjectFragmentBaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengfeng
 * @data 2023/4/20
 * @description
 */
public class ProjectFragment extends BaseFragment {

    private ProjectFragmentBaseAdapter adapter;

    private FragmentProjectBinding binding;

    private List<Project> projectList
            = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {
        //设置item布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recycleView.setLayoutManager(linearLayoutManager);
        //baseAdapter
        adapter = new ProjectFragmentBaseAdapter(R.layout.item_project, projectList);
        //设置不同数据发生改变
        adapter.setDiffCallback(new ProjectFragmentBaseAdapter.DiffEventCallback());

        binding.recycleView.setAdapter(adapter);

    }
}
