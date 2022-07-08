package ru.myapp.contactbook.services;

import org.springframework.stereotype.Service;
import ru.myapp.contactbook.models.Address;
import ru.myapp.contactbook.repositories.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address save(Address entity) {
        return addressRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
