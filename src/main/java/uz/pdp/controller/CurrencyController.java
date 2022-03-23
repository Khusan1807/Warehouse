package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.ApiResponse;
import uz.pdp.entity.Currency;
import uz.pdp.repository.CurrencyRepo;
import uz.pdp.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CurrencyRepo currencyRepo;


    @GetMapping("/all")
    public List<Currency> get() {
        return currencyRepo.findAll();
    }


    @PostMapping("/addOrEdit")
    public ApiResponse addOrEdit(@RequestBody Currency currency) {
        return currencyService.addOrEdit(currency);
    }


    @DeleteMapping("/delete/{id}")
    public ApiResponse delete(@PathVariable Long id) {
        return currencyService.delete(id);
    }

}
