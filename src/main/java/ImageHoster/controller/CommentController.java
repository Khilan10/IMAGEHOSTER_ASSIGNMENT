package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;



    //This controller method is called when the request pattern is of type 'image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives a comment to be stored in the database
    //user information is collected from logged in user
    //createddate,text,user and image deatil are set to newComment object
    // and now the comment will be sent to the business logic to be persisted in the database
    //After storing the image, this method directs to  controller with mapping images/{imageId}/{title} i.e showImage method of this controller

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String title, @RequestParam("comment")  String comment, HttpSession session) throws IOException {

        System.out.println("Inside Comment Controller");

        User user = (User) session.getAttribute("loggeduser");

        //get image details
        Image image = imageService.getImage(imageId);

        LocalDate localDate = LocalDate.now();

        //creating a new Comment
        Comment newComment=new Comment();
        newComment.setCreatedDate(localDate);
        newComment.setText(comment);
        newComment.setUser(user);
        newComment.setImage(image);

        //now calling create comment method of CommentService
        commentService.createComment(newComment);

        return "redirect:/images/" + imageId +"/"+title;

    }
}
