package prj1.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prj1.domains.Brand;
import prj1.domains.Product;
import prj1.domains.Tag;
import prj1.repositories.TagRepository;
import prj1.services.TagService;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag saveTag(Tag tag) {
        if (isNameUnique(tag)) {
            return tagRepository.save(tag);
        } else {
            return null;
        }
    }

    private boolean isNameUnique(Tag tag) {
        Optional<Tag> existingTag = tagRepository.findByName(tag.getName());
        return existingTag.isEmpty() || existingTag.get().getId().equals(tag.getId());
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> getTagById(String id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag updateTag(String id, Tag updatedTag) {
        Optional<Tag> existingTag = tagRepository.findById(id);

        if (existingTag.isPresent()) {
            Tag tagToUpdate = existingTag.get();
            tagToUpdate.setName(updatedTag.getName());

            return tagRepository.save(tagToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public List<Tag> searchTags(String keyword) {
        return tagRepository.findByNameContainingIgnoreCase(keyword);
    }

//        public void productTag( String productId , List<String> tagId){
//
//    }

//    public void productTag(String productId, List<String> tagIds) {
//        Optional<Product> productOptional = productRepository.findById(productId);
//
//        if (productOptional.isPresent()) {
//            Product product = productOptional.get();
//
//            List<Tag> tagsToAdd = tagRepository.findAllById(tagIds);
//            product.getTags().addAll(tagsToAdd);
//
//            productRepository.save(product);
//        } else {
//
//        }
//    }

}





