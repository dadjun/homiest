package com.customer.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.customer.dao.CustomerDao;
import com.customer.model.CustomerBean;
import com.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;//这里会报错，但是并不会影响

    @Override
    public int addCustomer(CustomerBean user) {

        return customerDao.insert(user);
    }

    @Override
    public int deleteCustomer(CustomerBean customer) {

        return customerDao.delete(customer);
    }


    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<CustomerBean> findAllCustomers(int pageNum, int pageSize,CustomerBean customer) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerBean> userDomains = customerDao.selectCustomers(customer);
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    @Override
    public List<String> selectCustomerCountry(){
        List<String> countryList = customerDao.selectCustomerCountry();
        if (countryList.get(0) == null)
        countryList.set(0,"");
        return countryList;
    }

    @Override
    public  int  updateCustomer(CustomerBean customer){
        return  customerDao.updateCustomer(customer);
    }

}