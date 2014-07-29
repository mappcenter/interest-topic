/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package extentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author liempt
 */
public class TwitterPostListEnt {
    public List<TwitterPostEnt> listEnts;
    public long totalRecords;

    public TwitterPostListEnt() {
        listEnts = new ArrayList<TwitterPostEnt>();
        totalRecords = 0;
    }

    public TwitterPostListEnt(List<TwitterPostEnt> ls, long totals) {
        listEnts = ls;
        totalRecords = totals;
    }
}
