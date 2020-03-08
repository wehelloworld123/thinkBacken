package com.myIsoland.service.redis;

import com.myIsoland.common.component.JedisCacheService;
import com.myIsoland.enitity.redis.UserLike;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class JedisServiceImpl implements JedisService {

    @Override
    public void saveLiked2Redis(String likedUserId, String likedPostId) {

    }

    @Override
    public void unlikeFromRedis(String likedUserId, String likedPostId) {

    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {

    }

    @Override
    public void incrementLikedCount(String likedUserId) {

    }

    @Override
    public void decrementLikedCount(String likedUserId) {

    }

    @Override
    public List<UserLike> getLikedDataFromRedis() {
        return null;
    }

/*    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        return null;
    }*/
}
