package com.example.cashcard;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {

    private CashCardRepository cashCardRepository;

    public CashCardController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping()
    public ResponseEntity<Collection<CashCard>> findAll(Pageable pageable, Principal principal) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount")));
        Page<CashCard> page = cashCardRepository.findByOwner(principal.getName(), pageRequest);
        return ResponseEntity.ok(page.toList());
    }
    @GetMapping("/{requestedId}")
    public ResponseEntity<CashCard> findById(@PathVariable Long requestedId, Principal principal) {
        Optional<CashCard> cashCardOptional = Optional.ofNullable(findCashCard(requestedId, principal));
//        Optional<CashCard> optionalCashCard = cashCardRepository.findById(requestedId);
//        if (optionalCashCard.isPresent()) {
//            return ResponseEntity.ok(optionalCashCard.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        return cashCardOptional
                .map(cashCard -> ResponseEntity.ok(cashCard))
                .orElseGet(() -> ResponseEntity.notFound().build());
//         optionalCashCard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    private ResponseEntity createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ubc, Principal principal) {
        CashCard cashCard = new CashCard(null, newCashCardRequest.getAmount(), principal.getName());
        CashCard savedCashCard = cashCardRepository.save(cashCard);
        URI locationOfNewCashCard = ubc
                .path("cashcards/{id}")
                .buildAndExpand(savedCashCard.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putCashCard(@PathVariable Long requestedId, @RequestBody CashCard cashCardUpdate, Principal principal) {
        CashCard cashCard = findCashCard(requestedId, principal);
        if (cashCard != null) {
            CashCard updatedCashCard = new CashCard(cashCard.getId(), cashCardUpdate.getAmount(), principal.getName());
            cashCardRepository.save(updatedCashCard);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteCashCard(@PathVariable Long id, Principal principal) {
        if (cashCardRepository.existsByIdAndOwner(id, principal.getName())) {
            cashCardRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private CashCard findCashCard(Long requestedId, Principal principal) {
        return cashCardRepository.findByIdAndOwner(requestedId, principal.getName());
    }
}
