package demo;
import com.ccy.mybatis.demo.domain.Role;
import com.ccy.mybatis.demo.domain.RoleMapper;
import com.ccy.mybatis.demo.domain.User;
import com.ccy.mybatis.demo.domain.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.xml.transform.Source;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

    private static SqlSessionFactory sqlSessionFactory;
    @Before
    public void startTest() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("E:\\code\\gitcode\\mybaitsdemo\\src\\main\\resources\\mybatis-config.xml"));
//        InputStream inputStream = ClassLoader.getSystemResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void userTest() throws InterruptedException {
        //配置文件中只配置了User接口类的地址 没有配置xml地址 只能使用接口中的sql 不能使用xml中的sql 会报错
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.listAll();
        //缓存验证 发现只有在sleep之前有sql打印 sleep之后没有sql打印
        Thread.sleep(2000);
        System.out.println("sleep end");
        users = mapper.listAll();

//        User user = mapper.findById(2);
//        System.out.println(user.toString());

        users.stream().forEach(u->{
            System.out.println(u.getId());
        });

    }

    @Test
    public void roleTest(){
        //配置文件中只配置了role mapper xml 的地址  可以使用接口中的sql和xml中的sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.findById(1);
        System.out.println("findById = " + role.getId());

//        List<Role> roles = mapper.listAll();
//        roles.stream().forEach(r -> System.out.println(r.getId()));


    }
    //mybatis typeHandler demo
    @Test
    public void typeHandlerTestAdd(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = new Role();
        Double d = Math.random() * 10000;
        String random = String.valueOf(d.intValue());
        role.setRole("typeHandlerTest"+ random);
        role.setAvailable(1);
        role.setDescription("hello");
        int num = mapper.add(role);
        System.out.println(num);
        sqlSession.commit();
    }

    @Test
    public void typeHandlerTestGet(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.findById(4);
        System.out.println(role.getDescription());

    }

    //
    @Test
    public void listTypeHandlerTestAdd(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = new Role();
        Double d = Math.random() * 10000;
        String random = String.valueOf(d.intValue());
        role.setRole("typeHandlerTest"+ random);
        role.setAvailable(1);
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        role.setDescriptionList(list);
        int num = mapper.add2(role);
        System.out.println(num);
        sqlSession.commit();
    }

    @Test
    public void listTypeHandlerTestGet(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        Role role = mapper.findById2(13);
        System.out.println(role.getDescriptionList().size());

    }

}
