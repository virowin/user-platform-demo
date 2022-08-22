import cn.virowin.user.platform.facade.UserService;
import cn.virowin.user.platform.facade.vo.request.UserInfoRequestVO;
import cn.virowin.user.platform.facade.vo.response.UserInfoResponseVO;
import cn.virowin.user.platform.provider.dao.dataobject.UserDO;
import cn.virowin.user.platform.provider.dao.mapper.UserMapper;
import cn.virowin.user.platform.start.EmbeddedZooKeeper;
import cn.virowin.user.platform.start.MainApplication;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author virowin
 * @date 2022/8/22 22:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MainApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceImplTest {
    @DubboReference
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    private static EmbeddedZooKeeper embeddedZooKeeper;
    /**
     *
     * Method: insertUser(UserInfoRequestVO requestVO)
     *
     */
    @BeforeClass
    public static void beforeClass() throws Exception {
        embeddedZooKeeper = new EmbeddedZooKeeper(2181, false);
        embeddedZooKeeper.start();
        //@todo: we can use h2 to verify db connect without mysql
    }

    @AfterClass
    public static void afterClass() throws Exception {
        embeddedZooKeeper.stop();
    }

    @Test
    public void test0InsertUser() throws Exception {
        UserInfoRequestVO userInfoRequestVO = new UserInfoRequestVO();
        userInfoRequestVO.setEmail("abc@126.com");
        userInfoRequestVO.setNickname("abc123");
        userInfoRequestVO.setPassword("qwe123QWE@#!");
        long id = userService.insertUser(userInfoRequestVO);
        Assert.assertTrue(id > 0);

        UserInfoResponseVO userInfoResponseVO = userService.getUserByEmail(userInfoRequestVO.getEmail());
        Assert.assertEquals(userInfoResponseVO.getNickname(), userInfoRequestVO.getNickname());
        Assert.assertTrue(userService.checkPassword(userInfoRequestVO.getPassword(), userInfoResponseVO.getPassword()));


        UserInfoRequestVO userInfoRequestVO1 = new UserInfoRequestVO();
        userInfoRequestVO1.setEmail("abc1@126.com");
        userInfoRequestVO1.setNickname("abc123");
        userInfoRequestVO1.setPassword("qwe123QWE@#!");
        long id1 = userService.insertUser(userInfoRequestVO1);
        Assert.assertTrue(id1 > 0);

        UserInfoRequestVO userInfoRequestVO2 = new UserInfoRequestVO();
        userInfoRequestVO2.setEmail("abc2@126.com");
        userInfoRequestVO2.setNickname("abc123");
        userInfoRequestVO2.setPassword("qwe123QWE@#!");
        long id2 = userService.insertUser(userInfoRequestVO2);
        Assert.assertTrue(id2 > 0);
    }

    /**
     *
     * Method: updateUser(UserInfoRequestVO requestVO)
     *
     */
    @Test
    public void test1UpdateUser() throws Exception {
        UserInfoRequestVO userInfoRequestVO = new UserInfoRequestVO();
        userInfoRequestVO.setEmail("abc@126.com");
        userInfoRequestVO.setNickname("cba321");
        userInfoRequestVO.setPassword("ewq321EWQ!@#");
//        userInfoRequestVO
        Assert.assertTrue(userService.updateUser(userInfoRequestVO));

        UserInfoResponseVO userInfoResponseVO = userService.getUserByEmail(userInfoRequestVO.getEmail());
        Assert.assertEquals(userInfoResponseVO.getNickname(), userInfoRequestVO.getNickname());
        Assert.assertTrue(userService.checkPassword(userInfoRequestVO.getPassword(), userInfoResponseVO.getPassword()));
    }

    /**
     *
     * Method: getUserById(long id)
     *
     */
    @Test
    public void test2GetUserById() throws Exception {
        List<UserInfoResponseVO> list = userService.selectUser(10, 0);
        if (list.size() > 0) {
            UserInfoResponseVO one = list.get(0);
            UserInfoResponseVO userInfoResponseVO = userService.getUserById(one.getId());
            Assert.assertTrue(userInfoResponseVO != null && userInfoResponseVO.getId() > 0);
        }
    }

    /**
     *
     * Method: getUserByEmail(String email)
     *
     */
    @Test
    public void test3GetUserByEmail() throws Exception {
        List<UserInfoResponseVO> list = userService.selectUser(10, 0);
        if (list.size() > 0) {
            UserInfoResponseVO one = list.get(0);
            UserInfoResponseVO userInfoResponseVO = userService.getUserByEmail(one.getEmail());
            Assert.assertTrue(userInfoResponseVO != null && userInfoResponseVO.getId() > 0);
        }
    }

    /**
     *
     * Method: selectUser(int limit, int offset)
     *
     */
    @Test
    public void test4SelectUser() throws Exception {
        List<UserInfoResponseVO> list = userService.selectUser(10, 0);
        Assert.assertTrue(list.size() > 0);
    }

    /**
     *
     * Method: selectUserCount()
     *
     */
    @Test
    public void test5SelectUserCount() throws Exception {
        long count = userService.selectUserCount();
        Assert.assertTrue(count > 0);
    }

    /**
     *
     * Method: deleteUser(long id)
     *
     */
    @Test
    public void test6DeleteUserId() throws Exception {
        String email = "abc@126.com";
        UserInfoResponseVO userInfoResponseVO = userService.getUserByEmail(email);
        Assert.assertTrue(userService.deleteUser(userInfoResponseVO.getId()));
        UserInfoResponseVO userInfoResponseVO1 = userService.getUserByEmail(email);
        Assert.assertTrue(userInfoResponseVO1 == null || userInfoResponseVO1.getId() <= 0);
    }

    /**
     *
     * Method: deleteUser(List<Long> ids)
     *
     */
    @Test
    public void test7DeleteUserIds() throws Exception {
        List<UserInfoResponseVO> list = userService.selectUser(10, 0);
        List<Long> longList = new ArrayList<>();
        if (list.size() > 0) {
            for (UserInfoResponseVO one : list) {
                longList.add(one.getId());
            }
            boolean ret = userService.deleteUser(longList);
            Assert.assertTrue(ret);
        }
    }
    @Test
    public void test8CheckPassword() throws Exception {
        Assert.assertTrue(userService.checkPassword("qwe123QWE@#!","b138289125e9345ba5bb21907b3e6caf"));
    }

    @Test
    public void test9DeleteUserIdsHard() throws Exception {
        userMapper.delete((new QueryWrapper<UserDO>()).eq("status", 1));
    }
}
