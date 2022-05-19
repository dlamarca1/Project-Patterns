package dio.projectpatternsclass.Service.Impl;

import dio.projectpatternsclass.Model.Address;
import dio.projectpatternsclass.Model.AddressRepository;
import dio.projectpatternsclass.Model.Client;
import dio.projectpatternsclass.Model.ClientRepository;
import dio.projectpatternsclass.Service.ClientService;
import dio.projectpatternsclass.Service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Client> searchAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client searchById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }

    @Override
    public void insert(Client client) {
        SaveClientWithCep(client);

    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> clientDb = clientRepository.findById(id);
        if (clientDb.isPresent()) {
            SaveClientWithCep(client);
        }

    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);

    }

    private void SaveClientWithCep(Client client) {
        String cep = client.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.searchCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        client.setAddress(address);

        clientRepository.save(client);
    }
}
