package lzhs.com.virtualdemo.declare_info;

import java.io.Serializable;

/**
 * 申报详情  受益人<br/>
 * 作者：LZHS<br/>
 * 时间： 2018/1/17 09:48<br/>
 * 邮箱：1050629507@qq.com
 */
public class BeanDeclarInfoProduct implements Serializable {

    /**
     * id : 6
     * goodsImage : http://pic.jiushang.cn/upload/admin_app/11/2017/09/28/szKZjcwSwr.jpg
     * goodsName : 52度水井坊典藏500ml
     * channel : banquet
     */

    private int id;
    private String goodsImage;
    private String goodsName;
    private String channel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}