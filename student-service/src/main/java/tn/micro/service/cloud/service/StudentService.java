package tn.micro.service.cloud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tn.micro.service.cloud.entity.Student;
import tn.micro.service.cloud.exception.AdressBadRequestException;
import tn.micro.service.cloud.proxies.AddressFeignClient;
import tn.micro.service.cloud.repository.StudentRepository;
import tn.micro.service.cloud.request.CreateAddressRequest;
import tn.micro.service.cloud.request.CreateStudentRequest;
import tn.micro.service.cloud.response.AddressResponse;
import tn.micro.service.cloud.response.StudentResponse;

@Service
public class StudentService implements IStudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	AddressFeignClient client;
	//WebClient webClient;

	@Override
	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
		
	    System.out.println("Tentative d'appel à address-service via Feign");
		
		
		CreateAddressRequest request = new CreateAddressRequest(createStudentRequest.getStreet(), createStudentRequest.getCity());
		//AddressResponse address = createAddressWithWebClient(request);
		
		AddressResponse address = client.createAddress(request);
	    System.out.println("Réponse reçue depuis address-service : " + address);
		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(address.getId());
		student = studentRepository.save(student);

		return new StudentResponse(student,address);
	}

	//@Override
	//public StudentResponse getById(long id) {
		
		//Student student =studentRepository.findById(id).get();
		//AddressResponse address = client.getById(student.getAddressId());
		//return new StudentResponse(student ,address );
	//}
	@Override
	public StudentResponse getById(long id) {
		Student student = studentRepository.findById(id).get();
		AddressResponse address = client.getById(student.getAddressId());
		return new StudentResponse(student, address);
//		
//	    return studentRepository.findById(id)
//	            .map(StudentResponse::new)  // Si l'étudiant existe, on le mappe en réponse
//	            .orElseThrow(() -> new AdressBadRequestException("Student not found for ID: " + id));  // Si non, on lance une exception
	}

	
	

	@Override
	public List<StudentResponse> getAllStudents() {
		
		   // Récupérer tous les étudiants depuis le repository
	    List<Student> students = studentRepository.findAll();		
		
		 // Convertir chaque étudiant en StudentResponse, y compris l'adresse associée
	    return students.stream().map(student -> {
	        AddressResponse address = client.getById(student.getAddressId());
	        return new StudentResponse(student, address);
	    }).toList();
	}

	//public AddressResponse getAddressById(long addressId) {
	//	Mono<AddressResponse> addressResponse = webClient.get().uri("/getById/" + addressId).retrieve()
		//		.bodyToMono(AddressResponse.class);
	//	return addressResponse.block();
//	}

//	private AddressResponse createAddressWithWebClient(CreateAddressRequest request) {
	//	return webClient.post().uri("/create").contentType(MediaType.APPLICATION_JSON).bodyValue(request).retrieve()
	//			.bodyToMono(AddressResponse.class).block();
	//}
}
