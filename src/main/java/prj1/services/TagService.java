package prj1.services;

import prj1.domains.Brand;
import prj1.domains.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag saveTag(Tag tag);

    List<Tag> getAllTags();

    Optional<Tag> getTagById(String id);

    Tag updateTag(String id, Tag updatedTag);

    List<Tag> searchTags(String keyword);
}
