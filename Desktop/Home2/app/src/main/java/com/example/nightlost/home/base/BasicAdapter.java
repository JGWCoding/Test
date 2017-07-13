package com.example.nightlost.home.base;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.nightlost.home.conf.ThreadManager;
import com.example.nightlost.home.utils.UiUtil;

import java.util.List;
/**
 * 数据适配器
 * */
public abstract class BasicAdapter<T> extends BaseAdapter {
    public static final int TYPE_LOADMORE = 0;
    public static final int TYPE_NORMAL = 1;
    public static final int TYPE_TITLE = 2;

    /**
     * 指定的position对应的条目的type,默认是1，注意是自然排序0,1,2,。。
     */
    public int getItemViewType(int position) {
        if(position == getCount() -1){
            return TYPE_LOADMORE;
        }else {
            //有可能是title类型
            return getNormalType(position);
//            return TYPE_NORMAL;
        }
    }

    protected int getNormalType(int position) {
        return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        //多了一个新的类型：加载更多
        return super.getViewTypeCount() + 1;
    }

    private List<T> mDatas;

    public BasicAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        if(mDatas!=null)
        {
            return mDatas.size() + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(mDatas!=null)
        {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 第一个是把加载更多当成普通的条目，（不适用footview)
     * 第二个是最后一个条目露出一点的时候去请求服务器数据，也就是调用getview()
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        if(convertView == null){
            //1.创建holder控制器  Fragment.getView();
            if(position == getCount()-1){
                //给加载更多做一个单独的holder
                holder = new LoadMoreHolder();
//                holder = getLoadMoreHolder();
            }else {
                holder = getItemHolder(position);
            }
            //2.填充布局
            convertView = holder.inflateAndFindView();
//            convertView = View.inflate(UiUtil.getContext(), R.layout.home_item_temp,null);
            //3.查找子view,注意子view的引用归holder来管理
//              holder.findChildrenView();
//            holder.tv = (TextView) convertView.findViewById(R.id.tv_home_item_temp);
            //4.view和控制器绑定在一起
            convertView.setTag(holder);

        }else{
            holder = (BaseHolder) convertView.getTag();
        }
        //5.填充数据
        //TODO:最后一个也要设置数据
        if(position == getCount()-1){
            //TODO:加载更多
            //因为数据是固定的，我们就更新状态
            if(holder instanceof LoadMoreHolder) {
                //判断当前页面是否需要加载更多的功能
                if(supportLoadMore()) {
                    loadMore((LoadMoreHolder) holder);
                }else{
                    //不支持加载更多的页面，设置state为none
                    ((LoadMoreHolder) holder).setData(LoadMoreHolder.STATE_NO_MORE);
                }
            }else{
                Log.e("loadmore", "holder的类型错乱了");
            }
        }else {
            T t = mDatas.get(position);
            holder.setData(t);
        }
        return convertView;
    }

    protected boolean supportLoadMore() {
        return false;
    }

    private BaseHolder getLoadMoreHolder() {
        if(mHolder == null){
            mHolder = new LoadMoreHolder();
        }
        return mHolder;
    }

    private LoadMoreHolder mHolder;

    private boolean isLoadingMore = false;
    private void loadMore(LoadMoreHolder holder) {
        if(isLoadingMore){
            return;
        }
        isLoadingMore = true;
        holder.setData(LoadMoreHolder.STATE_LOADING_MORE); //开启转圈圈效果
        ThreadManager.getNormalPool().execute(new LoadMoreTask(holder));
    }

    protected abstract BaseHolder getItemHolder(int postion);

    private class LoadMoreTask implements Runnable {
        private final LoadMoreHolder holder;

        public LoadMoreTask(LoadMoreHolder holder) {
            this.holder = holder;
        }

        @Override
        public void run() {
            int state = LoadMoreHolder.STATE_LOADING_MORE; //开启转圈圈效果
            //1.注意返回值，必须要拿到数据
            List<T> moreDatas = null;
            try {
                moreDatas = getMoreData();
                //2.把更多的数据添加到mDatas里
                //TODO:最后一页不满页的情况要处理
                if(moreDatas!=null && moreDatas.size()>0) {
                    mDatas.addAll(moreDatas);
                    if(moreDatas.size()<20){
                        //d.和b一样的没有更多数据，有余数，最后一页为余数 101页，第6页是只有一个
                        state = LoadMoreHolder.STATE_NO_MORE;
                    }
                }else{
                    //b.服务器连接成功没有更多数据了 100 --- 5页
                    state = LoadMoreHolder.STATE_NO_MORE;
                    //TODO:c.缓存导致未null
                }
            } catch (Exception e) {
                e.printStackTrace();
                //抓到这些异常
                state = LoadMoreHolder.STATE_RETRY;
                //a.TODO:异常的null情况，服务器没连上
            }
            //3.再次更新UI,刷新adapter---->还要改变状态（关闭转圈圈）
            final int finalState = state;
            final List<T> finalMoreDatas = moreDatas;
            UiUtil.post(new Runnable() {
                @Override
                public void run() {
                    //数据变化了才修改
                    if(finalMoreDatas !=null) {
                        notifyDataSetChanged();
                    }
//                    int state = LoadMoreHolder.STATE_NO_MORE;
                    holder.setData(finalState);
                    isLoadingMore = false;
                }
            });
        }
    }

    protected abstract List<T> getMoreData() throws Exception;
}