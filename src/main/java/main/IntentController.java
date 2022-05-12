package main;

import main.model.Intent;
import main.model.IntentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class IntentController {

    @Autowired
    private IntentRepository intentRepository;


    @GetMapping("/intent/")
    public List<Intent> list() {
        Iterable<Intent> intentIterable = intentRepository.findAll();
        ArrayList<Intent> intentArrayList = new ArrayList<>();
        for (Intent intent : intentIterable) {
            intentArrayList.add(intent);
        }
        return intentArrayList;
    }

    @PostMapping("/intent/")
    public int addIntent(Intent intent) {

        Intent newIntent = intentRepository.save(intent);
        return newIntent.getId();

    }

    @DeleteMapping("/intent/id")
    public String deleteIntent(int id) {

        intentRepository.deleteById(id);
        return "Желание под номером " + id + " успешно удалено и очень надеемся, что оно осуществилось";
    }

    @DeleteMapping("/intent/")
    public String deleteAllIntent() {

        intentRepository.deleteAll();
        return "Все желания успешно удаленыю Вероятно, они уже осуществились или обязательно сбудутся";
    }

    @PutMapping("/intent/id")
    public String putIntent(Intent intent, int id) {
        intentRepository.save(intent);
        return "Ваше желание под номером " + id + " изменено к лучшему";
    }


    @GetMapping("/intent/id")
    public ResponseEntity getIntent(int id) {


        Optional<Intent> intentOptional = intentRepository.findById(id);

        return intentOptional.map(intent -> new ResponseEntity(intent, HttpStatus.OK)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }


}
