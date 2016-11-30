package com.dl.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dl.news.NewDateialActivity;
import com.dl.news.R;
import com.dl.news.adapter.SocialListAdapter;
import com.dl.news.bean.SocietyNewBean;
import com.dl.news.util.CommentUtils;
import com.dl.news.util.HttpUtil;
import com.dl.news.view.SocietyHeaderView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMeiTuanRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * Created by Administrator on 2016/10/31.
 */
public class SocialFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.lv_data)
    ListView lvData;
    @BindView(R.id.rl_modulename_refresh)
    BGARefreshLayout rlModulenameRefresh;
    private List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> data;
    private int currentPager = 1;
    private SocialListAdapter listAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_social, container, false);
        ButterKnife.bind(this, view);
        initRefreshLayout();
        initData(false);
        setListener();
        return view;
    }

    private void setListener() {
        lvData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SocialListAdapter.ViewHolder holder = (SocialListAdapter.ViewHolder) view.getTag();
                listAdapter.saveClickItem(position);
                listAdapter.notifyDataSetChanged();
                Intent intent = new Intent(getActivity(),NewDateialActivity.class);
                intent.putExtra("url",holder.url);
                startActivity(intent);
            }
        });
    }

    private void initData(final boolean isRefresh) {
        HttpUtil httpUtil = new HttpUtil();
        HashMap<String, String> param = new HashMap<>();
        param.put("channelId", "5572a108b3cdc86cf39001d5");
        param.put("channelName", "娱乐焦点");
        param.put("needContent", "0");
        param.put("needHtml", "0");
        param.put("needAllList", "0");
        httpUtil.RequstNet(getActivity(), CommentUtils.URL_SOCIETY_NEWS, param).callBack(new HttpUtil.onResponseListener() {



            @Override
            public void onFailure() {
                Toast.makeText(getActivity(), "访问网络失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void succeed(String json) {
                Gson gson = new Gson();
                SocietyNewBean societyNewBean = gson.fromJson(json, SocietyNewBean.class);
                List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = societyNewBean.showapi_res_body.pagebean.contentlist;
                if (isRefresh) {
                    //data.clear();
                    data.addAll(contentlist);
                    listAdapter.clear();
                    listAdapter.notifyDataSetChanged();
                    rlModulenameRefresh.endRefreshing();
                } else {
                    data = contentlist;
                    listAdapter = new SocialListAdapter(getActivity(), data);
                    lvData.setAdapter(listAdapter);
                }
            }
        });
    }

    private void initRefreshLayout() {
        rlModulenameRefresh.setDelegate(this);
        BGAMeiTuanRefreshViewHolder meiTuanRefreshViewHolder = new BGAMeiTuanRefreshViewHolder(getActivity(), true);
        meiTuanRefreshViewHolder.setPullDownImageResource(R.mipmap.bga_refresh_mt_pull_down);
        meiTuanRefreshViewHolder.setChangeToReleaseRefreshAnimResId(R.drawable.bga_refresh_mt_change_to_release_refresh);
        meiTuanRefreshViewHolder.setRefreshingAnimResId(R.drawable.bga_refresh_mt_refreshing);

        rlModulenameRefresh.setRefreshViewHolder(meiTuanRefreshViewHolder);
        rlModulenameRefresh.setCustomHeaderView(new SocietyHeaderView().getCustomHeaderView(getActivity()), true);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        initData(true);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return loadMore();
    }

    private boolean loadMore() {
        if (currentPager >= 5) {
            Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            HttpUtil httpUtil = new HttpUtil();
            HashMap<String, String> param = new HashMap<>();
            param.put("channelId", "5572a108b3cdc86cf39001d5");
            param.put("channelName", "娱乐焦点");
            param.put("needContent", "0");
            param.put("needHtml", "0");
            param.put("needAllList", "0");
            param.put("page", "" + (++currentPager));
            httpUtil.RequstNet(getActivity(), CommentUtils.URL_SOCIETY_NEWS, param).callBack(new HttpUtil.onResponseListener() {

                @Override
                public void onFailure() {
                    Toast.makeText(getActivity(), "访问网络失败", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void succeed(String json) {
                    Gson gson = new Gson();
                    SocietyNewBean societyNewBean = gson.fromJson(json, SocietyNewBean.class);
                    List<SocietyNewBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = societyNewBean.showapi_res_body.pagebean.contentlist;
                    data.addAll(data.size(),contentlist);
                    listAdapter.notifyDataSetChanged();
                    rlModulenameRefresh.endLoadingMore();
                }
            });
            return true;
        }
    }
}
