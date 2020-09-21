package com.example.crazyshopping.ui.home.activity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.crazyshopping.R;
import com.example.crazyshopping.base.BaseActivity;
import com.example.crazyshopping.bean.AddCartInfoBean;
import com.example.crazyshopping.bean.HomeHotBean;
import com.example.crazyshopping.common.CartCustomView;
import com.example.crazyshopping.interfaces.home.hot.IHot;
import com.example.crazyshopping.interfaces.home.hot.IHot.IHotView;
import com.example.crazyshopping.persenter.home.hot.HotPersenter;
import com.example.crazyshopping.ui.home.adapter.HotdetailsAdapter;
import com.example.crazyshopping.utils.SpUtils;
import com.example.crazyshopping.utils.SystemUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HotdetailsActivity extends BaseActivity<IHot.IHotPersenter> implements IHotView {


    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_count)
    TextView txtCount;
    private String html = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                $\n" +
            "            </body>\n" +
            "        </html>";

    @BindView(R.id.layout_back)
    RelativeLayout layoutBack;
    @BindView(R.id.txt_TITLE)
    TextView txtTitle1;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_product)
    TextView txtProduct;
    @BindView(R.id.layout_norms)
    FrameLayout layoutNorms;
    @BindView(R.id.layout_comment)
    FrameLayout layoutComment;
    @BindView(R.id.shijian)
    TextView shijian;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.layout_imgs)
    LinearLayout layoutImgs;
    @BindView(R.id.layout_parameter)
    LinearLayout layoutParameter;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.rcy)
    RecyclerView rcy;
    @BindView(R.id.layout_collect)
    RelativeLayout layoutCollect;
    @BindView(R.id.img_cart)
    ImageView imgCart;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.pinglun_image)
    ImageView pinglunimage;
    @BindView(R.id.pinglun_title)
    TextView pingluntitle;
    @BindView(R.id.layout_bottom)
    LinearLayout layoutBottom;
    @BindView(R.id.cart)
    TextView cart;

    int type;
    private ArrayList<HomeHotBean.DataBeanX.InfoBean> list;
    private HotdetailsAdapter adapter;

    @Override
    protected void initData() {
        int intent = getIntent().getIntExtra("id", 0);
        persenter.getHot(intent);
    }

    @Override
    protected IHot.IHotPersenter initPersenter() {
        return new HotPersenter();
    }

    @Override
    protected void initView() {
        ImageView image = findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcy.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new HotdetailsAdapter(this, list);
        rcy.setAdapter(adapter);

        layoutNorms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });

        layoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow();
            }
        });
    }

    private PopupWindow mPopWindow;
    private HomeHotBean goodDetailBean;
    private int currentNum = 1;

    private void showPopWindow() {
        if (mPopWindow != null && mPopWindow.isShowing()) {

        } else {
            View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popwindow_good, null);
            int height = SystemUtils.dp2px(this, 250);
            mPopWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, height);
            mPopWindow.setFocusable(false);
            mPopWindow.setOutsideTouchable(false);
            mPopWindow.setContentView(contentView);
            contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            CartCustomView cartCustomView = contentView.findViewById(R.id.layout_cartwindow);
            TextView txtClose = contentView.findViewById(R.id.txt_close);
            ImageView img = contentView.findViewById(R.id.img);
            Glide.with(this).load(list.get(i).getPrimary_pic_url()).into(img);
            TextView name = contentView.findViewById(R.id.tvname);
            TextView title = contentView.findViewById(R.id.tvtitle);
            title.setText("价格    " + list.get(i).getRetail_price());
            name.setText(list.get(i).getName());
            txtClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopWindow.dismiss();
                    mPopWindow = null;
                }
            });
            int[] pt = new int[2];
            //获取到的屏幕宽高(除开了当前组件的宽高）
            layoutBottom.getLocationInWindow(pt);
            // Display display = getWindowManager().getDefaultDisplay();
            // int activityheight = display.getHeight();
            mPopWindow.showAtLocation(layoutBottom, Gravity.NO_GRAVITY, 0, pt[1] - height);
            cartCustomView.initView();
            cartCustomView.setOnClickListener(new CartCustomView.IClick() {
                @Override
                public void clickCB(int value) {
                    currentNum = value;
                }
            });
        }
    }


    private void addCart() {
        boolean islogin = SpUtils.getInstance().getBoolean("token");
        if (islogin) {
            //判断当前的规格弹框是否打开
            if (mPopWindow != null && mPopWindow.isShowing()) {
                //添加到购物车的操作
                if (goodDetailBean.getData().getProductList().size() > 0) {
                    int goodsId = goodDetailBean.getData().getProductList().get(0).getGoods_id();
                    Log.e("TAG", "addCart: "+goodsId );
                    Toast.makeText(this, ""+goodsId, Toast.LENGTH_SHORT).show();
                    int productId = goodDetailBean.getData().getProductList().get(0).getId();
                    Log.e("TAG", "addCart: "+productId );
                    persenter.addCart(goodsId, currentNum, productId);
                    mPopWindow.dismiss();
                    mPopWindow = null;
                } else {
                    Toast.makeText(this, "没有产品数据", Toast.LENGTH_SHORT).show();
                }
            } else {
                showPopWindow();
            }
        } else {
            Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show();
//            Intent跳转到登录

        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_hotdetails;
    }


    @Override
    public void showTips(String tips) {

    }

    @Override
    public void showLoading(int visible) {

    }

    private void updateBanner(List<HomeHotBean.DataBeanX.GalleryBean> gallery) {

        if (banner.getTag() == null || (int) banner.getTag() == 1) {
            List<String> imgs = new ArrayList<>();
            for (HomeHotBean.DataBeanX.GalleryBean item : gallery) {
                imgs.add(item.getImg_url());
            }
            banner.setImages(imgs);
            banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
        }
    }

    int i;

    private void updateshuju(List<HomeHotBean.DataBeanX.IssueBean> issueBeans) {
        txtName.setText(issueBeans.get(i).getQuestion());
        txtTitle1.setText(issueBeans.get(i).getAnswer());
    }

    private void updateDetailInfo(HomeHotBean.DataBeanX.InfoBean infoBean) {
        pingluntitle.setText(infoBean.getGoods_brief());
        Glide.with(this).load(infoBean.getList_pic_url()).into(pinglunimage);
        if (!TextUtils.isEmpty(infoBean.getGoods_desc())) {
            String h5 = infoBean.getGoods_desc();
            html = html.replace("$", h5);

            webView.loadDataWithBaseURL("about:blank", html, "text/html", "utf-8", null);
            //webView.loadData(html,"text/html","utf-8");
        }
    }

    private void updateParameter(List<HomeHotBean.DataBeanX.AttributeBean> attributeBean) {
        layoutParameter.removeAllViews(); //清空
        for (HomeHotBean.DataBeanX.AttributeBean item : attributeBean) {
            View view = LayoutInflater.from(this).inflate(R.layout.layout_parameter, null);
            layoutParameter.addView(view);
        }
    }


    @Override
    public void getHotRetuln(HomeHotBean retuln) {
        goodDetailBean = retuln;
        updateBanner(retuln.getData().getGallery());
        updateshuju(retuln.getData().getIssue());
        updateDetailInfo(retuln.getData().getInfo());
        updateParameter(retuln.getData().getAttribute());
        list.add(retuln.getData().getInfo());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addCartInfoReturn(AddCartInfoBean result) {
        int count = result.getData().getCartTotal().getGoodsCount();
        Toast.makeText(this, ""+count, Toast.LENGTH_SHORT).show();
        txtCount.setText(String.valueOf(count));
    }

    @OnClick({R.id.layout_collect, R.id.img_cart, R.id.cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_collect:
                break;
            case R.id.img_cart:
                finish();
                break;
            case R.id.cart:
                 addCart();
                break;
        }
    }
}