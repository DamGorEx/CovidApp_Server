package mcs.Model.Repositories;

import mcs.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    @Query("SELECT u from Image u where u.name = ?1")
    Image findByName(String filename);
}
