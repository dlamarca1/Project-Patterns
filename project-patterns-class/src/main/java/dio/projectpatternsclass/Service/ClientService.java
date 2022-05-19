package dio.projectpatternsclass.Service;

import dio.projectpatternsclass.Model.Client;
import org.springframework.stereotype.Service;


@Service
public interface ClientService {

    Iterable<Client> searchAll();

    Client searchById(Long id);

    void insert (Client client);

    void update (Long id, Client client);

    void delete (Long id);

}
