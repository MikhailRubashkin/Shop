package dao.repository.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRepositoryImplTest {


        private UserRepositoryImpl repository;

        @Before
        public void setUp() {
            repository = UserRepositoryImpl.getInstance();
        }

        @Test
        public void getInstance() {
            Assert.assertNotNull(repository);
        }

        /*@Test
        public void save() {
            User user = new User();
            user.setId(1);
            user.setLogin("save");
            user.setName("save");
            user.setPassword(100);
            //user.setBirthday(22);
            try {
                User userEntity = repository.save(user);
                Assert.assertNotNull(userEntity);
                Assert.assertEquals(user, userEntity);
                repository.remove(userEntity.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void get() {
            User user = new User();
            user.setLogin("get");
            user.setName("get");
            user.setPassword(100);
            //user.setBirthday(22);
            try {
                User userEntity = repository.save(user);
                userEntity = repository.get(userEntity.getId());
                Assert.assertNotNull(userEntity);
                repository.remove(userEntity.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void getAll() {
            User user = new User();
            user.setLogin("getAll");
            user.setName("getAll");
            user.setPassword(100);
            //user.setBirthday(22);
            try {
                User userEntity = repository.save(user);
                List<User> all = repository.getAll();
                Assert.assertNotNull(all);
                Assert.assertTrue(all.size() > 0);
                repository.remove(userEntity.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void update() {
            User user = new User();
            user.setLogin("update");
            user.setName("update");
            user.setPassword(100);
            //user.setBirthday(22);
            try {
                User userEntity = repository.save(user);
                userEntity.setLogin("update_test");
                userEntity.setName("update_test");
                userEntity.setPassword(100);
               // userEntity.setBirthday(22);
                repository.update(user);
                user = repository.get(userEntity.getId());
                Assert.assertNotNull(userEntity);
                Assert.assertEquals(user, userEntity);
                repository.remove(userEntity.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Test
        public void remove() {
            User user = new User();
            user.setLogin("remove");
            user.setName("remove");
            user.setPassword(100);
           // user.setBirthday(22);
            try {
                user = repository.save(user);
                int remove = repository.remove(user.getId());
                User userEntity = repository.get(remove);
                Assert.assertNull(userEntity);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }

