package com.fernan.gt.ForoHubChallenge.foroAPI.controller;

import com.fernan.gt.ForoHubChallenge.foroAPI.domain.model.Topic;
import com.fernan.gt.ForoHubChallenge.foroAPI.domain.model.TopicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
public class TopicosController {
    @Autowired
    private TopicRepository topicRepository;

    @PostMapping
    public ResponseEntity<Topic> registrarTopic(@RequestBody @Valid Topic topic, UriComponentsBuilder uriComponentsBuilder) {
        topic.setFechaCreacion(LocalDateTime.now());  // Ensure the creation date is set
        Topic savedTopic = topicRepository.save(topic);
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(savedTopic.getId()).toUri();
        return ResponseEntity.created(url).body(savedTopic);
    }

    @GetMapping
    public ResponseEntity<List<Topic>> listadoTopics() {
        List<Topic> topics = topicRepository.findAll();
        return ResponseEntity.ok(topics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topic> actualizarTopic(@PathVariable Long id, @RequestBody @Valid Topic topicDetails) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.setTitulo(topicDetails.getTitulo());
            topic.setContenido(topicDetails.getContenido());
            topic.setAutor(topicDetails.getAutor());
            topic.setCurso(topicDetails.getCurso());
            Topic updatedTopic = topicRepository.save(topic);
            return ResponseEntity.ok(updatedTopic);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (optionalTopic.isPresent()) {
            topicRepository.delete(optionalTopic.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topic> retornaDatosTopic(@PathVariable Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        return optionalTopic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
