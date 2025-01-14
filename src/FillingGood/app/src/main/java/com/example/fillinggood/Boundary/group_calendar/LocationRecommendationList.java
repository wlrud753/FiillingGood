package com.example.fillinggood.Boundary.group_calendar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fillinggood.Boundary.DBboundary.DBmanager;
import com.example.fillinggood.Boundary.home.MainActivity;
import com.example.fillinggood.Control.RecommendationController;
import com.example.fillinggood.Entity.Group;
import com.example.fillinggood.Entity.GroupMember;
import com.example.fillinggood.Entity.GroupSchedule;
import com.example.fillinggood.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//장소 추천 리스트를 보여주는 class입니다(GroupRecommendationForm 하단에 뜨는 fragment)
public class LocationRecommendationList extends Fragment{
    private RadioButton rank1, rank2, rank3, rank4, rank5;
    private Button saveResult;
    RecommendationController recommendationController = new RecommendationController();

    private String groupName;
    private Group thisGroup = DBmanager.getInstance().getGroupInfo(groupName);
    private String rt, name;
    private double[][] tt;
    private int timerank, locrank;
    ArrayList<String> rl = new ArrayList<>();

    public LocationRecommendationList(String groupName, String rt, double[][] tt, int timerank){
        this.groupName = groupName;
        this.rt = rt;
        this.tt = tt;
        this.timerank = timerank;
        this.name = DBmanager.getInstance().FindRecommendingName(groupName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View root = inflater.inflate(R.layout.location_recommendation_list, container, false);

        String[] location = {
                "PA관 1층(파관 라운지 / 파관 투썸 앞)", "PA관 4층 스터디룸",
                "마관 3층 휴게실", "J관 세미나실(J관 1층 스터디룸)",
                "J관 4층 휴게공간(J관 문과대 휴게실 맞은편 휴게 공간)",
                "경카 스터디룸", "만레사 스터디룸(도서관 스터디룸)",
                "도라지(도서관 라운지)", "GA관 스터디룸",
                "AS관 로비(AS관 5층 공대 휴게실)", "빈 강의실",
                "과방", "GN관 계단", "우정관 카페 옆", "커브",
                "굿투데이", "정문 스타벅스", "신촌 카페", "플리즈 커피",
                "스터디 카페", "테이스트", "뗴이야르관 8층 전자과 전용 스터디룸",
                "다산관로비", "마관 5층휴게실", "K관 1층카페", "술탄커피"};

        // 사전 설문조사 통한 그룹별 vector (10 이상 방문 => 10으로 통일)
        // row: 모임, col: 장소 => transpose 필요
        int[][] group_arr = {
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 2, 0, 3, 2, 0, 3, 2, 0, 0, 10, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 5, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 3, 2, 4, 0, 0, 0, 0, 1, 5, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {5, 1, 0, 0, 2, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 4, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0},
                {6, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 3, 0, 1, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 5, 10, 5, 3, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {6, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {6, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 1, 10, 1, 1, 10, 0, 0, 4, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {10, 0, 0, 2, 2, 0, 1, 3, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 3, 2, 0, 0, 0, 0, 5, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0},
                {0, 4, 0, 0, 0, 3, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        // 장소별 vector(우리가 사용할 item-based 협업필터링에 사용)
        // row: 장소, col: 모임
        final int[][] location_arr = new int[group_arr[0].length][group_arr.length];

        // 행렬 transpose
        for (int i = 0; i < group_arr.length; i++) {
            for (int j = 0; j < group_arr[i].length; j++) {
                location_arr[j][i] = group_arr[i][j];
            }
        }

        // <장소, 장소 방문 횟수 vector> HashMap 생성
        final HashMap<String, int[]> recommend_location = new HashMap<>();
        for(int i = 0; i<location.length; i++)
            recommend_location.put(location[i], location_arr[i]);

        // 방문 장소 고빈도 top 11
        String[] top_locations = {"J관 4층 휴게공간(J관 문과대 휴게실 맞은편 휴게 공간)",
                "PA관 1층(파관 라운지 / 파관 투썸 앞)", "J관 세미나실(J관 1층 스터디룸)",
                "커브",	"PA관 4층 스터디룸", "도라지(도서관 라운지)",
                "만레사 스터디룸(도서관 스터디룸)", "빈 강의실", "AS관 로비(AS관 5층 공대 휴게실)",
                "우정관 카페 옆", "정문 스타벅스"};


        //ArrayList<String> list = getRecommended().. 이런식으로 작성 필요
/*
        String[] array = {"1순위장소이름", "2순위장소이름"};
        HashMap<String, String[]> hash = thisGroup.getLocation_priority();
        hash.put("3순위장소이름", array);
        thisGroup.setLocation_priority(hash);
*/
        // 모임 장소 추천을 처음 받는 경우 => 고빈도 장소 5개 추천
        if (true) {
            rl.add(top_locations[0]);
            rl.add(top_locations[1]);
            rl.add(top_locations[2]);
            rl.add(top_locations[3]);
            rl.add(top_locations[4]);
        }

        // 모임 장소 추천을 받은 적이 있고, 그 장소에 대한 피드백 결과가 나쁘지 않았던 경우
        // 그 장소를 포함한 그와 유사한 장소 top5 추천

        // 직전에 모임을 한 장소이면서 피드백이 괜찮았던 장소 불러오기
        String select_location; // DB 맞춰서 수정 필요
        ArrayList<GroupSchedule> groupSchedules = DBmanager.getInstance().getGroupSchedule(groupName);
        Random rand = new Random();
        if(groupSchedules != null){
            int idx = rand.nextInt(groupSchedules.size() - 1);
            select_location = groupSchedules.get(idx).getLocation();
            Log.d("check", "" + select_location);
            if(select_location == null)
                select_location = "PA관 1층(파관 라운지 / 파관 투썸 앞)";
        } else{
            select_location = "PA관 1층(파관 라운지 / 파관 투썸 앞)";
        }

        if (true){
            ArrayList result = recommendationController.top_match(recommend_location, select_location);
            rl = result;
            ArrayList<GroupMember> members = DBmanager.getInstance().getGroupMember(groupName);
            for(int i = 0; i < rl.size(); i++){
                for(int j = 0; j < members.size(); j++){
                    DBmanager.getInstance().saveLocRecommending(groupName, members.get(j).getID(), i+1, rl.get(i).toString());
                }
            }
        }

        rank1 = (RadioButton)root.findViewById(R.id.rank1);
        rank2 = (RadioButton)root.findViewById(R.id.rank2);
        rank3 = (RadioButton)root.findViewById(R.id.rank3);
        rank4 = (RadioButton)root.findViewById(R.id.rank4);
        rank5 = (RadioButton)root.findViewById(R.id.rank5);

        thisGroup = RecommendationController.getGroup(groupName);
        if (!thisGroup.getGroupLeader().getID().equals(MainActivity.User.getID())){
            rank1.setText("1순위 : "+rl.get(0));
            rank2.setText("2순위 : "+rl.get(1));
            rank3.setText("3순위 : "+rl.get(2));
            rank4.setText("4순위 : "+rl.get(3));
            rank5.setText("5순위 : "+rl.get(4));
        }
        else {
            rank1.setText("1순위 : "+rl.get(0));//+"\n(투표한 멤버: "+//해당 투표한 멤버 이름들+")\n");
            rank2.setText("2순위 : "+rl.get(1));//+"\n(투표한 멤버: "+//해당 투표한 멤버 이름들+")\n");
            rank3.setText("3순위 : "+rl.get(2));//+"\n(투표한 멤버: "+//해당 투표한 멤버 이름들+")\n");
            rank4.setText("4순위 : "+rl.get(3));//+"\n(투표한 멤버: "+//해당 투표한 멤버 이름들+")\n");
            rank5.setText("5순위 : "+rl.get(4));//+"\n(투표한 멤버: "+//해당 투표한 멤버 이름들+")\n");
        }



        saveResult = (Button)root.findViewById(R.id.save_result);

        saveResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //결과 저장하는 코드

                if (!rank1.isChecked() && !rank2.isChecked() && !rank3.isChecked() &&!rank4.isChecked() && !rank5.isChecked()){
                    Toast.makeText(getContext(), "선택된 장소가 없습니다", Toast.LENGTH_SHORT).show();
                    return;
                }

                int rank;
                if(rank1.isChecked())
                    rank = 1;
                else if(rank2.isChecked())
                    rank = 2;
                else if(rank3.isChecked())
                    rank = 3;
                else if(rank4.isChecked())
                    rank = 4;
                else if(rank5.isChecked())
                    rank = 5;
                else
                    rank = 1;
                RecommendationController.saveLocChoiceRecommending(groupName, MainActivity.User.getID(), rank);
                locrank = rank;

                // 최종적으로 선택한 시간대 가중치 -0.1
                RecommendationController.Adjustment(thisGroup, rt, tt);
                //thisGroup.setGroupTimeTable(Group.getFeedTimeTable(rt, tt));

                RecommendationController.makeGsch(groupName, rt, timerank, locrank, name, rl.get(rank-1));

                //else, 아래 코드 실행
                getActivity().onBackPressed();
                Toast.makeText(getContext(), "추천 순위가 등록되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}
