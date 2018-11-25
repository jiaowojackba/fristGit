package queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class activeQueueDemo {
    public static void main(String[] args) throws JMSException {
//        创建连接工厂
        String brokerURL="tcp://192.168.25.128:61616";
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
//        创建连接对象

        Connection connection = connectionFactory.createConnection();

//      3  开启连接
        connection.start();
//        4创建session对象
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//      5创建对列对象
        Queue queue = session.createQueue("test.queue");


//        6创建消息生产者
        MessageProducer producer = session.createProducer(queue);
        //7创建消息
        TextMessage textMessage = session.createTextMessage("aaaaaaa");
//        8发送消息
        producer.send(textMessage);
//        9灌流
        producer.close();
        connection.close();
        session.close();


    }
}
