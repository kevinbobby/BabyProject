package com.zhubibo.baby.module.gallery;

import java.io.Serializable;

/**
 * Created by kevin on 2017/7/4.
 */

public class ImageEntity implements Serializable {

    public int imageId;

    public String title;

    public String content;

    public int bgColor = -1;

    public int textColor = -1;
}
