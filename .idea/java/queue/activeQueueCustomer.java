package queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
//啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊
public class activeQueueCustomer {

    public static void main(String[] args) throws JMSException, IOException {
//        创建连接工厂
        String brokerURL = "tcp://192.168.25.128:61616";
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

        final MessageConsumer consumer = session.createConsumer(queue);
        //7创建消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println("接收到的queue消息啦");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
//        9灌流
        consumer.close();
        connection.close();
        session.close();

    }
    }
