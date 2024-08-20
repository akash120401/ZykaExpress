package com.zykaExpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zykaExpress.entities.Address;
import com.zykaExpress.entities.User;
import com.zykaExpress.repository.AddressRepository;

@Service
@Transactional
public class AddressServiceImpl implements IAddressService {

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private UserServiceImpl userService;

	@Override
	public Address addAddress(Address address, int userId) {

		User user = userService.getUserDetails(userId);
		address.setSelectedUser(user);
		return addressRepo.save(address);
	}

	@Override
	public String deleteAddress(int addressId) {
		String mesg = "Deleting address details failed !!!!!";
		// if you want to confirm the id :
		if (addressRepo.existsById(addressId)) {
			addressRepo.deleteById(addressId);
			mesg = "Deleted address details of emp of " + addressId;
		}
		return mesg;
	}

	@Override
	public Address editAddress(Address address, int addressId) {
		Optional<Address> address1 = Optional.of(addressRepo.findbyId(addressId));
		Address address2 = address1.orElse(null);
		address2.setLine1(address.getLine1());
		address2.setLine2(address.getLine2());
		address2.setContactNo(address.getContactNo());
		return address2;
	}

	@Override
	public List<Address> getAllAddressesByUserId(int userId) {

		return addressRepo.getAllAddressesByUserId(userId);
	}
}
