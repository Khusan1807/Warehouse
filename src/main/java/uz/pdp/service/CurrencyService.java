package uz.pdp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Currency;
import uz.pdp.repository.CurrencyRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyService {


    private final CurrencyRepo currencyRepo;

    public ApiResponse addOrEdit(Currency currency) {
        try {
            boolean existsByName = currencyRepo.existsByName(currency.getName());
            Currency currency1 = new Currency();
            if (!existsByName && currency.getId() != null) {
                currency1 = currencyRepo.getById(currency.getId());
            }
            currency1.setName(currency.getName());
            currencyRepo.save(currency1);
            return new ApiResponse(true, currency.getId() != null ? "Edited!" : "Saved");
        } catch (Exception e) {
            return new ApiResponse(false, "This Currency already exists!");
        }
    }


    public ApiResponse delete(Long id) {
        Optional<Currency> optionalCurrency = currencyRepo.findById(id);
        if (!optionalCurrency.isPresent()) {
            return new ApiResponse(false, "Error on Deleting!");
        }
        currencyRepo.delete(optionalCurrency.get());
        return new ApiResponse(true, "Deleted!");
    }
}
