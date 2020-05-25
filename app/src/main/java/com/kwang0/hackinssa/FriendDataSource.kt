package com.kwang0.hackinssa

import com.kwang0.hackinssa.data.models.Friend
import io.reactivex.Completable
import io.reactivex.Flowable

interface FriendDataSource {
    /**
     * data에 source 에서 user 데이터를 가져옴
     *
     * @return Friend
     */
    fun getFriend(): Flowable<Friend>

    /**
     * data source 에 user 를 집어넣음,
     * 만약 user 가 존재한다면 업데이트 시킴
     *
     * @param friend
     */
    fun insertOrUpdateFriend(friend: Friend): Completable

    /**
     * data source 의 모든 유저를 지움
     */
    fun deleteAllFriends()
}