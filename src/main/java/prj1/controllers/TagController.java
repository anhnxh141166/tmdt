package prj1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prj1.domains.Brand;
import prj1.domains.Tag;
import prj1.services.TagService;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable String id) {
        return tagService.getTagById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Tag> saveTag(@RequestBody Tag tag) {
        return ResponseEntity.ok(tagService.saveTag(tag));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable String id, @RequestBody Tag updatedTag) {
        Tag result = tagService.updateTag(id, updatedTag);

        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Tag>> searchTags(@RequestParam String keyword) {
        return ResponseEntity.ok(tagService.searchTags(keyword));
    }



//    @PostMapping("/productTag/{productId}")
//    public ResponseEntity<String> productTag(@PathVariable String productId, @RequestBody List<String> tagIds) {
//        tagService.productTag(productId, tagIds);
//        return ResponseEntity.ok("Tags added to product successfully");
//    }
}
