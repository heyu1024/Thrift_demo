package com.heyu.client;

import com.heyu.thriftDemo.Person;
import com.heyu.thriftDemo.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

/**
 * 客户端代码
 * @author shkstart
 * @date 2021-06-29 15:30
 */
public class ThriftClient {
    public static void main(String[] args) throws Exception {
        //   arg.transportFactory(new TFramedTransport.Factory());和服务器保持一致
        TTransport transport=new TFramedTransport(new TSocket("localhost",8899),600);

//                arg.protocolFactory(new TCompactProtocol.Factory());
        TProtocol protocol=new TCompactProtocol(transport);

        PersonService.Client client=new PersonService.Client(protocol);

        try {
            transport.open();//打开socket

            Person person = client.getPersonByUsername("tom");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("------------");

            Person person2=new Person();
            person2.setUsername("jack");
            person2.setAge(22);
            person2.setMarried(false);

            client.sacePerson(person2);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }

    }
}
