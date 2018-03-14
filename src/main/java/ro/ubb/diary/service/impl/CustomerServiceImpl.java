package ro.ubb.diary.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ubb.diary.dao.CustomerDao;
import ro.ubb.diary.model.Customer;
import ro.ubb.diary.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService{


	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired CustomerDao customerDao;
	
	@Override
	public void insert(Customer cus) {

		logger.info("Calling insert on customerDao");
		customerDao.insert(cus);
	}
	
	@Override
	public void insertBatch(List<Customer> customers) {
		customerDao.inserBatch(customers);
	}	
	
	public List<Customer> loadAllCustomer(){
		logger.info("Calling loadAllCustomers on noteDao");
		List<Customer> listCust = customerDao.loadAllCustomer();
		return listCust;
	}

	@Override
	public void getCustomerById(long cust_id) {
		Customer cust = customerDao.findCustomerById(cust_id);
		System.out.println(cust);
	}

	@Override
	public void getCustomerNameById(long cust_id) {
		String name = customerDao.findNameById(cust_id);
		System.out.println("Customer's name = " + name);
	}

	@Override
	public void getTotalNumerCustomer() {
		int totalNumberCustomer = customerDao.getTotalNumberCustomer();
		System.out.println("Total Number Customer is: " + totalNumberCustomer);
	}

}
