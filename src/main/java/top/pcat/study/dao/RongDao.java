package top.pcat.study.dao;

import io.rong.methods.group.Group;
import io.rong.methods.user.User;
import io.rong.models.Result;
import io.rong.models.group.GroupMember;
import io.rong.models.group.GroupModel;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.pcat.study.config.RongCloudConfig;

@Slf4j
@Service
public class RongDao {

    @Autowired
    RongCloudConfig rongCloudConfig;

    public String register(String uuid, String name) throws Exception {
        User user = rongCloudConfig.rongCloud().user;
        UserModel userModel = new UserModel()
                .setId(uuid)
                .setName(name)
                .setPortrait("");
        TokenResult result = user.register(userModel);
        return result.getToken();
    }

    public void createGroup(String userId,String groupId,String groupName){
        Group group = rongCloudConfig.rongCloud().group;
        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/group/group.html#create
         *
         * 创建群组方法
         *
         */
        GroupMember[] members = {new GroupMember().setId(userId)};
        GroupModel groupModel = new GroupModel()
                .setId(groupId)
                .setMembers(members)
                .setName(groupName);
        Result groupCreateResult = null;
        try {
            groupCreateResult = (Result)group.create(groupModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert groupCreateResult != null;
        log.info(groupCreateResult.toString());
    }


}