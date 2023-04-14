package com.xiexinhai8.base_alg.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1125. 最小的必要团队
 *
 * 作为项目经理，你规划了一份需求的技能清单 req_skills，并打算从备选人员名单 people 中选出些人组成一个「必要团队」（ 编号为 i 的备选人员 people[i] 含有一份该备选人员掌握的技能列表）。
 *
 * 所谓「必要团队」，就是在这个团队中，对于所需求的技能列表 req_skills 中列出的每项技能，团队中至少有一名成员已经掌握。可以用每个人的编号来表示团队中的成员：
 *
 * 例如，团队 team = [0, 1, 3] 表示掌握技能分别为 people[0]，people[1]，和 people[3] 的备选人员。
 * 请你返回 任一 规模最小的必要团队，团队成员用人员编号表示。你可以按 任意顺序 返回答案，题目数据保证答案存在。
 *
 * 输入：req_skills = ["java","nodejs","reactjs"], people = [["java"],["nodejs"],["nodejs","reactjs"]]
 * 输出：[0,2]
 *
 * 输入：req_skills = ["algorithms","math","java","reactjs","csharp","aws"], people = [["algorithms","math","java"],
 * ["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * 输出：[1,2]
 * @author xxh <xiexinhai@kuaishou.com>
 * Created on 2023-04-13
 */
public class SmallestSufficientTeam {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Set<Integer>> skillInverted = new HashMap<>();
        for (int i = 0; i < req_skills.length; i++) {
            skillInverted.put(req_skills[i], new HashSet<>());
        }
        for (int i = 0 ; i < people.size(); i++) {
            for (int j = 0; j < people.get(i).size(); j++) {
                Set<Integer> peps = skillInverted.get(people.get(i).get(j));
                if (peps != null) {
                    peps.add(i);
                }
            }
        }

        List<String> reqSkills = Arrays.asList(req_skills);
        List<Integer> needPeps = needPepsChoose(reqSkills, skillInverted, people);
        int[] r = new int[needPeps.size()];
        for (int i = 0; i < needPeps.size(); i++) {
            r[i] = needPeps.get(i);
        }
        return r;
    }

    List<Integer> needPepsChoose(List<String> reqSkills, Map<String, Set<Integer>>skillInverted, List<List<String>> people) {
        if (reqSkills.size() <= 0) {
            return new ArrayList();
        }
        String skill = reqSkills.get(0);
        Set<Integer> peps = skillInverted.get(skill);
        int minPep = Integer.MAX_VALUE;
        int needPep = -1;
        List<Integer> needPeps1 = null;
        for (int index : peps) {
            List<String> pSkills = people.get(index);
            List<String> lackSkills = new ArrayList<>(reqSkills);
            for (String pSkill : pSkills) {
                if (lackSkills.contains(pSkill)) {
                    lackSkills.remove(pSkill);
                }
            }

            List<Integer> needPeps = needPepsChoose(lackSkills, skillInverted, people);
            if (needPeps.size() < minPep) {
                minPep = needPeps.size();
                needPep = index;
                needPeps1 = needPeps;
            }

        }

        needPeps1.add(needPep);
        return needPeps1;

    }
}
