package com.heyu.service;

import com.heyu.impl.PersonServiceImpl;
import com.heyu.thriftDemo.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.layered.TFramedTransport;

/**
 * 服务端代码
 * @author shkstart
 * @date 2021-06-29 15:20
 */
public class ThriftServer {
    public static void main(String[] args) throws Exception {
//        Thrift支持的服务器类型：非阻塞的TNonblockingServerSocket
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);//绑定端口号8899

//        THsHaServer高可用的server
        THsHaServer.Args arg=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);

//        处理器
        PersonService.Processor<PersonServiceImpl> processor=new PersonService.Processor<>(new PersonServiceImpl());

//        设定工厂
//        协议层
        arg.protocolFactory(new TCompactProtocol.Factory());
//        传输层
        arg.transportFactory(new TFramedTransport.Factory());

        arg.processorFactory(new TProcessorFactory(processor));
//        THsHaServer半同步，半异步server
        TServer server=new THsHaServer(arg);

        System.out.println("Thrift Server Started!");

        //死循环，异步非阻塞
        server.serve();

    }
}
