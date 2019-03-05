package com.bsit.poetryencryption;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import com.bsit.poetryencryption.bean.Poetry;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author fpp
 * @Date 2019/3/5 0005 22:37
 */
public class MainActivity extends AppCompatActivity {

    TextView tvAtyMainHello;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        tvAtyMainHello = findViewById(R.id.tv_aty_main_hello);
        tvAtyMainHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getXmlData();
            }
        });
    }

    private String initData() {


        Context context = null;
        String packageName = this.getPackageName();

        try {

            context = this.createPackageContext(
                    packageName, Context.CONTEXT_IGNORE_SECURITY);
            AssetManager s = context.getAssets();
            try {
                InputStream is = s.open("poetrys.xml");
                XmlPullParser xmlParser = Xml.newPullParser();
                try {
                    xmlParser.setInput(is, "utf-8");
                    int event = xmlParser.getEventType();
                    while (event != XmlPullParser.END_DOCUMENT) {
                        switch (event) {
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:
                                if ("testName".equals(xmlParser.getName())) {
                                    String title = xmlParser.nextText();

                                    String bg = xmlParser.getAttributeValue(null, "Background");

                                    if (title != null) {
                                        return title;
                                    }
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                break;
                        }
                        event = xmlParser.next();
                    }
                    is.close();
                } catch (XmlPullParserException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";

    }

    private static List<Poetry> mPoetryList = new ArrayList<Poetry>();

    public void getXmlData() {
        // 解析 poetrys.xml文件
        // 创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            try {
                InputStream is = getAssets().open("poetrys.xml");
                // 通过reader对象的read方法加载 poetrys.xml文件,获取docuemnt对象。
                Document document = reader.read(is);
                // 通过document对象获取根节点 poetrys
                Element poetrys = document.getRootElement();
                // 通过element对象的elementIterator方法获取迭代器
                Iterator it = poetrys.elementIterator();
                // 遍历迭代器，获取根节点中的信息（书籍）
                mPoetryList.clear();
                while (it.hasNext()) {
                    Poetry p=new Poetry();
                    System.err.println("=====开始遍历某一诗词=====");
                    Element element = (Element) it.next();
                    // 获取book的属性名以及 属性值
                    List<Attribute> poetrysAttrs = element.attributes();
                    for (Attribute attr : poetrysAttrs) {
                        System.err.println("属性名：" + attr.getName() + "--属性值："
                                + attr.getValue());
                    }
                    Iterator poetry = element.elementIterator();
                    while (poetry.hasNext()) {
                        Element poetryChild = (Element) poetry.next();
                        if ("name".equals(poetryChild.getName())){
                            p.setName(poetryChild.getStringValue());
                        }else if ("content".equals(poetryChild.getName())){
                            p.setContent(poetryChild.getStringValue());
                        }else if ("encryptResult".equals(poetryChild.getName())){
                            p.setEncryptResult(poetryChild.getStringValue());
                        }
                        System.err.println(
                                "节点名：" + poetryChild.getName()
                                + "--节点值：" + poetryChild.getStringValue()
                        );
                    }
                    mPoetryList.add(p);
                    System.err.println("=====结束遍历某一本书=====");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


}
