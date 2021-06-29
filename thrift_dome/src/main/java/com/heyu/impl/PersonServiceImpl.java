package com.heyu.impl;

import com.heyu.thriftDemo.DataException;
import com.heyu.thriftDemo.Person;
import com.heyu.thriftDemo.PersonService;
import org.apache.thrift.TException;

/**
 * 编写PersonService.Iface的实现类
 * @author shkstart
 * @date 2021-06-29 15:14
 * 实现服务类PersonService中的接口Iface
 */
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("Got client Param:"+username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);

        return person;
    }

    @Override
    public void sacePerson(Person person) throws DataException, TException {
        System.out.println("Got client Param:");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());

    }
}
