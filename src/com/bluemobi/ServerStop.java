package com.bluemobi;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * 关闭服务器
 * @author haojian
 * Mar 6, 2012 2:38:47 PM
 */
public class ServerStop {
    
    private static final Logger LOGGER = LoggerFactory.getLogger("ServerStartAndStopLog");
    
    private ServerStop(){
        
    }
    
    private static final String CONFPATH = System.getProperty("user.dir")
    + System.getProperty("file.separator") + "conf"
    + System.getProperty("file.separator")+"app-config.xml";
    
    public static void main(String[] args) {
        shutDown( getShutDownPort() );
    }
    
    public static void shutDown(int shutdownPoint){
        
        String returnMsg = null;
        try {
            Socket s = new Socket("localhost",shutdownPoint);
            OutputStream os = s.getOutputStream();
            InputStream is = s.getInputStream();
            byte[] bb = "shutdown".getBytes();
            os.write(bb);
            os.flush();
            
            byte[] bb2 = new byte[1024];
            int len = is.read(bb2);
            while(len!=-1){
                returnMsg = new String(bb2,0,len);
                LOGGER.info(returnMsg);
                len = is.read(bb2);
            }
            
        } catch (Exception e) {
            LOGGER.error("【服务器】关闭成功！【{}】",e);
        } 
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            LOGGER.error("睡眠的时候出现异常！【{}】",e);
        }
    }


    /**
     * 获取服务器关闭端口
     * @return
     */
    public static int getShutDownPort(){
        
        int shutDownPort = 0;
        
        try{
            
            Document doc = getW3CDocument();
            //得到文档名称为Student的元素的节点列表
            NodeList nList = doc.getElementsByTagName("shutdown-port");
            Node node = nList.item(0);
            shutDownPort = Integer.valueOf(node.getFirstChild().getNodeValue().trim());;
            
            LOGGER.info("关闭端口："+shutDownPort);
            
        }catch (Exception e) {
            LOGGER.error("读取xml配置异常！没有找到相应的关闭端口字段：shutdown-port：【{}】",e);
        }

        return shutDownPort;
        
        
    }
    
    
    
    /**
     * 通过xml名称获取 xml 的根元素
     * @author haojian
     * Apr 7, 2013 10:33:24 AM
     * @param fileName
     * @return
     */
    public static Document getW3CDocument(){
        
        Document doc = null;
        
        try{
            //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            
            doc = dbBuilder.parse(new File(CONFPATH));
        }catch (Exception e) {
            LOGGER.error("读取xml文件异常！请检查文件是否存在，或者文件格式是否错误,【{}】",e);
        }
        
        return doc;
    }
    
}
