package com.bluemobi.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.appcore.util.FileUtil;
import com.appcore.util.SftpUtil;
import com.bluemobi.conf.ImageServerConfig;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.to.PicInfoTO;
import com.bluemobi.util.ImageUtils;

/**
 * 控制器抽象类 手机接口控制器 和 web控制器需继承此类
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-26 下午5:12:48
 * 
 */
public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    /**
     * 获取userId
     * 
     * @author haojian
     * @date 2015-10-15 上午10:09:30
     * @return
     * @return int
     */
    public abstract int getUserId();

    /**
     * 获取userName
     * 
     * @author haojian
     * @date 2015-12-2 上午9:47:19
     * @return
     * @return String
     */
    public abstract String getUserName();

    /**
     * 图片上传，默认会上传到图片服务器
     * 
     * @auther zhangzheng
     * @date 2015-9-30
     * @param files
     *            要上传的图片文件
     * @param imageUrl
     *            图片上传子路径路径
     * @return resultMap,imageUrl:图片路径;flag:上传是否成功
     */
    public Map<String, Object> uploadImage(MultipartFile[] files, String imageUrl) {
        return this.uploadImage(files, imageUrl, false);
    }

    public Map<String, Object> testUploadImage(HttpServletRequest request, String imageUrl) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> uploadFiles = multipartRequest.getFiles("file");

        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (int i = 0; i < uploadFiles.size(); i++) {
            LOGGER.info("fileName = " + uploadFiles.get(i).getOriginalFilename());
            if (!uploadFiles.get(i).isEmpty()) {
                long pre = System.currentTimeMillis();
                try {
                    // 检验文件夹是否存在，不存在 就创建
                    FileUtil.makeDir(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl);
                    // 文件后缀名
                    String suffix = uploadFiles.get(i).getOriginalFilename()
                            .substring(uploadFiles.get(i).getOriginalFilename().lastIndexOf(".") + 1, uploadFiles.get(i).getOriginalFilename().length());
                    // 拿到输出流，同时重命名上传的文件
                    String imageName = UUID.randomUUID().toString() + "." + suffix;
                    FileOutputStream os = new FileOutputStream(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName);

                    // 拿到上传文件的输入流
                    InputStream in = uploadFiles.get(i).getInputStream();
                    // 以写字节的方式写文件
                    int len = 0;
                    byte[] bb = new byte[4096];
                    while ((len = in.read(bb)) != -1) {
                        os.write(bb, 0, len);
                    }
                    os.flush();
                    os.close();
                    in.close();

                    resultMap.put("hashcode", imageName.substring(0, imageName.indexOf(".")));
                    resultMap.put("imageUrl", imageUrl + imageName);

                    long finaltime = System.currentTimeMillis();
                    LOGGER.info("消耗时间：【{}】秒", (finaltime - pre) / 1000d);
                    resultMap.put("flag", true);
                } catch (Exception e) {
                    LOGGER.error("上传出错,错误信息：【{}】", e);
                    resultMap.put("flag", false);
                }
            }
        }
        return resultMap;
    }

    /**
     * 图片上传
     * 
     * @auther zhangzheng
     * @date 2015-9-30
     * @param files
     *            要上传的图片文件
     * @param imageUrl
     *            图片上传子路径路径
     * @param isUploadToImageServer
     *            是否需要上传到图片服务器
     * @return resultMap,imageUrl:图片路径;flag:上传是否成功
     */
    public Map<String, Object> uploadImage(MultipartFile[] files, String imageUrl, boolean isUploadToImageServer) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        for (int i = 0; i < files.length; i++) {
            LOGGER.info("fileName = " + files[i].getOriginalFilename());
            if (!files[i].isEmpty()) {
                long pre = System.currentTimeMillis();
                try {
                    // 检验文件夹是否存在，不存在 就创建
                    FileUtil.makeDir(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl);
                    // 文件后缀名
                    String suffix = files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".") + 1, files[i].getOriginalFilename().length());
                    // 拿到输出流，同时重命名上传的文件
                    String imageName = UUID.randomUUID().toString() + "." + suffix;
                    FileOutputStream os = new FileOutputStream(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName);

                    // 拿到上传文件的输入流
                    InputStream in = files[i].getInputStream();
                    // 以写字节的方式写文件
                    int len = 0;
                    byte[] bb = new byte[4096];
                    while ((len = in.read(bb)) != -1) {
                        os.write(bb, 0, len);
                    }
                    os.flush();
                    os.close();
                    in.close();

                    // 上传图片到图片服务器
                    if (isUploadToImageServer) {
                        sftpUpload(imageUrl, suffix, "", imageName);
                        //aliyunOssUploadFile(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, imageUrl, imageName);
                    }

                    resultMap.put("hashcode", imageName.substring(0, imageName.indexOf(".")));
                    resultMap.put("imageUrl", imageUrl + imageName);

                    long finaltime = System.currentTimeMillis();
                    LOGGER.info("消耗时间：【{}】秒", (finaltime - pre) / 1000d);
                    resultMap.put("flag", true);
                } catch (Exception e) {
                    LOGGER.error("上传出错,错误信息：【{}】", e);
                    resultMap.put("flag", false);
                }
            }
        }
        return resultMap;
    }

    /**
     * 动态发布图片，一张或多张
     * 
     * @auther zhangz
     * @date 2016-6-13 下午1:50:15
     * @param files
     * @param imageUrl
     * @param isUploadToImageServer
     * @return
     */
    public List<PicInfoTO> uploadImageBatch(MultipartFile[] files, String imageUrl, boolean isUploadToImageServer) {
        List<PicInfoTO> picInfoTOs = new ArrayList<PicInfoTO>();
        PicInfoTO picInfoTO = null;
        int imageCount = files.length;
        for (int i = 0; i < files.length; i++) {
            picInfoTO = new PicInfoTO();
            LOGGER.info("fileName = " + files[i].getOriginalFilename());
            if (!files[i].isEmpty()) {
                int pre = (int) System.currentTimeMillis();
                try {
                    // 检验文件夹是否存在，不存在 就创建
                    FileUtil.makeDir(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl);
                    // 文件后缀名
                    String suffix = files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".") + 1, files[i].getOriginalFilename().length());
                    // 拿到输出流，同时重命名上传的文件
                    String name = UUID.randomUUID().toString();
                    String imageName = name + "." + suffix;
                    FileOutputStream os = new FileOutputStream(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName);

                    // 拿到上传文件的输入流
                    InputStream in = files[i].getInputStream();
                    // 以写字节的方式写文件
                    int len = 0;
                    byte[] bb = new byte[4096];
                    while ((len = in.read(bb)) != -1) {
                        os.write(bb, 0, len);
                    }
                    os.flush();
                    os.close();
                    in.close();

                    // 保存一份缩略图
                    // 多图 358*358，横图 692*400，竖图 386*466
                    BufferedImage bufferedImage = ImageIO.read(new File(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName));
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();

                    picInfoTO.setWide(width);
                    picInfoTO.setHigh(height);

                    int width_s = 0;
                    int height_s = 0;

                    // 多图
                    if (imageCount > 1) {
                        if (width > height) { // 截宽
                            int cut = (width - height) / 2;
                            width_s = height;
                            height_s = height;
                            ImageUtils.cutImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, cut, 0, cut + height, height, height, height, BaseConstant.BASE_IMAGE_ADDRESS
                                    + imageUrl + name + "_cut." + suffix);
                        } else { // 截高
                            int cut = (height - width) / 2;
                            width_s = width;
                            height_s = width;
                            ImageUtils.cutImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, 0, cut, width, cut + width, width, width, BaseConstant.BASE_IMAGE_ADDRESS
                                    + imageUrl + name + "_cut." + suffix);
                        }
                        // 压缩图片
                        if (width > 385 && height > 385) {
                            ImageUtils.reduceImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix, 385, 385, BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name
                                    + "_small." + suffix);
                        } else {
                            ImageUtils.reduceImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix, width_s, height_s, BaseConstant.BASE_IMAGE_ADDRESS
                                    + imageUrl + name + "_small." + suffix);
                        }
                        // picInfoTO.setWide(385);
                        // picInfoTO.setHigh(385);
                    } else { // 单图
                        if (width > height) { // 宽图
                            double x = width / 692.0;
                            double y = height / 400.0;
                            if (x > y) { // 截宽
                                int lastWidth = (int) (width * y / x);
                                int cutWidth = width - lastWidth;
                                int cut = cutWidth / 2;
                                width_s = lastWidth;
                                height_s = height;
                                ImageUtils.cutImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, cut, 0, cut + lastWidth, height, lastWidth, height,
                                        BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix);
                            } else {
                                int lastHeight = (int) (height * x / y);
                                int cutHeight = height - lastHeight;
                                int cut = cutHeight / 2;
                                width_s = width;
                                height_s = lastHeight;
                                ImageUtils.cutImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, 0, cut, width, cut + lastHeight, width, lastHeight,
                                        BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix);
                            }
                            if (width > 692 && height > 400) {
                                ImageUtils.reduceImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix, 692, 400, BaseConstant.BASE_IMAGE_ADDRESS + imageUrl
                                        + name + "_small." + suffix);
                            } else {
                                ImageUtils.reduceImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix, width_s, height_s, BaseConstant.BASE_IMAGE_ADDRESS
                                        + imageUrl + name + "_small." + suffix);
                            }
                            // picInfoTO.setWide(692);
                            // picInfoTO.setHigh(400);
                        } else { // 高图
                            double x = width / 386.0;
                            double y = height / 466.0;
                            if (x > y) { // 截宽
                                int lastWidth = (int) (width * y / x);
                                int cutWidth = width - lastWidth;
                                int cut = cutWidth / 2;
                                width_s = lastWidth;
                                height_s = height;
                                ImageUtils.cutImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, cut, 0, cut + lastWidth, height, lastWidth, height,
                                        BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix);
                            } else {
                                int lastHeight = (int) (height * x / y);
                                int cutHeight = height - lastHeight;
                                int cut = cutHeight / 2;
                                width_s = width;
                                height_s = lastHeight;
                                ImageUtils.cutImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, 0, cut, width, cut + lastHeight, width, lastHeight,
                                        BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix);
                            }
                            if (width > 386 && height > 466) {
                                ImageUtils.reduceImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix, 386, 466, BaseConstant.BASE_IMAGE_ADDRESS + imageUrl
                                        + name + "_small." + suffix);
                            } else {
                                ImageUtils.reduceImg(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_cut." + suffix, width_s, height_s, BaseConstant.BASE_IMAGE_ADDRESS
                                        + imageUrl + name + "_small." + suffix);
                            }
                            // picInfoTO.setWide(386);
                            // picInfoTO.setHigh(466);
                        }
                    }

                    // 上传图片到图片服务器
                    if (isUploadToImageServer) {
                        sftpUpload(imageUrl, suffix, name, imageName);
                        //aliyunOssUploadFile(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName, imageUrl, imageName);
                        //aliyunOssUploadFile(BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_small." + suffix, imageUrl, name + "_small." + suffix);
                    }

                    picInfoTO.setCompressPath(imageUrl + name + "_small." + suffix);
                    picInfoTO.setPath(imageUrl + imageName);

                    int finaltime = (int) System.currentTimeMillis();
                    LOGGER.info("消耗时间：", finaltime - pre);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.error("上传出错,错误信息：【{}】", e);
                }
                picInfoTOs.add(picInfoTO);
            }
        }
        return picInfoTOs;
    }

    /**
     * 将文件上传至服务器
     * 
     * @auther zhangz
     * @date 2016-7-5 上午10:41:15
     * @param imageUrl
     * @param suffix
     * @param name
     * @param imageName
     * @throws Exception
     */
    private void sftpUpload(String imageUrl, String suffix, String name, String imageName) throws Exception {
        String localFilePathName = BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + imageName;

        String HOST = ImageServerConfig.HOST;
        short PORT = ImageServerConfig.PORT;
        String USER_NAME = ImageServerConfig.USER_NAME;
        String PASSWORD = ImageServerConfig.PASSWORD;
        String BASE_IMAGE_PATH = ImageServerConfig.BASE_IMAGE_PATH;
        SftpUtil.upload(HOST, PORT, USER_NAME, PASSWORD, localFilePathName, BASE_IMAGE_PATH + imageUrl, imageName);

        if (name != null && !"".equals(name)) {
            String localFilePathSmallName = BaseConstant.BASE_IMAGE_ADDRESS + imageUrl + name + "_small." + suffix;
            SftpUtil.upload(HOST, PORT, USER_NAME, PASSWORD, localFilePathSmallName, BASE_IMAGE_PATH + imageUrl, name + "_small." + suffix);
        }
    }
    
    /**
     * 人民币单位转换（元转分）
     * @author HeWW
     * 2016-10-12
     * @param yuan
     * @return
     */
    public int getYuanToCent(BigDecimal yuan){
        return yuan.multiply(new BigDecimal(100)).intValue();
    }
    /**
     * 人民币单位转换（分转元）
     * @author HeWW
     * 2016-10-12
     * @param cent 分
     * @param len  小数点后面的位数
     * @return
     */
    public BigDecimal getCentToYuan(Integer cent,int len){
        return new BigDecimal(cent).divide(new BigDecimal(100),len,BigDecimal.ROUND_HALF_UP);
    }
}
