package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    //private ImageRepository imageRepository;
    private CommentRepository commentRepository;

    //Call the createComment method in the CommentRepository class to persist the comment in the database
    public void createComment(Comment comment){
        commentRepository.createComment(comment);
    }
}
