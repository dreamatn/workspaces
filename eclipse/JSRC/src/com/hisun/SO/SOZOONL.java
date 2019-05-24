package com.hisun.SO;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCMMTL;
import com.hisun.BP.BPCMSG_ERROR_MSG;
import com.hisun.BP.BPRAPT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;
import com.hisun.SC.SCRCWAT;
import com.hisun.SC.SCRJRN;
import com.hisun.SC.SCRPARM;

public class SOZOONL {
    boolean pgmRtn = false;
    String PGM_BPZMMTL = "BPZMMTL ";
    Object CWA_PTR;
    int WK_JRN_NO1 = 0;
    int WK_JRN_NO2 = 0;
    short WK_CNT = 0;
    char WK_INIT = ' ';
    char WK_FOUND_APP = ' ';
    int RESP_CODE = 0;
    String WS_MSGID = " ";
    int WK_COMM_LEN = 0;
    SOZOONL_PGM_REST_NAME PGM_REST_NAME = new SOZOONL_PGM_REST_NAME();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCMSG SCCMSG = new SCCMSG();
    SOCMSG SOCMSG = new SOCMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SOCCPSW SOCCPSW = new SOCCPSW();
    SCRCWA SCRCWAT = new SCRCWA();
    SCRCWAT SCRPCWA = new SCRCWAT();
    SCRJRN SCRJRN = new SCRJRN();
    SCRPARM SCRPARM = new SCRPARM();
    BPCMMTL BPCMMTL = new BPCMMTL();
    BPRAPT BPRAPT = new BPRAPT();
    int WK_NEXT_JRN_NO = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    String WK_TELLER_NO = " ";
    String WK_PASSWORD = " ";
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "SOZOONL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SOZOONL_WL3 = SCCGSCA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR;
        IBS.init(SCCGWA, SOCCPSW);
        SOCCPSW.TL_ID = WK_TELLER_NO;
        SOCCPSW.PSW = WK_PASSWORD;
        SOZCPSW SOZCPSW = new SOZCPSW();
        SOZCPSW.MP(SCCGWA, SOCCPSW);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        T000_READ_SCTCWA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCRPCWA.AC_DATE_AREA[1-1].AC_DATE);
        if (SCRPCWA.SYS_STUS == 'A') {
            SCCGWA.COMM_AREA.JRN_IN_USE = SCRPCWA.JRN_IN_USE;
            SCCGWA.COMM_AREA.MST_IN_USE = SCRPCWA.MST_IN_USE;
            T000_GROUP_JOURNAL();
            if (pgmRtn) return;
            WK_NEXT_JRN_NO += 1;
            if (SCRPCWA.JRN_IN_USE == '1') {
                WK_JRN_NO1 = WK_NEXT_JRN_NO;
                if (SCRPCWA.AC_DATE_AREA[1-1].AC_DATE != SCRPCWA.AC_DATE_AREA[2-1].AC_DATE) {
                    SCCGWA.COMM_AREA.JRN_IN_USE = '2';
                    T000_GROUP_JOURNAL();
                    if (pgmRtn) return;
                    WK_NEXT_JRN_NO += 1;
                    WK_JRN_NO2 = WK_NEXT_JRN_NO;
                    SCCGWA.COMM_AREA.JRN_IN_USE = SCRPCWA.JRN_IN_USE;
                } else {
                    WK_JRN_NO2 = 1;
                }
            } else {
                WK_JRN_NO2 = WK_NEXT_JRN_NO;
                if (SCRPCWA.AC_DATE_AREA[1-1].AC_DATE != SCRPCWA.AC_DATE_AREA[2-1].AC_DATE) {
                    SCCGWA.COMM_AREA.JRN_IN_USE = '1';
                    T000_GROUP_JOURNAL();
                    if (pgmRtn) return;
                    WK_NEXT_JRN_NO += 1;
                    WK_JRN_NO1 = WK_NEXT_JRN_NO;
                    SCCGWA.COMM_AREA.JRN_IN_USE = SCRPCWA.JRN_IN_USE;
                } else {
                    WK_JRN_NO1 = 1;
                }
            }
            B001_RECOVER_APT();
            if (pgmRtn) return;
        } else {
            WK_JRN_NO1 = SCRPCWA.NEXT_JRN_NO1;
            WK_JRN_NO2 = SCRPCWA.NEXT_JRN_NO2;
            if (SCRPCWA.SYS_STUS == ' ' 
                || SCRPCWA.SYS_STUS == 0X00) {
                B001_RECOVER_APT();
                if (pgmRtn) return;
            }
            T000_READ_UPD_SCTCWA();
            if (pgmRtn) return;
            SCRPCWA.SYS_STUS = 'A';
            T000_REWRITE_SCTCWA();
            if (pgmRtn) return;
        }
    }
    public void B001_RECOVER_APT() throws IOException,SQLException,Exception {
        for (WK_CNT = 1; WK_CNT <= 99; WK_CNT += 1) {
