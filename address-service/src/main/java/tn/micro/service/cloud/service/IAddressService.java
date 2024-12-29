package tn.micro.service.cloud.service;

import org.springframework.stereotype.Service;

import tn.micro.service.cloud.request.CreateAddressRequest;
import tn.micro.service.cloud.response.AddressResponse;

@Service
public interface IAddressService {

	AddressResponse createAddress(CreateAddressRequest createAddressRequest);

	AddressResponse getById(long id);

}
