package com.example.oneinkedoneproject.service.follow;

import com.example.oneinkedoneproject.domain.Follow;
import com.example.oneinkedoneproject.domain.User;
import com.example.oneinkedoneproject.dto.follow.AddFollowRequestDto;
import com.example.oneinkedoneproject.dto.follow.FollowResponseDto;
import com.example.oneinkedoneproject.repository.follow.FollowRepository;
import com.example.oneinkedoneproject.repository.user.UserRepository;
import com.example.oneinkedoneproject.utils.GenerateIdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public Follow follow(User fromUser, AddFollowRequestDto request){
        User followUser = userRepository.findById(request.getFollowUserId())
                .orElseThrow(IllegalArgumentException::new);

        return followRepository.save(new Follow(GenerateIdUtils.generateFollowId(), followUser, fromUser));
    }

    // 팔로우 생성 기능
    @Transactional(readOnly = true)
    public List<FollowResponseDto> getFollows(User toUser){
        String curUserEmail = toUser.getEmail();
        List<Follow> follows = followRepository.findAllByFromUser_Email(curUserEmail);
        return follows.stream()
                .map(follow -> new FollowResponseDto(follow.getId(), follow.getToUser().getRealname(), follow.getToUser().getIdentity(), follow.getToUser().getImage(), follow.getToUser().getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FollowResponseDto> getFollowsOfUser(String email) {
        return followRepository.findAllByFromUser_Email(email).stream()
                .map(follow -> new FollowResponseDto(follow.getId(), follow.getToUser().getRealname(), follow.getToUser().getIdentity(), follow.getToUser().getImage(), follow.getToUser().getEmail()))
        .toList();
    }

    // 팔로우 목록 조회 기능
    @Transactional(readOnly = true)
    public List<FollowResponseDto> getFollowers(User fromUser){
        String curUserEmail = fromUser.getUsername();
        List<Follow> follows = followRepository.findAllByToUser_Email(curUserEmail); // To
        return follows.stream()
                .map(follow -> new FollowResponseDto(follow.getId() ,follow.getFromUser().getRealname(), follow.getFromUser().getIdentity(), follow.getFromUser().getImage(), follow.getFromUser().getEmail()))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<FollowResponseDto> getFollowersOfUser(String email) {
        return followRepository.findAllByToUser_Email(email).stream()
                .map(follow -> new FollowResponseDto(follow.getId() ,follow.getFromUser().getRealname(), follow.getFromUser().getIdentity(), follow.getFromUser().getImage(), follow.getFromUser().getEmail()))
                .toList();
    }

    @Transactional
    public void unfollow(String followId) {
        followRepository.deleteById(followId);
    }
}
