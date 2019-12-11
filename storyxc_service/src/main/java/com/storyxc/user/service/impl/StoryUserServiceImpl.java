package com.storyxc.user.service.impl;
import com.storyxc.user.dao.StoryUserMapper;
import com.storyxc.pojo.StoryUser;
import com.storyxc.user.service.StoryUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:Xc
 * @Description:StoryUser业务层接口实现类
 * @Date
 *****/
@Service
public class StoryUserServiceImpl implements StoryUserService {

    @Autowired
    private StoryUserMapper storyUserMapper;


    /**
     * StoryUser条件+分页查询
     * @param storyUser 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<StoryUser> findPage(StoryUser storyUser, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(storyUser);
        //执行搜索
        return new PageInfo<StoryUser>(storyUserMapper.selectByExample(example));
    }

    /**
     * StoryUser分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<StoryUser> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<StoryUser>(storyUserMapper.selectAll());
    }

    /**
     * StoryUser条件查询
     * @param storyUser
     * @return
     */
    @Override
    public List<StoryUser> findList(StoryUser storyUser){
        //构建查询条件
        Example example = createExample(storyUser);
        //根据构建的条件查询数据
        return storyUserMapper.selectByExample(example);
    }


    /**
     * StoryUser构建查询对象
     * @param storyUser
     * @return
     */
    public Example createExample(StoryUser storyUser){
        Example example=new Example(StoryUser.class);
        Example.Criteria criteria = example.createCriteria();
        if(storyUser!=null){
            // 
            if(!StringUtils.isEmpty(storyUser.getId())){
                    criteria.andEqualTo("id",storyUser.getId());
            }
            // 
            if(!StringUtils.isEmpty(storyUser.getUsername())){
                    criteria.andLike("username","%"+storyUser.getUsername()+"%");
            }
            // 
            if(!StringUtils.isEmpty(storyUser.getPassword())){
                    criteria.andEqualTo("password",storyUser.getPassword());
            }
            // 性别，1男，0女
            if(!StringUtils.isEmpty(storyUser.getSex())){
                    criteria.andEqualTo("sex",storyUser.getSex());
            }
            // 
            if(!StringUtils.isEmpty(storyUser.getPhone())){
                    criteria.andEqualTo("phone",storyUser.getPhone());
            }
            // 创建时间
            if(!StringUtils.isEmpty(storyUser.getCreatedTime())){
                    criteria.andEqualTo("createdTime",storyUser.getCreatedTime());
            }
            // 头像地址
            if(!StringUtils.isEmpty(storyUser.getHeadPic())){
                    criteria.andEqualTo("headPic",storyUser.getHeadPic());
            }
            // 最后登录时间
            if(!StringUtils.isEmpty(storyUser.getLastLogin())){
                    criteria.andEqualTo("lastLogin",storyUser.getLastLogin());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        storyUserMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改StoryUser
     * @param storyUser
     */
    @Override
    public void update(StoryUser storyUser){
        storyUserMapper.updateByPrimaryKey(storyUser);
    }

    /**
     * 增加StoryUser
     * @param storyUser
     */
    @Override
    public void add(StoryUser storyUser){
        storyUserMapper.insert(storyUser);
    }

    /**
     * 根据ID查询StoryUser
     * @param id
     * @return
     */
    @Override
    public StoryUser findById(Integer id){
        return  storyUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询StoryUser全部数据
     * @return
     */
    @Override
    public List<StoryUser> findAll() {
        return storyUserMapper.selectAll();
    }
}
