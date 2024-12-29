package tn.micro.service.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.micro.service.cloud.AddressNotFoundException;
import tn.micro.service.cloud.entity.Address;
import tn.micro.service.cloud.repository.AddressRepository;
import tn.micro.service.cloud.request.CreateAddressRequest;
import tn.micro.service.cloud.response.AddressResponse;

@Service
public class AddressService implements IAddressService {


	@Autowired
	AddressRepository addressRepository;

	@Override
	public AddressResponse createAddress(CreateAddressRequest createStudentRequest) {

		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		address = addressRepository.save(address);
		System.out.println("le address port 6062");


		return new AddressResponse(address);
	}

	//@Override
	//public AddressResponse getById(long id) {
		//return new AddressResponse(addressRepository.findById(id).get());
	//}
	@Override
	public AddressResponse getById(long id) {
		System.out.println("le address port 6062");
	    return addressRepository.findById(id)
	            .map(AddressResponse::new)  
	            .orElseThrow(() -> new AddressNotFoundException("Address not found for ID: " + id));
	    
	}

	

	

}
