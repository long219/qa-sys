package com.ps.service;

import com.ps.domain.MessageVO;

/**
 * @author 26498
 */
public interface ActivityService {

    /**
     * 积分兑换
     * @param cId
     * @param userId
     * @return
     */
    MessageVO exchange(Integer cId , Integer userId) throws InterruptedException;

}
