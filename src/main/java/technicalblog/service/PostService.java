package technicalblog.service;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

@Service
public class PostService {

    /*
    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;
    */

    @Autowired
    private PostRepository repository;

	public PostService() {
        System.out.println("*** PostService ***");
    }

    //Method - 3 ORM
	public List<Post> getAllPosts() {

	       /*
        // Using JPA implementation (Done in repository class)
        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("SELECT p from Post p", Post.class);
        List<Post> resultList = query.getResultList();
        */

        return repository.getAllPosts();

    }

        //Method -1 (without using jdbc and orm)
        /*
     public ArrayList<Post> getAllPosts() {
        //Below thing needed when we not use dataBase
         ArrayList<Post> posts = new ArrayList<>();
        Post post1 = new Post();
        post1.setTitle("Post 1");
        post1.setBody("Post Body 1");
        post1.setDate(new Date());

        Post post2 = new Post();
        post2.setTitle("Post 2");
        post2.setBody("Post Body 2");
        post2.setDate(new Date());

        Post post3 = new Post();
        post3.setTitle("Post 3");
        post3.setBody("Post Body 3");
        post3.setDate(new Date());

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
   */

        //While using database (Method-2)

        /*
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver"); //Loading the driver

            //Connecting to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalBlog","postgres", "postgres");

            //in order to execute the query against the databse from java need to create statement object.
            Statement statement = connection.createStatement();

            //In order to iterate over the result fetched from the query.
            ResultSet rs = statement.executeQuery("SELECT * FROM post");
            while(rs.next()){
                Post post = new Post();

                //getString gets the value from the column name for particular row
                //set is used to convert the value feteched by getStrign via column name to respective object -> here post object.
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));

                //Adding the post object to arraylist
                posts.add(post);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return posts;

        }
         */

    public Post getOnePost(){
        return repository.getLatestPost();
    }

    /*
    public ArrayList<Post> getOnePost() {
        ArrayList<Post> posts = new ArrayList<>();
        //
        Post post1 = new Post();
        post1.setTitle("This is your Post");
        post1.setBody("This is your Post. It has some valid content");
        post1.setDate(new Date());
        posts.add(post1);
       //
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver"); //Loading the driver

            //Connecting to the database
             connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalBlog","postgres", "postgres");

            //in order to execute the query against the databse from java need to create statement object.
            Statement statement = connection.createStatement();

            //In order to iterate over the result fetched from the query.
            ResultSet rs = statement.executeQuery("SELECT * FROM post WHERE id=4");
            while(rs.next()){
                Post post = new Post();

                //getString gets the value from the column name for particular row
                //set is used to convert the value feteched by getStrign via column name to respective object -> here post object.
                post.setTitle(rs.getString("title"));
                post.setBody(rs.getString("body"));

                //Adding the post object to arraylist
                posts.add(post);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally{
            try{
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }


        return posts;

}
*/


    public void createPost (Post newPost){
        newPost.setDate(new Date());
        repository.createPost(newPost);
        System.out.println("NewPost: "+ newPost);
    }

    public Post getPost(Integer postId) {
        return repository.getPost(postId);
    }

    public void updatePost(Post updatedPost) {
        updatedPost.setDate(new Date());
        repository.updatePost(updatedPost);
    }

    public void deletePost(Integer postId) {
        repository.deletePost(postId);
    }

}
