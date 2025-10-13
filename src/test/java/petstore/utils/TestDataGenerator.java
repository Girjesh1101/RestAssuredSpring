package petstore.utils;

import petstore.model.Category;
import petstore.model.Pet;
import petstore.model.Tag;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    private  static  final Random random = new Random();
    public  static Pet createPet(Long id , String name , String status){

        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        pet.setStatus(status);

        Category category  = new Category(1L ,"Dogs");
        pet.setCategory(category);

        List<String> photoUrls = Arrays.asList("https://example.com/photo1.png");
        pet.setPhotoUrls(photoUrls);

        Tag tag = new Tag(1L , "friendly");
        pet.setTags(Arrays.asList(tag));

        return  pet;
    }

    public  static  Pet createRandomPet(){
        Long id = Math.abs(random.nextLong());
        String[] names = {"Buddy" , "Max" , "Charlie" , "Lucy", "Bailey"};

        String[] statuses  = {"available" , "pending" , "sold"};

        return  createPet(id ,
                names[random.nextInt(names.length)],
                statuses[random.nextInt(statuses.length)]);
    }

    public  static  Long generateRandomId(){
        return Math.abs(random.nextLong());
    }
}
