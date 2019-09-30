package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private ImageRepository imageRepository;

    //Call the createComment method in the ImageRepository class to persist the comment in the database
    public void createComment(Comment comment){
        imageRepository.createComment(comment);
    }
}
