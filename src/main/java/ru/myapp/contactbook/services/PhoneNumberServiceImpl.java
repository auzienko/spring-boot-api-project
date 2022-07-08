package ru.myapp.contactbook.services;

import org.springframework.stereotype.Service;
import ru.myapp.contactbook.models.PhoneNumber;
import ru.myapp.contactbook.repositories.PhoneNumberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public List<PhoneNumber> findAll() {
        return phoneNumberRepository.findAll();
    }

    @Override
    public Optional<PhoneNumber> findById(Long id) {
        return phoneNumberRepository.findById(id);
    }

    @Override
    public PhoneNumber save(PhoneNumber entity) {
        return phoneNumberRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        phoneNumberRepository.deleteById(id);
    }
}
