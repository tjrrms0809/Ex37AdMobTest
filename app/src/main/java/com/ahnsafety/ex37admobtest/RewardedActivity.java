package com.ahnsafety.ex37admobtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RewardedActivity extends AppCompatActivity {

    RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewarded);

        rewardedAd= new RewardedAd(this, "ca-app-pub-3940256099942544/5224354917");

        //광로 로드 및 콜백리스너 추가
        rewardedAd.loadAd(new AdRequest.Builder().build(), loadCallback);
    }//onCreate method..

    //보상형광고 로드 콜백리스너 생성
    RewardedAdLoadCallback loadCallback= new RewardedAdLoadCallback(){
        @Override
        public void onRewardedAdLoaded() {
            super.onRewardedAdLoaded();

            //보상형광고객체 보이기
            rewardedAd.show(RewardedActivity.this, rewardedAdCallback);

        }

        @Override
        public void onRewardedAdFailedToLoad(int i) {
            super.onRewardedAdFailedToLoad(i);
            Toast.makeText(RewardedActivity.this, "로드 실패", Toast.LENGTH_SHORT).show();
        }
    };



    //보상형광로 콜백리스너 생성
    RewardedAdCallback rewardedAdCallback= new RewardedAdCallback() {
        @Override
        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
            //사용자가 기존시간 이상 동영상 시청하여
            //보상을 받을 때 자동으로 실행
            //파라미터로 전달된 RewardItem객체가
            // 보상값을 가지고 있음.

            //보상타입
            String type=rewardItem.getType();

            //보상수량
            int num= rewardItem.getAmount();

            Toast.makeText(RewardedActivity.this, type+" : "+ num, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRewardedAdClosed() {
            super.onRewardedAdClosed();
        }
    };


    public void clickBtn(View view) {
        //광로 로드 및 콜백리스너 추가
        rewardedAd.loadAd(new AdRequest.Builder().build(), loadCallback);
    }
}//Activity class..