package com.zhubibo.baby.module.gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.zhubibo.baby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 2017/7/4.
 */

public class HomeworkActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView imageRv;
    private ImageAdapter adapter;
    private List<ImageEntity> data;

    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    private boolean isGrid = true;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        data = new ArrayList<>();
        initData(30);

        gridLayoutManager = new GridLayoutManager(this, 2);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);

        adapter = new ImageAdapter(data);
        adapter.setGrid(isGrid);
        adapter.setOnItemClickListener(this);

        imageRv = (RecyclerView) findViewById(R.id.imageRv);
        imageRv.setItemAnimator(new DefaultItemAnimator());
        imageRv.setLayoutManager(isGrid ? gridLayoutManager : staggeredGridLayoutManager);
        imageRv.setAdapter(adapter);
        imageRv.setLongClickable(true);

        MyItemTouchHelperCallback itemTouchHelperCallback = new MyItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(imageRv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuChange:
                isGrid = !isGrid;
                imageRv.setLayoutManager(isGrid ? gridLayoutManager : staggeredGridLayoutManager);
                adapter.setGrid(isGrid);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(ImageEntity imageEntity) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("imageEntity", imageEntity);
        startActivity(detailIntent);
    }

    private void initData(int size) {

        for (int i = 0; i < size; i++) {
            ImageEntity entity = new ImageEntity();
            int r = i % 6;
            switch (r) {
                case 0:
                    entity.imageId = R.drawable.image1;
                    entity.content = "其中最值得一提的就是手工臊子面了，它以精致的做工配料和地道的农家口味，让很多人情有独钟，流连忘返。手工面和面、揉面、擀面和切面都很有讲究，有句俗言是这样讲的“擀成纸，切成线，煮在锅里莲花转，捞到筷子打秋千，盛到碗里赛牡丹”。";
                    break;
                case 1:
                    entity.imageId = R.drawable.image2;
                    entity.content = "关于庆阳臊子面，民间还有一个有趣的风俗习惯：相传，在很久以前，有户人家娶了一个美貌、聪明、勤快、伶俐的媳妇。新媳妇到婆家的第二天，为全家做了一餐面条，一家人食后无不称赞面条滑爽鲜美。后来，她的小叔考中了官职，请同窗好友到家作客，便请嫂子为大家做最拿手的面条款待客人，大家吃后都赞不绝口。";
                    break;
                case 2:
                    entity.imageId = R.drawable.image3;
                    entity.content = "正如一首民歌唱道：“十八省里(那个)转一遍，好不过咱们庆阳的臊子面”。庆阳臊子面在经过千年的发展过程中，也已形成了独特的地域风味。传统的膳食臊子面，因其太辣，吃在嘴里不断发出嘘嘘哨声，又被称为“哨子面”。庆阳臊子面做工精细，制作独特，吃法有趣，堪称一绝。";
                    break;
                case 3:
                    entity.imageId = R.drawable.image4;
                    entity.content = "台湾作家林清玄的《阳光的味道》，写一晒谷子的年轻农夫，在晒谷时偶然发现了阳光的香味，竟使他决意留在家乡。他和作者漫谈起幼时刚晒干的衣服、新晒的棉被、新晒的书画的味道，说那里面都留存着阳光的味道。";
                    break;
                case 4:
                    entity.imageId = R.drawable.image5;
                    entity.content = "匾里的淀粉是早就洗出的，然而院里的阳光停留就几个钟头，搬进搬出的，有好些时日了。萝卜片是新切的，白白的薄片摊在门板上，像小时瓦屋缝隙里漏进来的一个个光柱椭圆地印在泥地上，斑斑驳驳，层层叠叠着。还有切成条状的萝卜，倒像是粼粼湖面上闪动的银鱼了。";
                    break;
                case 5:
                    entity.imageId = R.drawable.image6;
                    entity.content = "呼——嘬着嘴吹着热气直冒的爊豆腐，一边急不可待地吞进肚去，小时吃爊豆腐的馋嘴样还是那般清晰，我以为这要算淀粉所做食物中绝好的美食了。或者将淀粉加水搅拌呈麦糊状，于热锅一糊，白色渐成黝黑，切条煮透，加调料葱花，入口滑润，亦是上佳的美食。我曾见过母亲将大半碗淀粉和水少许，于热锅中搅成面团，又摘成鱼丸大小，煮后既滑润，又多了几分韧劲，真是过齿难忘!";
                    break;
            }
            entity.title = "item" + i;

            paletteItem(entity);

            data.add(entity);
        }
    }

    private void paletteItem(final ImageEntity imageEntity) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageEntity.imageId);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int defaultColor = Color.argb(128, 255, 255, 255);
                // 获取柔和特性的颜色样品
                Palette.Swatch swatch = palette.getMutedSwatch();
                // 推荐的主色调
                int rgb = swatch.getRgb();
                // 推荐的内容文字颜色
                int bodyTextColor = swatch.getBodyTextColor();
                // 推荐的标题颜色
                int titleTextColor = swatch.getTitleTextColor();
                // 分析颜色在图片中所占的像素比例
                int population = swatch.getPopulation();

                imageEntity.bgColor = getTranslucentColor(0.7f, rgb);
                imageEntity.textColor = titleTextColor;
            }
        });
    }

    private int getTranslucentColor(float percent, int rgb) {
        // argb 0fff
        // argb 0000 1111 1111 1111

        int blue = rgb & 0xff;
        int green = rgb>>8 & 0xff;
        int red = rgb>>16 & 0xff;
        int alpha = rgb>>>24;

        alpha = Math.round(alpha * percent);

        return Color.argb(alpha, red, green, blue);
    }
}
